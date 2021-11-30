package com.ppmg.ei.controller;

import com.founder.ssm.core.exception.SystemException;
import com.founder.ssm.core.vo.BaseResponse;
import com.founder.ssm.web.common.CommonStatus;
import com.founder.uim.LoginResult;
import com.founder.uim.dao.AppUserDao;
import com.founder.uim.manage.ManagerFactory;
import com.founder.uim.manage.log.AccessInfo;
import com.founder.uim.manage.log.AccessManager;
import com.founder.uim.sso.filter.ISSOSession;
import com.founder.uim.sso.filter.SSOFilter;
import com.founder.uim.util.Encrypt;
import com.founder.uim.util.SSOUtil;
import com.founder.uim.xdk.AppUser;
import com.ppmg.common.controller.BaseController;
import com.ppmg.common.utils.RedisUtils;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.*;
@RestController
@Scope("prototype")
@Api(tags = {"登录接口"})
@RequestMapping("/base")
public class LoginController  extends BaseController {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource(name = "redisUtils")
    private RedisUtils redisUtils;

    private static final String SESSION_SECURITY_CODE = "imageCode";

    /**
     * 过期时间（分钟）
     */
    public final Long EXPIRE_TIME = 5L;

    @ApiOperation(value = "登录", notes = "登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "登录名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @PostMapping(value = "/login")
    public BaseResponse login(HttpServletRequest request, String loginName, String password, String code) {
        AppUserDao userDao = new AppUserDao();
        try {

            String host = request.getRemoteAddr();

            HttpSession httpSession = request.getSession();
            password = Encrypt.DataEncrypt(password);
            LoginResult result = userDao.login(loginName, password, host);
            if (!result.reault) {
                if("用户被锁定".equals(result.message)){
                    mapResponse.error("用户被禁用");
                }else{
                    mapResponse.error("用户名或密码不正确");
                }

                return mapResponse;
            }
            logger.info("验证用户名和密码正确");

            AppUser appUser = userDao.getAppUser(Integer.parseInt(String.valueOf(result.userId)));
            Integer userType = (Integer) appUser.getAttrMap().get("USERTYPE");
            if (userType != null && 5 != userType) {
                mapResponse.error("没有登录权限");
                return mapResponse;
            }
            if(StringUtils.isNotEmpty(code)){
                // String newNum = (String) redisUtils.get(SESSION_SECURITY_CODE+loginName);
                String  newNum=  (String)request.getSession().getAttribute(SESSION_SECURITY_CODE);
                if(!code.equalsIgnoreCase(newNum)){
                    mapResponse.error("验证码错误");
                    return mapResponse;
                }
            }

            String userName = appUser.getName();

            // 下面是保存UIM的SESSION，而SSOFilter中的是保存“访问者”的SESSION。
            ISSOSession ssoSession = ManagerFactory.createSSOSession();

            // 这个判断，是因为有时用户在登录后，点回退，进入登录页面，以其他用户登录
            // 这时需要把SSO中的原来的信息清除
            String guid = (String) httpSession.getAttribute(SSOFilter.SESSION_ID);
            if (guid != null) {
                ISSOSession ssoSessionTmp = ssoSession.getSSOSession(guid);
                if (ssoSessionTmp != null) {
                    ssoSessionTmp.remove();
                }
            }

            //强制退出已登录账号
            ISSOSession loginSsoSession = SSOUtil.getSSOSessionByUserId(appUser.getId() + "");
            if (guid == null && loginSsoSession != null) {
                loginSsoSession.remove();
            }
            // 把登录名存入SESSION
            ssoSession.setLoginName(loginName);
            // 把用户的ID存入SESSION
            ssoSession.setUserId(result.userId);
            ssoSession.setName(userName);
            ssoSession.setGuid(UUID.randomUUID().toString());
            ssoSession.getExtInfo().put("HOST", host);
            ssoSession.getExtInfo().put("userType", userType + "");

            httpSession.setAttribute(ssoSession.getGuid(), ssoSession);
            httpSession.setAttribute(SSOFilter.SESSION_ID, ssoSession.getGuid());
            httpSession.setAttribute(SSOFilter.UIM_BOUND, SSOFilter.UIM_BOUND);

            logger.info("验证用户名和密码正确，向MEMCACHED中写用户信息开始");
            ssoSession.insert();
            mapResponse.put("loginUser", ssoSession);

        } catch (SystemException e) {
            logger.error(e.getMessage());
            mapResponse.error(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage());
            mapResponse.error(e.getMessage());
            logger.error(e.getMessage(), e);
        } finally {
            userDao.close();
        }
        return mapResponse;

    }


    @ApiOperation(value = "登出", notes = "登出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "登录名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String"),
    })
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/logout")
    public BaseResponse logout(HttpServletRequest request) {
        try {

            HttpSession httpSession = request.getSession();
            String guid = (String) httpSession.getAttribute(SSOFilter.SESSION_ID);
            ISSOSession ssoSession = (ISSOSession) httpSession.getAttribute(guid);


            if (ssoSession != null) {
                Hashtable onlineUsers = LoginResult.getOnlineUsers();
                String userID = ssoSession.getLoginName();
                AccessInfo info = (AccessInfo) onlineUsers.get(userID);
                if (info != null) {
                    AccessManager.logout(info.getID());
                    onlineUsers.remove(userID);
                }
                ssoSession.remove();
            }

            // 清除SESSION中的内容
            List<String> attrNames = new ArrayList<String>();
            java.util.Enumeration enums = httpSession.getAttributeNames();
            while (enums.hasMoreElements()) {
                Object obj = enums.nextElement();
                if (obj instanceof String) {
                    attrNames.add((String) obj);
                }
            }
            for (int i = 0; i < attrNames.size(); i++) {
                httpSession.removeAttribute(attrNames.get(i));
            }

            httpSession.invalidate();

        } catch (SystemException e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
            logger.error(e.getMessage(), e);
        } catch (Exception e) {
            logger.error(e.getMessage());
            baseResponse.error(e.getMessage());
            logger.error(e.getMessage(), e);
        }
        return baseResponse;
    }

