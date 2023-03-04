package bin;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class GUI_Frame extends JFrame {
	
	private JLayeredPane layeredPane;
	private JPanel foundBusinessPanel_searchBusiness;
	private JScrollPane foundBusinessScrollPane_searchBusiness;
	private JPanel businessPanel;
	
	public JLayeredPane getLayeredPane() {
		return layeredPane;
	}
	
	public JPanel getFoundBusinessPanel() {
		return foundBusinessPanel_searchBusiness;
	}
	
	public JScrollPane getFoundBusinessScrollPane() {
		return foundBusinessScrollPane_searchBusiness;
	}
	
	public JPanel getBusinessPanel() {
		return businessPanel;
	}
	
	public GUI_Frame() {
		setTitle("FoodVibes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 490);
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 102));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(204, 255, 204));
		layeredPane.setBounds(227, 5, 602, 441);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		//side bar panel
		JPanel SideBar = new JPanel();
		SideBar.setBackground(new Color(204, 255, 204));
		SideBar.setBounds(5, 5, 212, 441);
		contentPane.add(SideBar);
		SideBar.setLayout(null);
		
		String userName = foodvibes.getUser().getName();
		JLabel titleLabel_sidebar = new JLabel("<html>Benvenuto<br>" + userName + "</html>");
		titleLabel_sidebar.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel_sidebar.setBounds(10, 11, 192, 50);
		titleLabel_sidebar.setFont(new Font("Calibri", Font.BOLD, 25));
		SideBar.add(titleLabel_sidebar);
		
		JButton searchButton_sidebar = new JButton("Cerca");
		searchButton_sidebar.setBackground(new Color(51, 204, 51));
		searchButton_sidebar.setFont(new Font("Calibri", Font.BOLD, 20));
		searchButton_sidebar.setBounds(0, 72, 212, 40);
		SideBar.add(searchButton_sidebar);
		
		searchButton_sidebar.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				CardLayout cardLayout = (CardLayout)(layeredPane.getLayout());
				cardLayout.show(layeredPane, "searchBusinessPanel");
			}
		});
		
		JButton registerBusinessButton_sidebar = new JButton("Registra attività");
		registerBusinessButton_sidebar.setBackground(new Color(51, 204, 51));
		registerBusinessButton_sidebar.setFont(new Font("Calibri", Font.BOLD, 20));
		registerBusinessButton_sidebar.setBounds(0, 111, 212, 40);
		SideBar.add(registerBusinessButton_sidebar);
		
		registerBusinessButton_sidebar.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				CardLayout cardLayout = (CardLayout)(layeredPane.getLayout());
				cardLayout.show(layeredPane, "registerBusinessPanel");
			}
		});
		
		//search business panel
		JPanel searchBusinessPanel = new JPanel();
		searchBusinessPanel.setBackground(new Color(204, 255, 204));
		searchBusinessPanel.setLayout(null);
		layeredPane.add(searchBusinessPanel, "searchBusinessPanel");
		
		JLabel title_searchBusiness = new JLabel("Cerca Attività");
		title_searchBusiness.setFont(new Font("Calibri", Font.BOLD, 20));
		title_searchBusiness.setBounds(10, 11, 198, 25);
		searchBusinessPanel.add(title_searchBusiness);
		
		JTextField searchTextArea_searchBusiness;
		searchTextArea_searchBusiness = new JTextField();
		searchTextArea_searchBusiness.setColumns(10);
		searchTextArea_searchBusiness.setBounds(10, 47, 361, 25);
		searchBusinessPanel.add(searchTextArea_searchBusiness);
		
		JButton button_searchBusiness = new JButton("🔎︎");
		button_searchBusiness.setBounds(371, 47, 49, 25);
		searchBusinessPanel.add(button_searchBusiness);
		button_searchBusiness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(searchTextArea_searchBusiness.getText().isBlank()) {
					JOptionPane.showMessageDialog(contentPane, "Compila il campo di ricerca.");
					return;
				}
				ArrayList<business> searchedBusinessList = foodvibes.searchBusiness(searchTextArea_searchBusiness.getText());
				foundBusinessPanel_searchBusiness.removeAll();
				for (int i = 0; i<searchedBusinessList.size(); i++) {
					business foundBusiness = searchedBusinessList.get(i);
					
					
					JPanel searchResultPanel = new JPanel();
					searchResultPanel.setBounds(30, 40, 300, 50);
					foundBusinessPanel_searchBusiness.add(searchResultPanel);
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
							foodvibes.showBusinessInfo(foundBusiness);
							newBusinessPanel(foundBusiness);
						}
					});
				}
				foundBusinessPanel_searchBusiness.validate();
				foundBusinessPanel_searchBusiness.repaint();
				foundBusinessScrollPane_searchBusiness.validate();
				foundBusinessScrollPane_searchBusiness.repaint();
			}
		});
		
		JSeparator separator_searchBusiness = new JSeparator();
		separator_searchBusiness.setBounds(10, 83, 578, 2);
		searchBusinessPanel.add(separator_searchBusiness);
		
		foundBusinessPanel_searchBusiness = new JPanel();
		foundBusinessPanel_searchBusiness.setBackground(new Color(204, 255, 204));
		foundBusinessPanel_searchBusiness.setBounds(10, 96, 578, 330);
		
		foundBusinessScrollPane_searchBusiness = new JScrollPane(foundBusinessPanel_searchBusiness);
		foundBusinessPanel_searchBusiness.setLayout(new BoxLayout(foundBusinessPanel_searchBusiness, BoxLayout.Y_AXIS));
		foundBusinessScrollPane_searchBusiness.setEnabled(false);
		foundBusinessScrollPane_searchBusiness.setBounds(10, 96, 578, 330);
		searchBusinessPanel.add(BorderLayout.CENTER, foundBusinessScrollPane_searchBusiness);
		
		//registerBusiness panel
		JPanel registerBusinessPanel = new JPanel();
		registerBusinessPanel.setBackground(new Color(204, 255, 204));
		registerBusinessPanel.setLayout(null);
		layeredPane.add(registerBusinessPanel, "registerBusinessPanel");
		
		JLabel titleLabel_registerBusiness = new JLabel("Registra la tua Attività");
		titleLabel_registerBusiness.setFont(new Font("Calibri", Font.BOLD, 20));
		titleLabel_registerBusiness.setBounds(10, 11, 187, 25);
		registerBusinessPanel.add(titleLabel_registerBusiness);
		
		JLabel descriptionLabel_registerBusiness = new JLabel("<html>Fornisci le informazioni necessarie per registrare la tua attività <br>  su FoodVibes</html>");
		descriptionLabel_registerBusiness.setVerticalAlignment(SwingConstants.TOP);
		descriptionLabel_registerBusiness.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionLabel_registerBusiness.setFont(new Font("Calibri", Font.PLAIN, 12));
		descriptionLabel_registerBusiness.setBounds(10, 48, 347, 30);
		registerBusinessPanel.add(descriptionLabel_registerBusiness);
		
		JSeparator separator_registerBusiness = new JSeparator();
		separator_registerBusiness.setForeground(new Color(0, 102, 0));
		separator_registerBusiness.setBounds(10, 89, 404, 2);
		registerBusinessPanel.add(separator_registerBusiness);
		
		JLabel newNameLabel_registerBusiness = new JLabel("Nome Attività");
		newNameLabel_registerBusiness.setFont(new Font("Calibri", Font.PLAIN, 14));
		newNameLabel_registerBusiness.setBounds(10, 118, 103, 15);
		registerBusinessPanel.add(newNameLabel_registerBusiness);
		
		JTextArea newNameTextArea_registerBusiness = new JTextArea();
		newNameTextArea_registerBusiness.setBounds(214, 113, 200, 22);
		registerBusinessPanel.add(newNameTextArea_registerBusiness);
		
		JLabel newAddressLabel_registerBusiness = new JLabel("Indirizzo");
		newAddressLabel_registerBusiness.setFont(new Font("Calibri", Font.PLAIN, 14));
		newAddressLabel_registerBusiness.setBounds(10, 156, 90, 15);
		registerBusinessPanel.add(newAddressLabel_registerBusiness);
		
		JTextArea newAddressTextArea_registerBusiness = new JTextArea();
		newAddressTextArea_registerBusiness.setBounds(214, 149, 200, 22);
		registerBusinessPanel.add(newAddressTextArea_registerBusiness);
		
		JLabel newOpeningHoursLabel_registerBusiness = new JLabel("Orari di apertura");
		newOpeningHoursLabel_registerBusiness.setFont(new Font("Calibri", Font.PLAIN, 14));
		newOpeningHoursLabel_registerBusiness.setBounds(10, 193, 103, 15);
		registerBusinessPanel.add(newOpeningHoursLabel_registerBusiness);
		
		JTextArea newOpeningHoursTextArea_registerBusiness = new JTextArea();
		newOpeningHoursTextArea_registerBusiness.setBounds(214, 186, 200, 22);
		registerBusinessPanel.add(newOpeningHoursTextArea_registerBusiness);
		
		JLabel newImageLabel_registerBusiness = new JLabel("Link immagini");
		newImageLabel_registerBusiness.setFont(new Font("Calibri", Font.PLAIN, 14));
		newImageLabel_registerBusiness.setBounds(10, 233, 103, 15);
		registerBusinessPanel.add(newImageLabel_registerBusiness);
		
		JTextArea newImageTextArea_registerBusiness = new JTextArea();
		newImageTextArea_registerBusiness.setBounds(214, 226, 200, 22);
		registerBusinessPanel.add(newImageTextArea_registerBusiness);
		
		JButton btnRegistra = new JButton("Registra");
		btnRegistra.setVerticalAlignment(SwingConstants.BOTTOM);
		btnRegistra.setFont(new Font("Calibri", Font.BOLD, 20));
		btnRegistra.setBounds(10, 293, 200, 33);
		registerBusinessPanel.add(btnRegistra);
		btnRegistra.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				foodvibes.insertBusinessInfo(newNameTextArea_registerBusiness.getText(), newAddressTextArea_registerBusiness.getText(), newOpeningHoursTextArea_registerBusiness.getText(), newImageTextArea_registerBusiness.getText());
			}
		});
		
		//business panel
		businessPanel = new JPanel();
		businessPanel.setBackground(new Color(204, 255, 204));
		layeredPane.add(businessPanel, "businessPanel");
		businessPanel.setLayout(null);
	}
	
	public void newBusinessPanel(business aBusiness) {
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
