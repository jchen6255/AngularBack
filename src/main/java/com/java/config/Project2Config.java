package com.java.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.java.dto.Like;
import com.java.dto.Post;
import com.java.dto.User;

@ComponentScan("com.java")
@org.springframework.context.annotation.Configuration
@EnableWebMvc
public class Project2Config {
	@Value("${url}")
	String url;
	@Value("${password}")
	String password;
	@Value("${username}")
	String username;
	@Value("${driverClassName}")
	String driverClassName;
	@Value("${accessKey}")
	String accessKey;
	@Value("${secretKey}")
	String secretKey;

	@Bean
	@RequestScope
	public Session getSession() {
		return sessionFactory().openSession();
	}

	@ApplicationScope
	@Bean
	public SessionFactory sessionFactory() {
		Configuration cfg = new Configuration();
		// create the schema
		//cfg.setProperty(Environment.HBM2DDL_AUTO, "create");
		cfg.addAnnotatedClass(User.class);
		cfg.addAnnotatedClass(Post.class);
		cfg.addAnnotatedClass(Like.class);
		// To which db, it should generate sql queries
		cfg.setProperty(Environment.DIALECT, "org.hibernate.dialect.OracleDialect");
		cfg.setProperty("hibernate.connection.username", username);
		cfg.setProperty("hibernate.connection.password", password);
		cfg.setProperty("hibernate.connection.driver_class", driverClassName);
		cfg.setProperty("hibernate.connection.url", url);
		cfg.setProperty(Environment.SHOW_SQL, "true");
		StandardServiceRegistryBuilder rb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
		SessionFactory sf = cfg.buildSessionFactory(rb.build());
		return sf;
	}

	@Bean
	public static PropertyPlaceholderConfigurer ppc() {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		ppc.setLocation(new ClassPathResource("database.properties"));
		return ppc;
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver vr = new InternalResourceViewResolver();
		vr.setPrefix("/WEB-INF/");
		vr.setSuffix(".html");
		return vr;
	}
	
	@Bean
	public AmazonS3 s3Client() {
		AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
		
		return AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials))
				.withRegion(Regions.US_EAST_2).build();
	}
	
	@Bean
    public CommonsMultipartResolver multipartResolver(){

        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        return resolver;
    }
}
