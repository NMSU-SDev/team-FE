import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class MetadataPreview extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame framePreview;	
	private static String preview;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
			try {
				MetadataPreview window = new MetadataPreview(preview);
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
	public MetadataPreview(String prev) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setResizable(true);
		setTitle("Metadata Preview");
		
		addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent we)
			{
				System.out.println("Preview window closing");
				dispose();
			}
		});
		
		preview = prev;
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
		
		//framePreview = new JFrame();
		//framePreview.setTitle("Metadata Preview");
		setBounds(100, 100, 400, 300);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{380, 0};
		gridBagLayout.rowHeights = new int[]{280, 0};
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
		
	}

}
