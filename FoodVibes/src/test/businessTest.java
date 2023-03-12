package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bin.business;
import bin.businessTiers;
import bin.foodvibes;
import bin.review;
import bin.user;

class businessTest {
	private business aBusiness;
	private user aUser;
	private review aReview;
	
	@BeforeEach
	void setUpABusiness() {
		aUser = new user("Sebastiano", "Brischetto", "Italiano", "30/09/95", "seby@gmail.com", "sebrisch", "nonna");
		aBusiness = new business ("nome","indirizzo","orari","immagine",aUser);
		aReview = new review(aUser,"nomeReview",5,"descrizioneReview");
	}	
	
	@Test
	@DisplayName("test costruttore e get")
	void getAndConstructorTest() {
		assertEquals("nome",aBusiness.getName());
		assertEquals("indirizzo",aBusiness.getAddress());
		assertEquals("orari",aBusiness.getOpeningHours());
		assertEquals("immagine",aBusiness.getImage());
		assertEquals(aUser,aBusiness.getOwner());
		assertEquals(0,aBusiness.getAvgVote());
	}
	
	@Test
	@DisplayName("test dei metodi set")
	void setTest() {
		user aUser2 = new user("Andrea", "Anfuso", "Italiano", "02/02/2000", "andry@gmail.com", "andry", "password123");
		
		aBusiness.setName("nomeNuovo");
		aBusiness.setAddress("indirizzoNuovo");
		aBusiness.setOpeningHours("orariNuovi");
		aBusiness.setImage("immagineNuova");
		aBusiness.setOwner(aUser2);
		aBusiness.setAvgVote(3);
		
		assertEquals("nomeNuovo",aBusiness.getName());
		assertEquals("indirizzoNuovo",aBusiness.getAddress());
		assertEquals("orariNuovi",aBusiness.getOpeningHours());
		assertEquals("immagineNuova",aBusiness.getImage());
		assertEquals(aUser2,aBusiness.getOwner());
		assertEquals(3,aBusiness.getAvgVote());
	}
	
	@Test
	@DisplayName("test aggiunta recensioni alla lista")
	void addNewReviewTest() {
		aBusiness.addNewReview(aReview);
		review insertedReview = aBusiness.getReviewList().get(0);
		assertEquals("nomeReview", insertedReview.getTitle());
		assertEquals(5, insertedReview.getVote());
		assertEquals("descrizioneReview", insertedReview.getDescription());
	}
	
	@Test
	@DisplayName("test rimozione recensioni alla lista")
	void removeReviewTest() {
		aBusiness.addNewReview(aReview);
		aBusiness.removeReviewFromBusiness(aReview);
		assertTrue(aBusiness.getReviewList().isEmpty());
	}
	
	@Test
	@DisplayName("test assegnazione del premio ad un business")
	void checkBusinessTierTest() {
		for(int i = 0; i < 100; i++) {
			aBusiness.addNewReview(aReview);
		}
		
		foodvibes.setBusinessTier(aBusiness);
		
		assertEquals(businessTiers.SILVER, aBusiness.getTier());
	}
	

}
