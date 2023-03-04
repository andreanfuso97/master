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
		
		
		//utente iniziale
		Calendar dateInfo = Calendar.getInstance();
		dateInfo.set(Calendar.YEAR, 1997);
		dateInfo.set(Calendar.MONTH, Calendar.JANUARY);
		dateInfo.set(Calendar.DAY_OF_MONTH, 10);
		Date bDate = dateInfo.getTime();
		user nUser = new user("Sebastiano", "Brischetto", "Italiano", bDate, "seby@gmail.com", "sebrisch", "nonna");
		userList.add(nUser);
		currentUser = userList.get(0);
		
		
		
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
	
	public static void insertBusinessInfo(String name, String address, String openingHours, String image) {
		if(name.isBlank()||address.isBlank()||openingHours.isBlank()||image.isBlank()) {
			JOptionPane.showMessageDialog(mainFrame, "Riempi tutti i campi.");
		}else {
			catalog.getInstance().add(new business(name, address, openingHours, image, currentUser));
			JOptionPane.showMessageDialog(mainFrame, "Attività registrata correttamente.");
		}
	}
	
	public static ArrayList<business> searchBusiness(String businessName) {
		return catalog.getInstance().getBusinessesByName(businessName);		
	}
	
	
	public static void showBusinessInfo(business aBusiness) {
		mainFrame.newBusinessPanel(aBusiness);
	}
	
	
	public static void showSearchResult(String aBusinessName) {
		if(aBusinessName.isBlank()) {
			JOptionPane.showMessageDialog(mainFrame, "Compila il campo di ricerca.");
			return;
		}
		ArrayList<business> searchedBusinessList = foodvibes.searchBusiness(aBusinessName);
		for (int i = 0; i<searchedBusinessList.size(); i++) {
			business foundBusiness = searchedBusinessList.get(i);
			mainFrame.newSearchResult(foundBusiness);
		}
		
	}
	
	public static void insertNewReview(business aBusiness, String reviewTitle, float reviewVote, String reviewDescription) {
		aBusiness.addNewReview(reviewTitle,reviewVote,reviewDescription);
		showReviews(aBusiness);
	}
	
	public static void showReviews(business aBusiness) {
		ArrayList<review> businessReviews = aBusiness.getBusinessReviews();
		for(int i = 0; i<businessReviews.size(); i++) {
			mainFrame.newReviewPanel(businessReviews.get(i));
		}
	}
	
}