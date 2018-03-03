import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Prototype2a {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prototype2a window = new Prototype2a();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Prototype2a() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 702, 596);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblWelcomeToThe = new JLabel("Welcome to the Metadata Tool");
		lblWelcomeToThe.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeToThe.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
		lblWelcomeToThe.setBounds(129, 105, 386, 29);
		frame.getContentPane().add(lblWelcomeToThe);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnContinue.setBounds(110, 225, 131, 67);
		frame.getContentPane().add(btnContinue);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnOpen.setBounds(270, 225, 126, 67);
		frame.getContentPane().add(btnOpen);
		
		JButton btnNew = new JButton("New");
		btnNew.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNew.setBounds(427, 226, 131, 67);
		frame.getContentPane().add(btnNew);
	}

}
