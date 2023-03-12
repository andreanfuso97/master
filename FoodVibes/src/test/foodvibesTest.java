package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bin.GUI_Frame;
import bin.business;
import bin.catalog;
import bin.foodvibes;
import bin.review;
import bin.user;

class foodvibesTest {
	private user aUser = new user("Andrea", "Anfuso", "Italiano", "02/02/2000", "aanfuso97@gmail.com", "qweasc", "nonna");
	@BeforeEach
	void setup() { 
		foodvibes.init();
	}
	
	@AfterEach
	void tearDown() {
		catalog.getInstance().getBusinessList().remove(0);
	}
	
	@Test
	void insertBusinessInfoTest() { //commentare la linea 108 di foodvibes per disabilitare l'errore relativo alla gui
		assertFalse(foodvibes.insertBusinessInfo("", "indirizzo", "openingHours", "immagine"));
		assertFalse(foodvibes.insertBusinessInfo("nomeinsert", "", "openingHours", "immagine"));
		assertFalse(foodvibes.insertBusinessInfo("nomeinsert", "indirizzo", "", "immagine"));
		assertFalse(foodvibes.insertBusinessInfo("nomeinsert", "indirizzo", "openingHours", ""));
		assertTrue(foodvibes.insertBusinessInfo("nomeinsert", "indirizzo", "openingHours", "immagine"));
		assertFalse(foodvibes.insertBusinessInfo("nomeinsert", "indirizzo", "openingHours", "immagine"));
	}
	
	@Test
	void showSearchResultTest() { //commentare la linea 132
		foodvibes.insertBusinessInfo("nome", "indirizzo", "openingHours", "immagine");
		assertFalse(foodvibes.showSearchResult(""));
		assertFalse(foodvibes.showSearchResult("aaaaa"));
		assertTrue(foodvibes.showSearchResult("nome"));		
	} 
	@Test
	void upVoteReviewTest() { //commentare 168 e 198
		foodvibes.insertBusinessInfo("Pasticceria Brischero", "via Briscone 27, Acireale (CT)", "07:00 - 22:00", "Immagine");
		business aBusiness = catalog.getInstance().getBusinessList().get(0);
		foodvibes.insertNewReview(aBusiness, "Non va bene", 2, "I prodotti sono buoni ma non trovo mai discord attivato quando entro nel locale.");
		review aReview = aBusiness.getReviewList().get(0);
		assertEquals(aReview.getLikes(), 0);
		foodvibes.upVoteReview(aBusiness, aReview);
		assertEquals(aReview.getLikes(), 1);
		foodvibes.upVoteReview(aBusiness, aReview);
		assertEquals(aReview.getLikes(), 0);
	}
	
	@Test 
	void loginTest() {
		assertFalse(foodvibes.login("prova", ""));
		assertTrue(foodvibes.login("sebrisch","nonna"));
	}
	
	@Test
	void registerTest() {
		assertFalse(foodvibes.registerNewUser("Andrea", "Anfuso", "Italiano", "02/02/2000", "aanfuso97@gmail.com", "sebrisch", "nonna"));
		assertTrue(foodvibes.registerNewUser("Andrea", "Anfuso", "Italiano", "02/02/2000", "aanfuso97@gmail.com", "sebrisch1 ", "nonna"));
	}

}
