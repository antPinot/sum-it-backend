package com.pinot.sumitbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication
@EnableMongoRepositories
public class SumItBackendApplication implements CommandLineRunner{
	
	private static final Logger logger = LoggerFactory.getLogger(SumItBackendApplication.class);
	
	public static void main(String[] args) {
		
		//Load environment variables before running application
		EnvLoader.loadEnv();
		
		SpringApplication.run(SumItBackendApplication.class, args);
		
	}
	
	@Override
	public void run(String...args) throws Exception{
		
//		serpService.DuckDuckGoRequest("Pointe du Gros Caval");
		
//		RestTemplate restTemplate = new RestTemplate();
//		System.out.println(restTemplate.getForObject("https://fr.wikipedia.org/api/rest_v1/page/summary/Aiguille_Verte", String.class));
		
		
	}

}
