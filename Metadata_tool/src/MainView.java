import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagLayout;

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
	private String[] templates;
	private String inputFile = "";
	private NodeList nList = null;
	private Node rootDOM = null;
	private MetadataNode rootMNode = new MetadataNode("root", "empty", (MetadataNode) null, (MetadataNode) null);
	/*
	 */
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	private MetadataNode currentNode = new MetadataNode("TOC", "Table of Contents", rootMNode, (MetadataNode) null);
	private FileOps1 fileOperations = new FileOps1();
	private XmlSessionManager session1 = new XmlSessionManager();
	private NewSession newSession;
	private MetadataPreview preview;
	private int treeLength = 0;

	// TEST VARIABLES //
	private Document doc1 = null;
	private Document doc2 = null;
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

	/**
	 * Create the application.
	 */
	public MainView()
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
		}
		catch (Exception e)
		{
			System.out.println("Error setting native LAF: " + e);
		}
		frameTeamFeMetadata = new JFrame();
		frameTeamFeMetadata.setTitle("Team FE Metadata Project [Pre-Release]");
		frameTeamFeMetadata.setBounds(100, 100, 600, 555);
		frameTeamFeMetadata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameTeamFeMetadata.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel panel = new JPanel();
		frameTeamFeMetadata.getContentPane().add(panel, "name_1876606686560390");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 10, 120, 40, 80, 0, 62, 0, 90, 25, 0 };
		gbl_panel.rowHeights = new int[] { 33, 20, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 20.0, 0.0, 1.0, 0.0, 20.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		
		JTextArea openingScreen = new JTextArea();
		openingScreen.setFont(new Font("Arial", Font.PLAIN, 13));
		openingScreen.setEditable(false);
		Border textBorder = BorderFactory.createLineBorder(Color.BLACK, 2, true);
		openingScreen.setBorder( BorderFactory.createCompoundBorder(textBorder, BorderFactory.createEmptyBorder(5, 5, 5, 5)));
		//** old border setting
		// openingScreen.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		GridBagConstraints gbc_openingScreen = new GridBagConstraints();
		gbc_openingScreen.gridheight = 2;
		gbc_openingScreen.gridwidth = 5;
		openingScreen.setLineWrap(true);
		openingScreen.setWrapStyleWord(true);
		gbc_openingScreen.insets = new Insets(0, 0, 5, 5);
		gbc_openingScreen.fill = GridBagConstraints.BOTH;
		gbc_openingScreen.gridx = 3;
		gbc_openingScreen.gridy = 2;
		panel.add(openingScreen, gbc_openingScreen);
		openingScreen.setColumns(10);
		openingScreen.setText("Create new session, open previous session, or import template to proceed...");

		JLabel navLabel = new JLabel("Navigation");
		navLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		navLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_navLabel = new GridBagConstraints();
		gbc_navLabel.ipadx = 20;
		gbc_navLabel.anchor = GridBagConstraints.SOUTH;
		gbc_navLabel.insets = new Insets(0, 5, 5, 5);
		gbc_navLabel.gridx = 1;
		gbc_navLabel.gridy = 0;
		panel.add(navLabel, gbc_navLabel);

		JLabel elementLabel = new JLabel(currentNode.getElementName());
		elementLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		elementLabel.setBounds(new Rectangle(0, 5, 0, 0));
		elementLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_elementLabel = new GridBagConstraints();
		gbc_elementLabel.gridwidth = 4;
		gbc_elementLabel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_elementLabel.insets = new Insets(0, 0, 5, 5);
		gbc_elementLabel.gridx = 3;
		gbc_elementLabel.gridy = 0;
		panel.add(elementLabel, gbc_elementLabel);

		/*
		 * Moved creation of the TOC to import behavior - Lucas
		 * 
		 * tree = new MyTree(rootMNode);
		 * 
		 * GridBagConstraints gbc_tree = new GridBagConstraints();
		 * gbc_tree.gridwidth = 2; gbc_tree.gridheight = 9; gbc_tree.insets =
		 * new Insets(0, 0, 5, 5); gbc_tree.fill = GridBagConstraints.BOTH;
		 * gbc_tree.gridx = 0; gbc_tree.gridy = 1; panel.add(tree, gbc_tree); //
		 * Create the scroll pane and add the tree to it. treeView = new
		 * JScrollPane(); // JScrollPane scrollPane = new JScrollPane();
		 * GridBagConstraints gbc_treeView = new GridBagConstraints();
		 * gbc_treeView.gridwidth = 2; gbc_treeView.gridheight = 9;
		 * gbc_treeView.insets = new Insets(0, 0, 5, 5); gbc_treeView.fill =
		 * GridBagConstraints.BOTH; gbc_treeView.gridx = 0; gbc_treeView.gridy =
		 * 1; panel.add(treeView, gbc_treeView); //
		 * treeView.setViewportView(tree); // scrollPane.setViewportView(tree);
		 */
		JTextArea questionLabel = new JTextArea(currentNode.getQuestion());
		questionLabel.setEditable(false);
		questionLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_questionLabel = new GridBagConstraints();
		questionLabel.setLineWrap(true);
		questionLabel.setWrapStyleWord(true);
		gbc_questionLabel.anchor = GridBagConstraints.WEST;
		gbc_questionLabel.gridwidth = 5;
		gbc_questionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_questionLabel.fill = GridBagConstraints.HORIZONTAL;
		gbc_questionLabel.gridx = 3;
		gbc_questionLabel.gridy = 1;
		panel.add(questionLabel, gbc_questionLabel);
		
		JTextArea txtrEnterTextHere = new JTextArea();
		txtrEnterTextHere.setLineWrap(true);
		txtrEnterTextHere.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, null));
		txtrEnterTextHere.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrEnterTextHere.setEnabled(false);
		GridBagConstraints gbc_txtrEnterTextHere = new GridBagConstraints();
		gbc_txtrEnterTextHere.gridheight = 4;
		gbc_txtrEnterTextHere.gridwidth = 5;
		gbc_txtrEnterTextHere.insets = new Insets(0, 0, 5, 5);
		gbc_txtrEnterTextHere.fill = GridBagConstraints.BOTH;
		gbc_txtrEnterTextHere.gridx = 3;
		gbc_txtrEnterTextHere.gridy = 4;
		panel.add(txtrEnterTextHere, gbc_txtrEnterTextHere);

		JCheckBox chckbxVerified = new JCheckBox("Verified");
		chckbxVerified.setFocusable(false);
		chckbxVerified.setEnabled(false);
		GridBagConstraints gbc_chckbxVerified = new GridBagConstraints();
		gbc_chckbxVerified.gridwidth = 5;
		gbc_chckbxVerified.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxVerified.gridx = 3;
		gbc_chckbxVerified.gridy = 8;
		panel.add(chckbxVerified, gbc_chckbxVerified);
		
		chckbxVerified.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0)
					{
						// TODO Auto-generated method stub
						verifyCurrentNode = true;
					}
			
				});

		/*
		 * MetadataNode page1 = new MetadataNode("First name",
		 * "First element name", "First question", "answer"); MetadataNode page2
		 * = new MetadataNode("Second name", "Second element name",
		 * "Second question", "answer"); MetadataNode page3 = new MetadataNode(
		 * "Third name", "Third element name", "Third question", "answer");
		 * page1.addChild(page2); page2.addChild(page3); page2.setParent(page1);
		 * page3.setParent(page2); currentNode = page1;
		 * elementLabel.setText(currentNode.getElementName());
		 * questionLabel.setText(currentNode.getQuestion()); String elements[] =
		 * new String[] { page1.getElement(), page2.getElement(),
		 * page3.getElement() }; list.setListData(elements);
		 */

		JButton prevButton = new JButton("Previous");
		prevButton.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_prevButton = new GridBagConstraints();
		gbc_prevButton.anchor = GridBagConstraints.EAST;
		gbc_prevButton.insets = new Insets(0, 0, 5, 5);
		gbc_prevButton.gridx = 4;
		gbc_prevButton.gridy = 9;
		panel.add(prevButton, gbc_prevButton);
		prevButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				MetadataNode tempNode;
				tempNode = currentNode.getParent();
				if (tempNode != null)
				{
					currentNode.setVerified(verifyCurrentNode);
					currentNode.setAnswer(txtrEnterTextHere.getText());
					//txtrEnterTextHere.setText("");
					chckbxVerified.setSelected(false);
					System.out.println(currentNode.getAnswer());
					System.out.println(currentNode.getVerified());
					//These test the two lines above
					
					currentNode = currentNode.getParent();
					elementLabel.setText(currentNode.getElementName());
					questionLabel.setText(currentNode.getQuestion());
					txtrEnterTextHere.setText(currentNode.getAnswer());
				}
				else
				{
					// do nothing
				}
			}

		});

		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.insets = new Insets(0, 0, 5, 5);
		gbc_saveButton.gridx = 5;
		gbc_saveButton.gridy = 9;
		panel.add(saveButton, gbc_saveButton);
		
		saveButton.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e)
					{
						// TODO Auto-generated method stub
						currentNode.setVerified(verifyCurrentNode);
						currentNode.setAnswer(txtrEnterTextHere.getText());
						System.out.println(currentNode.getAnswer());
						System.out.println(currentNode.getVerified());
						//These test the two lines above
					}
			
				});

		JButton nextButton = new JButton("Next");
		GridBagConstraints gbc_nextButton = new GridBagConstraints();
		gbc_nextButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_nextButton.insets = new Insets(0, 0, 5, 5);
		gbc_nextButton.gridx = 6;
		gbc_nextButton.gridy = 9;
		panel.add(nextButton, gbc_nextButton);
		nextButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				// TODO Auto-generated method stub
				MetadataNode tempNode;
				tempNode = currentNode.getChild();
				if (tempNode != null)
				{
					// need to save changes in MetadataNode to DOM first
					session1.saveMetadataToDOM(rootMNode, doc1);

					currentNode.setVerified(verifyCurrentNode);
					currentNode.setAnswer(txtrEnterTextHere.getText());
					txtrEnterTextHere.setText("");
					chckbxVerified.setSelected(false);
					System.out.println(currentNode.getAnswer());
					System.out.println(currentNode.getVerified());
					//These test the two lines above
					
					currentNode = tempNode;
					elementLabel.setText(currentNode.getElementName());
					questionLabel.setText(currentNode.getQuestion());
				}
				else
				{
					tempNode = currentNode.getSibling();
					if (tempNode != null)
					{
						currentNode.setVerified(verifyCurrentNode);
						currentNode.setAnswer(txtrEnterTextHere.getText());
						//txtrEnterTextHere.setText("");
						chckbxVerified.setSelected(false);
						System.out.println(currentNode.getAnswer());
						System.out.println(currentNode.getVerified());
						//These test the two lines above
						
						currentNode = tempNode;
						elementLabel.setText(currentNode.getElementName());
						questionLabel.setText(currentNode.getQuestion());
						txtrEnterTextHere.setText(currentNode.getAnswer());
					}
				}
			}

		});
		
		elementLabel.setVisible(false);
		questionLabel.setVisible(false);
		txtrEnterTextHere.setVisible(false);
		navLabel.setVisible(false);
		chckbxVerified.setVisible(false);
		prevButton.setVisible(false);
		nextButton.setVisible(false);
		saveButton.setVisible(false);
		

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
				// Open a new dialog window with two buttons: "USGS" and
				// "non-USGS"
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

										// call to create a document object
										// model
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
										// System.out.println("Creating a document object model...");
										// doc1 = session1.fileToDOM(SharedData.templateFile);									
										
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
					}
				});

			} // end new menu action performed
		});
		menuFile.add(menuItemNew);

		JMenuItem menuItemOpen = new JMenuItem("Open");
		menuItemOpen.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String extension = "";
				// call the open code from FileOps1
				System.out.println("Open menu option clicked");
				// load metadata or session file into 'file'
				file = fileOperations.openFile(frameTeamFeMetadata);

				/* Must check to make sure the user selected a file */
				if (file != null)
					extension = file.getName();

				// if file is an XML, run Preview method
				if (extension.contains(".xml"))
				{
					try
					{
						Desktop.getDesktop().open(file);
					}
					catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				// else if file is an xsm, run openSession method
				else if (extension.contains(".xsm"))
				{
					rootMNode = session1.openSession(file, currentNode, templates);
				}

				else
				{
					System.err.println("Not a session file");
					JOptionPane.showMessageDialog(null, "File selected was not a session file!", "Open Failed",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		menuFile.add(menuItemOpen);

		JMenuItem menuItemSave = new JMenuItem("Save");
		menuItemSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// call SaveSession() method that creates a File object to send
				// to the saveFile(File) method
				// call FileOps1 save file method
				fileOperations.saveFile(file);
			}
		});
		menuFile.add(menuItemSave);

		JMenuItem menuItemImport = new JMenuItem("Import");
		menuItemImport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// action for when import is selected
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
					doc1 = session1.fileToDOM(importF);
					NodeList nList = null;
					Node nNode = null;
					nList = doc1.getElementsByTagName("metadata");
					nNode = nList.item(0);
					rootMNode = session1.importDOMToMetadata(nNode);
					currentNode = rootMNode;
					elementLabel.setText(currentNode.getElementName());
					questionLabel.setText(currentNode.getQuestion());

					// Moved creation of the table of contents to create on
					// import
					// instead of on program start
					tree = new MyTree(rootMNode);

					GridBagConstraints gbc_tree = new GridBagConstraints();
					gbc_tree.gridwidth = 1;
					gbc_tree.gridheight = 9;
					gbc_tree.insets = new Insets(0, 0, 0, 0);
			/* ***** Grid bag constraints still will not resize horizontally */
					gbc_tree.weightx = 1.0;
					gbc_tree.fill = GridBagConstraints.BOTH;
					gbc_tree.gridx = 1;
					gbc_tree.gridy = 1;
					panel.add(tree, gbc_tree);

					// Create the scroll pane and add the tree to it.
					treeView = new JScrollPane();

					GridBagConstraints gbc_treeView = new GridBagConstraints();
					gbc_treeView.gridwidth = 1;
					gbc_treeView.gridheight = 9;
					gbc_treeView.insets = new Insets(0, 0, 0, 0);
					gbc_treeView.weightx = 1.0;
					gbc_treeView.fill = GridBagConstraints.BOTH;
					gbc_treeView.gridx = 1;
					gbc_treeView.gridy = 1;
					panel.add(treeView, gbc_treeView);
					
					elementLabel.setVisible(true);
					questionLabel.setVisible(true);
					txtrEnterTextHere.setVisible(true);
					navLabel.setVisible(true);
					chckbxVerified.setVisible(true);
					prevButton.setVisible(true);
					nextButton.setVisible(true);
					saveButton.setVisible(true);
					txtrEnterTextHere.setEnabled(true);
					chckbxVerified.setEnabled(true);
					openingScreen.setVisible(false);
				}
				else
					System.out.println("No file was selected.");

				if (importChooseReturnVal == JFileChooser.CANCEL_OPTION)
				{
					System.out.println("User cancelled file selection.");
				}
			}
		});
		menuFile.add(menuItemImport);

		JMenuItem menuItemExport = new JMenuItem("Export");
		menuItemExport.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				final JFileChooser exportFileChoose = new JFileChooser();
				FileFilter xmlFilter = new FileNameExtensionFilter("XML File - eXtensible Markup Language (*.xml)",
						"xml");

				exportFileChoose.setFileFilter(xmlFilter);
				exportFileChoose.setDialogTitle("Selection location for export...");

				int exportChooseReturnVal;
				File exportF = null;

				exportChooseReturnVal = exportFileChoose.showSaveDialog(frameTeamFeMetadata);
				exportF = exportFileChoose.getSelectedFile();
				if (exportF != null)
				{
					System.out.printf("File to be exported is %s\n", exportF.toString());
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

					String selection = txtrEnterTextHere.getSelectedText();

					if (selection == null)
					{
						return;
					}
					StringSelection clipString = new StringSelection(selection);
					clipboard.setContents(clipString, clipString);
					txtrEnterTextHere.replaceSelection("");
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

					String selection = txtrEnterTextHere.getSelectedText();

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
						txtrEnterTextHere.replaceSelection(clip_string);
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
				try
				{
							preview = new MetadataPreview(session1.metadataTreeToString(rootMNode));
							preview.setVisible(true);								
							//JOptionPane.showMessageDialog(null, "XML tree preview coming soon...", "Preview",
							//JOptionPane.INFORMATION_MESSAGE);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}	
		});
		menuView.add(menuItemPreview);

		JMenu menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);

		JMenuItem menuAbout = new JMenuItem("About");
		menuAbout.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				JOptionPane.showMessageDialog(null,
						"Metadata Software tool - version alpha 4.0"
								+ "\n2018 April 27 Build\nBuilt by Team FE\nAuthors: Sanford Johnston, "
								+ "Jacob Espinoza, Isaias Gerena, Lucas Herrman\n"
								+ "(Not for external distribution - Work in Progress)",
						"About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuHelp.add(menuAbout);

		// This block below is in development, it will never run
		// Please do not remove it or comment it out
		if (false)
		{
			treeLength = 0;
			MetadataNode tempNode1 = currentNode;
			while ((tempNode1 != null) && (tempNode1.getChild() != null))
			{
				treeLength++;
				tempNode1 = tempNode1.getChild();
			}
			if (treeLength == 0)
			{
				// list.setVisible(false);
				elementLabel.setVisible(false);
				questionLabel.setVisible(false);
				txtrEnterTextHere.setVisible(false);
				navLabel.setVisible(false);
				chckbxVerified.setVisible(false);
				prevButton.setVisible(false);
				nextButton.setVisible(false);
				saveButton.setVisible(false);
			}
			else
			{
				tempNode1 = currentNode;
				String elementNames[] = new String[treeLength];

				for (int i = 0; i < treeLength; i++)
				{
					elementNames[i] = tempNode1.getElement();
				}
				// list.setVisible(true);
				elementLabel.setVisible(true);
				questionLabel.setVisible(true);
				txtrEnterTextHere.setVisible(true);
				navLabel.setVisible(true);
				chckbxVerified.setVisible(true);
				prevButton.setVisible(true);
				nextButton.setVisible(true);
				saveButton.setVisible(true);

				// list.setVisibleRowCount(treeLength);
				// list.setListData(elementNames);
				elementLabel.setText(currentNode.getElement());
				questionLabel.setText(currentNode.getQuestion());
			}
		}
	}

	public void setCurrentNode(MetadataNode m)
	{
		currentNode = m;
	}

}