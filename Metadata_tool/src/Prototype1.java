import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Button;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextPane;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JCheckBox;
import javax.swing.JToolBar;
import javax.swing.JInternalFrame;
import java.awt.Component;
import javax.swing.Box;
//import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JSeparator;
import java.awt.TextField;
import java.awt.TextArea;
import javax.swing.JMenuItem;
import java.awt.Checkbox;
import java.awt.Color;

public class Prototype1 {

	private JFrame frmMetadataTool;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater( new Runnable() {
			public void run() {
				try {
					Prototype1 window = new Prototype1();
					window.frmMetadataTool.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}); // end EventQueue invokeLater method
	}

	/**
	 * Create the application.
	 */
	public Prototype1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMetadataTool = new JFrame();
		frmMetadataTool.setTitle("Metadata Tool");
		frmMetadataTool.setBounds(100, 100, 855, 575);
		frmMetadataTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMetadataTool.getContentPane().setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		progressBar.setStringPainted(true);
		progressBar.setForeground(Color.GREEN);
		progressBar.setOrientation(SwingConstants.VERTICAL);
		progressBar.setBounds(10, 54, 17, 429);
		frmMetadataTool.getContentPane().add(progressBar);
		
		JLabel lblProject = new JLabel("Citation Information");
		lblProject.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProject.setBounds(382, 8, 167, 20);
		frmMetadataTool.getContentPane().add(lblProject);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("origin check box");
		chckbxNewCheckBox_1.setBounds(788, 54, 23, 23);
		frmMetadataTool.getContentPane().add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxVerifiedManager = new JCheckBox("pubdate check box");
		chckbxVerifiedManager.setBounds(788, 131, 23, 23);
		frmMetadataTool.getContentPane().add(chckbxVerifiedManager);
		
		JCheckBox chckbxVerifiedNarrative = new JCheckBox("title check box");
		chckbxVerifiedNarrative.setBounds(788, 244, 23, 23);
		frmMetadataTool.getContentPane().add(chckbxVerifiedNarrative);
		
		JLabel lblVerified = new JLabel("Verified");
		lblVerified.setBounds(768, 13, 46, 14);
		frmMetadataTool.getContentPane().add(lblVerified);
		
		JButton btnContinue = new JButton("Next");
		btnContinue.setBounds(669, 487, 89, 23);
		frmMetadataTool.getContentPane().add(btnContinue);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(539, 487, 89, 23);
		frmMetadataTool.getContentPane().add(btnSave);
		
		JLabel label = new JLabel(">>");
		label.setBounds(779, 491, 32, 14);
		frmMetadataTool.getContentPane().add(label);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(219, 487, 89, 23);
		frmMetadataTool.getContentPane().add(btnBack);
		
		JLabel label_1 = new JLabel("<<");
		label_1.setBounds(184, 491, 23, 14);
		frmMetadataTool.getContentPane().add(label_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(121, 386, 1, 2);
		frmMetadataTool.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(142, 390, 1, 2);
		frmMetadataTool.getContentPane().add(separator_1);
		
		JLabel lblWhat = new JLabel("Originator - the name of an organization or individual that developed the data set.");
		lblWhat.setBounds(191, 58, 395, 14);
		frmMetadataTool.getContentPane().add(lblWhat);
		
		JLabel lblWhere = new JLabel("Publication Date - the date when the data set is published or otherwise made available for relea");
		lblWhere.setBounds(191, 146, 458, 14);
		frmMetadataTool.getContentPane().add(lblWhere);
		
		JLabel lblWhen = new JLabel("Title - the name by which the data set is known.");
		lblWhen.setBounds(191, 248, 230, 14);
		frmMetadataTool.getContentPane().add(lblWhen);
		
		TextField numberField = new TextField();
		numberField.setBounds(188, 78, 617, 47);
		frmMetadataTool.getContentPane().add(numberField);
		
		TextField managerField = new TextField();
		managerField.setBounds(185, 166, 620, 60);
		frmMetadataTool.getContentPane().add(managerField);
		
		Button button = new Button("Identification");
		button.setBounds(33, 55, 135, 22);
		frmMetadataTool.getContentPane().add(button);
		
		Button button_1 = new Button("Data Quality");
		button_1.setBounds(33, 101, 135, 22);
		frmMetadataTool.getContentPane().add(button_1);
		
		Button button_2 = new Button("Spatial Data Organization");
		button_2.setBounds(33, 146, 135, 22);
		frmMetadataTool.getContentPane().add(button_2);
		
		Button button_3 = new Button("Spatial Reference");
		button_3.setBounds(33, 190, 135, 22);
		frmMetadataTool.getContentPane().add(button_3);
		
		Button button_4 = new Button("Entity and Attribute");
		button_4.setBounds(33, 231, 135, 22);
		frmMetadataTool.getContentPane().add(button_4);
		
		Button button_5 = new Button("Metadata Reference");
		button_5.setBounds(33, 321, 135, 22);
		frmMetadataTool.getContentPane().add(button_5);
		
		Button button_6 = new Button("Distribution");
		button_6.setBounds(33, 278, 135, 22);
		frmMetadataTool.getContentPane().add(button_6);
		
		Button button_7 = new Button("Citation");
		button_7.setBounds(33, 366, 135, 22);
		frmMetadataTool.getContentPane().add(button_7);
		
		Button button_8 = new Button("Time Period");
		button_8.setBounds(33, 414, 135, 22);
		frmMetadataTool.getContentPane().add(button_8);
		
		Button button_9 = new Button("Contact");
		button_9.setBounds(33, 461, 135, 22);
		frmMetadataTool.getContentPane().add(button_9);
		
		JLabel lblNewLabel = new JLabel("Geospatial Data Presentation Form.");
		lblNewLabel.setBounds(194, 360, 171, 14);
		frmMetadataTool.getContentPane().add(lblNewLabel);
		
		TextField textField = new TextField();
		textField.setBounds(184, 268, 620, 60);
		frmMetadataTool.getContentPane().add(textField);
		
		TextField textField_1 = new TextField();
		textField_1.setBounds(184, 386, 620, 59);
		frmMetadataTool.getContentPane().add(textField_1);
		
		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("geoform check box");
		chckbxNewCheckBox_2.setBounds(787, 356, 23, 23);
		frmMetadataTool.getContentPane().add(chckbxNewCheckBox_2);
		
		JLabel lblTableOfContents = new JLabel("Table of Contents");
		lblTableOfContents.setHorizontalAlignment(SwingConstants.CENTER);
		lblTableOfContents.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblTableOfContents.setBounds(44, 13, 111, 14);
		frmMetadataTool.getContentPane().add(lblTableOfContents);
		
		JButton btnExport = new JButton("Export");
		btnExport.setBounds(379, 487, 89, 23);
		frmMetadataTool.getContentPane().add(btnExport);
		
		JMenuBar menuBar = new JMenuBar();
		frmMetadataTool.setJMenuBar(menuBar);
		
		JMenu mnFileMenu = new JMenu("File");
		menuBar.add(mnFileMenu);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mnFileMenu.add(mntmNew);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mnFileMenu.add(mntmOpen);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFileMenu.add(mntmSave);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFileMenu.add(mntmClose);
		
		JMenuItem mntmImportXml = new JMenuItem("Import XML");
		mnFileMenu.add(mntmImportXml);
		
		JMenuItem mntmExportXml = new JMenuItem("Export XML");
		mnFileMenu.add(mntmExportXml);
		
		JMenu mnEditMenu = new JMenu("Edit");
		menuBar.add(mnEditMenu);
		
		JMenuItem mntmCut = new JMenuItem("Cut");
		mnEditMenu.add(mntmCut);
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mnEditMenu.add(mntmCopy);
		
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mnEditMenu.add(mntmPaste);
		
		JMenuItem mntmFind = new JMenuItem("Find");
		mnEditMenu.add(mntmFind);
		
		JMenuItem mntmReplace = new JMenuItem("Replace");
		mnEditMenu.add(mntmReplace);
		
		JMenu mnViewMenu = new JMenu("View");
		menuBar.add(mnViewMenu);
		
		JMenuItem mntmProject = new JMenuItem("XML Preview");
		mnViewMenu.add(mntmProject);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Empty tags");
		mnViewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Verified tags");
		mnViewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmProductSpecifications = new JMenuItem("Project Data Products");
		mnViewMenu.add(mntmProductSpecifications);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Properties");
		mnViewMenu.add(mntmNewMenuItem_2);
		
		JMenu mnHelpMenu = new JMenu("Help");
		menuBar.add(mnHelpMenu);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Help Contents");
		mnHelpMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("USGS Metadata Parser");
		mnHelpMenu.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("FGDC Standards check");
		mnHelpMenu.add(mntmNewMenuItem_5);
	}
}
