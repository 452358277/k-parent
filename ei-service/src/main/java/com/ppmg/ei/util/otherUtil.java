package com.ppmg.ei.util;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ppmg.ei.service.CommTCodeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Component("otherUtil")
public class otherUtil {

    @Reference(check = false)
    private static CommTCodeService commTCodeService;

    /**
     * 根据seq的ID生成下一个序列
     * @param sequenceName
     * @return
     * @throws Exception
     */
    public static String getSeqNextValString(String sequenceName) throws Exception{
        String nextval="-1";
        //Map<String,Object> pramsMap = new HashMap<String,Object>();
        //pramsMap.put("sequencename","sequenceName");
        nextval = String.valueOf(commTCodeService.selectOne("getSeqNextVal",sequenceName));
        //commTCodeService.selectSql2Map()
        return nextval;
    }


    /**
     * 获取组织机构的groupId,不是主键
     * @return
     * @throws SQLException
     */
    public static long getNextGroupId() throws SQLException {
        long seqNo = 0;
        seqNo = Long.parseLong(String.valueOf(commTCodeService.selectOne("getNextGroupId","")));
        commTCodeService.updateSql("update BASE.UIM_SERIALNO set SEQ_NO=SEQ_NO+1 where ID=5");
        return seqNo;
    }
}
