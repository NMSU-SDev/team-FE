import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
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
import javax.swing.Icon;
import javax.swing.ImageIcon;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.*;
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
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.Rectangle;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dialog;

import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

@SuppressWarnings({ "unused", "rawtypes" })
public class MainView
{

	private JFrame frameTeamFeMetadata;
	private MyTree tree;
	JScrollPane treeView;
	private File file;
	private String inputFile = "";
	private NodeList nList = null;
	private Node rootDOM = null;
	private MetadataNode rootMNode = new MetadataNode<Object>("root", "empty", (MetadataNode<?>) null, (MetadataNode<?>) null);
	/*
	 */
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	private MetadataNode currentNode = new MetadataNode<Object>("TOC", "Table of Contents", rootMNode, (MetadataNode<?>) null);
	private FileOps1 fileOperations = new FileOps1();
	private XmlSessionManager session1 = new XmlSessionManager();
	private NewSession newSession;
	private MetadataPreview preview;
	private int treeLength = 0;
	private static final int MAX = 10;
	
	private String[] templates = new String[ MAX ];
	
	/* ** GUI ELEMENTS, global vars **** */
	JLabel elementLabel = new JLabel(currentNode.getElementName());
	JTextArea questionLabel = new JTextArea(currentNode.getQuestion());
	JPanel panel = new JPanel();
	JTextArea txtInput = new JTextArea();
	JLabel navLabel = new JLabel();
	JCheckBox cBoxVerified = new JCheckBox();
	JButton prevButton = new JButton();
	JButton nextButton = new JButton();
	JButton saveButton = new JButton();
	GridBagLayout gbl_panel = new GridBagLayout();
	
	ImageIcon mIcon = null;
	Image ico = null;

