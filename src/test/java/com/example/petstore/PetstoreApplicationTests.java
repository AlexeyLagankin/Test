package com.example.petstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class PetstoreApplicationTests {

	final PetstoreApplication application;

	@Autowired
	public PetstoreApplicationTests(PetstoreApplication application) {
		this.application = application;
	}

	@Test
	void contextLoads() {
		assertNotNull(application);
	}

}