    @ApiOperation(value = "获取登录动态码", notes = "获取登录动态码")
    @ApiResponses({
            @ApiResponse(code = CommonStatus.OK, message = "操作成功"),
            @ApiResponse(code = CommonStatus.EXCEPTION, message = "服务器内部异常"),
    })
    @GetMapping(value = "/generate")
    public void generate(HttpServletRequest req, HttpServletResponse response, String loginName){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String code = drawImg(output);
        //先清除redis 缓存
        redisUtils.remove(SESSION_SECURITY_CODE+loginName);

        // 将四位数字的验证码保存到Session中。
        HttpSession session = req.getSession();
        session.removeAttribute(SESSION_SECURITY_CODE);
        session.setAttribute(SESSION_SECURITY_CODE, code);
        //redisUtils.set(SESSION_SECURITY_CODE+loginName, code, EXPIRE_TIME, TimeUnit.MINUTES);
        try {
            // 将图像输出到Servlet输出流中
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private  String drawImg(ByteArrayOutputStream output){
        String code = "";
        for(int i=0; i<4; i++){
            code += randomChar();
        }
        int width = 70;
        int height = 25;
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Font font = new Font("Times New Roman",Font.PLAIN,20);
        Graphics2D g = bi.createGraphics();
        g.setFont(font);
        Color color = new Color(66,2,82);
        g.setColor(color);
        g.setBackground(new Color(226,226,240));
        g.clearRect(0, 0, width, height);
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code, (int)x, (int)baseY);
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    private char randomChar(){
        Random r = new Random();
        String str = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        return str.charAt(r.nextInt(str.length()));
    }

}
