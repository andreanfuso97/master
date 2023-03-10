package bin;
import java.util.Date;
import java.util.Calendar;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class foodvibes{
	private static GUI_Frame mainFrame;
	private static List<user> userList = new ArrayList<>();
	private static user currentUser;
	private static List<business> bronzeList = new ArrayList<>();
	private static List<business> silverList = new ArrayList<>();
	private static List<business> goldList = new ArrayList<>();
	private static List<business> platList = new ArrayList<>();
	private static business bestBusiness;
	private static user guestUser = new user("", "", "", "", "", "guest", "");
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainFrame = new GUI_Frame();
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		init();
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		METODI GET
	//-------------------------------------------------------------------------------------------------------------------
	
	public static user getUser() {
		return currentUser;
	}
	public static business getBestBusiness() {
		return bestBusiness;
	}
	
	public static GUI_Frame getMainFrame() {
		return mainFrame;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		METODI SET
	//-------------------------------------------------------------------------------------------------------------------
	
	public static void setUser(user newCurrentUser) {
		currentUser = newCurrentUser;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		INIZIALIZZAZIONE E TEST
	//-------------------------------------------------------------------------------------------------------------------

	public static void init() {
		
		registerNewUserNoMess("Mario", "Rossi", "Italiano", "30/09/95", "mariorossi@gmail.com", "mario60", "pizza");
		registerNewUserNoMess("Luca", "Nicotra", "Italiano", "02/02/2000", "lucanicotra@gmail.com", "luca82", "pasta");
		registerNewUserNoMess("Giovanni", "Verdi", "Italiano", "01/02/2000", "gioverdi@gmail.com", "giovanni93", "formaggio");
		registerNewAdminNoMess("Marco", "Mattarella", "Italiano", "01/02/2000", "marcomatt@gmail.com", "marco91", "cipolla");
		registerNewUserNoMess("Andrea", "Anfuso", "Italiano", "01/02/2000", "aanfuso97@gmail.com", "b", "b");
		registerNewUserNoMess("Andrea", "Anfuso", "Italiano", "01/02/2000", "aanfuso97@gmail.com", "a", "a");

		currentUser = userList.get(0);
		insertBusinessInfoNoMess("Pasticceria Rossi", "via Rossoni 27, Catania (CT)", "07:00 - 22:00", System.getProperty("user.dir") + "\\Images\\pasticceria1.jpg");
		currentUser = userList.get(1);
		insertBusinessInfoNoMess("Pasticceria Nicotra", "via Niconi 28, Messina (CT)", "07:00 - 22:00", System.getProperty("user.dir") + "\\Images\\pasticceria2.jpg");
		currentUser = userList.get(2);
		insertBusinessInfoNoMess("Pasticceria Verdi", "via Verdoni 29, Siracusa (CT)", "07:00 - 22:00", System.getProperty("user.dir") + "\\Images\\pasticceria3.jpg");
		currentUser = userList.get(3);
		insertNewReviewInit(catalog.getInstance().getBusinessList().get(0), "Non va bene", 2, "I prodotti sono buoni il locale ?? sporco.");
		currentUser = userList.get(2);
		insertNewReviewInit(catalog.getInstance().getBusinessList().get(1), "Buono", 3, "Posto carino cibo buono.");
		currentUser = userList.get(1);
		insertNewReviewInit(catalog.getInstance().getBusinessList().get(2), "Meraviglioso", 5, "Tutto buonissimo prezzi accessibili.");
		logoutNoMess();
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		REGISTRAZIONE NUOVA ATTIVIT??
	//-------------------------------------------------------------------------------------------------------------------
	
	public static boolean insertBusinessInfo(String name, String address, String openingHours, String image) {
		for(int i = 0; i<catalog.getInstance().getBusinessList().size(); i++) {
			if(catalog.getInstance().getBusinessList().get(i).getName().equals(name)) {
				JOptionPane.showMessageDialog(mainFrame, "Attivit?? con lo stesso nome gi?? presente, perfavore cambiare nome.");
				System.out.println("Attivit?? con lo stesso nome gi?? presente");
				return false;
			}
		}
	
		
		if(name.isBlank()||address.isBlank()||openingHours.isBlank()||image.isBlank()) {
			JOptionPane.showMessageDialog(mainFrame, "Riempi tutti i campi.");
			System.out.println("Campi non riempiti");
			return false;
		}
		business newBusiness = new business(name, address, openingHours, image, currentUser);
		catalog.getInstance().add(newBusiness);
		JOptionPane.showMessageDialog(mainFrame, "Attivit?? registrata correttamente.");
		//mainFrame.newBusinessPanel(newBusiness);
		return true;
	}
		
	
	//-------------------------------------------------------------------------------------------------------------------
	//		RICERCA ATTIVIT??
	//-------------------------------------------------------------------------------------------------------------------
	
	public static ArrayList<business> searchBusinessByName(String businessName) {
		return catalog.getInstance().getBusinessesByName(businessName);		
	}
	
	public static boolean showSearchResult(String aBusinessName) {
		if(aBusinessName.isBlank()) {
			JOptionPane.showMessageDialog(mainFrame, "Compila il campo di ricerca.");
			return false;
		}
		ArrayList<business> searchedBusinessList = foodvibes.searchBusinessByName(aBusinessName);
		if(searchedBusinessList.isEmpty()) {
			JOptionPane.showMessageDialog(mainFrame, "Nessuna attivit?? trovata con il nome corrispondente.");
			return false;
		}
		for (int i = 0; i<searchedBusinessList.size(); i++) {
			business foundBusiness = searchedBusinessList.get(i);
			mainFrame.newSearchResult(foundBusiness);
		}
		return true;
	}
	
	public static void showAllBusinesses() {
		ArrayList<business> searchedBusinessList = catalog.getInstance().getBusinessList();
		for (int i = 0; i<searchedBusinessList.size(); i++) {
			business foundBusiness = searchedBusinessList.get(i);
			mainFrame.newSearchResult(foundBusiness);
		}
	}
	
	public static void showBusinessInfo(business aBusiness) {
		mainFrame.newBusinessPanel(aBusiness);
	}

	//-------------------------------------------------------------------------------------------------------------------
	//		GESTIONE RECENSIONI
	//-------------------------------------------------------------------------------------------------------------------
	
	public static void showReviews(business aBusiness) {
		ArrayList<review> businessReviews = aBusiness.getReviewList();
		for(int i = 0; i<businessReviews.size(); i++) {
			if(businessReviews.get(i).getUser()==currentUser) {
				mainFrame.newReviewPanel(businessReviews.get(i), true, aBusiness);
			}else {
				mainFrame.newReviewPanel(businessReviews.get(i), false, aBusiness);
			}
		}
	}
	
	public static void insertNewReview(business aBusiness, String reviewTitle, float reviewVote, String reviewDescription) {
		aBusiness.addNewReview(new review(currentUser, reviewTitle, reviewVote, reviewDescription));
		aBusiness.updateAvgVote();
		setBusinessTier(aBusiness);
		showBusinessInfo(aBusiness);
		setBusinessTier(aBusiness);
		showBusinessInfo(aBusiness);
	}	
	
	
	public static void editReview(String title, float vote, String description, review aReview, business aBusiness) {
		aReview.setTitle(title);
		aReview.setVote(vote);
		aReview.setDescription(description);
		aBusiness.updateAvgVote();
		showBusinessInfo(aBusiness);
	}
	public static void removeReview(business aBusiness, review aReview) {
		aBusiness.removeReviewFromBusiness(aReview);
		aBusiness.updateAvgVote();
		showBusinessInfo(aBusiness);
	}

	//-------------------------------------------------------------------------------------------------------------------
	//		UPVOTE RECENSIONI
	//-------------------------------------------------------------------------------------------------------------------
	
	public static void upVoteReview(business aBusiness, review aReview) {
		if(!(currentUser.getLikedReviews().contains(aReview))) {
			aReview.upVote();
			currentUser.addLikedReview(aReview);
		} else {
			aReview.removeVote();
			currentUser.getLikedReviews().remove(aReview);
		}
		showReviews(aBusiness);	
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		OPERAZIONI DI AUTENTICAZIONE
	//-------------------------------------------------------------------------------------------------------------------
	
	public static boolean login(String username, String password) {
		for(int i = 0; i<userList.size(); i++) {
			user aUser = userList.get(i);
			if(aUser.getUsername().equals(username) && aUser.getPassword().equals(password)) {
				setUser(aUser);
				JOptionPane.showMessageDialog(mainFrame, "Login effettuato.");
				return true;
			}
		}
		JOptionPane.showMessageDialog(mainFrame, "Username e/o password errati.");
		return false;
	}
	
	
	public static void logout() {
		currentUser = guestUser;
		JOptionPane.showMessageDialog(mainFrame, "Logout effettuato.");
	}
	

	
	//-------------------------------------------------------------------------------------------------------------------
	//		GESTIONE ACCOUNT
	//-------------------------------------------------------------------------------------------------------------------
	
	public static boolean registerNewUser(String newName, String newSurname, String newNationality, String newBirthDate, String newEmail, String newUsername, String newPassword) {
		for(int i = 0; i<userList.size(); i++) {
			if(userList.get(i).getUsername().equals(newUsername)) {
				JOptionPane.showMessageDialog(mainFrame, "Nome utente in uso.");
				return false;
			}
		}
		userList.add(new user(newName,newSurname,newNationality, newBirthDate, newEmail, newUsername, newPassword));
		JOptionPane.showMessageDialog(mainFrame, "Registrazione effettuata con successo.");
		login(newUsername, newPassword);
		return true;
	}
	
	
	public static boolean registerNewAdmin(String newName, String newSurname, String newNationality, String newBirthDate, String newEmail, String newUsername, String newPassword) {
		for(int i = 0; i<userList.size(); i++) {
			if(userList.get(i).getUsername().equals(newUsername)) {
				JOptionPane.showMessageDialog(mainFrame, "Nome utente in uso.");
				return false;
			}
		}
		userList.add(new admin(newName,newSurname,newNationality, newBirthDate, newEmail, newUsername, newPassword));
		JOptionPane.showMessageDialog(mainFrame, "Registrazione effettuata con successo.");
		login(newUsername, newPassword);
		return true;
	}
	
	
	public static void editUserInfo(user aUser, String newName, String newSurname, String newNationality, String newBirthDate, String newEmail, String newUsername, String newPassword) {
		for(int i = 0; i<userList.size(); i++) {
			if(userList.get(i).getUsername().equals(newUsername) && !aUser.getUsername().equals(newUsername)) {
				JOptionPane.showMessageDialog(mainFrame, "Nome utente in uso.");
				return;
			}
		}
		aUser.setName(newName);
		aUser.setSurname(newSurname);
		aUser.setNationality(newNationality);
		aUser.setEmail(newEmail);
		aUser.setBirthDate(newBirthDate);
		aUser.setUsername(newUsername);
		aUser.setPassword(newPassword);
		return;
	}
	
	public static void removeUser(user aUser) {
		userList.remove(aUser);
		currentUser = guestUser;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		GESTIONE BUSINESS
	//-------------------------------------------------------------------------------------------------------------------
	
	public static void editBusinessInfo(business aBusiness, String name, String address, String openingHours, String image) {
		if(name.isBlank() || address.isBlank() || openingHours.isBlank()) {
			JOptionPane.showMessageDialog(mainFrame, "Riempi tutti i campi.");
			return;
		}
		for(int i=0; i<catalog.getInstance().getBusinessList().size(); i++) {
			business checkBusiness = catalog.getInstance().getBusinessList().get(i);
			if(checkBusiness.getName().equals(name) && !aBusiness.getName().equals(name)) {
				JOptionPane.showMessageDialog(mainFrame, "Attivit?? con lo stesso nome gi?? presente.");
				return;
			}
		}
		aBusiness.setName(name);
		aBusiness.setAddress(address);
		aBusiness.setOpeningHours(openingHours);
		aBusiness.setImage(image);
	}
	
	public static void removeBusiness(business aBusiness) {
		catalog.getInstance().removeFromList(aBusiness);
	}

	//-------------------------------------------------------------------------------------------------------------------
	//		GESTIONE BEST BUSINESSES
	//-------------------------------------------------------------------------------------------------------------------	
	
	public static void setBusinessTier(business aBusiness) {
		int reviewsNumber = aBusiness.getReviewList().size();
		
		if(reviewsNumber >= 50 && reviewsNumber < 100) {
			aBusiness.setTier(businessTiers.BRONZE);
		} else if(reviewsNumber >= 100 && reviewsNumber < 250) {
			aBusiness.setTier(businessTiers.SILVER);
		} else if(reviewsNumber >= 250 && reviewsNumber < 500) {
			aBusiness.setTier(businessTiers.GOLD);
		} else if(reviewsNumber >= 500) {
			aBusiness.setTier(businessTiers.PLAT);
		} else {
			aBusiness.setTier(businessTiers.NONE);
		}
		
		if(bestBusiness == null || (reviewsNumber > bestBusiness.getReviewList().size() && aBusiness.getAvgVote() >= 4)) {
			bestBusiness = aBusiness;
		}
	}
	
	public static void setBestBusiness(business aBusiness) {
		bestBusiness = aBusiness;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		GESTIONE REPORT
	//-------------------------------------------------------------------------------------------------------------------
	
	public static void addReportedReview(review aReview, business aBusiness, String type) {
		reportList.getInstance().addReport(aReview, aBusiness, type, currentUser);
		System.out.println("Segnalata: " + aReview + ", " + type);
	}
	
	public static void removeReportedReview(report aReport) {
		reportList.getInstance().removeReport(aReport);
	}
	
	public static void showAllReports() {
		List<report> reports = reportList.getInstance().getReportedReviewsList();
		for(int i = 0; i < reports.size(); i++) {
			mainFrame.foundReport(reports.get(i));
		}
	}
	

	//-------------------------------------------------------------------------------------------------------------------
	//SOLO PER TESTING (Usate per evitare la comparsa ripetuta di messaggi durante la creazione delle instanze gi?? presenti all'avvio del programma)
	//-------------------------------------------------------------------------------------------------------------------
	
	//SOLO PER TESTING (Usata per evitare i messaggi durante la creazione delle instanze gi?? presenti all'avvio del programma)
		public static boolean registerNewAdminNoMess(String newName, String newSurname, String newNationality, String newBirthDate, String newEmail, String newUsername, String newPassword) {
			for(int i = 0; i<userList.size(); i++) {
				if(userList.get(i).getUsername().equals(newUsername)) {
					JOptionPane.showMessageDialog(mainFrame, "Nome utente in uso.");
					return false;
				}
			}
			userList.add(new admin(newName,newSurname,newNationality, newBirthDate, newEmail, newUsername, newPassword));
			loginNoMess(newUsername, newPassword);
			return true;
		}
	
	//SOLO PER TESTING (Usata per evitare i messaggi durante la creazione delle instanze gi?? presenti all'avvio del programma)
	public static boolean registerNewUserNoMess(String newName, String newSurname, String newNationality, String newBirthDate, String newEmail, String newUsername, String newPassword) {
		for(int i = 0; i<userList.size(); i++) {
			if(userList.get(i).getUsername().equals(newUsername)) {
				JOptionPane.showMessageDialog(mainFrame, "Nome utente in uso.");
				return false;
			}
		}
		userList.add(new user(newName,newSurname,newNationality, newBirthDate, newEmail, newUsername, newPassword));
		loginNoMess(newUsername, newPassword);
		return true;
	}


	//SOLO PER TESTING (Usata per evitare i messaggi durante la creazione delle instanze gi?? presenti all'avvio del programma)
	public static void logoutNoMess() {
		currentUser = guestUser;
	}
	
	//SOLO PER TESTING (Usata per evitare i messaggi durante la creazione delle instanze gi?? presenti all'avvio del programma)
	public static boolean loginNoMess(String username, String password) {
		for(int i = 0; i<userList.size(); i++) {
			user aUser = userList.get(i);
			if(aUser.getUsername().equals(username) && aUser.getPassword().equals(password)) {
				setUser(aUser);
				return true;
			}
		}
		JOptionPane.showMessageDialog(mainFrame, "Username e/o password errati.");
		return false;
	}
	
	//SOLO PER TESTING (Usata per evitare i messaggi durante la creazione delle instanze gi?? presenti all'avvio del programma)
	public static void insertNewReviewInit(business aBusiness, String reviewTitle, float reviewVote, String reviewDescription) {
		aBusiness.addNewReview(new review(currentUser, reviewTitle, reviewVote, reviewDescription));
		aBusiness.updateAvgVote();
	}	

	//SOLO PER TESTING (Usata per evitare i messaggi durante la creazione delle instanze gi?? presenti all'avvio del programma)
	public static boolean insertBusinessInfoNoMess(String name, String address, String openingHours, String image) {
		for(int i = 0; i<catalog.getInstance().getBusinessList().size(); i++) {
			if(catalog.getInstance().getBusinessList().get(i).getName().equals(name)) {
				JOptionPane.showMessageDialog(mainFrame, "Attivit?? con lo stesso nome gi?? presente, perfavore cambiare nome.");
				System.out.println("Attivit?? con lo stesso nome gi?? presente");
				return false;
			}
		}
	
		
		if(name.isBlank()||address.isBlank()||openingHours.isBlank()||image.isBlank()) {
			JOptionPane.showMessageDialog(mainFrame, "Riempi tutti i campi.");
			System.out.println("Campi non riempiti");
			return false;
		}
		
		business newBusiness = new business(name, address, openingHours, image, currentUser);
		catalog.getInstance().add(newBusiness);
		return true;
	}
}

