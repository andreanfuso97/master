package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bin.business;
import bin.catalog;
import bin.report;
import bin.reportList;
import bin.review;
import bin.user;

class reportListTest {
	private user aUser;
	private review aReview;
	private business aBusiness;
	
	@BeforeEach
	void setUpReports() {
		aUser = new user("Sebastiano", "Brischetto", "Italiano", "30/09/95", "seby@gmail.com", "sebrisch", "nonna");
		aBusiness = new business ("nome","indirizzo","orari","immagine",aUser);
		aReview = new review(aUser, "title", 5, "description");
	}
	
	
	@Test
	void addTest() {
		reportList.getInstance().addReport(aReview, aBusiness, "molestie", aUser);
		report addedReport = reportList.getInstance().getReportedReviewsList().get(0);
		assertEquals(aUser, addedReport.getAuthor());
		assertEquals(aBusiness, addedReport.getBusiness());
		assertEquals("molestie", addedReport.getType());
		assertEquals(aReview, addedReport.getReview());
	}
	
	@Test
	void removeTest() {
		reportList.getInstance().removeReport(reportList.getInstance().getReportedReviewsList().get(0));
		assertTrue(reportList.getInstance().getReportedReviewsList().isEmpty());
	}
}
