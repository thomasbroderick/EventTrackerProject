package com.skilldistillery.bjjtracker.entities.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.skilldistillery.bjjtracker.entities.Round;

class RoundTests {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Round r;
	
	
	@BeforeAll
	public static void setUpAll() throws Exception {
		emf = Persistence.createEntityManagerFactory("BJJTracker");

	}

	@BeforeEach
	public void setUp() throws Exception {
		em = emf.createEntityManager();
		r = em.find(Round.class, 1);
	}

	@AfterEach
	public void tearDown() throws Exception {
		em.close();
		r = null;
	}

	@AfterAll
	public static void tearDownAll() throws Exception {
		emf.close();

	}
	
	@Test
	void test_round_mapping() {
		assertEquals("Daniel Calvert", r.getOpponentName());
		assertEquals("Purple", r.getOpponentRank());
	}
	
	

}
