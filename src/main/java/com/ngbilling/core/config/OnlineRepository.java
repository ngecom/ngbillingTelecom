package com.ngbilling.core.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableJpaRepositories(basePackages = "com.ngbilling.core", entityManagerFactoryRef = "onlineEntityManagerFactory", transactionManagerRef = "onlineTransactionManager")
public class OnlineRepository{

	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.online.datasource")
	public DataSourceProperties onlineDataSourceProperties() {
		return new DataSourceProperties();
	}
	
	@Bean(name = "onlineDataSource")
	@Primary
	@ConfigurationProperties("spring.online.datasource.hikari")
	public HikariDataSource  onlineDataSource() {
		 return onlineDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}
	
	@Bean("onlineJdbcTemplate")
	@Primary
	public JdbcTemplate onlineJdbcTemplate(@Qualifier("onlineDataSource") HikariDataSource onlineDataSource) {
		return new JdbcTemplate(onlineDataSource);
	}

	@Bean(name = "onlineEntityManagerFactory")
	@Primary
	public LocalContainerEntityManagerFactoryBean onlineEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(onlineDataSource()).persistenceUnit(CommonConstants.JPA_UNIT_NAME_1)
				.packages("com.ngbilling.core.server.persistence.dto").build();
	}

	@Bean(name = "onlineTransactionManager")
	@Primary
	public PlatformTransactionManager onlineTransactionManager(
			@Qualifier("onlineEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
		return new JpaTransactionManager(entityManagerFactoryBean.getObject());
	}
	
}
