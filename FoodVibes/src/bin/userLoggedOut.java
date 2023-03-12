package bin;

public class userLoggedOut extends userState{

	public userLoggedOut(user user) {
		super(user);
		System.out.println("Logged Out");
	}
	

	@Override
	public void thirdSidebarButton() {
		foodvibes.getMainFrame().showRegisterPanel();
	}

	@Override
	public void logButton() {
		foodvibes.getMainFrame().showLoginPanel();
	}
	

}
