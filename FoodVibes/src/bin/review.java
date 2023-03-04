package bin;

public class review {
	private user user;
	private String title;
	private float vote;
	private String description;
	
	public review(String newTitle, float newVote, String newDescription) {
		title = newTitle;
		vote = newVote;
		description = newDescription;
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

	public void setTitle(String title) {
		this.title = title;
	}
	public void setVote(float vote) {
		this.vote = vote;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
