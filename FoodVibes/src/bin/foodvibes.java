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
	
	public static user getUser() {
		return currentUser;
	}
	
	public static void setUser(user newCurrentUser) {
		currentUser = newCurrentUser;
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		INIZIALIZZAZIONE E TEST
	//-------------------------------------------------------------------------------------------------------------------

	public static void init() {
		Calendar dateInfo = Calendar.getInstance();
		dateInfo.set(Calendar.YEAR, 1997);
		dateInfo.set(Calendar.MONTH, Calendar.JANUARY);
		dateInfo.set(Calendar.DAY_OF_MONTH, 10);
		Date bDate = dateInfo.getTime();
		user nUser = new user("Sebastiano", "Brischetto", "Italiano", bDate, "seby@gmail.com", "sebrisch", "nonna");
		userList.add(nUser);
		//currentUser = userList.get(0);
		test();
	}
	
	public static void test() {
		Calendar dateInfo = Calendar.getInstance();
		dateInfo.set(Calendar.YEAR, 1997);
		dateInfo.set(Calendar.MONTH, Calendar.JUNE);
		dateInfo.set(Calendar.DAY_OF_MONTH, 6);
		Date bDate = dateInfo.getTime();
		user nUser = new user("Andrea", "Anfuso", "Italiano", bDate, "aanfuso97@gmail.com", "andreanfuso", "nonna");
		userList.add(nUser);
		business B = new business("Pasticceria Brischero", "via Briscone 27, Acireale (CT)", "07:00 - 22:00", "Immagine", nUser);
		review R = new review(nUser, "Non va bene", 2, "I prodotti sono buoni ma non trovo mai discord attivato quando entro nel locale.");
		B.addNewReview(R);
		B.updateAvgVote();
		catalog.getInstance().add(B);
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	
	//-------------------------------------------------------------------------------------------------------------------
	//		REGISTRAZIONE NUOVA ATTIVITÀ
	//-------------------------------------------------------------------------------------------------------------------
	
	
	public static void insertBusinessInfo(String name, String address, String openingHours, String image) {
		if(name.isBlank()||address.isBlank()||openingHours.isBlank()||image.isBlank()) {
			JOptionPane.showMessageDialog(mainFrame, "Riempi tutti i campi.");
		}else {
			business newBusiness = new business(name, address, openingHours, image, currentUser);
			catalog.getInstance().add(newBusiness);
			JOptionPane.showMessageDialog(mainFrame, "Attività registrata correttamente.");
			mainFrame.newBusinessPanel(newBusiness);
		}
	}
	
	
	//-------------------------------------------------------------------------------------------------------------------
	//		RICERCA ATTIVITÀ
	//-------------------------------------------------------------------------------------------------------------------
	
	public static ArrayList<business> searchBusiness(String businessName) {
		return catalog.getInstance().getBusinessesByName(businessName);		
	}
	
	public static void showSearchResult(String aBusinessName) {
		if(aBusinessName.isBlank()) {
			JOptionPane.showMessageDialog(mainFrame, "Compila il campo di ricerca.");
			return;
		}
		ArrayList<business> searchedBusinessList = foodvibes.searchBusiness(aBusinessName);
		if(searchedBusinessList.isEmpty()) {
			JOptionPane.showMessageDialog(mainFrame, "Nessuna attività trovata con il nome corrispondente.");
			return;
		}
		for (int i = 0; i<searchedBusinessList.size(); i++) {
			business foundBusiness = searchedBusinessList.get(i);
			mainFrame.newSearchResult(foundBusiness);
		}
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
		ArrayList<review> businessReviews = aBusiness.getBusinessReviews();
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
		aBusiness.getBusinessReviews().remove(aReview);
		aBusiness.updateAvgVote();
		showBusinessInfo(aBusiness);
	}

	//-------------------------------------------------------------------------------------------------------------------
	//		UPVOTE RECENSIONI
	//-------------------------------------------------------------------------------------------------------------------
	
	public static void upVoteReview(business aBusiness, review aReview) {
		aReview.upVote();
		showReviews(aBusiness);
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		AGGIUNGI SEGNALAZIONE RECENSIONE
	//-------------------------------------------------------------------------------------------------------------------
	
	public static void addReportedReview(review aReview, String type) {
		reportedReviews.getInstance().add(aReview, type);
		System.out.println("Segnalata: " + aReview + ", " + type);
		
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		OPERAZIONI DI ACCESSO
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
		currentUser = null;
		JOptionPane.showMessageDialog(mainFrame, "Logout effettuato.");
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		REGISTRAZIONE NUOVO ACCOUNT
	//-------------------------------------------------------------------------------------------------------------------
	
	public static boolean registerNewUser(String newName, String newSurname, String newNationality, Date newBirthDate, String newEmail, String newUsername, String newPassword) {
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
}