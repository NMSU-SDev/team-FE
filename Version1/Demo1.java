import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.AbstractListModel;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Demo1
{

	private JFrame frmTeamFeMetadata;

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
					Demo1 window = new Demo1();
					window.frmTeamFeMetadata.setVisible(true);
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
	public Demo1()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize()
	{
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch(Exception e) {
	        System.out.println("Error setting native LAF: " + e);
	    }
		
		frmTeamFeMetadata = new JFrame();
		frmTeamFeMetadata.setTitle("Team FE Metadata tool - Demo 1");
		frmTeamFeMetadata.setBounds(100, 100, 700, 579);
		frmTeamFeMetadata.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTeamFeMetadata.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel panel = new JPanel();
		frmTeamFeMetadata.getContentPane().add(panel, "name_1876606686560390");
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel.setLayout(gbl_panel);

		JList list = new JList();
		// list.setFixedCellWidth(50);
		// list.setFixedCellHeight(65);
		list.setModel(new AbstractListModel()
		{
			String[] values = new String[] { "TOC", "Page 1", "Page 2", "Page 3", "Page 4", "Page 5", "Page 6",
					"Page 7" };

			public int getSize()
			{
				return values.length;
			}

			public Object getElementAt(int index)
			{
				return values[index];
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.gridheight = 11;
		gbc_list.insets = new Insets(0, 0, 0, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		panel.add(list, gbc_list);

		JLabel lblInputTextBelow = new JLabel("Input text below");
		// lblInputTextBelow.setBorder(new LineBorder(new Color(0, 0, 0)));
		GridBagConstraints gbc_lblInputTextBelow = new GridBagConstraints();
		gbc_lblInputTextBelow.gridwidth = 2;
		gbc_lblInputTextBelow.insets = new Insets(0, 0, 5, 5);
		gbc_lblInputTextBelow.gridx = 1;
		gbc_lblInputTextBelow.gridy = 0;
		panel.add(lblInputTextBelow, gbc_lblInputTextBelow);

		JEditorPane editorPane = new JEditorPane();
		GridBagConstraints gbc_editorPane = new GridBagConstraints();
		gbc_editorPane.gridwidth = 8;
		gbc_editorPane.gridheight = 10;
		gbc_editorPane.insets = new Insets(0, 0, 5, 5);
		gbc_editorPane.fill = GridBagConstraints.BOTH;
		gbc_editorPane.gridx = 1;
		gbc_editorPane.gridy = 1;
		panel.add(editorPane, gbc_editorPane);

		JMenuBar menuBar = new JMenuBar();
		frmTeamFeMetadata.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		// mnFile.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(mnFile);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// the new menu option was selected
				JOptionPane.showMessageDialog(null, "Create a new project option was selected"
						+ "\nFunctionality coming soon..."
						, "New", JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		mnFile.add(mntmNew);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// call the open code from FileOps1
				System.out.println("Open menu option clicked");
				FileOps1.openFile( );
			}
		});
		mnFile.add(mntmOpen);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Had there been a project open, it would have been saved."
						, "Save", JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		mnFile.add(mntmSave);

		JMenuItem mntmImport = new JMenuItem("Import");
		mntmImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// action for when import is selected
				JOptionPane.showMessageDialog(null, "Import action coming soon..."
						, "Import", JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		mnFile.add(mntmImport);

		JMenuItem mntmExport = new JMenuItem("Export");
		mntmExport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Export action coming soon..."
						, "Export", JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		mnFile.add(mntmExport);

		JMenu mnEdit = new JMenu("Edit");
		// mnEdit.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(mnEdit);

		JMenuItem mntmCut = new JMenuItem("Cut");
		mnEdit.add(mntmCut);

		JMenuItem mntmCopy = new JMenuItem("Copy");
		mnEdit.add(mntmCopy);

		JMenuItem mntmPaste = new JMenuItem("Paste");
		mnEdit.add(mntmPaste);

		JMenu mnView = new JMenu("View");
		// mnView.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(mnView);

		JMenuItem mntmPreview = new JMenuItem("Preview");
		mntmPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "XML tree preview coming soon..."
						, "Preview", JOptionPane.INFORMATION_MESSAGE
				);
			}
		});
		mnView.add(mntmPreview);

		JMenu mnHelp = new JMenu("Help");
		// mnHelp.setBorder(new LineBorder(new Color(0, 0, 0)));
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null, "Metadata Software tool - version alpha 1.0" + 
			"\n2018 March 15 Build\nBuilt by Team FE\nAuthors: Sanford Johnston, " + 
			"Jacob Espinoza, Isaias Gerena, Lucas Herrman\n" +
			"(Not for external distribution - Work in Progress)", "About", JOptionPane.INFORMATION_MESSAGE
						);
			}
		});
		mnHelp.add(mntmAbout);
	}

}