package org.jianchunchen.common.mysql.core;

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * @author jianchun.chen
 * @date 2020/11/26 14:13
 * <p>
 * -----
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "mysql.core")
@PropertySource("classpath:mysql-core-jdbc.properties")
@MapperScan(basePackages = "org.jianchunchen.model.mappers",sqlSessionFactoryRef = "mysqlCoreSessionFactory")
public class MysqlCoreConfig {

    private String jdbcUserName;
    private String jdbcUrl;
    private String jdbcPassword;
    private String jdbcDriver;
    private String rootMapper;
    private String aliasesPackage;
    private String txScanPackage;


    /**
     * 设置数据库连接池
     */
    @Bean("mysqlDataSource")
    public DataSource mysqlDataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(this.getJdbcUserName());
        dataSource.setPassword(this.getJdbcPassword());
        dataSource.setJdbcUrl(this.getJdbcUrl());
        dataSource.setDriverClassName(this.getJdbcDriver());
        dataSource.setMaximumPoolSize(55);
        dataSource.setMinimumIdle(5);

        return dataSource;
    }



    @Bean("mysqlCoreSessionFactory")
    public SqlSessionFactoryBean mysqlCoreSessionFactory(@Qualifier("mysqlDataSource")DataSource dataSource) throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();

        factoryBean.setDataSource(dataSource);
        factoryBean.setTypeAliasesPackage(this.getAliasesPackage());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:mappers/**/*.xml");
        factoryBean.setMapperLocations(resources);
        return factoryBean;
    }

}
