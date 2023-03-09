package bin;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.Border;

public class GUI_Frame extends JFrame {
	
	private JLayeredPane layeredPane;
	private JPanel foundBusinessPanel_searchBusiness;
	private JScrollPane foundBusinessScrollPane_searchBusiness;
	private JPanel businessPanel;
	private JPanel reviewsPanel_businessPanel;
	private JScrollPane reviewsScrollPane_businessPanel;
	private CardLayout cardLayout;
	private JPanel searchBusinessPanel;
	private JPanel reportsPanel;
	private JButton reportList;
	
	public GUI_Frame() {
		
		//-------------------------------------------------------------------------------------------------------------------
		//		INIZIALIZZAZIONE FRAME
		//-------------------------------------------------------------------------------------------------------------------
		setResizable(false);
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
		cardLayout = (CardLayout)(layeredPane.getLayout());
		
		
		
		//-------------------------------------------------------------------------------------------------------------------
		//		BARRA LATERALE
		//-------------------------------------------------------------------------------------------------------------------
		
		JPanel SideBar = new JPanel();
		SideBar.setBackground(new Color(204, 255, 204));
		SideBar.setBounds(5, 5, 212, 441);
		contentPane.add(SideBar);
		SideBar.setLayout(null);
		
		JLabel titleLabel_sidebar = new JLabel("Benvenuto");
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
				foundBusinessPanel_searchBusiness.removeAll();
				cardLayout.show(layeredPane, "searchBusinessPanel");
				foodvibes.showAllBusinesses();
				foundBusinessPanel_searchBusiness.validate();
				foundBusinessPanel_searchBusiness.repaint();
				foundBusinessScrollPane_searchBusiness.validate();
				foundBusinessScrollPane_searchBusiness.repaint();
			}
		});
		
		JButton registerBusinessButton_sidebar = new JButton("Registra attività");
		registerBusinessButton_sidebar.setBackground(new Color(51, 204, 51));
		registerBusinessButton_sidebar.setFont(new Font("Calibri", Font.BOLD, 20));
		registerBusinessButton_sidebar.setBounds(0, 111, 212, 40);
		SideBar.add(registerBusinessButton_sidebar);
		registerBusinessButton_sidebar.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				if(foodvibes.getUser() == null) {
					JOptionPane.showMessageDialog(contentPane, "Effettua il login per poter registrare una attività.");
				}else {
					cardLayout.show(layeredPane, "registerBusinessPanel");					
				}
			}
		});
		

		JButton registerButton = new JButton("Registrazione");
		registerButton.setFont(new Font("Calibri", Font.BOLD, 20));
		registerButton.setBackground(new Color(51, 204, 51));
		registerButton.setBounds(0, 360, 212, 40);
		SideBar.add(registerButton);
		registerButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				cardLayout.show(layeredPane, "registerPanel");
			}
		});
		JButton logUserButton = new JButton("Login");
		logUserButton.setFont(new Font("Calibri", Font.BOLD, 20));
		logUserButton.setBackground(new Color(51, 204, 51));
		logUserButton.setBounds(0, 401, 212, 40);
		SideBar.add(logUserButton);
		logUserButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				if(foodvibes.getUser() == null) {
					cardLayout.show(layeredPane, "loginPanel");
				}else {
					foodvibes.logout();
					reportList.setVisible(false);
					logUserButton.setText("Login");
					titleLabel_sidebar.setText("Benvenuto");
					cardLayout.show(layeredPane, "searchBusinessPanel");
					registerButton.setVisible(true);
				}
			}
		});
		
		reportList = new JButton("Segnalazioni");
		reportList.setFont(new Font("Calibri", Font.BOLD, 20));
		reportList.setBackground(new Color(51, 204, 51));
		reportList.setBounds(0, 151, 212, 40);
		SideBar.add(reportList);
		reportList.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				reportsPanel.removeAll();
				cardLayout.show(layeredPane, "reportListPanel");
				foodvibes.showAllReports();
			}
		});
		reportList.setVisible(false);
		
		//-------------------------------------------------------------------------------------------------------------------
		//		FINESTRA DI RICERCA
		//-------------------------------------------------------------------------------------------------------------------
		
		searchBusinessPanel = new JPanel();
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
				foundBusinessPanel_searchBusiness.removeAll();
				foodvibes.showSearchResult(searchTextArea_searchBusiness.getText());
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
		
		//-------------------------------------------------------------------------------------------------------------------
		//		FINESTRA DI REGISTRAZIONE ATTIVITÀ
		//-------------------------------------------------------------------------------------------------------------------
		
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
		
		//-------------------------------------------------------------------------------------------------------------------
		//		FINESTRA ATTIVITÀ
		//-------------------------------------------------------------------------------------------------------------------
		businessPanel = new JPanel();
		businessPanel.setBackground(new Color(204, 255, 204));
		layeredPane.add(businessPanel, "businessPanel");
		businessPanel.setLayout(null);
		
		//-------------------------------------------------------------------------------------------------------------------
		//		FINESTRA LOGIN
		//-------------------------------------------------------------------------------------------------------------------
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(new Color(204, 255, 204));
		layeredPane.add(loginPanel, "loginPanel");
		loginPanel.setLayout(null);
		
		JLabel titleLabel_login = new JLabel("Effettua il login");
		titleLabel_login.setFont(new Font("Calibri", Font.PLAIN, 25));
		titleLabel_login.setBounds(213, 11, 155, 43);
		loginPanel.add(titleLabel_login);
		
		JLabel userNameLabel_login = new JLabel("Nome utente");
		userNameLabel_login.setFont(new Font("Calibri", Font.PLAIN, 15));
		userNameLabel_login.setBounds(108, 73, 107, 24);
		loginPanel.add(userNameLabel_login);
		
		JTextField userNameTextField_login = new JTextField();
		userNameTextField_login.setBounds(272, 71, 241, 24);
		loginPanel.add(userNameTextField_login);
		userNameTextField_login.setColumns(10);
		
		JLabel passwordLabel_login = new JLabel("Password");
		passwordLabel_login.setFont(new Font("Calibri", Font.PLAIN, 15));
		passwordLabel_login.setBounds(108, 108, 107, 24);
		loginPanel.add(passwordLabel_login);
		
		JTextField passwordTextField_login = new JTextField();
		passwordTextField_login.setColumns(10);
		passwordTextField_login.setBounds(272, 106, 241, 24);
		loginPanel.add(passwordTextField_login);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(247, 158, 89, 23);
		loginPanel.add(loginButton);
		loginButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {
				if(foodvibes.login(userNameTextField_login.getText(), passwordTextField_login.getText())) {
					cardLayout.show(layeredPane, "searchBusinessPanel");
					titleLabel_sidebar.setText("<html>Benvenuto<br>" + userNameTextField_login.getText() + "</html>");
					logUserButton.setText("Logout");
					registerButton.setVisible(false);
					if(foodvibes.getUser() instanceof admin) {
						reportList.setVisible(true);
					}
					
				}
			}
		});
		
		//-------------------------------------------------------------------------------------------------------------------
		//		FINESTRA REGISTRAZIONE NUOVO ACCOUNT
		//-------------------------------------------------------------------------------------------------------------------
		
		JPanel registerPanel = new JPanel();
		registerPanel.setBackground(new Color(204, 255, 204));
		layeredPane.add(registerPanel, "registerPanel");
		registerPanel.setLayout(null);
		
		JLabel titleLabel_register = new JLabel("Inserisci i tuoi dati");
		titleLabel_register.setFont(new Font("Calibri", Font.PLAIN, 25));
		titleLabel_register.setBounds(198, 11, 186, 43);
		registerPanel.add(titleLabel_register);
		
		JLabel userNameLabel_register = new JLabel("Nome");
		userNameLabel_register.setFont(new Font("Calibri", Font.PLAIN, 15));
		userNameLabel_register.setBounds(108, 73, 107, 24);
		registerPanel.add(userNameLabel_register);
		
		JTextField nameTextField_register = new JTextField();
		nameTextField_register.setColumns(10);
		nameTextField_register.setBounds(272, 71, 241, 24);
		registerPanel.add(nameTextField_register);
		
		JLabel surnameLabel_register = new JLabel("Cognome");
		surnameLabel_register.setFont(new Font("Calibri", Font.PLAIN, 15));
		surnameLabel_register.setBounds(108, 108, 107, 24);
		registerPanel.add(surnameLabel_register);
		
		JTextField surnameTextField_register = new JTextField();
		surnameTextField_register.setColumns(10);
		surnameTextField_register.setBounds(272, 106, 241, 24);
		registerPanel.add(surnameTextField_register);
		
		JLabel nationalityLabel_register = new JLabel("Nazionalità");
		nationalityLabel_register.setFont(new Font("Calibri", Font.PLAIN, 15));
		nationalityLabel_register.setBounds(108, 143, 107, 24);
		registerPanel.add(nationalityLabel_register);
		
		JTextField nationalityTextField_register = new JTextField();
		nationalityTextField_register.setColumns(10);
		nationalityTextField_register.setBounds(272, 141, 241, 24);
		registerPanel.add(nationalityTextField_register);
		
		JLabel birthdateLabel_register = new JLabel("Data di nascità");
		birthdateLabel_register.setFont(new Font("Calibri", Font.PLAIN, 15));
		birthdateLabel_register.setBounds(108, 178, 107, 24);
		registerPanel.add(birthdateLabel_register);
		
		JLabel emailLabel_register = new JLabel("Email");
		emailLabel_register.setFont(new Font("Calibri", Font.PLAIN, 15));
		emailLabel_register.setBounds(108, 213, 107, 24);
		registerPanel.add(emailLabel_register);
		
		JTextField emailTextField_register = new JTextField();
		emailTextField_register.setColumns(10);
		emailTextField_register.setBounds(272, 213, 241, 24);
		registerPanel.add(emailTextField_register);
		
		JLabel usernameLabel_register = new JLabel("Username");
		usernameLabel_register.setFont(new Font("Calibri", Font.PLAIN, 15));
		usernameLabel_register.setBounds(108, 248, 107, 24);
		registerPanel.add(usernameLabel_register);
		
		JTextField usernameTextField_register = new JTextField();
		usernameTextField_register.setColumns(10);
		usernameTextField_register.setBounds(272, 248, 241, 24);
		registerPanel.add(usernameTextField_register);
		
		JLabel passwordLabel_register = new JLabel("Password");
		passwordLabel_register.setFont(new Font("Calibri", Font.PLAIN, 15));
		passwordLabel_register.setBounds(108, 283, 107, 24);
		registerPanel.add(passwordLabel_register);
		
		JTextField passwordTextField_register = new JTextField();
		passwordTextField_register.setColumns(10);
		passwordTextField_register.setBounds(272, 283, 241, 24);
		registerPanel.add(passwordTextField_register);
		
		JButton registerButton_register = new JButton("Registrati");
		registerButton_register.setBounds(247, 400, 89, 23);
		registerPanel.add(registerButton_register);
		registerButton_register.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {
				Calendar dateInfo = Calendar.getInstance();
				dateInfo.set(Calendar.YEAR, 1997);
				dateInfo.set(Calendar.MONTH, Calendar.JANUARY);
				dateInfo.set(Calendar.DAY_OF_MONTH, 10);
				Date bDate = dateInfo.getTime();
				if(foodvibes.registerNewUser(nameTextField_register.getText(), surnameTextField_register.getText(), nationalityTextField_register.getText(),bDate , emailTextField_register.getText(), usernameTextField_register.getText(), passwordTextField_register.getText())) {
					cardLayout.show(layeredPane, "searchBusinessPanel");
					titleLabel_sidebar.setText("<html>Benvenuto<br>" + usernameTextField_register.getText() + "</html>");
					logUserButton.setText("Logout");
					registerButton.setVisible(false);
					
				}
			}
		});
		
		JPanel reportListPanel = new JPanel();
		reportListPanel.setBackground(new Color(204, 255, 204));
		layeredPane.add(reportListPanel, "reportListPanel");
		reportListPanel.setLayout(null);
		
		JLabel titlereportList = new JLabel("Recensioni Segnalate");
		titlereportList.setHorizontalAlignment(SwingConstants.CENTER);
		titlereportList.setFont(new Font("Calibri", Font.BOLD, 25));
		titlereportList.setBounds(10, 11, 582, 40);
		reportListPanel.add(titlereportList);
		
		JScrollPane reportListScrollPane = new JScrollPane();
		reportListScrollPane.setBounds(10, 50, 580, 380);
		reportListScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		reportListPanel.add(reportListScrollPane);
		
		reportsPanel = new JPanel();
		reportsPanel.setBackground(new Color(204, 255, 204));
		reportListScrollPane.setViewportView(reportsPanel);
		reportsPanel.setLayout(new BoxLayout(reportsPanel, BoxLayout.Y_AXIS));

	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		CONTENITORE ATTIVITÀ TROVATE
	//-------------------------------------------------------------------------------------------------------------------
	
	public void newSearchResult(business foundBusiness) {
		JPanel searchResultPanel = new JPanel();
		searchResultPanel.setBounds(30, 40, 300, 50);
		foundBusinessPanel_searchBusiness.add(searchResultPanel);
		searchResultPanel.setMaximumSize(new Dimension(1000, 30));
		searchResultPanel.setBackground(new Color(204, 255, 204));
		searchResultPanel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel searchedBusinessVoteLabel_searchResultPanel = new JLabel("Voto: " + Float.toString(foundBusiness.getAvgVote()));
		searchedBusinessVoteLabel_searchResultPanel.setFont(new Font("Calibri", Font.BOLD, 20));
		searchedBusinessVoteLabel_searchResultPanel.setBounds(10, 11, 198, 25);
		searchResultPanel.add(searchedBusinessVoteLabel_searchResultPanel);
		
		JLabel searchedBusinessNameLabel_searchResultPanel = new JLabel(foundBusiness.getName());
		searchedBusinessNameLabel_searchResultPanel.setFont(new Font("Calibri", Font.BOLD, 20));
		searchedBusinessNameLabel_searchResultPanel.setBounds(10, 11, 198, 25);
		searchResultPanel.add(searchedBusinessNameLabel_searchResultPanel);
		
		
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
	
	//-------------------------------------------------------------------------------------------------------------------
	//		FINESTRA ATTIVITÀ
	//-------------------------------------------------------------------------------------------------------------------
	
	public void newBusinessPanel(business aBusiness) {
		businessPanel.removeAll();
		
		JLabel nameLabel_businessPanel = new JLabel(aBusiness.getName());
		nameLabel_businessPanel.setFont(new Font("Calibri", Font.BOLD, 30));
		nameLabel_businessPanel.setBounds(10, 10, 290, 43);
		businessPanel.add(nameLabel_businessPanel);
		
		JLabel businessAvgVote = new JLabel("Voto: " + aBusiness.getAvgVote());
		businessAvgVote.setFont(new Font("Calibri", Font.BOLD, 15));
		businessAvgVote.setBounds(400, 10, 290, 43);
		businessPanel.add(businessAvgVote);
		
		JLabel addressLabel_businessPanel = new JLabel("Indirizzo: " + aBusiness.getAddress());
		addressLabel_businessPanel.setFont(new Font("Calibri", Font.BOLD, 15));
		addressLabel_businessPanel.setBounds(10, 65, 291, 43);
		businessPanel.add(addressLabel_businessPanel);
		
		
		JLabel openingHours_businessPanel = new JLabel("Orari di apertura: " + aBusiness.getOpeningHours());
		openingHours_businessPanel.setFont(new Font("Calibri", Font.BOLD, 15));
		openingHours_businessPanel.setBounds(10, 119, 291, 43);
		businessPanel.add(openingHours_businessPanel);

		JLabel imageLabel_businessPanel = new JLabel("Immagine attività");
		Image img = new ImageIcon(this.getClass().getResource("\\images\\placeholder.png")).getImage();
		imageLabel_businessPanel.setIcon(new ImageIcon(img));
		imageLabel_businessPanel.setBounds(311, 11, 281, 151);
		businessPanel.add(imageLabel_businessPanel);
		
		reviewsScrollPane_businessPanel = new JScrollPane();
		reviewsScrollPane_businessPanel.setBounds(10, 196, 580, 234);
		reviewsScrollPane_businessPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		businessPanel.add(reviewsScrollPane_businessPanel);
		
		reviewsPanel_businessPanel = new JPanel();
		reviewsPanel_businessPanel.setBackground(new Color(164, 215, 164));
		reviewsScrollPane_businessPanel.setViewportView(reviewsPanel_businessPanel);
		reviewsPanel_businessPanel.setLayout(new BoxLayout(reviewsPanel_businessPanel, BoxLayout.Y_AXIS));
		
		if(foodvibes.getUser() != null) {
			if(foodvibes.getUser() == aBusiness.getOwner()) {
				JButton editBusinessButton_businessPanel = new JButton("Modifica informazioni");
				editBusinessButton_businessPanel.setBounds(10, 162, 183, 23);
				businessPanel.add(editBusinessButton_businessPanel);
				editBusinessButton_businessPanel.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){
						editBusinessPanel(aBusiness);
						newBusinessPanel(aBusiness);
					}
				});
				
				JButton removeBusinessButton_businessPanel = new JButton("Rimuovi Attività");
				removeBusinessButton_businessPanel.setBounds(193, 162, 183, 23);
				businessPanel.add(removeBusinessButton_businessPanel);
				removeBusinessButton_businessPanel.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){
						if (JOptionPane.showConfirmDialog(businessPanel, "Vuoi eliminare l'attività?", "Elimina attività", JOptionPane.YES_NO_OPTION) == 0) {
							foodvibes.removeBusiness(aBusiness);
							foundBusinessPanel_searchBusiness.removeAll();
							foodvibes.showAllBusinesses();
							foundBusinessPanel_searchBusiness.validate();
							foundBusinessPanel_searchBusiness.repaint();
							foundBusinessScrollPane_searchBusiness.validate();
							foundBusinessScrollPane_searchBusiness.repaint();
							cardLayout.show(layeredPane, "searchBusinessPanel");
						}
					}
				});
				
			} else {		
				JButton newReviewButton_businessPanel = new JButton("Aggiungi Recensione");
				newReviewButton_businessPanel.setBounds(10, 162, 153, 23);
				businessPanel.add(newReviewButton_businessPanel);
				newReviewButton_businessPanel.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){
						reviewsPanel_businessPanel.removeAll();
						inputReviewDataPanel(aBusiness);
						reviewsPanel_businessPanel.validate();
						reviewsPanel_businessPanel.repaint();
						reviewsScrollPane_businessPanel.validate();
						reviewsScrollPane_businessPanel.repaint();
					}
				});
			}
		}
		foodvibes.showReviews(aBusiness);
		
		CardLayout cardLayout = (CardLayout)(layeredPane.getLayout());
		cardLayout.show(layeredPane, "businessPanel");
		
		businessPanel.validate();
		businessPanel.repaint();
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		CONTENITORE RECENSIONE
	//-------------------------------------------------------------------------------------------------------------------
	
	public void newReviewPanel(review aReview, Boolean isCreator, business aBusiness) {	
		JPanel foundReviewPanel_businessPanel = new JPanel();
		foundReviewPanel_businessPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		foundReviewPanel_businessPanel.setPreferredSize(new Dimension(570, 150));
		foundReviewPanel_businessPanel.setMaximumSize(new Dimension(570, 150));
		foundReviewPanel_businessPanel.setBounds(30, 40, 280, 50);
		foundReviewPanel_businessPanel.setBackground(new Color(164, 215, 164));
		reviewsPanel_businessPanel.add(foundReviewPanel_businessPanel);
		foundReviewPanel_businessPanel.setLayout(null);
		
		JLabel voteReviewLabel_businessPanel = new JLabel(Float.toString(aReview.getVote()));
		voteReviewLabel_businessPanel.setBounds(10, 10, 89, 14);
		foundReviewPanel_businessPanel.add(voteReviewLabel_businessPanel);
		
		JLabel reviewTitleLabel_businessPanel = new JLabel(aReview.getTitle());
		reviewTitleLabel_businessPanel.setFont(new Font("Calibri", Font.BOLD, 17));
		reviewTitleLabel_businessPanel.setBounds(10, 35, 100, 20);
		foundReviewPanel_businessPanel.add(reviewTitleLabel_businessPanel);
		
		JLabel descriptionReviewLabel_businessPanel = new JLabel("<html><p>" + aReview.getDescription() + "</p></html>");
		descriptionReviewLabel_businessPanel.setBounds(10, 56, 560, 103);
		foundReviewPanel_businessPanel.add(descriptionReviewLabel_businessPanel);
		descriptionReviewLabel_businessPanel.setVerticalAlignment(JLabel.TOP);
		
		JSeparator reviewSeparator_businessPanel = new JSeparator();
		reviewSeparator_businessPanel.setBounds(10, 148, 580, 2);
		foundReviewPanel_businessPanel.add(reviewSeparator_businessPanel);
		
		if(isCreator) {
			JButton modifyButton = new JButton("✎");
			modifyButton.setBounds(460, 4, 51, 29);
			foundReviewPanel_businessPanel.add(modifyButton);
			modifyButton.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){
					reviewsPanel_businessPanel.removeAll();
					editDataPanel(aBusiness, aReview);
					reviewsPanel_businessPanel.validate();
					reviewsPanel_businessPanel.repaint();
					reviewsScrollPane_businessPanel.validate();
					reviewsScrollPane_businessPanel.repaint();
				}
			});
			
			JButton removeButton = new JButton("🗑");
			removeButton.setBounds(511, 4, 51, 29);
			foundReviewPanel_businessPanel.add(removeButton);
			removeButton.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){
					if (JOptionPane.showConfirmDialog(foundReviewPanel_businessPanel, "Vuoi eliminare la recensione?", "Elimina recensione", JOptionPane.YES_NO_OPTION) == 0) {
						reviewsPanel_businessPanel.removeAll();
						foodvibes.removeReview(aBusiness, aReview);
						reviewsPanel_businessPanel.validate();
						reviewsPanel_businessPanel.repaint();
						reviewsScrollPane_businessPanel.validate();
						reviewsScrollPane_businessPanel.repaint();
					}
				}
			});
		} else if(foodvibes.getUser() != null){
			JButton upVoteButton = new JButton("👍 " + aReview.getLikes());
			upVoteButton.setBounds(446, 4, 65, 29);
			foundReviewPanel_businessPanel.add(upVoteButton);
			upVoteButton.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){
					reviewsPanel_businessPanel.removeAll();
					foodvibes.upVoteReview(aBusiness, aReview);
					reviewsPanel_businessPanel.validate();
					reviewsPanel_businessPanel.repaint();
					reviewsScrollPane_businessPanel.validate();
					reviewsScrollPane_businessPanel.repaint();
				}
				
			});
			
			JButton removeButton = new JButton("!");
			removeButton.setBounds(511, 4, 51, 29);
			foundReviewPanel_businessPanel.add(removeButton);
			removeButton.addActionListener(new ActionListener(){  
				public void actionPerformed(ActionEvent e){
					reportReviewPanel(aBusiness, aReview);
				}
			});
		}
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		FINESTRA POP-UP INSERIMENTO NUOVA RECENSIONE
	//-------------------------------------------------------------------------------------------------------------------
	
	public void inputReviewDataPanel(business aBusiness) {
		
		JPanel newReviewPanel = new JPanel();
		newReviewPanel.setPreferredSize(new Dimension(434,240));
		newReviewPanel.setBounds(10, 10, 414, 240);
		newReviewPanel.setLayout(null);
		
		JLabel titleLabel = new JLabel("Titolo");
		titleLabel.setBounds(10, 36, 96, 14);
		newReviewPanel.add(titleLabel);
		
		JTextField titleTextField = new JTextField();
		titleTextField.setBounds(116, 33, 288, 20);
		newReviewPanel.add(titleTextField);
		titleTextField.setColumns(10);
		
		JLabel voteLabel = new JLabel("Voto");
		voteLabel.setBounds(10, 11, 46, 14);
		newReviewPanel.add(voteLabel);
		
        String[] comboBoxOptions = {"1", "2", "3", "4", "5"};
        JComboBox<String> comboBox = new JComboBox<>(comboBoxOptions);
        comboBox.setBounds(116, 8, 288, 20);
        newReviewPanel.add(comboBox);
		
		JLabel descriptionLabel = new JLabel("Descrizione");
		descriptionLabel.setBounds(10, 61, 96, 14);
		newReviewPanel.add(descriptionLabel);
		
		JTextArea descriptionTextArea = new JTextArea();
		descriptionTextArea.setBounds(116, 58, 288, 171);
		newReviewPanel.add(descriptionTextArea);
		descriptionTextArea.setColumns(10);
		descriptionTextArea.setBorder(titleTextField.getBorder());
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);
			
		if (JOptionPane.showConfirmDialog(reviewsPanel_businessPanel, newReviewPanel, "Nuova recensione", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			foodvibes.insertNewReview(aBusiness,titleTextField.getText(),Float.parseFloat(comboBox.getSelectedItem().toString()),descriptionTextArea.getText());
		}else {
			foodvibes.showReviews(aBusiness);
		}
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		FINESTRA POP-UP MODIFICA RECENSIONE
	//-------------------------------------------------------------------------------------------------------------------
	
	public void editDataPanel(business aBusiness, review aReview) {
		
		JPanel newReviewPanel = new JPanel();
		newReviewPanel.setPreferredSize(new Dimension(434,240));
		newReviewPanel.setBounds(10, 10, 414, 240);
		newReviewPanel.setLayout(null);
		
		JLabel titleLabel = new JLabel("Titolo");
		titleLabel.setBounds(10, 36, 96, 14);
		newReviewPanel.add(titleLabel);
		
		JTextField titleTextField = new JTextField();
		titleTextField.setBounds(116, 33, 288, 20);
		newReviewPanel.add(titleTextField);
		titleTextField.setText(aReview.getTitle());
		titleTextField.setColumns(10);
		
		JLabel voteLabel = new JLabel("Voto");
		voteLabel.setBounds(10, 11, 46, 14);
		newReviewPanel.add(voteLabel);
		
        String[] comboBoxOptions = {"1", "2", "3", "4", "5"};
        JComboBox<String> comboBox = new JComboBox<>(comboBoxOptions);
        comboBox.setBounds(116, 8, 288, 20);
        newReviewPanel.add(comboBox);
        comboBox.setSelectedIndex((int)aReview.getVote()-1);
		
		JLabel descriptionLabel = new JLabel("Descrizione");
		descriptionLabel.setBounds(10, 61, 96, 14);
		newReviewPanel.add(descriptionLabel);
		
		JTextArea descriptionTextArea = new JTextArea();
		descriptionTextArea.setBounds(116, 58, 288, 171);
		newReviewPanel.add(descriptionTextArea);
		descriptionTextArea.setColumns(10);
		descriptionTextArea.setBorder(titleTextField.getBorder());
		descriptionTextArea.setLineWrap(true);
		descriptionTextArea.setWrapStyleWord(true);
		descriptionTextArea.setText(aReview.getDescription());
		
		int result = JOptionPane.showConfirmDialog(reviewsPanel_businessPanel, newReviewPanel, "modifica recensione", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);		
		if (result == JOptionPane.OK_OPTION) {
			foodvibes.editReview(titleTextField.getText(),Float.parseFloat(comboBox.getSelectedItem().toString()),descriptionTextArea.getText(),aReview, aBusiness);
		}else {
			foodvibes.showReviews(aBusiness);
		}
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		FINESTRA POP-UP SEGNALAZIONE
	//-------------------------------------------------------------------------------------------------------------------
	
	public void reportReviewPanel(business aBusiness, review aReview) {
		JPanel reportReviewPanel = new JPanel();
        reportReviewPanel.setBounds(154, 53, 394, 215);
        reportReviewPanel.setPreferredSize(new Dimension(350,200));
        reportReviewPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Segnala recensione");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Calibri", Font.BOLD, 25));
        titleLabel.setBounds(70, 29, 203, 52);
        reportReviewPanel.add(titleLabel);

        JLabel descriptionLabel = new JLabel("Scegli il tipo di segnalazione:");
        descriptionLabel.setBounds(92, 90, 180, 22);
        reportReviewPanel.add(descriptionLabel);
        
        String[] comboBoxOptions = {"Volgarità", "Sabotaggio", "Molestie", "Razzismo"};
        JComboBox<String> comboBox = new JComboBox<>(comboBoxOptions);
        comboBox.setBounds(92, 123, 160, 22);
        reportReviewPanel.add(comboBox);


      int result = JOptionPane.showConfirmDialog(reviewsPanel_businessPanel, reportReviewPanel, "Segnala recensione", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
      if (result == JOptionPane.OK_OPTION) {
    	  foodvibes.addReportedReview(aReview, aBusiness, comboBox.getSelectedItem().toString());
		}
		
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		FINESTRA POP-UP MODIFICA ATTIVITÀ
	//-------------------------------------------------------------------------------------------------------------------
	
	public void editBusinessPanel(business aBusiness) {
		
		JPanel editBusinessPanel = new JPanel();
		editBusinessPanel.setPreferredSize(new Dimension(434,240));
		editBusinessPanel.setBounds(10, 10, 414, 240);
		editBusinessPanel.setLayout(null);
		
		JLabel nameLabel = new JLabel("Nome: ");
		nameLabel.setBounds(10, 11, 96, 14);
		editBusinessPanel.add(nameLabel);
		
		JTextField nameTextField = new JTextField();
		nameTextField.setBounds(116, 8, 288, 20);
		editBusinessPanel.add(nameTextField);
		nameTextField.setColumns(10);
		nameTextField.setText(aBusiness.getName());
		
		JLabel addressLabel = new JLabel("Indirizzo: ");
		addressLabel.setBounds(10, 42, 96, 14);
		editBusinessPanel.add(addressLabel);
		
		JTextField addressTextField = new JTextField();
		addressTextField.setBounds(116, 39, 288, 20);
		editBusinessPanel.add(addressTextField);
		addressTextField.setColumns(10);
		addressTextField.setText(aBusiness.getAddress());
		
		JLabel openingHoursLabel = new JLabel("Orari: ");
		openingHoursLabel.setBounds(10, 73, 96, 14);
		editBusinessPanel.add(openingHoursLabel);
		
		JTextField openingHoursTextField = new JTextField();
		openingHoursTextField.setBounds(116, 70, 288, 20);
		editBusinessPanel.add(openingHoursTextField);
		openingHoursTextField.setColumns(10);
		openingHoursTextField.setText(aBusiness.getOpeningHours());
		
		JLabel imageLabel = new JLabel("Immagine: ");
		imageLabel.setBounds(10, 104, 96, 14);
		editBusinessPanel.add(imageLabel);
		
		JTextField imageTextField = new JTextField();
		imageTextField.setBounds(116, 101, 288, 20);
		editBusinessPanel.add(imageTextField);
		imageTextField.setColumns(10);
		imageTextField.setText(aBusiness.getImage());

			
		if (JOptionPane.showConfirmDialog(reviewsPanel_businessPanel, editBusinessPanel, "Modifica informazioni attività", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			foodvibes.editBusinessInfo(aBusiness, nameTextField.getText(), addressTextField.getText(), openingHoursTextField.getText(), imageTextField.getText());
		}
	}
	
	public void foundReport(report aReport) {
		JPanel foundReport = new JPanel();
		foundReport.setAlignmentX(Component.LEFT_ALIGNMENT);
		foundReport.setPreferredSize(new Dimension(580, 150));
		foundReport.setMaximumSize(new Dimension(580, 150));
		foundReport.setBackground(new Color(164, 215, 164));
		foundReport.setBounds(30, 40, 280, 50);
		reportsPanel.add(foundReport);
		foundReport.setLayout(null);
		
		JLabel reviewTitleLabel_businessPanel = new JLabel(aReport.getReview().getTitle());
		reviewTitleLabel_businessPanel.setFont(new Font("Calibri", Font.BOLD, 20));
		reviewTitleLabel_businessPanel.setBounds(10, 11, 130, 25);
		foundReport.add(reviewTitleLabel_businessPanel);
		
		JLabel reportedBy = new JLabel("<html>Segnalato da: <b>" + aReport.getAuthor().getUsername() + "</b> per: <b>" + aReport.getType() + "</b></html>" );
		reportedBy.setFont(new Font("Calibri", Font.PLAIN, 14));
		reportedBy.setBounds(167, 11, 393, 25);
		foundReport.add(reportedBy);
		
		JLabel descriptionReviewLabel_businessPanel = new JLabel("<html><p>" + aReport.getReview().getDescription() + "</p></html>");
		descriptionReviewLabel_businessPanel.setFont(new Font("Calibri", Font.PLAIN, 14));
		descriptionReviewLabel_businessPanel.setBounds(10, 34, 560, 84);
		foundReport.add(descriptionReviewLabel_businessPanel);
		descriptionReviewLabel_businessPanel.setVerticalAlignment(JLabel.TOP);
		
		JSeparator reviewSeparator_businessPanel = new JSeparator();
		reviewSeparator_businessPanel.setBounds(10, 148, 580, 2);
		foundReport.add(reviewSeparator_businessPanel);
		
		
		JButton removeReportedReview = new JButton("Rimuovi recensione");
		removeReportedReview.setBounds(390, 123, 170, 25);
		foundReport.add(removeReportedReview);
		removeReportedReview.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				reportsPanel.removeAll();
				foodvibes.removeReportedReview(aReport);
				foodvibes.showAllReports();
				reportsPanel.validate();
				reportsPanel.repaint();
			}
		});
	}
}
