package com.farouk.bengharssallah.spring.jwt.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.farouk.bengharssallah.spring.jwt.domain.Future;
import com.farouk.bengharssallah.spring.jwt.domain.User;
import com.farouk.bengharssallah.spring.jwt.dto.JSONFuture;
import com.farouk.bengharssallah.spring.jwt.repository.FutureRepository;
import com.farouk.bengharssallah.spring.jwt.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class RestAPIController {


    private EmbeddedDatabase dataSource;
	
	
	
    private FutureRepository futureRepository;
	
	
    private UserRepository userRepository;
	
	
	private PasswordEncoder bcryptEncoder;
	
	
	public RestAPIController(FutureRepository futureRepository, EmbeddedDatabase dataSource, 
			UserRepository userRepository, PasswordEncoder bcryptEncoder){
		             this.futureRepository = futureRepository;
		             this.dataSource = dataSource;
		             this.userRepository = userRepository;
		             this.bcryptEncoder = bcryptEncoder;
		             reset();
	}
	
	
	@GetMapping({ "/futures" })
	public ResponseEntity<List<Future>> futures() {
		return new ResponseEntity<List<Future>>(futureRepository.findAll(), HttpStatus.OK);
	}
	
	
	
	@GetMapping({ "/futures/{ticker}" })
	public ResponseEntity<Future> firstPage(@PathVariable String ticker) {
		return new ResponseEntity<Future>(futureRepository.findByTicker(ticker), HttpStatus.OK);
	}
	
	
	
	
	 private void reset()  {
	        try {
	            this.clearDatabase();
	       
	      final User user = new User();user.setUsername("admin");user.setPassword(bcryptEncoder.encode("admin"));
	      userRepository.save(user);
	      ObjectMapper objectMapper = new ObjectMapper();
	      List<JSONFuture> futures = objectMapper.readValue(new ClassPathResource("files/futures.json").getFile(), 
	    		  new TypeReference<List<JSONFuture>>(){});
			for(JSONFuture jfuture : futures) {
				 Future future = new Future();
				 future.setTicker(jfuture.getTicker());
				 future.setName(jfuture.getName());
				 future.setExchange(jfuture.getExchange());
				 future.setVolume(new Double((Math.random()*1000) + Math.random()*345).longValue());
				 futureRepository.save(future);
			           }
			
			userRepository.findAll().forEach(u -> System.out.println(u.getUsername() + ":" + u.getPassword()));
	                          }
			 catch (Exception e) {
		            e.printStackTrace();
		        }
	    }
	
	
	
	
	
	 private void clearDatabase() throws SQLException {
	        final Connection c = this.dataSource.getConnection();
	        final Statement s = c.createStatement();
	        s.execute("SET REFERENTIAL_INTEGRITY FALSE");
	        final Set<String> tables = new HashSet<String>();
	        ResultSet rs = s.executeQuery("SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='PUBLIC'");
	        while (rs.next()) {
	            final String rss = rs.getString(1);
	                tables.add(rss);
	        }
	        rs.close();
	        for (final String table : tables) {
	            s.executeUpdate("TRUNCATE TABLE " + table);
	        }
	        final Set<String> sequences = new HashSet<String>();
	        rs = s.executeQuery("SELECT SEQUENCE_NAME FROM INFORMATION_SCHEMA.SEQUENCES WHERE SEQUENCE_SCHEMA='PUBLIC'");
	        while (rs.next()) {
	            sequences.add(rs.getString(1));
	        }
	        rs.close();
	        for (final String seq : sequences) {
	            s.executeUpdate("ALTER SEQUENCE " + seq + " RESTART WITH 1");
	        }
	        s.execute("SET REFERENTIAL_INTEGRITY TRUE");
	        s.close();
	    }

}