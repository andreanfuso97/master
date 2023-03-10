package bin;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.*;
import java.io.FilenameFilter;

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
	private JPanel userInfoPanel;
	private JButton logUserButton;
	private JLabel titleLabel_sidebar;
	private JButton registerButton;
	
	public GUI_Frame() {
		
		//-------------------------------------------------------------------------------------------------------------------
		//		INIZIALIZZAZIONE FRAME
		//-------------------------------------------------------------------------------------------------------------------
		
		setResizable(false);
		setTitle("FoodVibes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 850, 490);
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(204, 255, 204));
		layeredPane.setBounds(217, 0, 617, 451);
		contentPane.add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		cardLayout = (CardLayout)(layeredPane.getLayout());
		
		//-------------------------------------------------------------------------------------------------------------------
		//		BARRA LATERALE
		//-------------------------------------------------------------------------------------------------------------------
		
		JPanel SideBar = new JPanel();
		SideBar.setForeground(new Color(134, 249, 163));
		SideBar.setBackground(new Color(10, 184, 114));
		SideBar.setBounds(0, 0, 217, 451);
		contentPane.add(SideBar);
		SideBar.setLayout(null);
		
		titleLabel_sidebar = new JLabel("Benvenuto");
		titleLabel_sidebar.setForeground(new Color(255, 245, 238));
		titleLabel_sidebar.setBackground(new Color(240, 248, 255));
		titleLabel_sidebar.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel_sidebar.setBounds(10, 0, 192, 71);
		titleLabel_sidebar.setFont(new Font("Calibri", Font.BOLD, 25));
		SideBar.add(titleLabel_sidebar);
		
		JButton searchButton_sidebar = new JButton("Cerca");
		searchButton_sidebar.setForeground(new Color(255, 245, 238));
		searchButton_sidebar.setBackground(new Color(20, 131, 108));
		searchButton_sidebar.setFont(new Font("Calibri", Font.BOLD, 20));
		searchButton_sidebar.setBounds(0, 72, 217, 40);
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
		
		JButton registerBusinessButton_sidebar = new JButton("Registra attivit??");
		registerBusinessButton_sidebar.setForeground(new Color(255, 245, 238));
		registerBusinessButton_sidebar.setBackground(new Color(20, 131, 108));
		registerBusinessButton_sidebar.setFont(new Font("Calibri", Font.BOLD, 20));
		registerBusinessButton_sidebar.setBounds(0, 111, 217, 40);
		SideBar.add(registerBusinessButton_sidebar);
		registerBusinessButton_sidebar.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				if(foodvibes.getUser().getState() instanceof userLoggedOut) {
					JOptionPane.showMessageDialog(contentPane, "Effettua il login per poter registrare una attivit??.");
				}else {
					cardLayout.show(layeredPane, "registerBusinessPanel");					
				}
			}
		});
		
		reportList = new JButton("Segnalazioni");
		reportList.setForeground(new Color(255, 245, 238));
		reportList.setFont(new Font("Calibri", Font.BOLD, 20));
		reportList.setBackground(new Color(20, 131, 108));
		reportList.setBounds(0, 151, 217, 40);
		SideBar.add(reportList);
		reportList.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				cardLayout.show(layeredPane, "reportListPanel");
				reportsPanel.removeAll();
				foodvibes.showAllReports();
				reportsPanel.validate();
				reportsPanel.repaint();
			}
		});
		reportList.setVisible(false);

		registerButton = new JButton("Registrazione");
		registerButton.setForeground(new Color(255, 245, 238));
		registerButton.setFont(new Font("Calibri", Font.BOLD, 20));
		registerButton.setBackground(new Color(20, 131, 108));
		registerButton.setBounds(0, 372, 217, 40);
		SideBar.add(registerButton);
		registerButton.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent e){
				foodvibes.getUser().getState().thirdSidebarButton();
				}
			}
		);
		logUserButton = new JButton("Login");
		logUserButton.setForeground(new Color(255, 245, 238));
		logUserButton.setFont(new Font("Calibri", Font.BOLD, 20));
		logUserButton.setBackground(new Color(20, 131, 108));
		logUserButton.setBounds(0, 411, 217, 40);
		SideBar.add(logUserButton);
		logUserButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				foodvibes.getUser().getState().logButton();
			}
		});
		
		//-------------------------------------------------------------------------------------------------------------------
		//		FINESTRA DI RICERCA
		//-------------------------------------------------------------------------------------------------------------------
		
		searchBusinessPanel = new JPanel();
		searchBusinessPanel.setBackground(new Color(255, 245, 238));
		searchBusinessPanel.setLayout(null);
		layeredPane.add(searchBusinessPanel, "searchBusinessPanel");
		
		JLabel title_searchBusiness = new JLabel("Cerca Attivit??");
		title_searchBusiness.setBackground(SystemColor.inactiveCaptionBorder);
		title_searchBusiness.setFont(new Font("Calibri", Font.BOLD, 20));
		title_searchBusiness.setBounds(10, 11, 198, 25);
		searchBusinessPanel.add(title_searchBusiness);
		
		JTextField searchTextArea_searchBusiness;
		searchTextArea_searchBusiness = new JTextField();
		searchTextArea_searchBusiness.setColumns(10);
		searchTextArea_searchBusiness.setBounds(10, 47, 361, 25);
		searchBusinessPanel.add(searchTextArea_searchBusiness);
		
		JButton button_searchBusiness = new JButton("???????");
		button_searchBusiness.setForeground(new Color(255, 245, 238));
		button_searchBusiness.setBackground(new Color(10, 184, 114));
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
		foundBusinessPanel_searchBusiness.setBorder(null);
		foundBusinessPanel_searchBusiness.setBackground(new Color(255, 245, 238));
		foundBusinessPanel_searchBusiness.setBounds(10, 96, 578, 330);

		foundBusinessScrollPane_searchBusiness = new JScrollPane(foundBusinessPanel_searchBusiness);
		foundBusinessPanel_searchBusiness.setLayout(new BoxLayout(foundBusinessPanel_searchBusiness, BoxLayout.Y_AXIS));
		foundBusinessScrollPane_searchBusiness.setEnabled(false);
		foundBusinessScrollPane_searchBusiness.setBounds(10, 96, 578, 330);
		searchBusinessPanel.add(BorderLayout.CENTER, foundBusinessScrollPane_searchBusiness);
		
		//-------------------------------------------------------------------------------------------------------------------
		//		FINESTRA DI REGISTRAZIONE ATTIVIT??
		//-------------------------------------------------------------------------------------------------------------------
		
		JPanel registerBusinessPanel = new JPanel();
		registerBusinessPanel.setBackground(new Color(255, 245, 238));
		registerBusinessPanel.setLayout(null);
		layeredPane.add(registerBusinessPanel, "registerBusinessPanel");
		
		JLabel titleLabel_registerBusiness = new JLabel("Registra la tua Attivit??");
		titleLabel_registerBusiness.setFont(new Font("Calibri", Font.BOLD, 20));
		titleLabel_registerBusiness.setBounds(10, 11, 187, 25);
		registerBusinessPanel.add(titleLabel_registerBusiness);
		
		JLabel descriptionLabel_registerBusiness = new JLabel("<html>Fornisci le informazioni necessarie per registrare la tua attivit?? <br>  su FoodVibes</html>");
		descriptionLabel_registerBusiness.setVerticalAlignment(SwingConstants.TOP);
		descriptionLabel_registerBusiness.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionLabel_registerBusiness.setFont(new Font("Calibri", Font.PLAIN, 12));
		descriptionLabel_registerBusiness.setBounds(10, 48, 347, 30);
		registerBusinessPanel.add(descriptionLabel_registerBusiness);
		
		JSeparator separator_registerBusiness = new JSeparator();
		separator_registerBusiness.setForeground(new Color(0, 102, 0));
		separator_registerBusiness.setBounds(10, 89, 404, 2);
		registerBusinessPanel.add(separator_registerBusiness);
		
		JLabel newNameLabel_registerBusiness = new JLabel("Nome Attivit??:");
		newNameLabel_registerBusiness.setFont(new Font("Calibri", Font.BOLD, 14));
		newNameLabel_registerBusiness.setBounds(10, 118, 103, 15);
		registerBusinessPanel.add(newNameLabel_registerBusiness);
		
		JTextArea newNameTextArea_registerBusiness = new JTextArea();
		newNameTextArea_registerBusiness.setBounds(146, 113, 200, 22);
		registerBusinessPanel.add(newNameTextArea_registerBusiness);
		
		JLabel newAddressLabel_registerBusiness = new JLabel("Indirizzo:");
		newAddressLabel_registerBusiness.setFont(new Font("Calibri", Font.BOLD, 14));
		newAddressLabel_registerBusiness.setBounds(10, 156, 90, 15);
		registerBusinessPanel.add(newAddressLabel_registerBusiness);
		
		JTextArea newAddressTextArea_registerBusiness = new JTextArea();
		newAddressTextArea_registerBusiness.setBounds(146, 149, 200, 22);
		registerBusinessPanel.add(newAddressTextArea_registerBusiness);
		
		JLabel newOpeningHoursLabel_registerBusiness = new JLabel("Orari di apertura:");
		newOpeningHoursLabel_registerBusiness.setFont(new Font("Calibri", Font.BOLD, 14));
		newOpeningHoursLabel_registerBusiness.setBounds(10, 193, 103, 15);
		registerBusinessPanel.add(newOpeningHoursLabel_registerBusiness);
		
		JTextArea newOpeningHoursTextArea_registerBusiness = new JTextArea();
		newOpeningHoursTextArea_registerBusiness.setBounds(146, 186, 200, 22);
		registerBusinessPanel.add(newOpeningHoursTextArea_registerBusiness);
		
		JLabel newImageLabel_registerBusiness = new JLabel("Immagine:");
		newImageLabel_registerBusiness.setFont(new Font("Calibri", Font.BOLD, 14));
		newImageLabel_registerBusiness.setBounds(10, 233, 103, 15);
		registerBusinessPanel.add(newImageLabel_registerBusiness);
		

		JTextArea imageFilePath = new JTextArea();
		
		JButton selectedImage = new JButton("Scegli Immagine");
		selectedImage.setForeground(new Color(255, 245, 238));
		selectedImage.setBackground(new Color(20, 131, 108));
		selectedImage.setFont(new Font("Calibri", Font.PLAIN, 14));
		selectedImage.setBounds(146, 226, 130, 22);
		registerBusinessPanel.add(selectedImage);
		selectedImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "\\Images\\"));
				int res = fileChooser.showOpenDialog(null);
				
				if(res == JFileChooser.APPROVE_OPTION) {
					File imageFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
					System.out.println(imageFile);
					
					imageFilePath.setText(imageFile.toString());
					
				}
			}
		});
		
		JButton btnRegistra = new JButton("Registra");
		btnRegistra.setForeground(new Color(255, 245, 238));
		btnRegistra.setBackground(new Color(20, 131, 108));
		btnRegistra.setVerticalAlignment(SwingConstants.BOTTOM);
		btnRegistra.setFont(new Font("Calibri", Font.BOLD, 20));
		btnRegistra.setBounds(10, 293, 200, 33);
		registerBusinessPanel.add(btnRegistra);
		btnRegistra.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				foodvibes.insertBusinessInfo(newNameTextArea_registerBusiness.getText(), newAddressTextArea_registerBusiness.getText(), newOpeningHoursTextArea_registerBusiness.getText(), imageFilePath.getText());
			}
		});
		
		//-------------------------------------------------------------------------------------------------------------------
		//		FINESTRA ATTIVIT??
		//-------------------------------------------------------------------------------------------------------------------
		
		businessPanel = new JPanel();
		businessPanel.setBackground(new Color(255, 245, 238));
		layeredPane.add(businessPanel, "businessPanel");
		businessPanel.setLayout(null);
		
		//-------------------------------------------------------------------------------------------------------------------
		//		FINESTRA LOGIN
		//-------------------------------------------------------------------------------------------------------------------
		
		JPanel loginPanel = new JPanel();
		loginPanel.setBackground(new Color(255, 245, 238));
		layeredPane.add(loginPanel, "loginPanel");
		loginPanel.setLayout(null);
		
		JLabel titleLabel_login = new JLabel("Effettua il login");
		titleLabel_login.setFont(new Font("Calibri:", Font.BOLD, 25));
		titleLabel_login.setBounds(213, 11, 200, 43);
		loginPanel.add(titleLabel_login);
		
		JLabel userNameLabel_login = new JLabel("Nome utente:");
		userNameLabel_login.setFont(new Font("Calibri", Font.PLAIN, 15));
		userNameLabel_login.setBounds(108, 73, 107, 24);
		loginPanel.add(userNameLabel_login);
		
		JTextField userNameTextField_login = new JTextField();
		userNameTextField_login.setBounds(272, 71, 241, 24);
		loginPanel.add(userNameTextField_login);
		userNameTextField_login.setColumns(10);
		
		JLabel passwordLabel_login = new JLabel("Password:");
		passwordLabel_login.setFont(new Font("Calibri", Font.PLAIN, 15));
		passwordLabel_login.setBounds(108, 108, 107, 24);
		loginPanel.add(passwordLabel_login);
		
		JPasswordField passwordTextField_login = new JPasswordField();
		passwordTextField_login.setColumns(10);
		passwordTextField_login.setBounds(272, 106, 241, 24);
		loginPanel.add(passwordTextField_login);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBackground(new Color(10, 184, 114));
		loginButton.setForeground(new Color(255, 245, 238));
		loginButton.setFont(new Font("Calibri", Font.BOLD, 18));
		loginButton.setBounds(272, 158, 97, 32);
		loginPanel.add(loginButton);
		loginButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {
				if(foodvibes.login(userNameTextField_login.getText(), passwordTextField_login.getText())) {
					user currentUser = foodvibes.getUser();
					if(currentUser instanceof admin) {
						currentUser.setUserState(new adminLogged(currentUser));
						reportList.setVisible(true);
					} else {
						currentUser.setUserState(new userLogged(currentUser));
					}
					registerButton.setText("Profilo");
					titleLabel_sidebar.setText("<html>Benvenuto<br>" + userNameTextField_login.getText() + "</html>");
					logUserButton.setText("Logout");
					cardLayout.show(layeredPane, "searchBusinessPanel");
				}
			}
		});
		
		//-------------------------------------------------------------------------------------------------------------------
		//		FINESTRA REGISTRAZIONE NUOVO ACCOUNT
		//-------------------------------------------------------------------------------------------------------------------
		
		JPanel registerPanel = new JPanel();
		registerPanel.setBackground(new Color(255, 245, 238));
		layeredPane.add(registerPanel, "registerPanel");
		registerPanel.setLayout(null);
		
		JLabel titleLabel_register = new JLabel("Inserisci i tuoi dati");
		titleLabel_register.setFont(new Font("Calibri", Font.BOLD, 25));
		titleLabel_register.setBounds(247, 11, 190, 43);
		registerPanel.add(titleLabel_register);
		
		JLabel userNameLabel_register = new JLabel("Nome:");
		userNameLabel_register.setFont(new Font("Calibri", Font.BOLD, 15));
		userNameLabel_register.setBounds(108, 73, 107, 24);
		registerPanel.add(userNameLabel_register);
		
		JTextField nameTextField_register = new JTextField();
		nameTextField_register.setColumns(10);
		nameTextField_register.setBounds(247, 71, 241, 24);
		registerPanel.add(nameTextField_register);
		
		JLabel surnameLabel_register = new JLabel("Cognome:");
		surnameLabel_register.setFont(new Font("Calibri", Font.BOLD, 15));
		surnameLabel_register.setBounds(108, 108, 107, 24);
		registerPanel.add(surnameLabel_register);
		
		JTextField surnameTextField_register = new JTextField();
		surnameTextField_register.setColumns(10);
		surnameTextField_register.setBounds(247, 106, 241, 24);
		registerPanel.add(surnameTextField_register);
		
		JLabel nationalityLabel_register = new JLabel("Nazionalit??:");
		nationalityLabel_register.setFont(new Font("Calibri", Font.BOLD, 15));
		nationalityLabel_register.setBounds(108, 143, 107, 24);
		registerPanel.add(nationalityLabel_register);
		
		JTextField nationalityTextField_register = new JTextField();
		nationalityTextField_register.setColumns(10);
		nationalityTextField_register.setBounds(247, 141, 241, 24);
		registerPanel.add(nationalityTextField_register);
		
		JLabel birthdateLabel_register = new JLabel("Data di nascita:");
		birthdateLabel_register.setFont(new Font("Calibri", Font.BOLD, 15));
		birthdateLabel_register.setBounds(108, 178, 107, 24);
		registerPanel.add(birthdateLabel_register);
		
		String[] monthDays = new String[31];
		for(int i = 0; i < 31; i++) {
			monthDays[i] = Integer.toString(i+1);
		}
		
		JComboBox birthDay = new JComboBox(monthDays);
		birthDay.setForeground(new Color(255, 245, 238));
		birthDay.setBackground(new Color(10, 184, 114));
		birthDay.setFont(new Font("Calibri", Font.PLAIN, 15));
		birthDay.setBounds(247, 178, 50, 24);
		registerPanel.add(birthDay);
		
		
		String[] month = {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novemrbe", "Dicembre"};
		ArrayList<String> thirtyDaysMonths = new ArrayList<>();
		thirtyDaysMonths.add("Aprile");
		thirtyDaysMonths.add("Giugno");
		thirtyDaysMonths.add("Settembre");
		thirtyDaysMonths.add("Novembre");
		JComboBox birthMonth = new JComboBox(month);
		birthMonth.setForeground(new Color(255, 245, 238));
		birthMonth.setBackground(new Color(10, 184, 114));
		birthMonth.setFont(new Font("Calibri", Font.PLAIN, 15));
		birthMonth.setBounds(305, 178, 100, 24);
		registerPanel.add(birthMonth);
		birthMonth.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if(birthMonth.getSelectedItem().toString().equals("Febbraio") && 
				   Integer.parseInt(birthDay.getSelectedItem().toString())  > 28) {
					birthDay.setSelectedIndex(27);
					registerPanel.validate();
					registerPanel.repaint();				
					} else if (thirtyDaysMonths.contains(birthMonth.getSelectedItem().toString()) &&
							Integer.parseInt(birthDay.getSelectedItem().toString())  > 30) {
						birthDay.setSelectedIndex(29);
						registerPanel.validate();
						registerPanel.repaint();	
					}
			}
		});
		
		String[] years = new String[100];
		int j = 2023;
		for(int i = 0; i < 100; i++) {
			years[i] = Integer.toString(j);
			j--;
		}
		
		JComboBox birthYear = new JComboBox(years);
		birthYear.setForeground(new Color(255, 245, 238));
		birthYear.setBackground(new Color(10, 184, 114));
		birthYear.setFont(new Font("Calibri", Font.PLAIN, 15));
		birthYear.setBounds(415, 178, 70, 24);
		registerPanel.add(birthYear);
		
		JLabel emailLabel_register = new JLabel("Email:");
		emailLabel_register.setFont(new Font("Calibri", Font.BOLD, 15));
		emailLabel_register.setBounds(108, 213, 107, 24);
		registerPanel.add(emailLabel_register);
		
		JTextField emailTextField_register = new JTextField();
		emailTextField_register.setColumns(10);
		emailTextField_register.setBounds(247, 213, 241, 24);
		registerPanel.add(emailTextField_register);
		
		JLabel usernameLabel_register = new JLabel("Username:");
		usernameLabel_register.setFont(new Font("Calibri", Font.BOLD, 15));
		usernameLabel_register.setBounds(108, 248, 107, 24);
		registerPanel.add(usernameLabel_register);
		
		JTextField usernameTextField_register = new JTextField();
		usernameTextField_register.setColumns(10);
		usernameTextField_register.setBounds(247, 248, 241, 24);
		registerPanel.add(usernameTextField_register);
		
		JLabel passwordLabel_register = new JLabel("Password:");
		passwordLabel_register.setFont(new Font("Calibri", Font.BOLD, 15));
		passwordLabel_register.setBounds(108, 283, 107, 24);
		registerPanel.add(passwordLabel_register);
		
		JPasswordField passwordTextField_register = new JPasswordField();
		passwordTextField_register.setColumns(10);
		passwordTextField_register.setBounds(247, 283, 241, 24);
		registerPanel.add(passwordTextField_register);
		
		JButton registerButton_register = new JButton("Registrati");
		registerButton_register.setBackground(new Color(10, 184, 114));
		registerButton_register.setForeground(new Color(255, 245, 238));
		registerButton_register.setFont(new Font("Calibri", Font.BOLD, 18));
		registerButton_register.setBounds(247, 331, 115, 35);
		registerPanel.add(registerButton_register);
		registerButton_register.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {
				String birthdate = birthDay.getSelectedItem() + " " + birthMonth.getSelectedItem() + " " + birthYear.getSelectedItem();
				System.out.println(birthdate);
				if(foodvibes.registerNewUser(nameTextField_register.getText(), surnameTextField_register.getText(), nationalityTextField_register.getText(), birthdate, emailTextField_register.getText(), usernameTextField_register.getText(), passwordTextField_register.getText())) {
					user currentUser = foodvibes.getUser();
					if(currentUser instanceof admin) {
						currentUser.setUserState(new adminLogged(currentUser));
						reportList.setVisible(true);
					} else {
						currentUser.setUserState(new userLogged(currentUser));
					}
					cardLayout.show(layeredPane, "searchBusinessPanel");
					titleLabel_sidebar.setText("<html>Benvenuto<br>" + usernameTextField_register.getText() + "</html>");
					logUserButton.setText("Logout");
					registerButton.setText("Profilo");
					
				}
			}
		});
		
		//-------------------------------------------------------------------------------------------------------------------
		//		FINESTRA LISTA REPORTS
		//-------------------------------------------------------------------------------------------------------------------
		
		JPanel reportListPanel = new JPanel();
		reportListPanel.setBackground(new Color(255, 245, 238));
		layeredPane.add(reportListPanel, "reportListPanel");
		reportListPanel.setLayout(null);
		
		JLabel titlereportList = new JLabel("Recensioni Segnalate");
		titlereportList.setBackground(new Color(255, 245, 238));
		titlereportList.setHorizontalAlignment(SwingConstants.CENTER);
		titlereportList.setFont(new Font("Calibri", Font.BOLD, 25));
		titlereportList.setBounds(10, 11, 582, 40);
		reportListPanel.add(titlereportList);
		
		JScrollPane reportListScrollPane = new JScrollPane();
		reportListScrollPane.setBackground(new Color(255, 245, 238));
		reportListScrollPane.setBounds(10, 50, 580, 380);
		reportListScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		reportListPanel.add(reportListScrollPane);
		
		reportsPanel = new JPanel();
		reportsPanel.setBackground(new Color(255, 245, 238));
		reportListScrollPane.setViewportView(reportsPanel);
		reportsPanel.setLayout(new BoxLayout(reportsPanel, BoxLayout.Y_AXIS));
		
		//-------------------------------------------------------------------------------------------------------------------
		//		FINESTRA REGISTRAZIONE NUOVO ACCOUNT
		//-------------------------------------------------------------------------------------------------------------------
		
		userInfoPanel = new JPanel();
		userInfoPanel.setBackground(new Color(255, 245, 238));
		layeredPane.add(userInfoPanel, "userInfoPanel");
		userInfoPanel.setLayout(null);
	
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		CONTENITORE ATTIVIT?? TROVATE
	//-------------------------------------------------------------------------------------------------------------------
	
	public void newSearchResult(business foundBusiness) {
		JPanel searchResultPanel = new JPanel();
		searchResultPanel.setBounds(30, 40, 300, 50);
		foundBusinessPanel_searchBusiness.add(searchResultPanel);
		searchResultPanel.setMaximumSize(new Dimension(570, 70));
		searchResultPanel.setBackground(new Color(255, 245, 238));
		searchResultPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 10, 5, 5);
		gbc.fill = GridBagConstraints.BOTH;
		JLabel searchedBusinessVoteLabel_searchResultPanel = new JLabel(Float.toString(foundBusiness.getAvgVote()), SwingConstants.CENTER);
		searchedBusinessVoteLabel_searchResultPanel.setBackground(new Color(10, 184, 114));
		searchedBusinessVoteLabel_searchResultPanel.setPreferredSize(new Dimension(40, 40));
		searchedBusinessVoteLabel_searchResultPanel.setOpaque(true);
		searchedBusinessVoteLabel_searchResultPanel.setFont(new Font("Calibri", Font.BOLD, 20));
		searchedBusinessVoteLabel_searchResultPanel.setBounds(10, 11, 40, 40);			
		searchResultPanel.add(searchedBusinessVoteLabel_searchResultPanel, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.weightx = 0.4;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(5, 5, 5, 5);
		JLabel searchedBusinessNameLabel_searchResultPanel = new JLabel(foundBusiness.getName(), SwingConstants.LEFT);
		searchedBusinessNameLabel_searchResultPanel.setFont(new Font("Calibri", Font.BOLD, 20));
		searchedBusinessNameLabel_searchResultPanel.setBounds(10, 11, 198, 25);	
		searchResultPanel.add(searchedBusinessNameLabel_searchResultPanel, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.insets = new Insets(5, 5, 5, 10);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		JButton searchedBusinessButton_searchResultPanel = new JButton("Seleziona");
		searchedBusinessButton_searchResultPanel.setForeground(new Color(255, 245, 238));
		searchedBusinessButton_searchResultPanel.setBackground(new Color(10, 184, 114));
		searchedBusinessButton_searchResultPanel.setFont(new Font("Calibri", Font.BOLD, 16));
		searchedBusinessButton_searchResultPanel.setPreferredSize(new Dimension(150, 40));
		searchResultPanel.add(searchedBusinessButton_searchResultPanel, gbc);

		searchedBusinessButton_searchResultPanel.addActionListener(new ActionListener(){						
			public void actionPerformed(ActionEvent e){ 
				foodvibes.showBusinessInfo(foundBusiness);
				newBusinessPanel(foundBusiness);
			}
		});
		
		if(foundBusiness.getTier() != businessTiers.NONE) {
			JPanel tierPanel = new JPanel();
			tierPanel.setBounds(30, 40, 300, 50);
			foundBusinessPanel_searchBusiness.add(tierPanel);
			tierPanel.setMaximumSize(new Dimension(578, 20));
			tierPanel.setBackground(new Color(204, 255, 204));
			tierPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));

			if(foundBusiness == foodvibes.getBestBusiness()) {
				gbc.gridx = 0;
				gbc.gridy = 1;
				gbc.weightx = 0.1;
				gbc.anchor = GridBagConstraints.WEST;
				gbc.insets = new Insets(5, 10, 5, 10);
				JLabel bestLabel = new JLabel("BEST ACTIVITY");
				bestLabel.setFont(new Font("Calibri", Font.BOLD, 16));
				bestLabel.setForeground(Color.blue);
				searchResultPanel.add(bestLabel, gbc);
			}
			
			gbc.gridx = 1;
			gbc.gridy = 1;
			gbc.weightx = 0.1;
			gbc.anchor = GridBagConstraints.WEST;
			gbc.insets = new Insets(5, 5, 5, 10);
			JLabel tierLabel = new JLabel("  " + foundBusiness.getTier().toString() + "  ");
			tierLabel.setFont(new Font("Calibri", Font.BOLD, 14));
			searchResultPanel.add(tierLabel, gbc);
		}
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		FINESTRA ATTIVIT??
	//-------------------------------------------------------------------------------------------------------------------
	
	public void newBusinessPanel(business aBusiness) {
		businessPanel.removeAll();
		
		JLabel nameLabel_businessPanel = new JLabel(aBusiness.getName());
		nameLabel_businessPanel.setFont(new Font("Calibri", Font.BOLD, 30));
		nameLabel_businessPanel.setBounds(10, 10, 290, 43);
		businessPanel.add(nameLabel_businessPanel);
		
		JLabel businessAvgVote = new JLabel(Float.toString(aBusiness.getAvgVote()), SwingConstants.CENTER);
		businessAvgVote.setBackground(new Color(10, 184, 114));
		businessAvgVote.setOpaque(true);
		businessAvgVote.setFont(new Font("Calibri", Font.BOLD, 16));
		businessAvgVote.setBounds(10, 44, 38, 38);
		businessPanel.add(businessAvgVote);
		
		JLabel ownerLabel = new JLabel("<html>Proprietario: <b>" + aBusiness.getOwner().getUsername() + "</b></html>", SwingConstants.CENTER);
		ownerLabel.setFont(new Font("Calibri", Font.PLAIN, 15));
		ownerLabel.setBounds(50, 34, 150, 32);
		businessPanel.add(ownerLabel);
		
		JLabel addressLabel_businessPanel = new JLabel("<html>Indirizzo: <br> <b>" + aBusiness.getAddress() + "</b></html>");
		addressLabel_businessPanel.setFont(new Font("Calibri", Font.PLAIN, 15));
		addressLabel_businessPanel.setBounds(10, 72, 291, 60);
		businessPanel.add(addressLabel_businessPanel);
		
		
		JLabel openingHours_businessPanel = new JLabel("<html>Orari di apertura: <br> <b>" + aBusiness.getOpeningHours() + "</b></html>");
		openingHours_businessPanel.setFont(new Font("Calibri", Font.PLAIN, 15));
		openingHours_businessPanel.setBounds(10, 111, 291, 60);
		businessPanel.add(openingHours_businessPanel);
		
		ImageIcon businessIcon = new ImageIcon(aBusiness.getImage());
		Image businessImage = businessIcon.getImage();
		Image modImage = businessImage.getScaledInstance(100, 60, java.awt.Image.SCALE_SMOOTH);
		businessIcon = new ImageIcon(businessImage);
		JLabel imageLabel_businessPanel = new JLabel();
		imageLabel_businessPanel.setIcon(businessIcon);
		imageLabel_businessPanel.setBounds(310, 50, 280, 140);
		businessPanel.add(imageLabel_businessPanel);
		
		reviewsScrollPane_businessPanel = new JScrollPane();
		reviewsScrollPane_businessPanel.setBounds(10, 196, 580, 234);
		reviewsScrollPane_businessPanel.setBackground(new Color(255, 245, 238));
		reviewsScrollPane_businessPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		businessPanel.add(reviewsScrollPane_businessPanel);
		
		reviewsPanel_businessPanel = new JPanel();
		reviewsPanel_businessPanel.setBackground(new Color(255, 245, 238));
		reviewsScrollPane_businessPanel.setViewportView(reviewsPanel_businessPanel);
		reviewsPanel_businessPanel.setLayout(new BoxLayout(reviewsPanel_businessPanel, BoxLayout.Y_AXIS));
		
		if(!(foodvibes.getUser().getState() instanceof userLoggedOut)) {
			if(foodvibes.getUser() == aBusiness.getOwner()) {
				JButton editBusinessButton_businessPanel = new JButton("Modifica");
				editBusinessButton_businessPanel.setBackground(new Color(10, 184, 114));
				editBusinessButton_businessPanel.setForeground(new Color(255, 245, 238));
				editBusinessButton_businessPanel.setBounds(400, 20, 90, 20);
				businessPanel.add(editBusinessButton_businessPanel);
				editBusinessButton_businessPanel.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){
						editBusinessPanel(aBusiness);
						newBusinessPanel(aBusiness);
					}
				});
				
				JButton removeBusinessButton_businessPanel = new JButton("Rimuovi");
				removeBusinessButton_businessPanel.setBackground(new Color(10, 184, 114));
				removeBusinessButton_businessPanel.setForeground(new Color(255, 245, 238));
				removeBusinessButton_businessPanel.setBounds(490, 20, 90, 20);
				businessPanel.add(removeBusinessButton_businessPanel);
				removeBusinessButton_businessPanel.addActionListener(new ActionListener(){  
					public void actionPerformed(ActionEvent e){
						if (JOptionPane.showConfirmDialog(businessPanel, "Vuoi eliminare l'attivit???", "Elimina attivit??", JOptionPane.YES_NO_OPTION) == 0) {
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
				newReviewButton_businessPanel.setForeground(new Color(255, 245, 238));
				newReviewButton_businessPanel.setBackground(new Color(10, 184, 114));
				newReviewButton_businessPanel.setBounds(10, 170, 160, 23);
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
		foundReviewPanel_businessPanel.setBackground(new Color(255, 245, 238));
		foundReviewPanel_businessPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		foundReviewPanel_businessPanel.setPreferredSize(new Dimension(570, 150));
		foundReviewPanel_businessPanel.setMaximumSize(new Dimension(570, 150));
		foundReviewPanel_businessPanel.setBounds(30, 50, 280, 50);
		reviewsPanel_businessPanel.add(foundReviewPanel_businessPanel);
		foundReviewPanel_businessPanel.setLayout(null);
		
		JLabel voteReviewLabel_businessPanel = new JLabel(Float.toString(aReview.getVote()));
		voteReviewLabel_businessPanel.setBounds(10, 10, 89, 14);
		voteReviewLabel_businessPanel.setVerticalAlignment(JLabel.CENTER);
		foundReviewPanel_businessPanel.add(voteReviewLabel_businessPanel);
		
		JLabel authorLabel = new JLabel("<html>scritta da: <b>" + aReview.getUser().getUsername() + "</b></html>");
		authorLabel.setFont(new Font("Calibri", Font.PLAIN, 14));
		authorLabel.setBounds(40, 10, 200, 14);
		foundReviewPanel_businessPanel.add(authorLabel);
		authorLabel.setVerticalAlignment(JLabel.CENTER);
		
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
			JButton modifyButton = new JButton("???");
			modifyButton.setBackground(new Color(10, 184, 114));
			modifyButton.setForeground(new Color(255, 245, 238));
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
			
			JButton removeButton = new JButton("????");
			removeButton.setBackground(new Color(10, 184, 114));
			removeButton.setForeground(new Color(255, 245, 238));
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
		} else if(!(foodvibes.getUser().getState() instanceof userLoggedOut)){
			JButton upVoteButton = new JButton("???? " + aReview.getLikes());
			upVoteButton.setBackground(new Color(10, 184, 114));
			upVoteButton.setForeground(new Color(255, 245, 238));
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
			removeButton.setBackground(new Color(10, 184, 114));
			removeButton.setForeground(new Color(255, 245, 238));
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
		} else {
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
        
        String[] comboBoxOptions = {"Volgarit??", "Sabotaggio", "Molestie", "Razzismo"};
        JComboBox<String> comboBox = new JComboBox<>(comboBoxOptions);
        comboBox.setBounds(92, 123, 160, 22);
        reportReviewPanel.add(comboBox);


      int result = JOptionPane.showConfirmDialog(reviewsPanel_businessPanel, reportReviewPanel, "Segnala recensione", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
      if (result == JOptionPane.OK_OPTION) {
    	  foodvibes.addReportedReview(aReview, aBusiness, comboBox.getSelectedItem().toString());
		}
		
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		FINESTRA POP-UP MODIFICA ATTIVIT??
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
		
		JTextArea imageFilePath = new JTextArea();
		
		JButton selectedImage = new JButton("Scegli Immagine");
		selectedImage.setForeground(new Color(255, 245, 238));
		selectedImage.setBackground(new Color(20, 131, 108));
		selectedImage.setFont(new Font("Calibri", Font.PLAIN, 14));
		selectedImage.setBounds(116, 104, 130, 14);
		editBusinessPanel.add(selectedImage);
		selectedImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir") + "\\Images\\"));
				int res = fileChooser.showOpenDialog(null);
				
				if(res == JFileChooser.APPROVE_OPTION) {
					File imageFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
					System.out.println(imageFile);
					
					imageFilePath.setText(imageFile.toString());
					
				}
			}
		});
			
		if (JOptionPane.showConfirmDialog(reviewsPanel_businessPanel, editBusinessPanel, "Modifica informazioni attivit??", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
			foodvibes.editBusinessInfo(aBusiness, nameTextField.getText(), addressTextField.getText(), openingHoursTextField.getText(), imageFilePath.getText());
			System.out.println(aBusiness.getImage());
			foundBusinessPanel_searchBusiness.validate();
			foundBusinessPanel_searchBusiness.repaint();
			foundBusinessScrollPane_searchBusiness.validate();
			foundBusinessScrollPane_searchBusiness.repaint();
			foodvibes.showBusinessInfo(aBusiness);
		}
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		CONTENITORE PER REPORT
	//-------------------------------------------------------------------------------------------------------------------
	
	public void foundReport(report aReport) {
		JPanel foundReport = new JPanel();
		foundReport.setBackground(new Color(255, 245, 238));
		foundReport.setAlignmentX(Component.LEFT_ALIGNMENT);
		foundReport.setPreferredSize(new Dimension(580, 150));
		foundReport.setMaximumSize(new Dimension(580, 150));
		foundReport.setBounds(30, 40, 280, 50);
		reportsPanel.add(foundReport);
		foundReport.setLayout(null);
		
		JLabel reviewTitleLabel_businessPanel = new JLabel(aReport.getReview().getTitle());
		reviewTitleLabel_businessPanel.setFont(new Font("Calibri", Font.BOLD, 20));
		reviewTitleLabel_businessPanel.setBounds(10, 11, 130, 20);
		foundReport.add(reviewTitleLabel_businessPanel);
		
		JLabel descriptionReviewLabel_businessPanel = new JLabel("<html><p>" + aReport.getReview().getDescription() + "</p></html>");
		descriptionReviewLabel_businessPanel.setFont(new Font("Calibri", Font.PLAIN, 14));
		descriptionReviewLabel_businessPanel.setBounds(10, 34, 560, 84);
		foundReport.add(descriptionReviewLabel_businessPanel);
		descriptionReviewLabel_businessPanel.setVerticalAlignment(JLabel.TOP);
		
		JSeparator reviewSeparator_businessPanel = new JSeparator();
		reviewSeparator_businessPanel.setBounds(10, 148, 580, 2);
		foundReport.add(reviewSeparator_businessPanel);
		
		JLabel reportedBy = new JLabel("<html>Segnalato da: <b>" + aReport.getAuthor().getUsername() + "</b> per: <b>" + aReport.getType() + "</b></html>" );
		reportedBy.setFont(new Font("Calibri", Font.PLAIN, 14));
		reportedBy.setBounds(180, 11, 393, 20);
		foundReport.add(reportedBy);
		
		JButton removeReportedReview = new JButton("Rimuovi recensione");
		removeReportedReview.setForeground(new Color(255, 245, 238));
		removeReportedReview.setBackground(new Color(20, 131, 108));
		removeReportedReview.setBounds(410, 125, 150, 23);
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
	
	//-------------------------------------------------------------------------------------------------------------------
	//		FINESTRA USER INFO
	//-------------------------------------------------------------------------------------------------------------------
	
	public void showUserInfo(user aUser) {
		JLabel titleLabel_userInfo = new JLabel("Informazioni sull'account");
		titleLabel_userInfo.setFont(new Font("Calibri", Font.BOLD, 25));
		titleLabel_userInfo.setBounds(198, 11, 300, 50);
		userInfoPanel.add(titleLabel_userInfo);
		
		JLabel userNameLabel_userInfo = new JLabel("Nome");
		userNameLabel_userInfo.setFont(new Font("Calibri", Font.BOLD, 15));
		userNameLabel_userInfo.setBounds(108, 73, 107, 24);
		userInfoPanel.add(userNameLabel_userInfo);
		
		JLabel nameTextField_userInfo = new JLabel();
		nameTextField_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		nameTextField_userInfo.setBounds(272, 71, 241, 24);
		userInfoPanel.add(nameTextField_userInfo);
		nameTextField_userInfo.setText(aUser.getName());
		
		JLabel surnameLabel_userInfo = new JLabel("Cognome:");
		surnameLabel_userInfo.setFont(new Font("Calibri", Font.BOLD, 15));
		surnameLabel_userInfo.setBounds(108, 108, 107, 24);
		userInfoPanel.add(surnameLabel_userInfo);
		
		JLabel surnameTextField_userInfo = new JLabel();
		surnameTextField_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		surnameTextField_userInfo.setBounds(272, 106, 241, 24);
		userInfoPanel.add(surnameTextField_userInfo);
		surnameTextField_userInfo.setText(aUser.getSurname());
		
		JLabel nationalityLabel_userInfo = new JLabel("Nazionalit??:");
		nationalityLabel_userInfo.setFont(new Font("Calibri", Font.BOLD, 15));
		nationalityLabel_userInfo.setBounds(108, 143, 107, 24);
		userInfoPanel.add(nationalityLabel_userInfo);
		
		JLabel nationalityTextField_userInfo = new JLabel();
		nationalityTextField_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		nationalityTextField_userInfo.setBounds(272, 141, 241, 24);
		userInfoPanel.add(nationalityTextField_userInfo);
		nationalityTextField_userInfo.setText(aUser.getNationality());
		
		JLabel birthdateLabel_userInfo = new JLabel("Data di nascita:");
		birthdateLabel_userInfo.setFont(new Font("Calibri", Font.BOLD, 15));
		birthdateLabel_userInfo.setBounds(108, 178, 107, 24);
		userInfoPanel.add(birthdateLabel_userInfo);

		JLabel birthDateTextField_userInfo = new JLabel();
		birthDateTextField_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		birthDateTextField_userInfo.setBounds(272, 178, 241, 24);
		userInfoPanel.add(birthDateTextField_userInfo);
		birthDateTextField_userInfo.setText(aUser.getBirthDate());
		
		JLabel emailLabel_userInfo = new JLabel("Email:");
		emailLabel_userInfo.setFont(new Font("Calibri", Font.BOLD, 15));
		emailLabel_userInfo.setBounds(108, 213, 107, 24);
		userInfoPanel.add(emailLabel_userInfo);
		
		JLabel emailTextField_userInfo = new JLabel();
		emailTextField_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		emailTextField_userInfo.setBounds(272, 213, 241, 24);
		userInfoPanel.add(emailTextField_userInfo);
		emailTextField_userInfo.setText(aUser.getEmail());
		
		JLabel usernameLabel_userInfo = new JLabel("Username:");
		usernameLabel_userInfo.setFont(new Font("Calibri", Font.BOLD, 15));
		usernameLabel_userInfo.setBounds(108, 248, 107, 24);
		userInfoPanel.add(usernameLabel_userInfo);
		
		JLabel usernameTextField_userInfo = new JLabel();
		usernameTextField_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		usernameTextField_userInfo.setBounds(272, 248, 241, 24);
		userInfoPanel.add(usernameTextField_userInfo);
		usernameTextField_userInfo.setText(aUser.getUsername());
		
		JLabel passwordLabel_userInfo = new JLabel("Password:");
		passwordLabel_userInfo.setFont(new Font("Calibri", Font.BOLD, 15));
		passwordLabel_userInfo.setBounds(108, 283, 107, 24);
		userInfoPanel.add(passwordLabel_userInfo);
		
		JLabel passwordTextField_userInfo = new JLabel();
		passwordTextField_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		passwordTextField_userInfo.setBounds(272, 283, 241, 24);
		userInfoPanel.add(passwordTextField_userInfo);
		passwordTextField_userInfo.setText(aUser.getPassword());
		
		JButton editUserInfoButton = new JButton("Modifica");
		editUserInfoButton.setForeground(new Color(255, 245, 238));
		editUserInfoButton.setBackground(new Color(20, 131, 108));
		editUserInfoButton.setBounds(187, 400, 120, 23);
		userInfoPanel.add(editUserInfoButton);
		editUserInfoButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {
				editUserInfo(aUser);
			}
		});
		
		JButton removeUserButton = new JButton("Rimuovi");
		removeUserButton.setForeground(new Color(255, 245, 238));
		removeUserButton.setBackground(new Color(20, 131, 108));
		removeUserButton.setBounds(307, 400, 120, 23);
		userInfoPanel.add(removeUserButton);
		removeUserButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(userInfoPanel, "Vuoi eliminare il tuo account", "Elimina account", JOptionPane.YES_NO_OPTION) == 0) {
					foodvibes.removeUser(aUser);
					foodvibes.logout();
					reportList.setVisible(false);
					logUserButton.setText("Login");
					registerButton.setText("Registrazione");
					titleLabel_sidebar.setText("Benvenuto");
					cardLayout.show(layeredPane, "searchBusinessPanel");
				}
			}
		});
	}
	
	//-------------------------------------------------------------------------------------------------------------------
	//		FINESTRA POP-UP MODIFICA USER INFO
	//-------------------------------------------------------------------------------------------------------------------
	
	public void editUserInfo(user aUser) {
		
		JPanel editBusinessPanel = new JPanel();
		editBusinessPanel.setPreferredSize(new Dimension(600, 400));
		editBusinessPanel.setBounds(10, 10, 500, 400);
		editBusinessPanel.setLayout(null);
		
		JLabel titleLabel_userInfo = new JLabel("Informazioni sull'account");
		titleLabel_userInfo.setFont(new Font("Calibri", Font.PLAIN, 25));
		titleLabel_userInfo.setBounds(198, 11, 186, 43);
		editBusinessPanel.add(titleLabel_userInfo);
		
		JLabel userNameLabel_userInfo = new JLabel("Nome");
		userNameLabel_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		userNameLabel_userInfo.setBounds(108, 73, 107, 24);
		editBusinessPanel.add(userNameLabel_userInfo);
		
		JTextField nameTextField_userInfo = new JTextField();
		nameTextField_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		nameTextField_userInfo.setBounds(272, 71, 241, 24);
		editBusinessPanel.add(nameTextField_userInfo);
		nameTextField_userInfo.setText(aUser.getName());
		
		JLabel surnameLabel_userInfo = new JLabel("Cognome");
		surnameLabel_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		surnameLabel_userInfo.setBounds(108, 108, 107, 24);
		editBusinessPanel.add(surnameLabel_userInfo);
		
		JTextField surnameTextField_userInfo = new JTextField();
		surnameTextField_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		surnameTextField_userInfo.setBounds(272, 106, 241, 24);
		editBusinessPanel.add(surnameTextField_userInfo);
		surnameTextField_userInfo.setText(aUser.getSurname());
		
		JLabel nationalityLabel_userInfo = new JLabel("Nazionalit??");
		nationalityLabel_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		nationalityLabel_userInfo.setBounds(108, 143, 107, 24);
		editBusinessPanel.add(nationalityLabel_userInfo);
		
		JTextField nationalityTextField_userInfo = new JTextField();
		nationalityTextField_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		nationalityTextField_userInfo.setBounds(272, 141, 241, 24);
		editBusinessPanel.add(nationalityTextField_userInfo);
		nationalityTextField_userInfo.setText(aUser.getNationality());
		
		JLabel birthdateLabel_userInfo = new JLabel("Data di nascita");
		birthdateLabel_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		birthdateLabel_userInfo.setBounds(108, 178, 107, 24);
		editBusinessPanel.add(birthdateLabel_userInfo);
		
		String[] monthDays = new String[31];
		for(int i = 0; i < 31; i++) {
			monthDays[i] = Integer.toString(i+1);
		}
		
		JComboBox birthDay = new JComboBox(monthDays);
		birthDay.setFont(new Font("Calibri", Font.PLAIN, 15));
		birthDay.setBounds(272, 178, 50, 24);
		editBusinessPanel.add(birthDay);
		
		
		String[] month = {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novemrbe", "Dicembre"};
		ArrayList<String> thirtyDaysMonths = new ArrayList<>();
		thirtyDaysMonths.add("Aprile");
		thirtyDaysMonths.add("Giugno");
		thirtyDaysMonths.add("Settembre");
		thirtyDaysMonths.add("Novembre");
		JComboBox birthMonth = new JComboBox(month);
		birthMonth.setFont(new Font("Calibri", Font.PLAIN, 15));
		birthMonth.setBounds(330, 178, 100, 24);
		editBusinessPanel.add(birthMonth);
		birthMonth.addActionListener(new ActionListener () {
			public void actionPerformed(ActionEvent e) {
				if(birthMonth.getSelectedItem().toString().equals("Febbraio") && 
				   Integer.parseInt(birthDay.getSelectedItem().toString())  > 28) {
					birthDay.setSelectedIndex(27);
					editBusinessPanel.validate();
					editBusinessPanel.repaint();				
					} else if (thirtyDaysMonths.contains(birthMonth.getSelectedItem().toString()) &&
							Integer.parseInt(birthDay.getSelectedItem().toString())  > 30) {
						birthDay.setSelectedIndex(29);
						editBusinessPanel.validate();
						editBusinessPanel.repaint();	
					}
			}
		});
		
		String[] years = new String[100];
		int j = 2023;
		for(int i = 0; i < 100; i++) {
			years[i] = Integer.toString(j);
			j--;
		}
		
		JComboBox birthYear = new JComboBox(years);
		birthYear.setFont(new Font("Calibri", Font.PLAIN, 15));
		birthYear.setBounds(440, 178, 70, 24);
		editBusinessPanel.add(birthYear);
		
		JLabel emailLabel_userInfo = new JLabel("Email");
		emailLabel_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		emailLabel_userInfo.setBounds(108, 213, 107, 24);
		editBusinessPanel.add(emailLabel_userInfo);
		
		JTextField emailTextField_userInfo = new JTextField();
		emailTextField_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		emailTextField_userInfo.setBounds(272, 213, 241, 24);
		editBusinessPanel.add(emailTextField_userInfo);
		emailTextField_userInfo.setText(aUser.getEmail());
		
		JLabel usernameLabel_userInfo = new JLabel("Username");
		usernameLabel_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		usernameLabel_userInfo.setBounds(108, 248, 107, 24);
		editBusinessPanel.add(usernameLabel_userInfo);
		
		JTextField usernameTextField_userInfo = new JTextField();
		usernameTextField_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		usernameTextField_userInfo.setBounds(272, 248, 241, 24);
		editBusinessPanel.add(usernameTextField_userInfo);
		usernameTextField_userInfo.setText(aUser.getUsername());
		
		JLabel passwordLabel_userInfo = new JLabel("Password");
		passwordLabel_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		passwordLabel_userInfo.setBounds(108, 283, 107, 24);
		editBusinessPanel.add(passwordLabel_userInfo);
		
		JTextField passwordTextField_userInfo = new JTextField();
		passwordTextField_userInfo.setFont(new Font("Calibri", Font.PLAIN, 15));
		passwordTextField_userInfo.setBounds(272, 283, 241, 24);
		editBusinessPanel.add(passwordTextField_userInfo);
		passwordTextField_userInfo.setText(aUser.getPassword());
		
		int result = JOptionPane.showConfirmDialog(reviewsPanel_businessPanel, editBusinessPanel, "Segnala recensione", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if (result == JOptionPane.OK_OPTION) {
			String birthDate = birthDay.getSelectedItem().toString() + " " + birthMonth.getSelectedItem().toString() + " " + birthYear.getSelectedItem().toString();
			foodvibes.editUserInfo(aUser, nameTextField_userInfo.getText(), surnameTextField_userInfo.getText(), nationalityTextField_userInfo.getText(), birthDate, emailTextField_userInfo.getText(), usernameTextField_userInfo.getText(), passwordTextField_userInfo.getText());
			userInfoPanel.removeAll();
			showUserInfo(aUser);
			cardLayout.show(layeredPane, "userInfoPanel");
		}
	}
	

	//-------------------------------------------------------------------------------------------------------------------
	// FUNZIONI USATE DALLE CLASSI CHE GESTISCONO LO STATO PER ACCEDERE AGLI ELEMENTI GUI 
	//-------------------------------------------------------------------------------------------------------------------
	
	public void hideReportListButton() {
		reportList.setVisible(false);
	}
	
	public void setLoginButton() {
		logUserButton.setText("Login");
		cardLayout.show(layeredPane, "searchBusinessPanel");
	}
	
	public void setRegisterButton() {
		registerButton.setText("Registrati");
	}
	
	public void setWelcomeGuest() {
		titleLabel_sidebar.setText("Benvenuto");
	}
	
	public void showRegisterPanel() {
		cardLayout.show(layeredPane, "registerPanel");
	}
	
	public void showLoginPanel() {
		cardLayout.show(layeredPane, "loginPanel");
	}
	
	public void showEditUserPanel() {
		userInfoPanel.removeAll();
		showUserInfo(foodvibes.getUser());
		cardLayout.show(layeredPane, "userInfoPanel");
	}
	
}
