import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

public class GUI_Frame extends JFrame {

	private JPanel contentPane;
	private JTextField searchTextArea_searchBusiness;

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
		contentPane.setBackground(new Color(153, 204, 204));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(new Color(204, 255, 204));
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
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
		
		searchTextArea_searchBusiness = new JTextField();
		searchTextArea_searchBusiness.setColumns(10);
		searchTextArea_searchBusiness.setBounds(10, 47, 361, 25);
		searchBusinessPanel.add(searchTextArea_searchBusiness);
		
		JButton button_searchBusiness = new JButton("🔎︎");
		button_searchBusiness.setBounds(371, 47, 43, 25);
		searchBusinessPanel.add(button_searchBusiness);
		
		button_searchBusiness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foodvibes.searchBusiness(searchTextArea_searchBusiness.getText());
			}
		});
		
		//new business
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
		descriptionLabel_newBusiness.setBounds(10, 32, 347, 30);
		registerBusinessPanel.add(descriptionLabel_newBusiness);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 73, 404, 2);
		registerBusinessPanel.add(separator);
		
		JLabel newNameLabel_newBusiness = new JLabel("Nome Attività");
		newNameLabel_newBusiness.setFont(new Font("Calibri", Font.PLAIN, 11));
		newNameLabel_newBusiness.setBounds(10, 86, 65, 15);
		registerBusinessPanel.add(newNameLabel_newBusiness);
		
		JTextArea newNameTextArea_newBusiness = new JTextArea();
		newNameTextArea_newBusiness.setBounds(214, 79, 200, 22);
		registerBusinessPanel.add(newNameTextArea_newBusiness);
		
		JLabel newAddressLabel_newBusiness = new JLabel("Indirizzo");
		newAddressLabel_newBusiness.setFont(new Font("Calibri", Font.PLAIN, 11));
		newAddressLabel_newBusiness.setBounds(10, 112, 39, 15);
		registerBusinessPanel.add(newAddressLabel_newBusiness);
		
		JTextArea newAddressTextArea_newBusiness = new JTextArea();
		newAddressTextArea_newBusiness.setBounds(214, 105, 200, 22);
		registerBusinessPanel.add(newAddressTextArea_newBusiness);
		
		JLabel newOpeningHoursLabel_newBusiness = new JLabel("Orari di apertura");
		newOpeningHoursLabel_newBusiness.setFont(new Font("Calibri", Font.PLAIN, 11));
		newOpeningHoursLabel_newBusiness.setBounds(10, 138, 79, 15);
		registerBusinessPanel.add(newOpeningHoursLabel_newBusiness);
		
		JTextArea newOpeningHoursTextArea_newBusiness = new JTextArea();
		newOpeningHoursTextArea_newBusiness.setBounds(214, 131, 200, 22);
		registerBusinessPanel.add(newOpeningHoursTextArea_newBusiness);
		
		JLabel newImageLabel_newBusiness = new JLabel("Link immagini");
		newImageLabel_newBusiness.setFont(new Font("Calibri", Font.PLAIN, 11));
		newImageLabel_newBusiness.setBounds(10, 164, 65, 15);
		registerBusinessPanel.add(newImageLabel_newBusiness);
		
		JTextArea newImageTextArea_newBusiness = new JTextArea();
		newImageTextArea_newBusiness.setBounds(214, 157, 200, 22);
		registerBusinessPanel.add(newImageTextArea_newBusiness);
		
		JButton button_newBusiness = new JButton("Registra ora");
		button_newBusiness.setVerticalAlignment(SwingConstants.BOTTOM);
		button_newBusiness.setFont(new Font("Calibri", Font.BOLD, 20));
		button_newBusiness.setBounds(145, 257, 133, 33);
		registerBusinessPanel.add(button_newBusiness);
		
		button_newBusiness.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				foodvibes.insertBusinessInfo(newNameTextArea_newBusiness.getText(), newAddressTextArea_newBusiness.getText(),
						newOpeningHoursTextArea_newBusiness.getText(), newImageTextArea_newBusiness.getText());
			}
		});
		
		//side bar
		JPanel SideBar = new JPanel();
		SideBar.setBackground(new Color(204, 255, 204));
		SideBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		SideBar.setBounds(5, 5, 212, 441);
		contentPane.add(SideBar);
		SideBar.setLayout(null);
		
		JLabel titleLabel_sidebar = new JLabel("Benvenuto");
		titleLabel_sidebar.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel_sidebar.setBounds(10, 11, 192, 50);
		titleLabel_sidebar.setFont(new Font("Calibri", Font.BOLD, 25));
		SideBar.add(titleLabel_sidebar);
		
		JButton searchButton_sidebar = new JButton("Cerca");
		searchButton_sidebar.setFont(new Font("Calibri", Font.BOLD, 20));
		searchButton_sidebar.setBounds(10, 72, 192, 50);
		SideBar.add(searchButton_sidebar);
		
		searchButton_sidebar.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				CardLayout cardLayout = (CardLayout)(layeredPane.getLayout());
				cardLayout.show(layeredPane, "searchBusinessPanel");
			}
		});
		
		JButton registerBusinessButton_sidebar = new JButton("Registra attività");
		registerBusinessButton_sidebar.setFont(new Font("Calibri", Font.BOLD, 20));
		registerBusinessButton_sidebar.setBounds(10, 122, 192, 50);
		SideBar.add(registerBusinessButton_sidebar);
		
		registerBusinessButton_sidebar.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				CardLayout cardLayout = (CardLayout)(layeredPane.getLayout());
				cardLayout.show(layeredPane, "registerBusinessPanel");
			}
		});
	}
}
