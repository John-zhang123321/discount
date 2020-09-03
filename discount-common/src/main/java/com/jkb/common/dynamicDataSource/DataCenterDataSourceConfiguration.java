//package com.jkb.common.dynamicDataSource;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = "com.jkb.*.mapper.datacenter", sqlSessionTemplateRef  = "dataCenterSqlSessionTemplate")
//public class DataCenterDataSourceConfiguration {
//    @Value("${spring.datasource.driver-class-name}")
//    private String driverClassName;
//
//    @Value("${spring.datasource.dataCenter.url}")
//    private String url;
//
//    @Value("${spring.datasource.dataCenter.username}")
//    private String username;
//
//    @Value("${spring.datasource.dataCenter.password}")
//    private String password;
//
//
//    @Bean(name = "dataCenterDataSource")
//    public DataSource dataSource() {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setDriverClassName(this.driverClassName);
//        dataSource.setUrl(this.url);
//        dataSource.setUsername(this.username);
//        dataSource.setPassword(this.password);
//        return dataSource;
//    }
//
//    @Bean(name = "dataCenterSqlSessionFactory")
//    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataCenterDataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:META-INF/mybatis/**/mapper/*Mapper*.xml"));
//        return bean.getObject();
//    }
//
//    @Bean(name = "dataCenterTransactionManager")
//    public DataSourceTransactionManager transactionManager(@Qualifier("dataCenterDataSource") DataSource dataSource) {
//        return new DataSourceTransactionManager(dataSource);
//    }
//
//    @Bean(name = "dataCenterSqlSessionTemplate")
//    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("dataCenterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}
