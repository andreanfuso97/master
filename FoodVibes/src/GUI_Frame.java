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
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		//---------------------------------------------------------------//
		//					search Business								 //
		//---------------------------------------------------------------//
		JPanel searchBusinessPanel = new JPanel();
		contentPane.add(searchBusinessPanel, "name_95121904108400");
		searchBusinessPanel.setLayout(null);
		
		JLabel title_searchBusiness = new JLabel("Cerca Attività");
		title_searchBusiness.setFont(new Font("Calibri", Font.BOLD, 20));
		title_searchBusiness.setBounds(10, 11, 198, 25);
		searchBusinessPanel.add(title_searchBusiness);
		
		searchTextArea_searchBusiness = new JTextField();
		searchTextArea_searchBusiness.setBounds(10, 47, 361, 25); 
		searchBusinessPanel.add(searchTextArea_searchBusiness);
		searchTextArea_searchBusiness.setColumns(10);
		
		JButton button_searchBusiness = new JButton("🔎︎");
		button_searchBusiness.setBounds(371, 47, 43, 25);
		searchBusinessPanel.add(button_searchBusiness);		
		
		button_searchBusiness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				foodvibes.searchBusiness(searchTextArea_searchBusiness.getText());
			}
		});
		
		//---------------------------------------------------------------//
		//					new Business								 //
		//---------------------------------------------------------------//		
		JPanel newBusinessPanel = new JPanel();
		contentPane.add(newBusinessPanel, "name_94581570579500");
		newBusinessPanel.setLayout(null);
		
		JLabel titleLabel_newBusiness = new JLabel("Registra la tua Attività");
		titleLabel_newBusiness.setBounds(10, 11, 187, 25);
		titleLabel_newBusiness.setFont(new Font("Calibri", Font.BOLD, 20));
		newBusinessPanel.add(titleLabel_newBusiness);
		
		JLabel descriptionLabel_newBusiness = new JLabel("<html>Fornisci le informazioni necessarie per registrare la tua attività <br>  su FoodVibes</html>");
		descriptionLabel_newBusiness.setBounds(10, 32, 347, 30);
		descriptionLabel_newBusiness.setVerticalAlignment(SwingConstants.TOP);
		descriptionLabel_newBusiness.setHorizontalAlignment(SwingConstants.LEFT);
		descriptionLabel_newBusiness.setFont(new Font("Calibri", Font.PLAIN, 12));
		newBusinessPanel.add(descriptionLabel_newBusiness);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 73, 404, 2);
		newBusinessPanel.add(separator);
		
		JLabel newNameLabel_newBusiness = new JLabel("Nome Attività");
		newNameLabel_newBusiness.setBounds(10, 86, 65, 15);
		newNameLabel_newBusiness.setFont(new Font("Calibri", Font.PLAIN, 11));
		newBusinessPanel.add(newNameLabel_newBusiness);
		
		JTextArea newNameTextArea_newBusiness = new JTextArea();
		newNameTextArea_newBusiness.setBounds(214, 79, 200, 22);
		newBusinessPanel.add(newNameTextArea_newBusiness);
		
		JLabel newAddressLabel_newBusiness = new JLabel("Indirizzo");
		newAddressLabel_newBusiness.setBounds(10, 112, 39, 15);
		newAddressLabel_newBusiness.setFont(new Font("Calibri", Font.PLAIN, 11));
		newBusinessPanel.add(newAddressLabel_newBusiness);
		
		JTextArea newAddressTextArea_newBusiness = new JTextArea();
		newAddressTextArea_newBusiness.setBounds(214, 105, 200, 22);
		newBusinessPanel.add(newAddressTextArea_newBusiness);
		
		JLabel newOpeningHoursLabel_newBusiness = new JLabel("Orari di apertura");
		newOpeningHoursLabel_newBusiness.setBounds(10, 138, 79, 15);
		newOpeningHoursLabel_newBusiness.setFont(new Font("Calibri", Font.PLAIN, 11));
		newBusinessPanel.add(newOpeningHoursLabel_newBusiness);
		
		JTextArea newOpeningHoursTextArea_newBusiness = new JTextArea();
		newOpeningHoursTextArea_newBusiness.setBounds(214, 131, 200, 22);
		newBusinessPanel.add(newOpeningHoursTextArea_newBusiness);
		
		JLabel newImageLabel_newBusiness = new JLabel("Link immagini");
		newImageLabel_newBusiness.setBounds(10, 164, 65, 15);
		newImageLabel_newBusiness.setFont(new Font("Calibri", Font.PLAIN, 11));
		newBusinessPanel.add(newImageLabel_newBusiness);
		
		JTextArea newImageTextArea_newBusiness = new JTextArea();
		newImageTextArea_newBusiness.setBounds(214, 157, 200, 22);
		newBusinessPanel.add(newImageTextArea_newBusiness);
		
		JButton button_newBusiness = new JButton("Registra ora");
		button_newBusiness.setBounds(145, 257, 133, 33);
		button_newBusiness.setVerticalAlignment(SwingConstants.BOTTOM);
		button_newBusiness.setFont(new Font("Calibri", Font.BOLD, 20));
		newBusinessPanel.add(button_newBusiness);
		
		button_newBusiness.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				foodvibes.insertBusinessInfo(newNameTextArea_newBusiness.getText(), newAddressTextArea_newBusiness.getText(),
						newOpeningHoursTextArea_newBusiness.getText(), newImageTextArea_newBusiness.getText());
			}
		});
	}
}
