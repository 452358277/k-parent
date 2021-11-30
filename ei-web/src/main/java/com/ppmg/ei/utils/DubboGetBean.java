package com.ppmg.ei.utils;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.ppmg.ei.model.ConditionInfoModel;
import com.ppmg.ei.model.ConditionModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DubboGetBean {
    /**
     *
     * @param ServiceName 调用的Service名字
	 * @param methodName 要调用Service里面的方法名字
	 * @param key  所要传的参数类型
	 * @param value  所要传的参数值
     * @return java.util.List<E>
     * @author zhaokuiyu
     * @date 2019/11/30 11:33
     * @creed: The best time to plant a tree is ten years ago, followed by now
     */
    public static Map<Object,Object> getBean(String ServiceName, String methodName, String key, Object value) {

        // 普通编码配置方式
        ApplicationConfig application = new ApplicationConfig();
        application.setName("dubbo-consumer-export");

        // 连接注册中心配置
        RegistryConfig registry = new RegistryConfig();
        registry.setAddress("zookeeper://172.29.201.231:2181");

        ReferenceConfig<GenericService> reference = new ReferenceConfig<GenericService>();
        reference.setApplication(application);
        //zookeeper所在服务器地址
        reference.setRegistry(registry);
        //调用的接口全路径
        reference.setInterface("com.ppmg.ei.service."+ServiceName);
        // 声明为泛化接口
        reference.setGeneric(true);

        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(reference);

        //调用接口内的方法
        Object result = genericService.$invoke(methodName,new String[] { key },new Object[] { value});
        System.out.println("数据源结果1---------------result---------------->>>>"+result);
        Map<Object,Object> ob = (Map<Object, Object>) result;
        //List<E> list = (List<E>) ob.get("list");
        System.out.println("数据源结果2----------------map---------------->>>>"+ob);
        //PageInfo<E> page = new PageInfo(list);
        return ob;
    }

    /**
     * main方法测试
     * @param args
     */
    public static void main(String[] args) {
        ConditionModel conditionModel = new ConditionModel();
        conditionModel.setConditionId("168ce826-f396-405e-a90e-6a9d5db0e323");
        conditionModel.setFundName("广东省粤科母基金投资管理有限公司");
        conditionModel.setFundSize(10000.0);

        ConditionInfoModel conditionInfoModel = new ConditionInfoModel();
        conditionInfoModel.setInfoId("7cf0e558-a48c-424e-a783-144e709d45ae");
        conditionInfoModel.setCooSize(123456789.0);

        //DubboGetBean.getBean("ConditionService","updateConditionInfos","com.ppmg.ei.model.ConditionInfoModel",conditionInfoModel);
        DubboGetBean.getBean("ConditionService","update","com.ppmg.ei.model.ConditionModel",conditionModel);
        /*searchCondition.setPageSize(5);
        searchCondition.setCurrPage(1);
        Object c =  DubboGetBean.getBean("ConditionService","selectListPage","com.founder.ssm.core.common.SearchCondition",searchCondition);
        Map<Object,Object> ob = (Map<Object, Object>) c;
        List<ConditionModel> cc = (List<ConditionModel>) ob.get("list");
        System.out.println(c);*/

        //Map<Object,Object> c =  DubboGetBean.getBean("ConditionService","selectById","java.lang.String","11f90f49-48d4-4a50-a3ba-0270a5ab0b85");
        //System.out.println(c.get("conditionId"));
    }
}
