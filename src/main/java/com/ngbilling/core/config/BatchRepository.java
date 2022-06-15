package com.ngbilling.core.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ngbilling.core.common.util.CommonConstants;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.ngbilling.core.server.batch", entityManagerFactoryRef = "batchEntityManagerFactory", transactionManagerRef = "batchTransactionManager")
public class BatchRepository {

    @Bean
    @ConfigurationProperties(prefix = "spring.batch.datasource")
    public DataSourceProperties batchDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "batchDataSource")
    @ConfigurationProperties("spring.batch.datasource.hikari")
    public HikariDataSource batchDataSource() {
        return batchDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean("batchdbcTemplate")
    public JdbcTemplate batchJdbcTemplate(@Qualifier("batchDataSource") HikariDataSource batchDataSource) {
        return new JdbcTemplate(batchDataSource);
    }

    @Bean(name = "batchEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean batchEntityManagerFactory(EntityManagerFactoryBuilder builder) {
        return builder.dataSource(batchDataSource()).persistenceUnit(CommonConstants.JPA_UNIT_NAME_2)
                .packages("com.ngbilling.core.server.persistence.dto").build();
    }

    @Bean(name = "batchTransactionManager")
    public PlatformTransactionManager batchTransactionManager(
            @Qualifier("batchEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }

}
