package bin;

public class adminLogged extends userState{

	public adminLogged(user user) {
		super(user);
		System.out.println("Admin Logged");
	}

	@Override
	public void thirdSidebarButton() {
		foodvibes.getMainFrame().showEditUserPanel();
	}
	
	@Override
	public void logButton() {
		foodvibes.getMainFrame().setRegisterButton();
		foodvibes.getMainFrame().setLoginButton();
		foodvibes.getMainFrame().hideReportListButton();
		foodvibes.getMainFrame().setWelcomeGuest();
		foodvibes.logout();
		currentUser.setUserState(new userLoggedOut(currentUser));
	}

}
