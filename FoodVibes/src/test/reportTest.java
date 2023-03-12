package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bin.business;
import bin.report;
import bin.review;
import bin.user;

class reportTest {

	@Test
	void getAndConstructorTest() {
		user aUser = new user("Sebastiano", "Brischetto", "Italiano", "30/09/95", "seby@gmail.com", "sebrisch", "nonna");
		business aBusiness = new business ("nome","indirizzo","orari","immagine",aUser);
		review aReview = new review(aUser, "title", 5, "description");
		report aReport = new report(aReview, aBusiness, "molestie", aUser);
		assertEquals(aUser, aReport.getAuthor());
		assertEquals(aBusiness, aReport.getBusiness());
		assertEquals("molestie", aReport.getType());
		assertEquals(aReview, aReport.getReview());
	}

}
