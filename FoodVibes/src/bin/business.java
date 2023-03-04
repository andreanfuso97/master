package bin;

import java.util.ArrayList;

public class business {
	private String name;
	private String address;
	private String openingHours;
	private String image;
	private user owner;
	private ArrayList<review> reviewList = new ArrayList<>();;
	
	//-------------------------------------------------------------------------------------------------------------------
	//		COSTRUTTORE
	//-------------------------------------------------------------------------------------------------------------------
	
	public business(String newName, String newAddress, String newOpeningHours, String newImage, user newOwner) {
		name = newName;
		address = newAddress;
		openingHours = newOpeningHours;
		image = newImage;
		owner = newOwner;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		METODI GET
	//-------------------------------------------------------------------------------------------------------------------
	public String getName() {
		return this.name;
	}
	public String getAddress() {
		return address;
	}
	public String getOpeningHours() {
		return openingHours;
	}
	public String getImage() {
		return image;
	}
	public user getOwner() {
		return owner;
	}
	public ArrayList<review> getBusinessReviews(){
		return reviewList;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		METODI SET
	//-------------------------------------------------------------------------------------------------------------------
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setOwner(user owner) {
		this.owner = owner;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		AGGIUNGI NUOVA RECENSIONE ALLA LISTA 
	//-------------------------------------------------------------------------------------------------------------------
	public void addNewReview(user user,String title, Float vote, String description) {
		reviewList.add(new review(user,title,vote,description));
		System.out.println("aggiunta nuova recensione (titolo: " + title + ", voto: " + vote + ", descrizione: " + description + ")");
	}
	
}
