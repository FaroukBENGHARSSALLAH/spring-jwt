package com.farouk.bengharssallah.spring.jwt.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DatabaseConfiguration {

	 @Bean
	    public EmbeddedDatabase dataSource() {
	        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
	        final EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).build();
	        return db;
	    }
}
