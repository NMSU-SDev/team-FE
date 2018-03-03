import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;

public class Prototype2b {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Prototype2b window = new Prototype2b();
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
	public Prototype2b() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 843, 608);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Metadata Tree") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Citation");
						node_1.add(new DefaultMutableTreeNode("Origin"));
						node_1.add(new DefaultMutableTreeNode("Publication Date"));
						node_1.add(new DefaultMutableTreeNode("Title"));
						node_1.add(new DefaultMutableTreeNode("Geoform"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Time Period");
						node_1.add(new DefaultMutableTreeNode("Beginning Date"));
						node_1.add(new DefaultMutableTreeNode("End Date"));
						node_1.add(new DefaultMutableTreeNode("Current\t\t"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Data Quality");
						node_1.add(new DefaultMutableTreeNode("Logic"));
						node_1.add(new DefaultMutableTreeNode("Complete"));
						node_1.add(new DefaultMutableTreeNode("Process Description"));
						node_1.add(new DefaultMutableTreeNode("Process Date"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Metadata Reference");
						node_1.add(new DefaultMutableTreeNode("Metadata Date"));
						node_1.add(new DefaultMutableTreeNode("Contact Orginization"));
						node_1.add(new DefaultMutableTreeNode("Address Type"));
						node_1.add(new DefaultMutableTreeNode("Address"));
						node_1.add(new DefaultMutableTreeNode("City"));
						node_1.add(new DefaultMutableTreeNode("State"));
						node_1.add(new DefaultMutableTreeNode("Postal Code"));
						node_1.add(new DefaultMutableTreeNode("Country"));
						node_1.add(new DefaultMutableTreeNode("Contact Voice Telephone"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Spatial Data Organization");
						node_1.add(new DefaultMutableTreeNode("Direct"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Spatial Reference");
						node_1.add(new DefaultMutableTreeNode("Grid Coordinate System Name"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Entity and Attribute");
						node_1.add(new DefaultMutableTreeNode("LiDAR"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Distribution");
						node_1.add(new DefaultMutableTreeNode("Progress\t"));
					add(node_1);
				}
			}
		));
		tree.setBounds(10, 95, 161, 441);
		frame.getContentPane().add(tree);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(237, 74, 556, 14);
		frame.getContentPane().add(progressBar);
		
		JLabel lblNewLabel = new JLabel("Citation Information");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(330, 11, 246, 29);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Originator - the name of an organization or individual that developed the data set.");
		lblNewLabel_1.setBounds(181, 96, 395, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Publication Date - the date when the data set is published or otherwise made available for release.");
		lblNewLabel_2.setBounds(181, 184, 473, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Title - the name by which the data set is known.");
		lblNewLabel_3.setBounds(181, 285, 230, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblGeospatialDataPresentation = new JLabel("Geospatial Data Presentation Form.");
		lblGeospatialDataPresentation.setBounds(185, 376, 171, 14);
		frame.getContentPane().add(lblGeospatialDataPresentation);
		
		TextField textField = new TextField();
		textField.setBounds(177, 116, 616, 62);
		frame.getContentPane().add(textField);
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(179, 204, 614, 75);
		frame.getContentPane().add(textField_1);
		
		TextField textField_2 = new TextField();
		textField_2.setBounds(177, 303, 616, 67);
		frame.getContentPane().add(textField_2);
		
		TextField textField_3 = new TextField();
		textField_3.setBounds(177, 396, 616, 67);
		frame.getContentPane().add(textField_3);
		
		JButton btnNewButton = new JButton("FGDC Verify");
		btnNewButton.setBounds(436, 513, 91, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Export");
		btnNewButton_1.setBounds(10, 44, 89, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(10, 11, 89, 23);
		frame.getContentPane().add(btnSave);
		
		JButton btnDataTypes = new JButton("Data Types");
		btnDataTypes.setBounds(109, 44, 89, 23);
		frame.getContentPane().add(btnDataTypes);
		
		JButton button = new JButton("<=");
		button.setBounds(181, 513, 89, 23);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("=>");
		button_1.setBounds(704, 513, 89, 23);
		frame.getContentPane().add(button_1);
		
		JLabel lblProgress = new JLabel("Progress:");
		lblProgress.setBounds(181, 74, 46, 14);
		frame.getContentPane().add(lblProgress);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setBounds(109, 11, 89, 23);
		frame.getContentPane().add(btnOptions);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
	}
}
