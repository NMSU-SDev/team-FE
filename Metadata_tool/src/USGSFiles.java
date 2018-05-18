import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JCheckBox;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ButtonGroup;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

/***
 * When done, this class should return an XML file with the user selected
 * attributes
 */

@SuppressWarnings({ "unused", "serial" })
public class USGSFiles extends JFrame
{

	private static File file;
	private FileOps1 getAFile = new FileOps1();
	private boolean successful = false;
	
	ButtonGroup group = new ButtonGroup();
		
	/* Helper method that gets a file from the classpath and then sets the template file */
	private void getAndSetFromClPath( String fName, String directory ) {
			
		// get the file from classpath resources
		try {
		/* Try input stream to work in a JAR */
		InputStream in = getClass().getResourceAsStream(fName);
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		File dir = new File(directory);
		
		if ( !dir.exists() ) {
			System.out.printf("Creating directory \'%s\'\n", directory);
			boolean result = dir.mkdir();
			
			if ( result == true ) System.out.println("Directory creation successful");
			else System.out.print("Directory creation failed!!");
		}
		
		String outFilePath = directory + "/" + fName;
		
		File newFile = new File(outFilePath);
		
		OutputStream out = new FileOutputStream( newFile );
		
		int read = 0;
		byte[] bytes = new byte[1024];
		
		while ( (read = in.read(bytes)) != -1 )
			out.write(bytes, 0, read);
		
		out.close();
		
		System.out.print("File path = ");
		System.out.println( newFile.getPath() );
		
		SharedData.setTemplateFile(newFile);
		
		System.out.println("Set template file");
		
		} catch (IOException ioex ) {
			ioex.printStackTrace();
			JOptionPane.showMessageDialog(null, ioex, "IOException Error!",
					JOptionPane.ERROR_MESSAGE);
		}
		catch (NullPointerException npex ) {
			npex.printStackTrace();
			String message = "Your template file library is not in the expected place\n"
					+ "Please create a 'lib' folder near the source files"
					+ "See the repo for more information\n\n" + npex.toString();
			
			JOptionPane.showMessageDialog(null, message, "Template file was not found!",
					JOptionPane.ERROR_MESSAGE);
		}

	} // end method get and set from class path
	
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
	 * Create the frame.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public USGSFiles(File nFile, Component parent) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		Image icon = null;
		
		// Set iconImage using get resource as stream and reading the image
		InputStream in = getClass().getResourceAsStream("FEIcon1.png");
		try {
			icon = ImageIO.read( in );
			
		} catch (IOException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1, "IOException Error!",
					JOptionPane.ERROR_MESSAGE);
		}
		
		setIconImage(icon);
		
		setResizable(false);
		setTitle("USGS Project Attributes");
		setLocationRelativeTo(null);
		file = nFile;
		setBounds(100, 100, 400, 300);
		
		JFrame thisJFrame = this;
		
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.CENTER);
			JButton btnOk = new JButton("OK");
			btnOk.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					String templateFileName = group.getSelection().getActionCommand();
					
					getAndSetFromClPath( templateFileName, "lib");
					
					System.out.println("User input has been saved...");
					SharedData.changeTemplateSet(true);
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
			
			JRadioButton rButton1 = new JRadioButton("Classified Point Cloud");
			rButton1.setActionCommand("ClassifiedPointCloud1.2_blank_template.xml");
			
			JRadioButton rButton2 = new JRadioButton("Contours");
			rButton2.setActionCommand("Contours_blank_template.xml");
			
			JRadioButton rButton3 = new JRadioButton("Digital Elevation Model");
			rButton3.setActionCommand("DEM_1.2_blank_template.xml");
			
			JRadioButton rButton4 = new JRadioButton("Digital Surface Model");
			rButton4.setActionCommand("DSM_1.2_blank_template.xml");
			
			JRadioButton rButton5 = new JRadioButton("Digital Terrain Model");
			rButton5.setActionCommand("DTM_1.2_blank_template.xml");
			
			JRadioButton rButton6 = new JRadioButton("Intensity");
			rButton6.setActionCommand("Intensity_1.2_blank_template.xml");
			
			JRadioButton rButton7 = new JRadioButton("Project Level");
			rButton7.setActionCommand("ProjectLevel1.2_blank_template.xml");
			
			JRadioButton rButton8 = new JRadioButton("Swath");
			rButton8.setActionCommand("Swath_1.2_blank_template.xml");
			
			group.add(rButton1);
			group.add(rButton2);
			group.add(rButton3);
			group.add(rButton4);
			group.add(rButton5);
			group.add(rButton6);
			group.add(rButton7);
			group.add(rButton8);

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

					newTemplateChooseReturnVal = newTemplateFileChoose.showOpenDialog(thisJFrame);
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
					
				}
			});

			GroupLayout gl_panel = new GroupLayout(panel);
			gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap(15, Short.MAX_VALUE)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addComponent(rButton2)
							.addComponent(rButton1)
							.addComponent(rButton7)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
									.addComponent(rButton8)
									.addPreferredGap(ComponentPlacement.RELATED, 156, Short.MAX_VALUE)
									.addComponent(btnOk)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnCancel))
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(rButton3)
										.addComponent(rButton6)
										.addComponent(rButton4)
										.addComponent(rButton5))
									.addGap(70)
									.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE))))
						.addGap(44))
			);
			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
					.addGroup(gl_panel.createSequentialGroup()
						.addComponent(rButton1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addComponent(rButton2, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
						.addGap(1)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(rButton3, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(rButton4, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(rButton5, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(rButton6, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addGap(1)
								.addComponent(rButton7, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(rButton8, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_panel.createSequentialGroup()
								.addComponent(btnNew, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addGap(70)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnCancel)
									.addComponent(btnOk))))
						.addGap(70))
			);
			panel.setLayout(gl_panel);
	}

}
