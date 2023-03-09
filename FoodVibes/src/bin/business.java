package bin;

import java.util.ArrayList;

public class business {
	private String name;
	private String address;
	private String openingHours;
	private String image;
	private user owner;
	private float avgVote;
	private ArrayList<review> reviewList = new ArrayList<>();
	
	//-------------------------------------------------------------------------------------------------------------------
	//		COSTRUTTORE
	//-------------------------------------------------------------------------------------------------------------------
	
	public business(String newName, String newAddress, String newOpeningHours, String newImage, user newOwner) {
		name = newName;
		address = newAddress;
		openingHours = newOpeningHours;
		image = newImage;
		owner = newOwner;
		avgVote = 0;
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
	public float getAvgVote() {
		return avgVote;
	}
	public ArrayList<review> getReviewList() {
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
	public void setAvgVote(float avgVote) {
		this.avgVote = avgVote;
	}
	public void setReviewList(ArrayList<review> reviewList) {
		this.reviewList = reviewList;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		AGGIUNGI NUOVA RECENSIONE ALLA LISTA 
	//-------------------------------------------------------------------------------------------------------------------
		
	public void addNewReview(review newReview) {
		reviewList.add(newReview);
		System.out.println("aggiunta nuova recensione (titolo: " + newReview.getTitle() + ", voto: " + newReview.getVote() + ", descrizione: " + newReview.getDescription() + ")");
	}
	public void removeReview(review aReview) {
		reviewList.remove(aReview);
	}
	//-------------------------------------------------------------------------------------------------------------------
	//		UPDATE VOTO MEDIO
	//-------------------------------------------------------------------------------------------------------------------
	
	public void updateAvgVote() {
		avgVote = 0;
		for(int i = 0; i < reviewList.size(); i++)  {
			avgVote += reviewList.get(i).getVote();
		}
		avgVote /= reviewList.size();
	}
	
}
