import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class GUI_Frame extends JFrame {

	private JPanel contentPane;

	
	public JPanel getContentPane() {
		return this.contentPane;
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI_Frame frame = new GUI_Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI_Frame() {
		setTitle("FoodVibes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 490);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(102, 204, 102));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(204, 255, 204));
		layeredPane.setBounds(227, 5, 602, 441);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		//search business
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
		
		
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 83, 578, 2);
		searchBusinessPanel.add(separator_1);
		
		JPanel foundBusinessPanel_searchBusiness = new JPanel();						//panel da prendere	
		foundBusinessPanel_searchBusiness.setBackground(new Color(204, 255, 204));
		foundBusinessPanel_searchBusiness.setBounds(10, 96, 578, 330);
		
		JScrollPane scrollPane = new JScrollPane(foundBusinessPanel_searchBusiness);
		foundBusinessPanel_searchBusiness.setLayout(new BoxLayout(foundBusinessPanel_searchBusiness, BoxLayout.Y_AXIS));
		scrollPane.setEnabled(false);
		scrollPane.setBounds(10, 96, 578, 330);
		searchBusinessPanel.add(BorderLayout.CENTER, scrollPane);
		
		button_searchBusiness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<business> searchedBusinesses = foodvibes.searchBusiness(searchTextArea_searchBusiness.getText());
				
				for(int i = 0; i < searchedBusinesses.size(); i++) {
					
					JPanel searchResultContainer = new JPanel();
					searchResultContainer.setBounds(30, 40, 300, 50);
					foundBusinessPanel_searchBusiness.add(searchResultContainer);
					searchResultContainer.setPreferredSize(new Dimension(0, 100));
					
					JLabel searchedBusiness = new JLabel(searchedBusinesses.get(i).getName());
					searchedBusiness.setFont(new Font("Calibri", Font.BOLD, 20));
					searchedBusiness.setBounds(10, 11, 198, 25);
					searchResultContainer.add(searchedBusiness);
					
					System.out.println("Adding: " + searchedBusinesses.get(i).getName());
					
				}
				
				foundBusinessPanel_searchBusiness.validate();
				foundBusinessPanel_searchBusiness.repaint();
				scrollPane.validate();
				scrollPane.repaint();
			}
		});
		
		//registerBusiness
		JPanel registerBusinessPanel = new JPanel();
		registerBusinessPanel.setBackground(new Color(204, 255, 204));
		registerBusinessPanel.setLayout(null);
		layeredPane.add(registerBusinessPanel, "registerBusinessPanel");
		
		JLabel titleLabel_newBusiness = new JLabel("Registra la tua Attività");
		titleLabel_newBusiness.setFont(new Font("Calibri", Font.BOLD, 20));
		titleLabel_newBusiness.setBounds(10, 11, 187, 25);
		registerBusinessPanel.add(titleLabel_newBusiness);
		
		JLabel descriptionLabel_newBusiness = new JLabel("<html>Fornisci le informazioni necessarie per registrare la tua attività <br>  su FoodVibes</html>");
		descriptionLabel_newBusiness.setVerticalAlignment(SwingConstants.TOP);
		descriptionLabel_newBusiness.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionLabel_newBusiness.setFont(new Font("Calibri", Font.PLAIN, 12));
		descriptionLabel_newBusiness.setBounds(10, 48, 347, 30);
		registerBusinessPanel.add(descriptionLabel_newBusiness);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 102, 0));
		separator.setBounds(10, 89, 404, 2);
		registerBusinessPanel.add(separator);
		
		JLabel newNameLabel_newBusiness = new JLabel("Nome Attività");
		newNameLabel_newBusiness.setFont(new Font("Calibri", Font.PLAIN, 14));
		newNameLabel_newBusiness.setBounds(10, 118, 103, 15);
		registerBusinessPanel.add(newNameLabel_newBusiness);
		
		JTextArea newNameTextArea_newBusiness = new JTextArea();
		newNameTextArea_newBusiness.setBounds(214, 113, 200, 22);
		registerBusinessPanel.add(newNameTextArea_newBusiness);
		
		JLabel newAddressLabel_newBusiness = new JLabel("Indirizzo");
		newAddressLabel_newBusiness.setFont(new Font("Calibri", Font.PLAIN, 14));
		newAddressLabel_newBusiness.setBounds(10, 156, 90, 15);
		registerBusinessPanel.add(newAddressLabel_newBusiness);
		
		JTextArea newAddressTextArea_newBusiness = new JTextArea();
		newAddressTextArea_newBusiness.setBounds(214, 149, 200, 22);
		registerBusinessPanel.add(newAddressTextArea_newBusiness);
		
		JLabel newOpeningHoursLabel_newBusiness = new JLabel("Orari di apertura");
		newOpeningHoursLabel_newBusiness.setFont(new Font("Calibri", Font.PLAIN, 14));
		newOpeningHoursLabel_newBusiness.setBounds(10, 193, 103, 15);
		registerBusinessPanel.add(newOpeningHoursLabel_newBusiness);
		
		JTextArea newOpeningHoursTextArea_newBusiness = new JTextArea();
		newOpeningHoursTextArea_newBusiness.setBounds(214, 186, 200, 22);
		registerBusinessPanel.add(newOpeningHoursTextArea_newBusiness);
		
		JLabel newImageLabel_newBusiness = new JLabel("Link immagini");
		newImageLabel_newBusiness.setFont(new Font("Calibri", Font.PLAIN, 14));
		newImageLabel_newBusiness.setBounds(10, 233, 103, 15);
		registerBusinessPanel.add(newImageLabel_newBusiness);
		
		JTextArea newImageTextArea_newBusiness = new JTextArea();
		newImageTextArea_newBusiness.setBounds(214, 226, 200, 22);
		registerBusinessPanel.add(newImageTextArea_newBusiness);
		
		JButton btnRegistra = new JButton("Registra");
		btnRegistra.setVerticalAlignment(SwingConstants.BOTTOM);
		btnRegistra.setFont(new Font("Calibri", Font.BOLD, 20));
		btnRegistra.setBounds(10, 293, 200, 33);
		registerBusinessPanel.add(btnRegistra);
		
		btnRegistra.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				foodvibes.insertBusinessInfo(newNameTextArea_newBusiness.getText(), newAddressTextArea_newBusiness.getText(),
						newOpeningHoursTextArea_newBusiness.getText(), newImageTextArea_newBusiness.getText());
			}
		});
		
		//side bar
		JPanel SideBar = new JPanel();
		SideBar.setBackground(new Color(204, 255, 204));
		SideBar.setBounds(5, 5, 212, 441);
		contentPane.add(SideBar);
		SideBar.setLayout(null);
		
		JLabel titleLabel_sidebar = new JLabel("Benvenuto");
		titleLabel_sidebar.setHorizontalAlignment(SwingConstants.CENTER);
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
	}
}
