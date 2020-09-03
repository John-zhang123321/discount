//package com.jkb.common.dynamicDataSource;
//
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * 动态数据源上下文
// */
//public class DynamicDataSourceContextHolder {
//
//    private static final ThreadLocal<TargetDataType> contextHolder = new ThreadLocal<>();
//
//    /*
//     * 管理所有的数据源id;
//     * 主要是为了判断数据源是否存在;
//     */
//    public static List<TargetDataType> dataSourceIds = new ArrayList(Arrays.asList(TargetDataType.manageMaster, TargetDataType.manageSlave, TargetDataType.manageDataCenter));
//
//    /**
//     * 使用setDataSourceType设置当前的
//     *
//     * @param dataSourceType
//     */
//    public static void setDataSourceType(TargetDataType dataSourceType) {
//        contextHolder.set(dataSourceType);
//    }
//
//    public static TargetDataType getDataSourceType() {
//        return contextHolder.get();
//    }
//
//    public static void clearDataSourceType() {
//        contextHolder.remove();
//    }
//
//    /**
//     * 判断指定DataSrouce当前是否存在
//     */
//    public static boolean containsDataSource(TargetDataType dataSourceId) {
//        return dataSourceIds.contains(dataSourceId);
//
//    }
//}
