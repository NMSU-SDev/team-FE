import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

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

public class MetadataPreview extends JFrame{

	private JFrame frame;	
	private static String preview;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MetadataPreview window = new MetadataPreview(preview);
					window.frame.setVisible(true);
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
		
		setResizable(true);			
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
		
		frame = new JFrame();
		frame.setBounds(100, 100, 614, 554);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{598, 0};
		gridBagLayout.rowHeights = new int[]{516, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		frame.getContentPane().add(scrollPane, gbc_scrollPane);
		
		JTextArea previewArea = new JTextArea();
		previewArea.setRows(25);
		previewArea.setLineWrap(true);
		previewArea.setText(preview);
		scrollPane.setViewportView(previewArea);
		
		
		
		
		
	}

}
