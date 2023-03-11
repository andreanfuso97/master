package bin;
import java.util.ArrayList;
import java.util.Date;

public class user {
	private String Name;
	private String Surname;
	private String Nationality;
	private String birthDate;
	private String email;
	private String username;
	private String password;
	private ArrayList<review> likedReviews;
	private userState state;
	
	public user(String newName, String newSurname, String newNationality, String newBirthDate, String newEmail, String newUsername, String newPassword) {
		super();
		Name = newName;
		Surname = newSurname;
		Nationality = newNationality;
		birthDate = newBirthDate;
		email = newEmail;
		username = newUsername;
		password = newPassword;
		likedReviews = new ArrayList<>();
		state = new userLoggedOut(this);
	}
	
	//get
	public String getName() {
		return Name;
	}
	public String getSurname() {
		return Surname;
	}
	public String getNationality() {
		return Nationality;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public String getEmail() {
		return email;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public userState getState() {
		return state;
	}
	public ArrayList getLikedReviews() {
		return likedReviews;
	}
	
	//set
	public void setName(String newName) {
		Name = newName;
	}
	public void setSurname(String newSurname) {
		Surname = newSurname;
	}
	public void setNationality(String newNationality) {
		Nationality = newNationality;
	}
	public void setBirthDate(String newBirthDate) {
		this.birthDate = newBirthDate;
	}
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	public void setUsername(String newUsername) {
		this.username = newUsername;
	}
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}
	public void setUserState(userState state) {
		this.state = state;
	}
	public void addLikedReview(review aReview) {
		likedReviews.add(aReview);
	}
	
	@Override
	public String toString() {
		return "user [Name=" + Name + ", Surname=" + Surname + ", Nationality=" + Nationality + ", birthDate="
				+ birthDate + ", email=" + email + ", username=" + username + ", password=" + password + "]";
	}
}
