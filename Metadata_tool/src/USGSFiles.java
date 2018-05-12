import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JCheckBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/***
 * When done, this class should return an XML file with the user selected
 * attributes
 */

@SuppressWarnings({ "unused", "serial" })
public class USGSFiles extends JDialog
{

	private static File file;
	private FileOps1 getAFile = new FileOps1();
	private boolean successful = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			USGSFiles dialog = new USGSFiles(file, null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Create the dialog.
	 */
	public USGSFiles(File nFile, Component parent)
	{
		setResizable(false);
		setTitle("USGS Project Attributes");
		setLocationRelativeTo(parent);
		file = nFile;
		setBounds(100, 100, 400, 300);
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			JButton btnOk = new JButton("OK");
			btnOk.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("(future) Save user input in an xml file...");
					SharedData.changeTemplateSet(true);
					SharedData.setTemplateFile(file);
					dispose();
				}
			});
			JButton btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					System.out.println("Discard user changes...");
					SharedData.changeTemplateSet(false);
					SharedData.setTemplateFile(null);
					dispose();
				}
			});

			JCheckBox checkBox_0 = new JCheckBox("Breakline");
			checkBox_0.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});

			JCheckBox checkBox_1 = new JCheckBox("Classified Point Cloud");
			checkBox_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//SharedData.changeTemplateSet(true);
					//SharedData.setTemplateFile();
				}
			});

			JCheckBox checkBox_2 = new JCheckBox("Digital Elevation Model");
			checkBox_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});

			JCheckBox checkBox_3 = new JCheckBox("Digital Surface Model");
			checkBox_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});

			JCheckBox checkBox_4 = new JCheckBox("Digital Terrain Model");
			checkBox_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});

			JCheckBox checkBox_5 = new JCheckBox("Intensity");
			checkBox_5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});

			JCheckBox checkBox_6 = new JCheckBox("Swath Point Cloud");
			checkBox_6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});

			JCheckBox checkBox_7 = new JCheckBox("Project Level");
			checkBox_7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});

			JCheckBox checkBox_8 = new JCheckBox("Contour");
			checkBox_8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});

			JButton btnNew = new JButton("Import New");
			btnNew.setToolTipText("Import a new USGS compatible template");
			btnNew.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					final JFileChooser newTemplateFileChoose = new JFileChooser();
					FileFilter xmlFilter = new FileNameExtensionFilter("XML File - eXtensible Markup Language (*.xml)",
							"xml");

					newTemplateFileChoose.setFileFilter(xmlFilter);
					// ** Force disable all files option
					newTemplateFileChoose.setAcceptAllFileFilterUsed(false);
					newTemplateFileChoose.setDialogTitle("Import a USGS compatible template");

					int newTemplateChooseReturnVal;

					newTemplateChooseReturnVal = newTemplateFileChoose.showOpenDialog(null);
					file = newTemplateFileChoose.getSelectedFile();
					
					if ( file != null )  {
						System.out.println("User set a custom USGS-compatible xml template");
						SharedData.changeTemplateSet(true);
						SharedData.setTemplateFile(file);
						dispose();
					} else {
						SharedData.changeTemplateSet(false);
						SharedData.setTemplateFile(null);
					}
					
					
					// ** TESTING: deprecated
					//file = getAFile.openFile(null);
				}
			});

			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap(15, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(checkBox_6)
							.addComponent(checkBox_7)
							.addComponent(checkBox_5)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(checkBox_8)
										.addComponent(checkBox_0))
									.addPreferredGap(ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
									.addComponent(btnOk)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnCancel))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(checkBox_1)
										.addComponent(checkBox_4)
										.addComponent(checkBox_2)
										.addComponent(checkBox_3))
									.addGap(70)
									.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))))
						.addGap(44))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(checkBox_7, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addComponent(checkBox_6, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(checkBox_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(checkBox_2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(checkBox_3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(checkBox_4, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(checkBox_5, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(checkBox_0, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(checkBox_8, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGap(70)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnCancel)
									.addComponent(btnOk))))
						.addGap(55))
			);
			panel.setLayout(gl_panel);
		}
	}
}
