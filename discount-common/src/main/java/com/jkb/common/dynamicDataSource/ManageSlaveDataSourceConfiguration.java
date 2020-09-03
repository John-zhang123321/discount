//package com.jkb.common.dynamicDataSource;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class ManageSlaveDataSourceConfiguration {
//
//    @Value("${spring.datasource.driver-class-name}")
//    private String driverClassName;
//
//    @Value("${spring.datasource.slave.url}")
//    private String url;
//
//    @Value("${spring.datasource.slave.username}")
//    private String username;
//
//    @Value("${spring.datasource.slave.password}")
//    private String password;
//
//    @Bean(name = "ManageSlaveDataSource")
//    public DataSource dataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(this.driverClassName);
//        dataSource.setUrl(this.url);
//        dataSource.setUsername(this.username);
//        dataSource.setPassword(this.password);
//        return dataSource;
//    }
//
////    @Bean(name = "ManageSlaveSqlSessionFactory")
////    public SqlSessionFactory sqlSessionFactory(@Qualifier("ManageSlaveDataSource") DataSource dataSource) throws Exception {
////        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
////        bean.setDataSource(dataSource);
////        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:META-INF/mybatis/**/mapper/*Mapper*.xml"));
////        return bean.getObject();
////    }
////
////    @Bean(name = "ManageSlaveTransactionManager")
////    public DataSourceTransactionManager transactionManager(@Qualifier("ManageSlaveDataSource") DataSource dataSource) {
////        return new DataSourceTransactionManager(dataSource);
////    }
////
////    @Bean(name = "ManageSlaveSqlSessionTemplate")
////    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("ManageSlaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
////        return new SqlSessionTemplate(sqlSessionFactory);
////    }
//}
