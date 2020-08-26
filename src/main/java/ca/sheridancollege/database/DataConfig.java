package ca.sheridancollege.database;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataConfig {

	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource datasource)
	{
		return new NamedParameterJdbcTemplate(datasource);
	}
	
	
	
	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource  datasource = new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost/bookstores");
		datasource.setUsername("root");
		datasource.setPassword("root");
		
		return datasource;
		
		
	}
}
