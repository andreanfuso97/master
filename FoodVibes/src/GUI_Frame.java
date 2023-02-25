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

public class GUI_Frame extends JFrame {

	private JPanel contentPane;

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
		setTitle("Nuova Attività");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registra la tua Attività");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel.setBounds(10, 11, 414, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("<html>Fornisci le informazioni necessarie per registrare la tua attività <br>  su FoodVibes</html>");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 47, 414, 42);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 80, 445, 2);
		contentPane.add(separator);
		
		JLabel lblNewLabel_name = new JLabel("Nome Attività");
		lblNewLabel_name.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNewLabel_name.setBounds(10, 100, 207, 14);
		contentPane.add(lblNewLabel_name);
		
		JTextArea textArea_name = new JTextArea();
		textArea_name.setBounds(217, 93, 207, 22);
		contentPane.add(textArea_name);
		
		
		JLabel lblNewLabel_address = new JLabel("Indirizzo");
		lblNewLabel_address.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNewLabel_address.setBounds(10, 142, 207, 14);
		contentPane.add(lblNewLabel_address);
		
		JTextArea textArea_address = new JTextArea();
		textArea_address.setBounds(217, 135, 207, 22);
		contentPane.add(textArea_address);
		
		
		JLabel lblNewLabel_openingHours = new JLabel("Orari di apertura");
		lblNewLabel_openingHours.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNewLabel_openingHours.setBounds(10, 184, 207, 14);
		contentPane.add(lblNewLabel_openingHours);
		
		JTextArea textArea_openingHours = new JTextArea();
		textArea_openingHours.setBounds(217, 177, 207, 22);
		contentPane.add(textArea_openingHours);
		
		
		JLabel lblNewLabel_image = new JLabel("Link immagini");
		lblNewLabel_image.setFont(new Font("Calibri", Font.PLAIN, 11));
		lblNewLabel_image.setBounds(10, 226, 207, 14);
		contentPane.add(lblNewLabel_image);
		
		JTextArea textArea_image = new JTextArea();
		textArea_image.setBounds(217, 219, 207, 22);
		contentPane.add(textArea_image);
		
		
		JButton btnNewButton = new JButton("Registra ora");
		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton.setBounds(132, 265, 150, 35);
		contentPane.add(btnNewButton);
		
		
		btnNewButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				foodvibes.insertBusinessInfo(textArea_name.getText(), textArea_address.getText(), textArea_openingHours.getText(), textArea_image.getText());
			}
		});
	}
}
