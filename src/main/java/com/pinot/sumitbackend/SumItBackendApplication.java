package com.pinot.sumitbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

import com.pinot.sumitbackend.document.Peak;
import com.pinot.sumitbackend.repositories.PeakRepository;
import com.pinot.sumitbackend.services.SERPService;

@SpringBootApplication
@EnableMongoRepositories
public class SumItBackendApplication implements CommandLineRunner{
	
	@Autowired
	private PeakRepository peakRepository;
	
	@Autowired
	private SERPService serpService;
	
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
