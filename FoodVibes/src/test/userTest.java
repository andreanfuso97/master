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
		Calendar dateInfo = Calendar.getInstance();
		dateInfo.set(Calendar.YEAR, 1997);
		dateInfo.set(Calendar.MONTH, Calendar.JANUARY);
		dateInfo.set(Calendar.DAY_OF_MONTH, 10);
		bDate = dateInfo.getTime();
		aUser = new user("Sebastiano", "Brischetto", "Italiano", bDate, "seby@gmail.com", "sebrisch", "nonna");
	}
	
	@Test
	void getTest() {
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
		Calendar dateInfo = Calendar.getInstance();		
		dateInfo.set(Calendar.YEAR, 2000);
		dateInfo.set(Calendar.MONTH, Calendar.FEBRUARY);
		dateInfo.set(Calendar.DAY_OF_MONTH, 3);
		bDate = dateInfo.getTime();
		
		aUser.setName("Andrea");
		aUser.setSurname("Anfuso");
		aUser.setNationality("Italiano");
		aUser.setBirthDate(bDate);
		aUser.setEmail("andry@gmail.com");
		aUser.setUsername("andry");
		aUser.setPassword("password123");
		
		assertEquals("Andrea", aUser.getName());
		assertEquals("Anfuso", aUser.getSurname());
		assertEquals("Italiano", aUser.getNationality());
		assertEquals(bDate, aUser.getBirthDate());
		assertEquals("andry@gmail.com",aUser.getEmail());
		assertEquals("andry",aUser.getUsername());
		assertEquals("password123",aUser.getPassword());
	}
	
}
