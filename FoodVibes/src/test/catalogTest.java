package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bin.business;
import bin.catalog;
import bin.user;

class catalogTest {
	private business aBusiness;
	
	@BeforeEach
	void setUpBusiness() {
		aBusiness = new business ("nome","indirizzo","orari","immagine",new user("Sebastiano", "Brischetto", "Italiano", "30/09/95", "seby@gmail.com", "sebrisch", "nonna"));
		catalog aCatalog = catalog.getInstance();
		aCatalog.add(aBusiness);	
	}
	
	@AfterEach
	void removeBusiness() {
		catalog.getInstance().getBusinessList().remove(0);
	}
	
	@Test
	void searchBusinessesByNameTest() {
		catalog aCatalog = catalog.getInstance();
		aCatalog.add(aBusiness);
		assertEquals(aBusiness, aCatalog.getBusinessesByName("nome").get(0));
	}
}
