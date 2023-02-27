import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class foodvibes{
	private static GUI_Frame mainFrame;
	
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
		System.out.println("matching business found (name: " + foundBusiness.name + ", address: " + foundBusiness.address + ", openingHours: " + foundBusiness.openingHours + ", image: " + foundBusiness.image);
		
		JPanel searchResultContainer = new JPanel();
		searchResultContainer.setBounds(30, 40, 300, 50);
		mainFrame.getFoundBusinessPanel().add(searchResultContainer);
		//searchResultContainer.setPreferredSize(new Dimension(0, 100));
		searchResultContainer.setMaximumSize(new Dimension(1000, 30));
		searchResultContainer.setBackground(new Color(204, 255, 204));
		
		JLabel searchedBusiness = new JLabel(foundBusiness.getName());
		searchedBusiness.setFont(new Font("Calibri", Font.BOLD, 20));
		searchedBusiness.setBounds(10, 11, 198, 25);
		searchResultContainer.add(searchedBusiness);	
	}
}