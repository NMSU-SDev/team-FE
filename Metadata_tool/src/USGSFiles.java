import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
			USGSFiles dialog = new USGSFiles(file);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public USGSFiles(File nFile)
	{
		setResizable(false);
		setTitle("USGS Project Attributes");
		file = nFile;
		setBounds(100, 100, 400, 280);
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

			JCheckBox chckbxNewCheckBox = new JCheckBox("Breakline");

			JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Classified Point Cloud");

			JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Digital Elevation Model");

			JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Digital Surface Model");

			JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Digital Terrain Model");

			JCheckBox chckbxNewCheckBox_5 = new JCheckBox("Intensity");

			JCheckBox chckbxNewCheckBox_6 = new JCheckBox("Swath Point Cloud");

			JCheckBox chckbxNewCheckBox_7 = new JCheckBox("Project Level");

			JCheckBox chckbxNewCheckBox_8 = new JCheckBox("Contour");

			JButton btnNewButton = new JButton("Import New");
			btnNewButton.setToolTipText("Import a new USGS compatible template");
			btnNewButton.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					file = getAFile.openFile(null);
				}
			});

			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap(9, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(chckbxNewCheckBox_6)
							.addComponent(chckbxNewCheckBox_7)
							.addComponent(chckbxNewCheckBox_5)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(chckbxNewCheckBox_8)
										.addComponent(chckbxNewCheckBox))
									.addPreferredGap(ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
									.addComponent(btnOk)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnCancel))
								.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(chckbxNewCheckBox_1)
										.addComponent(chckbxNewCheckBox_4)
										.addComponent(chckbxNewCheckBox_2)
										.addComponent(chckbxNewCheckBox_3))
									.addGap(70)
									.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))))
						.addGap(44))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(chckbxNewCheckBox_7)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(chckbxNewCheckBox_6)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(chckbxNewCheckBox_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(chckbxNewCheckBox_2)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(chckbxNewCheckBox_3)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(chckbxNewCheckBox_4)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(chckbxNewCheckBox_5)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(chckbxNewCheckBox)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(chckbxNewCheckBox_8))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGap(49)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnCancel)
									.addComponent(btnOk))))
						.addGap(55))
			);
			panel.setLayout(gl_panel);
		}
	}
}
