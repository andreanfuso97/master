package bin;

public class report {
	private review review;
	private business business;
	private String type;
	private user author;
	
	public report(review newReview, business newBusiness, String newType, user newAuthor) {
		review = newReview;
		business = newBusiness;
		type = newType;
		author = newAuthor;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		METODI GET
	//-------------------------------------------------------------------------------------------------------------------
	public business getBusiness() {
		return business;
	}
	public review getReview() {
		return review;
	}
	public user getAuthor() {
		return author;
	}
	public String getType() {
		return type;
	}
}
