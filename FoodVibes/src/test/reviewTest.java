package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bin.business;
import bin.review;
import bin.user;

class reviewTest {
	
	private user aUser = new user("Sebastiano", "Brischetto", "Italiano", "30/09/95", "seby@gmail.com", "sebrisch", "nonna");
	private review aReview;
	
	@BeforeEach
	void setup() {
		aReview = new review(aUser, "title", 5, "description");
	}
	
	@Test
	void getTest() {
		assertEquals(aReview.getUser(),aUser);
		assertEquals(aReview.getTitle(),"title");
		assertEquals(aReview.getVote(),5);
		assertEquals(aReview.getDescription(),"description");
	}
	
	@Test
	void setTest() {
		aReview.setTitle("newTitle");
		aReview.setVote(3);
		aReview.setDescription("newDescription");
		user newUser = new user("Andrea", "Anfuso", "Italiano", "01/10/2000", "andry@gmail.com", "andreanfuso", "nonna");
		aReview.setUser(newUser);
		assertEquals(aReview.getUser(),newUser);
		assertEquals(aReview.getTitle(),"newTitle");
		assertEquals(aReview.getVote(),3);
		assertEquals(aReview.getDescription(),"newDescription");
	}
	
	@Test
	void testVote() {
		aReview.upVote();
		aReview.upVote();
		aReview.removeVote();
		assertEquals(1, aReview.getLikes());
	}

}
