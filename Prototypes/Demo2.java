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
import javax.swing.JDialog;
import javax.swing.JFileChooser;

import java.awt.Rectangle;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Component;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings({ "unused", "rawtypes" })
@Deprecated
public class Demo2
{

	private JFrame frameTeamFeMetadata;
	private File file;
	private String inputFile = "";
	private NodeList nList = null;
	private Node rootDOM = null;
	private MetadataNode rootMNode = null;
<<<<<<< Updated upstream
	private static MetadataNode currentNode = null;
	private MetaXMLParser parse = new MetaXMLParser();
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
=======
	private MetadataNode currentNode = null;
	private XmlSessionManager parse = new XmlSessionManager();
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();	
>>>>>>> Stashed changes
	private FileOps1 fileOperations = new FileOps1();
	private XmlSessionManager session1 = new XmlSessionManager();
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
		gbl_panel.columnWidths = new int[] { 120, 50, 80, 0, 50, 0, 90, 25, 0 };
		gbl_panel.rowHeights = new int[] { 33, 20, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0 };
		gbl_panel.columnWeights = new double[] { 4.0, 0.0, 0.0, 0.0, 20.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JLabel navLabel = new JLabel("Navigation");
		navLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		navLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		GridBagConstraints gbc_navLabel = new GridBagConstraints();
		gbc_navLabel.ipadx = 20;
		gbc_navLabel.anchor = GridBagConstraints.SOUTH;
		gbc_navLabel.insets = new Insets(0, 35, 5, 5);
		gbc_navLabel.gridx = 0;
		gbc_navLabel.gridy = 0;
		panel.add(navLabel, gbc_navLabel);

		JLabel elementLabel = new JLabel("Element names goes here:");
		elementLabel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		elementLabel.setBounds(new Rectangle(0, 5, 0, 0));
		elementLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_elementLabel = new GridBagConstraints();
		gbc_elementLabel.gridwidth = 4;
		gbc_elementLabel.anchor = GridBagConstraints.SOUTHWEST;
		gbc_elementLabel.insets = new Insets(0, 0, 5, 5);
		gbc_elementLabel.gridx = 2;
		gbc_elementLabel.gridy = 0;
		panel.add(elementLabel, gbc_elementLabel);

		JLabel questionLabel = new JLabel("Question goes here:");
		questionLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GridBagConstraints gbc_questionLabel = new GridBagConstraints();
		gbc_questionLabel.anchor = GridBagConstraints.WEST;
		gbc_questionLabel.gridwidth = 4;
		gbc_questionLabel.insets = new Insets(0, 0, 5, 5);
		gbc_questionLabel.gridx = 2;
		gbc_questionLabel.gridy = 2;
		panel.add(questionLabel, gbc_questionLabel);

		JList list = new JList();
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, null));
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 8;
		gbc_list.insets = new Insets(0, 10, 5, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 1;
		panel.add(list, gbc_list);

		JTextArea txtrEnterTextHere = new JTextArea();
		txtrEnterTextHere.setBorder(new EtchedBorder(EtchedBorder.LOWERED, Color.GRAY, null));
		txtrEnterTextHere.setFont(new Font("Monospaced", Font.PLAIN, 12));
		GridBagConstraints gbc_txtrEnterTextHere = new GridBagConstraints();
		gbc_txtrEnterTextHere.gridheight = 4;
		gbc_txtrEnterTextHere.gridwidth = 5;
		gbc_txtrEnterTextHere.insets = new Insets(0, 0, 5, 5);
		gbc_txtrEnterTextHere.fill = GridBagConstraints.BOTH;
		gbc_txtrEnterTextHere.gridx = 2;
		gbc_txtrEnterTextHere.gridy = 4;
		panel.add(txtrEnterTextHere, gbc_txtrEnterTextHere);

		JCheckBox chckbxVerified = new JCheckBox("Verified");
		chckbxVerified.setEnabled(false);
		chckbxVerified.setFocusable(false);
		GridBagConstraints gbc_chckbxVerified = new GridBagConstraints();
		gbc_chckbxVerified.gridwidth = 5;
		gbc_chckbxVerified.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxVerified.gridx = 2;
		gbc_chckbxVerified.gridy = 8;
		panel.add(chckbxVerified, gbc_chckbxVerified);

		MetadataNode page1 = new MetadataNode("First name", "First element name", "First question", "answer");
		MetadataNode page2 = new MetadataNode("Second name", "Second element name", "Second question", "answer");
		MetadataNode page3 = new MetadataNode("Third name", "Third element name", "Third question", "answer");
		page1.addChild(page2);
		page2.addChild(page3);
		page2.setParent(page1);
		page3.setParent(page2);
		currentNode = page1;
		elementLabel.setText(currentNode.getElementName());
		questionLabel.setText(currentNode.getQuestion());
		String elements[] = new String[] { page1.getElement(), page2.getElement(), page3.getElement() };
		list.setListData(elements);

		JButton prevButton = new JButton("Previous");
		prevButton.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_prevButton = new GridBagConstraints();
		gbc_prevButton.anchor = GridBagConstraints.EAST;
		gbc_prevButton.insets = new Insets(0, 0, 5, 5);
		gbc_prevButton.gridx = 3;
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
					currentNode = currentNode.getParent();
					elementLabel.setText(currentNode.getElementName());
					questionLabel.setText(currentNode.getQuestion());
				}
				else
				{
					
				}
			}

		});

		JButton saveButton = new JButton("Save");
		GridBagConstraints gbc_saveButton = new GridBagConstraints();
		gbc_saveButton.insets = new Insets(0, 0, 5, 5);
		gbc_saveButton.gridx = 4;
		gbc_saveButton.gridy = 9;
		panel.add(saveButton, gbc_saveButton);

		JButton nextButton = new JButton("Next");
		GridBagConstraints gbc_nextButton = new GridBagConstraints();
		gbc_nextButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_nextButton.insets = new Insets(0, 0, 5, 5);
		gbc_nextButton.gridx = 5;
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
					currentNode = currentNode.getChild();
					elementLabel.setText(currentNode.getElementName());
					questionLabel.setText(currentNode.getQuestion());
				}
				else
				{

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
									if ( SharedData.isTemplateSet() == true )
									{
										System.out.println("NewSession result: file was set");
										
										// call to create a document object model
										// uses the XmlSessionManager class
										System.out.println("Creating a document object model...");
										doc1 = session1.fileToDOM( SharedData.templateFile );
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
				// call the open code from FileOps1
				System.out.println("Open menu option clicked");
				// load metadata or session file into 'file'
				file = fileOperations.openFile(frameTeamFeMetadata);
				// if file is an xml, run Preview method

				// **DEBUG** //
				// System.out.print(file.toString());
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
				importFileChoose.setDialogTitle("Import");

				int importChooseReturnVal;
				File importF = null;

				importChooseReturnVal = importFileChoose.showOpenDialog(frameTeamFeMetadata);
				importF = importFileChoose.getSelectedFile();
				if (importF != null)
				{
					System.out.printf("File to be imported is %s\n", importF.toString());
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
				JOptionPane.showMessageDialog(null, "XML tree preview coming soon...", "Preview",
						JOptionPane.INFORMATION_MESSAGE);
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
						"Metadata Software tool - version alpha 3.3"
								+ "\n2018 April 13 Build\nBuilt by Team FE\nAuthors: Sanford Johnston, "
								+ "Jacob Espinoza, Isaias Gerena, Lucas Herrman\n"
								+ "(Not for external distribution - Work in Progress)",
						"About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuHelp.add(menuAbout);
	}

	public static void setCurrentNode(MetadataNode m)
	{
		currentNode = m;
	}
}