package bin;

public class userLogged extends userState{

	public userLogged(user user) {
		super(user);
		System.out.println("User Logged");
	}
	
	@Override
	public void thirdSidebarButton() {
		foodvibes.getMainFrame().showEditUserPanel();
	}


	@Override
	public void logButton() {
		foodvibes.getMainFrame().setRegisterButton();
		foodvibes.getMainFrame().setLoginButton();
		foodvibes.getMainFrame().setWelcomeGuest();
		foodvibes.logout();
		currentUser.setUserState(new userLoggedOut(currentUser));
	}


}
