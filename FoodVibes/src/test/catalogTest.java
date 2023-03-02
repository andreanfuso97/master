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
		Calendar dateInfo = Calendar.getInstance();
		dateInfo.set(Calendar.YEAR, 1997);
		dateInfo.set(Calendar.MONTH, Calendar.JANUARY);
		dateInfo.set(Calendar.DAY_OF_MONTH, 10);
		aBusiness = new business ("nome","indirizzo","orari","immagine",new user("Sebastiano", "Brischetto", "Italiano", dateInfo.getTime(), "seby@gmail.com", "sebrisch", "nonna"));
		catalog aCatalog = catalog.getInstance();
		aCatalog.add(aBusiness);	
	}
	
	@AfterEach
	void removeBusiness() {
		catalog.getInstance().getBusinessList().remove(0);
		
	}
	
	@Test
	void getTest() {
		catalog aCatalog = catalog.getInstance();
		assertEquals(aBusiness, aCatalog.getBusinessList().get(0));
		}
	
	@Test
	void searchBusinessesByNameTest() {
		catalog aCatalog = catalog.getInstance();
		aCatalog.add(aBusiness);
		assertEquals("nome", aCatalog.getBusinessesByName("nome").get(0).getName());
	}
}
