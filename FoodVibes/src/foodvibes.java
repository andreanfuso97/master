import java.awt.CardLayout;
import java.util.Date;
import java.util.Calendar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class foodvibes{
	private static GUI_Frame mainFrame;
	private static List<user> userList = new ArrayList<>();
	private static user currentUser;
	
	
	public static void main(String[] args) {
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
		catalog.getInstance().add(new business(name, address, openingHours, image));
	}
	
	public static void searchBusiness(String businessName) {
		ArrayList<business> searchedBusinessList = catalog.getInstance().getBusinessList(businessName);
		JPanel businessPanel = mainFrame.getFoundBusinessPanel();
		JScrollPane businessScrollPane = mainFrame.getFoundBusinessScrollPane();
		
		businessPanel.removeAll();
		
		for (int i = 0; i<searchedBusinessList.size(); i++) {
			foodvibes.addFoundBusiness(searchedBusinessList.get(i));
		}
		businessPanel.validate();
		businessPanel.repaint();
		businessScrollPane.validate();
		businessScrollPane.repaint();
		
	}
	private static void addFoundBusiness(business foundBusiness) {
		System.out.println("matching business found (name: " + foundBusiness.getName() + ", address: " + foundBusiness.getAddress() + ", openingHours: " + foundBusiness.getOpeningHours() + ", image: " + foundBusiness.getImage());
		
		JPanel searchResultPanel = new JPanel();
		searchResultPanel.setBounds(30, 40, 300, 50);
		mainFrame.getFoundBusinessPanel().add(searchResultPanel);
		searchResultPanel.setMaximumSize(new Dimension(1000, 30));
		searchResultPanel.setBackground(new Color(204, 255, 204));
		
		JLabel searchedBusinessLabel_searchResultPanel = new JLabel(foundBusiness.getName());
		searchedBusinessLabel_searchResultPanel.setFont(new Font("Calibri", Font.BOLD, 20));
		searchedBusinessLabel_searchResultPanel.setBounds(10, 11, 198, 25);
		searchResultPanel.add(searchedBusinessLabel_searchResultPanel);
		
		JButton searchedBusinessButton_searchResultPanel = new JButton("seleziona");
		searchedBusinessButton_searchResultPanel.setFont(new Font("Calibri", Font.PLAIN, 12));
		searchResultPanel.add(searchedBusinessButton_searchResultPanel);
		searchedBusinessButton_searchResultPanel.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				foodvibes.showBusiness(foundBusiness);
			}
		});
	}
	
	private static void showBusiness(business aBusiness) {
		//generic business page layout
		JLayeredPane layeredPane = mainFrame.getLayeredPane();
		JPanel businessPanel = mainFrame.getBusinessPanel();
		
		businessPanel.removeAll();
		
		JLabel nameLabel_businessPanel1 = new JLabel(aBusiness.getName());
		nameLabel_businessPanel1.setFont(new Font("Calibri", Font.PLAIN, 30));
		nameLabel_businessPanel1.setBounds(10, 11, 582, 43);
		businessPanel.add(nameLabel_businessPanel1);
		
		JLabel addressLabel_businessPanel1 = new JLabel("Indirizzo: " + aBusiness.getAddress());
		addressLabel_businessPanel1.setFont(new Font("Calibri", Font.PLAIN, 15));
		addressLabel_businessPanel1.setBounds(10, 65, 291, 43);
		businessPanel.add(addressLabel_businessPanel1);
		
		JLabel openingHours_businessPanel1 = new JLabel("Orari di apertura: " + aBusiness.getOpeningHours());
		openingHours_businessPanel1.setFont(new Font("Calibri", Font.PLAIN, 15));
		openingHours_businessPanel1.setBounds(311, 65, 291, 43);
		businessPanel.add(openingHours_businessPanel1);
		
		JLabel imageLabel_businessPanel1 = new JLabel("Immagine attività");
		imageLabel_businessPanel1.setIcon(new ImageIcon(aBusiness.getImage()));
		imageLabel_businessPanel1.setBounds(10, 119, 582, 311);
		businessPanel.add(imageLabel_businessPanel1);
		
		CardLayout cardLayout = (CardLayout)(layeredPane.getLayout());
		cardLayout.show(layeredPane, "businessPanel");
	}
}