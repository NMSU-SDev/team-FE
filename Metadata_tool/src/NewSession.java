import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

@SuppressWarnings({ "serial", "unused" })
public class NewSession extends JFrame
{

	public NewSession(File nFile)
	{
		setResizable(false);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent we)
			{
				System.out.println("Attempt to close NewSession");

				int result = JOptionPane.showConfirmDialog(newFrame,
						"A template was not selected\n Do you want to " + "cancel starting a new project?",
						"Cancel new project?", JOptionPane.YES_OPTION);

				if (result == JOptionPane.YES_OPTION)
				{
					dispose();
				}
			}
		});
		setTitle("Choose Session Type");
		file = nFile;
		session();
	}

	private JFrame newFrame;
	private FileOps1 non_USGS = new FileOps1();
	private static File file, fileInternal;

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
					NewSession nSession = new NewSession(file);
					nSession.newFrame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void session()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			System.out.println("Error setting native LAF: " + e);
		}

		setBounds(100, 100, 344, 290);
		setLocationRelativeTo(null);
		JButton btnNewButton = new JButton("USGS");
		btnNewButton.setToolTipText("USGS compliant metadata");

		/*** USGS Button ****/
		btnNewButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				// open another dialog with USGS selectable items and an option
				// to import from file
				try
				{
					USGSFiles dialog = new USGSFiles(file);
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
					dialog.addWindowListener(new WindowAdapter()
					{
						@Override
						public void windowClosed(WindowEvent e)
						{
							if (SharedData.isTemplateSet() == true)
							{
								JOptionPane.showMessageDialog(newFrame,
										"Template was set successfully to USGS standard", "Template Set",
										JOptionPane.INFORMATION_MESSAGE);
								dispose();
							}
						}
					}); // end window listener for dialog

				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JButton btnNewButton_1 = new JButton("non-USGS");
		btnNewButton_1.setToolTipText("non-USGS compliant metadata");

		/*** Non-USGS Button **/
		btnNewButton_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("Clicked on non-USGS");
				// OpenFileChooser
				file = non_USGS.openFile(newFrame);
				// TODO: need to send the file back to Demo2.java
				if (file == null)
				{
					SharedData.changeTemplateSet(false);
					SharedData.setTemplateFile(null);
				}
				else
				{
					SharedData.changeTemplateSet(true);
					SharedData.setTemplateFile(file);
				}

				if (SharedData.isTemplateSet() == true)
				{
					JOptionPane.showMessageDialog(newFrame, "Template was set to a custom standard", "Template Set",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				}
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(29)
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 104,
												GroupLayout.PREFERRED_SIZE)
										.addGap(46).addComponent(btnNewButton_1).addContainerGap(28, Short.MAX_VALUE)));
		groupLayout
				.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup().addGap(61)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 120,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 118,
												GroupLayout.PREFERRED_SIZE))
								.addContainerGap(71, Short.MAX_VALUE)));
		getContentPane().setLayout(groupLayout);		
	}
}
