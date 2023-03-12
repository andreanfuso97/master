package bin;

public abstract class userState {
	protected user currentUser;
	
	public userState(user user) {
		this.currentUser = user;
	}
	
	public abstract void logButton();
	
	public abstract void thirdSidebarButton();
	
}
