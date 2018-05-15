import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JTextPane;

public class MetadataPreview extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame framePreview;	
	private static String preview;
	private static String cTitle;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
			try {
				MetadataPreview window = new MetadataPreview(preview, cTitle);
				window.framePreview.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
		});
	} 

	/**
	 * Create the application.
	 */
	public MetadataPreview(String text, String title) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setResizable(true);
		if ( title == null || title.equalsIgnoreCase("") ) {
			setTitle("Metadata Preview");
		} else {
			setTitle( title );
		}
		
		
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent we)
			{
				System.out.println("Preview window closing");
				dispose();
			}
		});
		
		preview = text;
		initialize();
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception e)
		{
			System.out.println("Error setting native LAF: " + e);
		}
	
		setBounds(100, 100, 500, 600);
		setLocationRelativeTo(null);
		
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
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{480, 0};
		gridBagLayout.rowHeights = new int[]{580, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		getContentPane().add(scrollPane, gbc_scrollPane);
		
		JTextPane textPane = new JTextPane();
		textPane.setText(preview);
		scrollPane.setViewportView(textPane);
		
		//start at the beginning of the text output
		// aka scroll to the top
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
		   public void run() { 
		       scrollPane.getVerticalScrollBar().setValue(0);
		   }
		});
		
	}

}
