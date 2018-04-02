import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.GroupLayout.Alignment;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTree;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import java.awt.List;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.Button;
import javax.swing.JInternalFrame;

public class Demo2 {

	private JFrame frameTeamFeMetadata;
	private File file;
	private String inputFile = "";
	private NodeList nList = null;
	private Node rootDOM = null;
	private MetadataNode rootMNode = null;
	private MetaXMLParser parse = new MetaXMLParser();
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();	
	private FileOps1 fileOperations;
	private NewSession newSession;
	
	// TEST VARIABLES //
	private Document doc1 = null;
	private Document doc2 = null;
	Scanner scan = new Scanner(System.in);
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					Demo2 window = new Demo2();
					window.frameTeamFeMetadata.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Demo2() 
	{
		initialize();		
	}

	/**
	 * Initialize the contents of the frameTeamFeMetadata.
	 */
	private void initialize() 
	{
		try 
		{
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch(Exception e) 
		{
	        System.out.println("Error setting native LAF: " + e);
	    }
		frameTeamFeMetadata = new JFrame();
		frameTeamFeMetadata.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Johnston\\Documents\\NMSU\\CS371-M01\\Metadata_tool\\Metadata_tool\\XSM.png"));
		frameTeamFeMetadata.setBounds(100, 100, 700, 600);
		frameTeamFeMetadata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameTeamFeMetadata.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		frameTeamFeMetadata.getContentPane().add(panel, "name_1876606686560390");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		
		List list = new List(30, false);
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 9;
		gbc_list.insets = new Insets(0, 0, 5, 5);
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		panel.add(list, gbc_list);
		
		Label label_1 = new Label("Element Name goes here");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.WEST;
		gbc_label_1.gridwidth = 6;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 2;
		gbc_label_1.gridy = 0;
		panel.add(label_1, gbc_label_1);
		
		Label label = new Label("Question goes here");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.WEST;
		gbc_label.gridheight = 3;
		gbc_label.gridwidth = 6;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 1;
		panel.add(label, gbc_label);
		
		JTextArea textArea = new JTextArea();
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.gridheight = 5;
		gbc_textArea.gridwidth = 6;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 2;
		gbc_textArea.gridy = 4;
		panel.add(textArea, gbc_textArea);
		
		Button button = new Button("Previous");
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.insets = new Insets(0, 0, 0, 5);
		gbc_button.gridx = 2;
		gbc_button.gridy = 9;
		panel.add(button, gbc_button);
		
		Button button_1 = new Button("  Next  ");
		GridBagConstraints gbc_button_1 = new GridBagConstraints();
		gbc_button_1.gridx = 8;
		gbc_button_1.gridy = 9;
		panel.add(button_1, gbc_button_1);
		
		JMenuBar menuBar = new JMenuBar();
		frameTeamFeMetadata.setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("File");
		// mnFile.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(menuFile);

		JMenuItem menuItemNew = new JMenuItem("New");
		menuItemNew.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) {
				// Open a new dialog window with two buttons: "USGS" and "non-USGS"
				// newSession.setVisible(true);
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							NewSession frame = new NewSession();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
				// the new menu option was selected
				JOptionPane.showMessageDialog(null, "Create a new project option was selected"
						+ "\nFunctionality coming soon..."
						, "New", JOptionPane.INFORMATION_MESSAGE );				
			}
		});
		menuFile.add(menuItemNew);

		JMenuItem menuItemOpen = new JMenuItem("Open");
		menuItemOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// call the open code from FileOps1
				System.out.println("Open menu option clicked");
				// load metadata or session file into 'file'
				file = fileOperations.openFile( ); 	
				// if file is an xml, run Preview method
				
				// **DEBUG** //
				System.out.print(file.toString());
			}
		});
		menuFile.add(menuItemOpen);

		JMenuItem menuItemSave = new JMenuItem("Save");
		menuItemSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// call SaveSession() method that creates a File object to send to the saveFile(File) method
				// call the FileOps1.saveFile( )
				fileOperations.saveFile( file );
				JOptionPane.showMessageDialog(null, "Had there been a project open, it would have been saved."
						, "Save", JOptionPane.INFORMATION_MESSAGE );
			}
		});
		menuFile.add(menuItemSave);

		JMenuItem menuItemImport = new JMenuItem("Import");
		menuItemImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// action for when import is selected
				JOptionPane.showMessageDialog(null, "Import action coming soon..."
						, "Import", JOptionPane.INFORMATION_MESSAGE );
			}
		});
		menuFile.add(menuItemImport);

		JMenuItem menuItemExport = new JMenuItem("Export");
		menuItemExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Export action coming soon..."
						, "Export", JOptionPane.INFORMATION_MESSAGE );
			}
		});
		menuFile.add(menuItemExport);
		
		JMenuItem menuAdd = new JMenuItem("Add");
		menuAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Add XML to Session action coming soon..."
						, "Add XML to Session", JOptionPane.INFORMATION_MESSAGE );
			}
		});
		menuFile.add(menuAdd);

		JMenu menuEdit = new JMenu("Edit");
		// mnEdit.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(menuEdit);

		JMenuItem menuItemCut = new JMenuItem("Cut");
		menuItemCut.addActionListener(new ActionListener() 
		{
			// Cut command
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getActionCommand().equals("Cut"))
				{

					String selection=textArea.getSelectedText();

					if(selection==null){
						return;
					}
					StringSelection clipString=new StringSelection(selection);
					clipboard.setContents(clipString,clipString);
					textArea.replaceSelection("");	
				}
			}
		});
		menuEdit.add(menuItemCut);

		JMenuItem menuItemCopy = new JMenuItem("Copy");
		menuItemCopy.addActionListener(new ActionListener() 
		{
			// Cut command
			public void actionPerformed(ActionEvent e) 
			{
				if(e.getActionCommand().equals("Copy"))
				{

					String selection=textArea.getSelectedText();

					if(selection==null){
						return;
					}
					StringSelection clipString=new StringSelection(selection);
					clipboard.setContents(clipString,clipString);
				}
			}
		});
		menuEdit.add(menuItemCopy);

		JMenuItem menuItemPaste = new JMenuItem("Paste");
		menuItemPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("Paste")){
					Transferable clip_data = clipboard.getContents(this);
					
					try{
						String clip_string= (String)clip_data.getTransferData(DataFlavor.stringFlavor);
						textArea.replaceSelection(clip_string);						
					}
					catch(Exception exception){
						JOptionPane.showMessageDialog(null, exception
								, "Preview", JOptionPane.INFORMATION_MESSAGE
						);
					}
				}
			}
		});
		menuEdit.add(menuItemPaste);

		JMenu menuView = new JMenu("View");
		// mnView.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(menuView);

		JMenuItem menuItemPreview = new JMenuItem("Preview");
		menuItemPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "XML tree preview coming soon..."
						, "Preview", JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		menuView.add(menuItemPreview);

		JMenu menuHelp = new JMenu("Help");
		// mnHelp.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(menuHelp);

		JMenuItem menuAbout = new JMenuItem("About");
		menuAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Metadata Software tool - version alpha 1.0" + 
			"\n2018 March 15 Build\nBuilt by Team FE\nAuthors: Sanford Johnston, " + 
			"Jacob Espinoza, Isaias Gerena, Lucas Herrman\n" +
			"(Not for external distribution - Work in Progress)", "About", JOptionPane.INFORMATION_MESSAGE
						);
			}
		});
		menuHelp.add(menuAbout);
	}		
	
}
