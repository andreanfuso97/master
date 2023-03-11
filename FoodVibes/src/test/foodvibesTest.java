package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bin.business;
import bin.foodvibes;
import bin.reportList;
import bin.review;
import bin.catalog;

class foodvibesTest {
	private business aBusiness;
	private review aReview;

	@BeforeEach
	void setupInsertNewReview() {
		foodvibes.insertNewReview(aBusiness, "title", 5, "description");
	}
	
	@Test
	void testInsertNewReview() {
	}
	
	@BeforeEach
	void setupUpVoteReview() {
	}
	
	@Test
	void testUpVoteReview() {
	}
	
	@BeforeEach
	void setupAddReportedReview() {
		//foodvibes.addReportedReview(aReview, aBusiness, "Volgarità");
	}
	
	@Test
	void testAddReportedReview() {
		//assertEquals(true, reportList.getInstance().getReportedReviewsList().contains(aReview));
	}

	@BeforeEach
	void setupRemoveReportedReview() {
		//foodvibes.addReportedReview(aReview, aBusiness, "Volgarità");
	}
	
	@Test
	void testRemoveReportedReview() {
		//assertEquals(false, reportList.getInstance().getReportedReviewsList().contains(aReview));
	}

}
