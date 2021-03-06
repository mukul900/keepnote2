package com.stackroute.keepnote.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/*This class will contain the application-context for the application. 
 * Define the following annotations:
 * @Configuration - Annotating a class with the @Configuration indicates that the 
 *                  class can be used by the Spring IoC container as a source of 
 *                  bean definitions
 * @EnableTransactionManagement - Enables Spring's annotation-driven transaction management capability.
 *                  
 * */
@Configuration
@EnableTransactionManagement

public class ApplicationContextConfig {

	/*
	 * Define the bean for DataSource. In our application, we are using MySQL as the
	 * dataSource. To create the DataSource bean, we need to know: 1. Driver class
	 * name 2. Database URL 3. UserName 4. Password
	 */

	
	@Bean
	@Autowired
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		dataSource.setUrl("jdbc:mysql://localhost:3306/keepnote?createDatabaseIfNotExist=true&"
//				+ "verifyServerCertificate=false&useSSL=false&requireSSL=false");
//		dataSource.setUsername("root");
//		dataSource.setPassword("root");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://" + System.getenv("MYSQL_HOST") + ":3306/" + System.getenv("MYSQL_DATABASE")
				+"?verifyServerCertificate=false&useSSL=false&requireSSL=false");
		dataSource.setUsername(System.getenv("MYSQL_USER"));
		dataSource.setPassword(System.getenv("MYSQL_PASSWORD"));
		return dataSource;
	}

	
	
/*

        
        Use this configuration while submitting solution in hobbes.
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://" + System.getenv("MYSQL_HOST") + ":3306/" + System.getenv("MYSQL_DATABASE")
				+"?verifyServerCertificate=false&useSSL=false&requireSSL=false");
		dataSource.setUsername(System.getenv("MYSQL_USER"));
		dataSource.setPassword(System.getenv("MYSQL_PASSWORD")); */

	/*
	 * Define the bean for SessionFactory. Hibernate SessionFactory is the factory
	 * class through which we get sessions and perform database operations.
	 */
	@Bean
	@Autowired
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder sfBuilder = new LocalSessionFactoryBuilder(dataSource);
		sfBuilder.scanPackages("com.stackroute.keepnote.model");
		sfBuilder.addProperties(getHibernateProperties());
		return sfBuilder.buildSessionFactory();
	}
	private Properties getHibernateProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.dialect","org.hibernate.dialect.MySQL5InnoDBDialect");
		prop.put("hibernate.show_sql","true");
		prop.put("hibernate.format_sql","true");
		prop.put("hibernate.hbm2ddl.auto","create");
		return prop;
	}
	

	/*
	 * Define the bean for Transaction Manager. HibernateTransactionManager handles
	 * transaction in Spring. The application that uses single hibernate session
	 * factory for database transaction has good choice to use
	 * HibernateTransactionManager. HibernateTransactionManager can work with plain
	 * JDBC too. HibernateTransactionManager allows bulk update and bulk insert and
	 * ensures data integrity.
	 */
	@Bean
	@Autowired
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager htm = new HibernateTransactionManager(sessionFactory);
		return htm;
	}
}
