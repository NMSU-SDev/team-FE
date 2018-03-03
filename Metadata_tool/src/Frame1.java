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

public class Frame1 {

	private JFrame frmMetadataTool;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater( new Runnable() {
			public void run() {
				try {
					Frame1 window = new Frame1();
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
	public Frame1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMetadataTool = new JFrame();
		frmMetadataTool.setTitle("Metadata Tool");
		frmMetadataTool.setBounds(100, 100, 623, 575);
		frmMetadataTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMetadataTool.getContentPane().setLayout(null);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setOrientation(SwingConstants.VERTICAL);
		progressBar.setBounds(10, 11, 17, 402);
		frmMetadataTool.getContentPane().add(progressBar);
		
		JLabel lblProject = new JLabel("Project");
		lblProject.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProject.setBounds(281, 11, 128, 14);
		frmMetadataTool.getContentPane().add(lblProject);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Verified Name");
		chckbxNewCheckBox.setBounds(571, 40, 23, 23);
		frmMetadataTool.getContentPane().add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Verified Number");
		chckbxNewCheckBox_1.setBounds(571, 68, 23, 23);
		frmMetadataTool.getContentPane().add(chckbxNewCheckBox_1);
		
		JCheckBox chckbxVerifiedManager = new JCheckBox("Verified PM");
		chckbxVerifiedManager.setBounds(571, 102, 23, 23);
		frmMetadataTool.getContentPane().add(chckbxVerifiedManager);
		
		JCheckBox chckbxVerifiedNarrative = new JCheckBox("Verified Narrative");
		chckbxVerifiedNarrative.setBounds(571, 136, 23, 23);
		frmMetadataTool.getContentPane().add(chckbxVerifiedNarrative);
		
		JLabel lblVerified = new JLabel("Verified");
		lblVerified.setBounds(559, 15, 46, 14);
		frmMetadataTool.getContentPane().add(lblVerified);
		
		JButton btnContinue = new JButton("Continue");
		btnContinue.setBounds(474, 487, 89, 23);
		frmMetadataTool.getContentPane().add(btnContinue);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(356, 487, 89, 23);
		frmMetadataTool.getContentPane().add(btnSave);
		
		JLabel label = new JLabel(">>");
		label.setBounds(573, 491, 32, 14);
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
		
		JLabel lblWho = new JLabel("Project");
		lblWho.setBounds(161, 44, 46, 14);
		frmMetadataTool.getContentPane().add(lblWho);
		
		JLabel lblWhat = new JLabel("Number");
		lblWhat.setBounds(161, 72, 46, 14);
		frmMetadataTool.getContentPane().add(lblWhat);
		
		JLabel lblWhere = new JLabel("Manager");
		lblWhere.setBounds(161, 106, 46, 14);
		frmMetadataTool.getContentPane().add(lblWhere);
		
		JLabel lblWhen = new JLabel("Narrative");
		lblWhen.setBounds(161, 140, 46, 14);
		frmMetadataTool.getContentPane().add(lblWhen);
		
		TextField nameField = new TextField();
		nameField.setBounds(216, 40, 349, 22);
		frmMetadataTool.getContentPane().add(nameField);
		
		TextField numberField = new TextField();
		numberField.setBounds(214, 68, 351, 22);
		frmMetadataTool.getContentPane().add(numberField);
		
		TextField managerField = new TextField();
		managerField.setBounds(213, 102, 352, 22);
		frmMetadataTool.getContentPane().add(managerField);
		
		TextArea narrativeArea = new TextArea();
		narrativeArea.setBounds(214, 136, 351, 345);
		frmMetadataTool.getContentPane().add(narrativeArea);
		
		Button button = new Button("Project");
		button.setBounds(33, 22, 70, 22);
		frmMetadataTool.getContentPane().add(button);
		
		Button button_1 = new Button("Contact Info");
		button_1.setBounds(33, 69, 70, 22);
		frmMetadataTool.getContentPane().add(button_1);
		
		Button button_2 = new Button("Data Types");
		button_2.setBounds(33, 117, 70, 22);
		frmMetadataTool.getContentPane().add(button_2);
		
		Button button_3 = new Button("Product Specifications");
		button_3.setBounds(33, 166, 122, 22);
		frmMetadataTool.getContentPane().add(button_3);
		
		Button button_4 = new Button("Projection");
		button_4.setBounds(33, 218, 70, 22);
		frmMetadataTool.getContentPane().add(button_4);
		
		Button button_5 = new Button("Quality");
		button_5.setBounds(33, 272, 70, 22);
		frmMetadataTool.getContentPane().add(button_5);
		
		Button button_6 = new Button("Processing");
		button_6.setBounds(33, 325, 70, 22);
		frmMetadataTool.getContentPane().add(button_6);
		
		Button button_7 = new Button("Ancillary");
		button_7.setBounds(33, 375, 70, 22);
		frmMetadataTool.getContentPane().add(button_7);
		
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
