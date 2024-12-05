package com.pinot.sumitbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.pinot.sumitbackend.document.Peak;
import com.pinot.sumitbackend.repositories.PeakRepository;

@SpringBootApplication
@EnableMongoRepositories
public class SumItBackendApplication implements CommandLineRunner{
	
	@Autowired
	private PeakRepository peakRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SumItBackendApplication.class, args);
		
	}
	
	@Override
	public void run(String...args) throws Exception{
		System.out.println(peakRepository.findByName("Aiguille de Chambeyron").getName());
	}

}
