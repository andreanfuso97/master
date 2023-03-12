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
		init();
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
		currentUser = guestUser;
		
		user adminUser = new admin("Sebastiano", "Brischetto", "Italiano", "30/09/95", "seby@gmail.com", "sebrisch", "nonna");
		userList.add(adminUser);
		test();
	}
	
	public static void test() {
		user aUser = new user("Andrea", "Anfuso", "Italiano", "02/02/2000", "aanfuso97@gmail.com", "andreanfuso", "nonna");
		userList.add(aUser);
		
		business B = new business("Pasticceria Brischero", "via Briscone 27, Acireale (CT)", "07:00 - 22:00", "Immagine", aUser);
		review R = new review(aUser, "Non va bene", 2, "I prodotti sono buoni ma non trovo mai discord attivato quando entro nel locale.");
		B.addNewReview(R);
		B.updateAvgVote();
		catalog.getInstance().add(B);
		
		aUser = new user("Andrea", "Anfuso", "Italiano", "01/02/2000", "aanfuso97@gmail.com", "a", "a");
		userList.add(aUser);
		
		aUser = new user("Andrea", "Anfuso", "Italiano", "01/02/2000", "aanfuso97@gmail.com", "b", "b");
		userList.add(aUser);
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		REGISTRAZIONE NUOVA ATTIVITÀ
	//-------------------------------------------------------------------------------------------------------------------
	
	public static boolean insertBusinessInfo(String name, String address, String openingHours, String image) {
		for(int i = 0; i<catalog.getInstance().getBusinessList().size(); i++) {
			if(catalog.getInstance().getBusinessList().get(i).getName().equals(name)) {
				JOptionPane.showMessageDialog(mainFrame, "Attività con lo stesso nome già presente, perfavore cambiare nome.");
				System.out.println("Attività con lo stesso nome già presente");
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
		JOptionPane.showMessageDialog(mainFrame, "Attività registrata correttamente.");
		//mainFrame.newBusinessPanel(newBusiness);
		return true;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		RICERCA ATTIVITÀ
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
			JOptionPane.showMessageDialog(mainFrame, "Nessuna attività trovata con il nome corrispondente.");
			return false;
		}
		for (int i = 0; i<searchedBusinessList.size(); i++) {
			business foundBusiness = searchedBusinessList.get(i);
			//mainFrame.newSearchResult(foundBusiness);
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
		//showBusinessInfo(aBusiness);
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
		//showReviews(aBusiness);	
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
	
}