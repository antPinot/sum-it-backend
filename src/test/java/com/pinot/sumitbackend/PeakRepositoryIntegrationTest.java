/**
 * 
 */
package com.pinot.sumitbackend;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.testcontainers.containers.MongoDBContainer;

import com.pinot.sumitbackend.repositories.PeakRepository;

/**
 * 
 */
@DataMongoTest
public class PeakRepositoryIntegrationTest {
	
	
	@Autowired
	private PeakRepository peakRepository;
	
	@BeforeEach
	final void SetUp() {
		final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.0.10");
		mongoDBContainer.start();
	}
	
}
