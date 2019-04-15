package com.frm.stock.stockdata.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import oracle.jdbc.pool.OracleDataSource;

@Configuration
public class DatabaseConfiguration {

	@Autowired
	Environment environment;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.driver-class-name}")
	private String driver;

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }
	@Bean
	DataSource dataSource() throws SQLException {

		
		/*  DriverManagerDataSource driverManagerDataSource = new
		  DriverManagerDataSource();
		  driverManagerDataSource.setUrl(environment.getProperty(url));
		  driverManagerDataSource.setUsername(environment.getProperty(username));
		  driverManagerDataSource.setPassword(environment.getProperty(password));
		  driverManagerDataSource.setDriverClassName(environment.getProperty(driver));
		  return driverManagerDataSource;
		 */

		OracleDataSource dataSource = new OracleDataSource();
		dataSource.setUser(username);
		dataSource.setPassword(password);
		dataSource.setURL(url);
		dataSource.setImplicitCachingEnabled(true);
		//dataSource.setFastConnectionFailoverEnabled(true);
		return dataSource;

	}

}
