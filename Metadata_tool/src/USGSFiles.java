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

public class USGSFiles extends JDialog {

	private static File file;
	private FileOps1 getAFile = new FileOps1();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			USGSFiles dialog = new USGSFiles(file);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public USGSFiles(File nFile) {
		file = nFile;
		setBounds(100, 100, 450, 300);
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			JButton btnOk = new JButton("OK");
			JButton btnCancel = new JButton("Cancel");
			
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
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					file = getAFile.openFile();
				}
			});

			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap(189, Short.MAX_VALUE)						
						.addGap(116)
						.addComponent(btnOk)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnCancel)
						.addContainerGap())
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(17)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(chckbxNewCheckBox_8)
							.addComponent(chckbxNewCheckBox)
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
									.addComponent(chckbxNewCheckBox_1)
									.addComponent(chckbxNewCheckBox_6)
									.addComponent(chckbxNewCheckBox_7)
									.addComponent(chckbxNewCheckBox_5)
									.addComponent(chckbxNewCheckBox_4)
									.addComponent(chckbxNewCheckBox_2)
									.addComponent(chckbxNewCheckBox_3))
								.addGap(98)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)))
						.addGap(60))
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
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnOk)
								.addComponent(btnCancel))))
			);
			panel.setLayout(gl_panel);
		}
	}
}
