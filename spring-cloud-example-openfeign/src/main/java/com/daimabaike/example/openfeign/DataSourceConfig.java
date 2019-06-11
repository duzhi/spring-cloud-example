package com.daimabaike.example.openfeign;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@Configuration
public class DataSourceConfig {

	@Bean("ds1")
	public DataSource dataSource() {
		return null;
	}

	// SimpleJdbcTemplate 过时，JdbcTemplate 和 NamedParameterJdbcTemplate 提供所有功能。

	@Bean("phoenixJdbcTemplate")
	public JdbcTemplate phoenixJdbcTemplate(@Qualifier("ds1") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean("NamedParameterJdbcTemplate")
	public NamedParameterJdbcTemplate X(@Qualifier("phoenixJdbcTemplate") JdbcTemplate jdbcTemplate) {
		return new NamedParameterJdbcTemplate(jdbcTemplate);
	}
	
}