	/* *** TEST VARIABLES  **** */
	private Document doc1 = null;
	private Document [] docs = new Document [MAX];
	Scanner scan = new Scanner(System.in);
	private boolean verifyCurrentNode = false;
	private JTextField textField;

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
					MainView window = new MainView();
					window.frameTeamFeMetadata.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public void setUI() {
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			System.out.println("Error setting native LAF: " + e);
		}
	}

	/**
	 * Create the application.
	 */
	public MainView()
	{
		setUI();
		initialize();
		displayWelcome();
	}
	
	/* Display a welcome dialog, or a "splash screen" */
	private void displayWelcome() {
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				Icon trueIcon = new ImageIcon( ico );
				
				String message = "Would you like to create a new session, open a previous session, or import a template?";
				Object options[] = { "New", "Open", "Import" };
				
				final JOptionPane welcomePane = new JOptionPane( 
						message, JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0] );
				
				// now a JDialog is used to be able to set a custom iconImage
				JDialog welcomeDialog = welcomePane.createDialog(frameTeamFeMetadata, "Welcome");
				welcomeDialog.setIconImage(ico);
				welcomeDialog.setVisible(true);
			
				String resultStr = "null";
				
				if(welcomePane.getValue() != null) resultStr = (String) welcomePane.getValue();
				
				System.out.printf("Welcome dialog result value is %s\n", resultStr);
				
				if (resultStr.equals("New") ) createNew();
				else if (resultStr.equals("Open")) open();
				else if (resultStr.equals("Import")) importFile();
			}
		});
		
	} // end displayWelcome()
	
	/* action done to create a new session */
	private void createNew() {
		// Open a new dialog window with two buttons: "USGS" and "non-USGS"
		System.out.println("Calling NewSession frame");
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					newSession = new NewSession(file);
					newSession.setVisible(true);
					newSession.addWindowListener(new WindowAdapter()
					{
						@Override
						public void windowClosed(WindowEvent e)
						{
							if (SharedData.isTemplateSet() == true)
							{
								System.out.println("NewSession result: file was set");
								file = SharedData.getTemplateFile();

								// call to create a document object model
								// uses the XmlSessionManager class
								doc1 = session1.fileToDOM(file);
								NodeList nList = null;
								Node nNode = null;
								nList = doc1.getElementsByTagName("metadata");
								nNode = nList.item(0);
								rootMNode = session1.importDOMToMetadata(nNode);
								
								currentNode = rootMNode;
								elementLabel.setText(currentNode.getElementName());
								questionLabel.setText(currentNode.getQuestion());
								tree = new MyTree(rootMNode);
								updateTree(tree);								
								showGUIFields();
							}
							else
								System.out.println("NewSession result: file was NOT set");
						}
					}); // end window listener for dialog

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			} // end run()
		});
	} // end createNew()
	
	/* action done to open an existing session or file */
	private void open() {
		String fName = "";
		System.out.println("Open menu option clicked");
		// call the open code from FileOps1
		// load metadata or session file into 'file'
		file = fileOperations.openFile(frameTeamFeMetadata);

		/* Must check to make sure the user selected a file */
		if (file != null)
			fName = file.getName();

		// if file is an XML, run Preview method
		if (fName.contains(".xml"))
		{
			try
			{
				Document doc1 = session1.fileToDOM( file );
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer();
				transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
				StringWriter writer = new StringWriter();
				transformer.transform(new DOMSource(doc1), new StreamResult(writer));
				String output = writer.getBuffer().toString();
				
				preview = new MetadataPreview( output, fName );
				preview.setVisible(true);
			}
			catch (TransformerException e)
			{
				e.printStackTrace();
			}
		}

		// else if file is an xsm, run openSession method
		else if (fName.contains(".xsm"))
		{
			rootMNode = session1.openSession(file, currentNode, templates);
		}

		else if ( file != null )
		{
			System.err.println("Not a session file");
			JOptionPane.showMessageDialog(null, "File selected was not a session file or an XML file!", "Open Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	} // end open
	
	public void importFile() {
		// action for when import is selected
		setUI();
		
		final JFileChooser importFileChoose = new JFileChooser();
		FileFilter xmlFilter = new FileNameExtensionFilter("XML File - eXtensible Markup Language (*.xml)",
				"xml");

		importFileChoose.setFileFilter(xmlFilter);
		// ** Force disable all files option
		importFileChoose.setAcceptAllFileFilterUsed(false);
		importFileChoose.setDialogTitle("Import");

		int importChooseReturnVal;
		File importF = null;

		importChooseReturnVal = importFileChoose.showOpenDialog(frameTeamFeMetadata);
		importF = importFileChoose.getSelectedFile();
	   
		
		if (importF != null)
		{
			templates[0] = importF.getAbsolutePath();
			setUI();
			
			try {
			
				doc1 = session1.fileToDOM(importF);
				NodeList nList = null;
				Node nNode = null;
				nList = doc1.getElementsByTagName("metadata");
				nNode = nList.item(0);
				rootMNode = session1.importDOMToMetadata(nNode);
				currentNode = rootMNode;
						
				// Moved creation of the table of contents to create on import
				// instead of on program start
				tree = new MyTree(rootMNode);			
				elementLabel.setText(currentNode.getElementName());
				questionLabel.setText(currentNode.getQuestion());
				updateTree(tree);
			
				showGUIFields();
			} catch ( NullPointerException  npEx) {
				System.err.println("A null pointer exeception occurred during import!");
				npEx.printStackTrace();
				String msg = "Document Object Model created from XML is corrupt\nTry another file";
				JOptionPane.showMessageDialog(null, msg, "Import Failed",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		else
			System.out.println("No file was selected.");

		if (importChooseReturnVal == JFileChooser.CANCEL_OPTION)
		{
			System.out.println("User cancelled file selection.");
		}
	} // end importFile();

	// Method to show all of the GUI fields,
	//  done after a successful import or new project.
	private void showGUIFields() 
	{
		elementLabel.setVisible(true);
		questionLabel.setVisible(true);
		txtInput.setVisible(true);
		navLabel.setVisible(true);
		cBoxVerified.setVisible(true);
		prevButton.setVisible(true);
		nextButton.setVisible(true);
		saveButton.setVisible(true);
		
		txtInput.setEnabled(true);
		cBoxVerified.setEnabled(true);
		
	}
	
	// method that will update the tree
	private void updateTree(MyTree tree2) 
	{
		GridBagConstraints gbc_tree = new GridBagConstraints();
		gbc_tree.gridwidth = 3;
		gbc_tree.gridheight = 9;
		gbc_tree.insets = new Insets(0, 0, 0, 0);
		
		/** Grid bag constraints still will not resize horizontally */

		gbc_tree.weightx = 1.0;
		gbc_tree.fill = GridBagConstraints.BOTH;
		gbc_tree.gridx = 1;
		gbc_tree.gridy = 1;
		panel.add(tree2, gbc_tree);

		// Create the scroll pane and add the tree to it.
		treeView = new JScrollPane();

		GridBagConstraints gbc_treeView = new GridBagConstraints();
		gbc_treeView.gridwidth = 3;
		gbc_treeView.gridheight = 9;
		gbc_treeView.insets = new Insets(0, 0, 0, 0);
		gbc_treeView.weightx = 1.0;
		gbc_treeView.fill = GridBagConstraints.BOTH;
		gbc_treeView.gridx = 1;
		gbc_treeView.gridy = 1;
		panel.add(treeView, gbc_treeView);
	
	}

	/**
	 * Initialize the contents of the frameTeamFeMetadata.
	 */
	private void initialize()
	{
		ico = null;
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			System.out.println("Error setting native LAF: " + e);
		}
		frameTeamFeMetadata = new JFrame();
	
		// Set iconImage using get resource as stream and reading the image
		InputStream in = getClass().getResourceAsStream("FEIcon1.png");
		try {
			ico = ImageIO.read( in );
			mIcon = new ImageIcon(ico);
			
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1, "IOException Error!",
					JOptionPane.ERROR_MESSAGE);
		}
		
		frameTeamFeMetadata.setIconImage(ico);
		
		
		frameTeamFeMetadata.setTitle("Team FE Metadata Project [Pre-Release]");
		frameTeamFeMetadata.setBounds(100, 100, 650, 550);
		frameTeamFeMetadata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameTeamFeMetadata.getContentPane().setLayout(new CardLayout(0, 0));
		frameTeamFeMetadata.setLocationRelativeTo(null);

		
		frameTeamFeMetadata.getContentPane().add(panel, "name_1876606686560390");
		gbl_panel.columnWidths = new int[] { 20, 10, 150, 10, 30, 40, 20, 50, 20, 40, 20, 0 };
		gbl_panel.rowHeights = new int[] { 33, 20, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 1.0, 2.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		navLabel.setText("Navigation");
		navLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		navLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_navLabel = new GridBagConstraints();
		gbc_navLabel.ipadx = 20;
		gbc_navLabel.anchor = GridBagConstraints.SOUTH;
		gbc_navLabel.insets = new Insets(0, 5, 5, 5);
		gbc_navLabel.gridx = 2;
		gbc_navLabel.gridy = 0;
		panel.add(navLabel, gbc_navLabel);
		navLabel.setVisible(false);

		
		elementLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		elementLabel.setBounds(new Rectangle(0, 5, 0, 0));
		elementLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_elementLabel = new GridBagConstraints();
		gbc_elementLabel.gridwidth = 4;
		gbc_elementLabel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_elementLabel.insets = new Insets(0, 0, 5, 5);
		gbc_elementLabel.gridx = 5;
		gbc_elementLabel.gridy = 0;
		panel.add(elementLabel, gbc_elementLabel);
		elementLabel.setVisible(false);

		questionLabel.setEditable(false);
		questionLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_questionLabel = new GridBagConstraints();
		questionLabel.setLineWrap(true);
		questionLabel.setWrapStyleWord(true);
		gbc_questionLabel.anchor = GridBagConstraints.WEST;
		gbc_questionLabel.gridwidth = 5;
		gbc_questionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_questionLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_questionLabel.gridx = 5;
		gbc_questionLabel.gridy = 1;
		panel.add(questionLabel, gbc_questionLabel);
		questionLabel.setVisible(false);
		
		txtInput.setLineWrap(true);
		txtInput.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, null));
		txtInput.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtInput.setEnabled(false);
		GridBagConstraints gbc_txtInput = new GridBagConstraints();
		gbc_txtInput.gridheight = 4;
		gbc_txtInput.gridwidth = 5;
		gbc_txtInput.insets = new Insets(0, 0, 5, 5);
		gbc_txtInput.fill = GridBagConstraints.BOTH;
		gbc_txtInput.gridx = 5;
		gbc_txtInput.gridy = 4;
		panel.add(txtInput, gbc_txtInput);
		txtInput.setVisible(false);

		cBoxVerified.setText("Verified");
		cBoxVerified.setFocusable(false);
		cBoxVerified.setEnabled(false);
		GridBagConstraints gbc_cBoxVerified = new GridBagConstraints();
		gbc_cBoxVerified.insets = new Insets(0, 0, 5, 5);
		gbc_cBoxVerified.gridx = 7;
		gbc_cBoxVerified.gridy = 8;
		panel.add(cBoxVerified, gbc_cBoxVerified);
		cBoxVerified.setVisible(false);
		
		cBoxVerified.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						verifyCurrentNode = true;
					}
			
				});

		prevButton.setText("Previous");
		prevButton.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_prevButton = new GridBagConstraints();
		gbc_prevButton.anchor = GridBagConstraints.EAST;
		gbc_prevButton.insets = new Insets(0, 0, 5, 5);
		gbc_prevButton.gridx = 6;
		gbc_prevButton.gridy = 9;
		panel.add(prevButton, gbc_prevButton);
		prevButton.setVisible(false);
		prevButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				MetadataNode tempNode;
				tempNode = currentNode.getParent();
				if (tempNode != null)
				{
					currentNode.setVerified(verifyCurrentNode);
					currentNode.setAnswer(txtInput.getText());
					
					System.out.println(currentNode.getAnswer());
					System.out.println(currentNode.getVerified());
					//These test the two lines above
					
					currentNode = currentNode.getParent();
					cBoxVerified.setSelected(currentNode.getVerified() );
					elementLabel.setText(currentNode.getElementName());
					questionLabel.setText(currentNode.getQuestion());
					txtInput.setText(currentNode.getAnswer());
				}
				else
				{
					// do nothing
				}
			}

		});

		saveButton.setText("Save");
		saveButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.insets = new Insets(0, 0, 5, 5);
		gbc_saveButton.gridx = 7;
		gbc_saveButton.gridy = 9;
		panel.add(saveButton, gbc_saveButton);
		saveButton.setVisible(false);
		
		saveButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{
						currentNode.setVerified(verifyCurrentNode);
						currentNode.setAnswer(txtInput.getText());
						System.out.println(currentNode.getAnswer());
						System.out.println(currentNode.getVerified());
						//These test the two lines above
						session1.saveMetadataToDOM(rootMNode, doc1);
					}
			
				});

		nextButton.setText("Next");
		GridBagConstraints gbc_nextButton = new GridBagConstraints();
		gbc_nextButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_nextButton.insets = new Insets(0, 0, 5, 5);
		gbc_nextButton.gridx = 8;
		gbc_nextButton.gridy = 9;
		panel.add(nextButton, gbc_nextButton);
		nextButton.setVisible(false);
		
		nextButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				MetadataNode tempNode = currentNode;
				currentNode.setVerified(verifyCurrentNode);
				currentNode.setAnswer(txtInput.getText());
				txtInput.setText("\n");
				if (currentNode.getChild() != null)
				{
					// gets next tree child
					tempNode = currentNode.getChild();
				}
				else if (currentNode.getSibling() != null)
				{
					// gets next sibling
					tempNode = tempNode.getSibling();
				}
				else if (currentNode.getParent().getSibling() != null)
				{
					// gets next sibling of parent	
					tempNode = tempNode.getParent().getSibling();
				}
				else if (tempNode.getParent() != null)
				{					
					while (tempNode.getParent().getSibling() == null && tempNode.getParent() != null )
						tempNode = tempNode.getParent();
					tempNode = tempNode.getParent().getSibling();
				}
				
				if (tempNode != null)
				{
					System.out.println((currentNode.getParent() != null) ? ("Parent: " + currentNode.getParent().getElement() + "\n ^") : "No Parent\n ^");
					System.out.print((currentNode.getSibling() != null) ? ("Sibling: " + currentNode.getSibling().getElement() + " <- ") : "No Sibling <- ");
					System.out.print(" Name: " + currentNode.getElement());
					System.out.print(" -> Answer: " + currentNode.getAnswer());
					System.out.println(" -> Verified: " + currentNode.getVerified());					
					System.out.println((currentNode.getChild() != null) ? (" v\nChild: " + currentNode.getChild().getElement()) : "No Child");
					
					currentNode = tempNode;
					elementLabel.setText(currentNode.getElementName());
					questionLabel.setText(currentNode.getQuestion());
					cBoxVerified.setSelected( currentNode.getVerified() );
				}				
			}

		});

		/***** MENU BAR and new menu option *****/

		JMenuBar menuBar = new JMenuBar();
		frameTeamFeMetadata.setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("File");
		// mnFile.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(menuFile);

		JMenuItem menuItemNew = new JMenuItem("New");
		menuItemNew.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				createNew();
			} // end new menu action performed
		});
		menuFile.add(menuItemNew);

		JMenuItem menuItemOpen = new JMenuItem("Open");
		menuItemOpen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				open();
			} // end action performed for open file menu
		});
		menuFile.add(menuItemOpen);

		JMenuItem menuItemSave = new JMenuItem("Save");
		menuItemSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// call SaveSession() method that creates a String object of this session
				String session = session1.saveSession(rootMNode, currentNode, templates);				
				// call FileOps1 save file method
				fileOperations.saveFile(file, session, frameTeamFeMetadata);
			}
		});
		menuFile.add(menuItemSave);

		JMenuItem menuItemImport = new JMenuItem("Import");
		menuItemImport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				importFile();
			}
		});
		menuFile.add(menuItemImport);

		JMenuItem menuItemExport = new JMenuItem("Export");
		menuItemExport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				/* *** This section still needs some work */
				final JFileChooser exportFileChoose = new JFileChooser();
				File exportF = null;
				FileFilter xmlFilter = new FileNameExtensionFilter("XML File - eXtensible Markup Language (*.xml)",
						"xml");

				exportFileChoose.setFileFilter(xmlFilter);
				exportFileChoose.setDialogTitle("Select location for export...");

				String [] outputList = new String [MAX];
				int exportChooseReturnVal;
				docs [0] = doc1;
				try {
				outputList = session1.exportXMLFiles(docs, templates, "12345678");
				exportF = new File(outputList[0]);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
				

				exportChooseReturnVal = exportFileChoose.showSaveDialog(frameTeamFeMetadata);
				exportF = exportFileChoose.getSelectedFile();
				if (exportF != null)
				{
					TransformerFactory transFactory = TransformerFactory.newInstance();
					try {
						// Create Transformer
						Transformer trans = transFactory.newTransformer();
						// Transform each Document to a Result
						for (int index = 0; index < docs.length; index++) {
							DOMSource source = new DOMSource(docs[index]);
							// This is where the magic happens
							StreamResult result = new StreamResult(new File(outputList[index])); 
							trans.transform(source, result);				
						}
					} catch (TransformerException e) {
						e.printStackTrace();
					} 				
				}
				else
					System.out.println("No file was selected.");

				if (exportChooseReturnVal == JFileChooser.CANCEL_OPTION)
				{
					System.out.println("User cancelled export.");
				}
			}
		});
		menuFile.add(menuItemExport);
		
		/* *****************

		JMenuItem menuAdd = new JMenuItem("Add");
		menuAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JOptionPane.showMessageDialog(null, "Add XML to Session action coming soon...", "Add XML to Session",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuFile.add(menuAdd);
		************/

		JMenu menuEdit = new JMenu("Edit");
		// mnEdit.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(menuEdit);

		JMenuItem menuItemCut = new JMenuItem("Cut");
		menuItemCut.addActionListener(new ActionListener()
		{
			// Cut command
			public void actionPerformed(ActionEvent e)
			{
				if (e.getActionCommand().equals("Cut"))
				{

					String selection = txtInput.getSelectedText();

					if (selection == null)
					{
						return;
					}
					StringSelection clipString = new StringSelection(selection);
					clipboard.setContents(clipString, clipString);
					txtInput.replaceSelection("");
				}
			}
		});
		menuEdit.add(menuItemCut);

		JMenuItem menuItemCopy = new JMenuItem("Copy");
		menuItemCopy.addActionListener(new ActionListener()
		{
			// Copy command
			public void actionPerformed(ActionEvent e)
			{
				if (e.getActionCommand().equals("Copy"))
				{

					String selection = txtInput.getSelectedText();

					if (selection == null)
					{
						return;
					}
					StringSelection clipString = new StringSelection(selection);
					clipboard.setContents(clipString, clipString);
				}
			}
		});
		menuEdit.add(menuItemCopy);

		JMenuItem menuItemPaste = new JMenuItem("Paste");
		menuItemPaste.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (e.getActionCommand().equals("Paste"))
				{
					Transferable clip_data = clipboard.getContents(this);

					try
					{
						String clip_string = (String) clip_data.getTransferData(DataFlavor.stringFlavor);
						txtInput.replaceSelection(clip_string);
					}
					catch (Exception exception)
					{
						JOptionPane.showMessageDialog(null, exception, "Preview", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		menuEdit.add(menuItemPaste);

		JMenu menuView = new JMenu("View");
		menuBar.add(menuView);

		JMenuItem menuItemPreview = new JMenuItem("Preview");
		menuItemPreview.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				EventQueue.invokeLater(new Runnable()
				{
					public void run()
					{
						try
							{
							String previewStr = "";
							previewStr = session1.metadataTreeToString(rootMNode);
							
							preview = new MetadataPreview( previewStr, "" );
							preview.setVisible(true);
							preview.addWindowListener(new WindowAdapter()
							{
								@Override
								public void windowClosed(WindowEvent e)
								{
									System.out.println("Preview window closed");
								}
							}); // end window listener for preview
							
							} catch (Exception e) {
								e.printStackTrace();
							}
					} // end run()
				}); // end new Runnable
			} // end actionPerformed
		}); //end addActionListener
		menuView.add(menuItemPreview);

		JMenu menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);

		JMenuItem menuAbout = new JMenuItem("About");
		menuAbout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				Image newimg = ico.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH); // scale icon the smooth way  
				ImageIcon smallmIcon = new ImageIcon(newimg);  // transform it back to an ImageIcon type
				
				JOptionPane.showMessageDialog(null,
						"Metadata Software tool - version alpha 6.0"
								+ "\n2018 May 15 Build\nBuilt by Team FE\nAuthors: Sanford Johnston, "
								+ "Jacob Espinoza, Isaias Gerena, Lucas Herrman\n"
								+ "(Not for production use - In development)",
						"About", JOptionPane.INFORMATION_MESSAGE, smallmIcon);
			}
		});
		menuHelp.add(menuAbout);

	} // *** end initalize() method

	public void setCurrentNode(MetadataNode m)
	{
		currentNode = m;
	}

}