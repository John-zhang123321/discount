//package com.jkb.common.dynamicDataSource;
//
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//import org.springframework.stereotype.Repository;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import javax.sql.DataSource;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 动态数据源
// */
//@Repository("dynamicDataSource")
//public class DynamicDataSource extends AbstractRoutingDataSource {
//
//    @Resource(name = "masterDataSource")
//    private DataSource masterDataSource;
//
//    @Resource(name = "ManageSlaveDataSource")
//    private DataSource manageSlaveDataSource;
//
//    @Resource(name = "dataCenterDataSource")
//    private DataSource dataCenterDataSource;
//
//    @PostConstruct
//    public void init() {
//        Map<Object, Object> map = new HashMap<>();
//        map.put(TargetDataType.manageMaster, masterDataSource);
//        map.put(TargetDataType.manageSlave, manageSlaveDataSource);
//        map.put(TargetDataType.manageDataCenter, dataCenterDataSource);
//
//        super.setTargetDataSources(map);
//        super.setDefaultTargetDataSource(masterDataSource);
//    }
//
//    @Override
//    protected Object determineCurrentLookupKey() {
//        /*
//         * DynamicDataSourceContextHolder代码中使用setDataSourceType
//         * 设置当前的数据源，在路由类中使用getDataSourceType进行获取，
//         *  交给AbstractRoutingDataSource进行注入使用。
//         */
//        return DynamicDataSourceContextHolder.getDataSourceType();
//    }
//}
