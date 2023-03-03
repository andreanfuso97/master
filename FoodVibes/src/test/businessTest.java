package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import bin.business;
import bin.user;

class businessTest {
	private business aBusiness;
	private user aUser;
	
	@BeforeEach
	void setUpaBusiness() {
		Calendar dateInfo = Calendar.getInstance();
		dateInfo.set(Calendar.YEAR, 1997);
		dateInfo.set(Calendar.MONTH, Calendar.JANUARY);
		dateInfo.set(Calendar.DAY_OF_MONTH, 10);
		aUser = new user("Sebastiano", "Brischetto", "Italiano", dateInfo.getTime(), "seby@gmail.com", "sebrisch", "nonna");
		aBusiness = new business ("nome","indirizzo","orari","immagine",aUser);
	}
	@Test
	@DisplayName("test dei metodi get")
	void getTest() {
		assertEquals("nome",aBusiness.getName());
		assertEquals("indirizzo",aBusiness.getAddress());
		assertEquals("orari",aBusiness.getOpeningHours());
		assertEquals("immagine",aBusiness.getImage());
		assertEquals(aUser,aBusiness.getOwner());
	}
	
	@Test
	@DisplayName("test dei metodi get")
	void setTest() {
		Calendar dateInfo = Calendar.getInstance();		
		dateInfo.set(Calendar.YEAR, 2000);
		dateInfo.set(Calendar.MONTH, Calendar.FEBRUARY);
		dateInfo.set(Calendar.DAY_OF_MONTH, 3);
		user aUser2 = new user("Andrea", "Anfuso", "Italiano", dateInfo.getTime(), "andry@gmail.com", "andry", "password123");
		
		aBusiness.setName("nomeNuovo");
		aBusiness.setAddress("indirizzoNuovo");
		aBusiness.setOpeningHours("orariNuovi");
		aBusiness.setImage("immagineNuova");
		aBusiness.setOwner(aUser2);
		
		assertEquals("nomeNuovo",aBusiness.getName());
		assertEquals("indirizzoNuovo",aBusiness.getAddress());
		assertEquals("orariNuovi",aBusiness.getOpeningHours());
		assertEquals("immagineNuova",aBusiness.getImage());
		assertEquals(aUser2,aBusiness.getOwner());
	}
	

}
