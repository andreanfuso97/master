package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bin.business;
import bin.review;
import bin.user;

class reviewTest {
	
	private user aUser;
	private review aReview;
	
	@BeforeEach
	void setupVote() {
		aReview = new review(aUser, "title", 5, "description");
		aReview.upVote();
		aReview.upVote();
		aReview.removeVote();
	}
	
	@Test
	void testVote() {
		assertEquals(1, aReview.getLikes());
	}

}
