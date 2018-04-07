import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.TextField;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class Prototype4 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prototype4 window = new Prototype4();
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
	public Prototype4() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 852, 632);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(809, 11, 17, 556);
		frame.getContentPane().add(scrollBar);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Citation", "---------------------------\t", "Description", "---------------------------\t", "Time Period", "---------------------------", "Status", "---------------------------", "Spatial Domain", "---------------------------", "Data Quality", "---------------------------", "Spatial Data Organization", "---------------------------", "Spatial Reference", "---------------------------", "Metadata Reference"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(10, 31, 125, 536);
		frame.getContentPane().add(list);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollBar_1.setBounds(134, 31, 17, 536);
		frame.getContentPane().add(scrollBar_1);
		
		JLabel lblPageList = new JLabel("Page List");
		lblPageList.setHorizontalAlignment(SwingConstants.CENTER);
		lblPageList.setBounds(10, 11, 125, 14);
		frame.getContentPane().add(lblPageList);
		
		JLabel lblNewLabel = new JLabel("Citation Information");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(325, 11, 246, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblOrigin = new JLabel("Originator - the name of an organization or individual that developed the data set.");
		lblOrigin.setBounds(161, 46, 395, 14);
		frame.getContentPane().add(lblOrigin);
		
		JLabel lblNewLabel_1 = new JLabel("Publication Date - the date when the data set is published or otherwise made available for release.");
		lblNewLabel_1.setBounds(161, 139, 473, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblTitleThe = new JLabel("Title - the name by which the data set is known.");
		lblTitleThe.setBounds(161, 267, 230, 14);
		frame.getContentPane().add(lblTitleThe);
		
		JLabel lblGeospatialDataPresentation = new JLabel("Geospatial Data Presentation Form.");
		lblGeospatialDataPresentation.setBounds(161, 389, 171, 14);
		frame.getContentPane().add(lblGeospatialDataPresentation);
		
		JLabel lblPage = new JLabel("Page:");
		lblPage.setBounds(423, 538, 46, 14);
		frame.getContentPane().add(lblPage);
		
		JLabel lblCitationInformation = new JLabel("1, Citation Information");
		lblCitationInformation.setBounds(467, 538, 109, 14);
		frame.getContentPane().add(lblCitationInformation);
		
		JButton button = new JButton("<=");
		button.setBounds(161, 534, 89, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("=>");
		button_1.setBounds(710, 534, 89, 23);
		frame.getContentPane().add(button_1);
		
		TextField textField = new TextField();
		textField.setBounds(157, 66, 615, 62);
		frame.getContentPane().add(textField);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("New check box");
		chckbxNewCheckBox.setBounds(778, 82, 25, 23);
		frame.getContentPane().add(chckbxNewCheckBox);
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(157, 159, 615, 102);
		frame.getContentPane().add(textField_1);
		
		TextField textField_2 = new TextField();
		textField_2.setBounds(157, 287, 615, 96);
		frame.getContentPane().add(textField_2);
		
		TextField textField_3 = new TextField();
		textField_3.setBounds(157, 409, 615, 93);
		frame.getContentPane().add(textField_3);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("New check box");
		chckbxNewCheckBox_1.setBounds(778, 196, 25, 23);
		frame.getContentPane().add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("New check box");
		chckbxNewCheckBox_2.setBounds(778, 325, 25, 23);
		frame.getContentPane().add(chckbxNewCheckBox_2);
		
		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("New check box");
		chckbxNewCheckBox_3.setBounds(778, 441, 25, 23);
		frame.getContentPane().add(chckbxNewCheckBox_3);
		
		JLabel lblVerified = new JLabel("Verified");
		lblVerified.setBounds(768, 32, 46, 14);
		frame.getContentPane().add(lblVerified);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenu mnNewMenu = new JMenu("Help");
		menuBar.add(mnNewMenu);
	}
}
