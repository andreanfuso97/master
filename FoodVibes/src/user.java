import java.util.Date;
public class user {
	private String Name;
	private String Surname;
	private String Nationality;
	private Date birthDate;
	private String email;
	private String username;
	private String password;
	
	public user(String name, String surname, String nationality, Date birthDate, String email, String username,
			String password) {
		super();
		Name = name;
		Surname = surname;
		Nationality = nationality;
		this.birthDate = birthDate;
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSurname() {
		return Surname;
	}
	public void setSurname(String surname) {
		Surname = surname;
	}
	public String getNationality() {
		return Nationality;
	}
	public void setNationality(String nationality) {
		Nationality = nationality;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
