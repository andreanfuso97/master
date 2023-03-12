package test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import bin.user;

class userTest {
	private user aUser;
	Date bDate;
	
	@BeforeEach
	void setUpUser() {
		aUser = new user("Sebastiano", "Brischetto", "Italiano", "30/09/95", "seby@gmail.com", "sebrisch", "nonna");
	}
	
	@Test
	void getAndConstructorTest() {
		assertEquals("Sebastiano", aUser.getName());
		assertEquals("Brischetto", aUser.getSurname());
		assertEquals("Italiano", aUser.getNationality());
		assertEquals(bDate, aUser.getBirthDate());
		assertEquals("seby@gmail.com",aUser.getEmail());
		assertEquals("sebrisch",aUser.getUsername());
		assertEquals("nonna",aUser.getPassword());
	}
	
	@Test
	void setTest() {
		
		aUser.setName("Andrea");
		aUser.setSurname("Anfuso");
		aUser.setNationality("Italiano");
		aUser.setBirthDate("30/09/00");
		aUser.setEmail("andry@gmail.com");
		aUser.setUsername("andry");
		aUser.setPassword("password123");
		
		assertEquals("Andrea", aUser.getName());
		assertEquals("Anfuso", aUser.getSurname());
		assertEquals("Italiano", aUser.getNationality());
		assertEquals("30/09/00", aUser.getBirthDate());
		assertEquals("andry@gmail.com",aUser.getEmail());
		assertEquals("andry",aUser.getUsername());
		assertEquals("password123",aUser.getPassword());
	}
	
}
