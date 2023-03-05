package bin;

public class review {
	private user user;
	private String title;
	private float vote;
	private String description;
	
	public review(user newUser,String newTitle, float newVote, String newDescription) {
		user = newUser;
		title = newTitle;
		vote = newVote;
		description = newDescription;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		METODI GET
	//-------------------------------------------------------------------------------------------------------------------
	
	public user getUser() {
		return user;
	}

	public String getTitle() {
		return title;
	}
	
	public float getVote() {
		return vote;
	}
	
	public String getDescription() {
		return description;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//	METODI SET
	//-------------------------------------------------------------------------------------------------------------------

	public void setTitle(String title) {
		this.title = title;
	}
	public void setVote(float vote) {
		this.vote = vote;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setUser(user newUser) {
		this.user = newUser;
	}
}
