/**
 * 
 */
package com.pinot.sumitbackend;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.pinot.sumitbackend.document.Peak;
import com.pinot.sumitbackend.repositories.PeakRepository;

/**
 * 
 */
@Testcontainers
@DataMongoTest
public class PeakRepositoryIntegrationTest {
	
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0").withExposedPorts(27017);
	
	@DynamicPropertySource
	static void containerProperties (DynamicPropertyRegistry registry) {
		 registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}
	
	@AfterAll
	static void tearDown() {
	  mongoDBContainer.stop();
	}
	
	@Autowired
	private PeakRepository peakRepository;
	
	@Test
	public void givenPeakRepository_whenFindByElevation_thenOk() {
		
		Peak peak = new Peak(2581, "test1", "testUri", null, null, null);
		Peak savedPeak = peakRepository.save(peak);
		Peak retrievedPeak = peakRepository.findByElevation(savedPeak.getElevation());
		
		assertThat(retrievedPeak.getId()).isEqualTo(peak.getId());
		
		
	}
	
}
