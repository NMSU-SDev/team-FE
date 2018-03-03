package Window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import com.jgoodies.forms.factories.DefaultComponentFactory; //idk why this doesn't work for you jacob
import javax.swing.JSeparator;
import javax.swing.JEditorPane;
import java.awt.Choice;
import javax.swing.ListSelectionModel;
import java.awt.Canvas;
import javax.swing.JCheckBox;
import net.miginfocom.swing.MigLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Button;
//some of these imports are this way because I used them at some point in creating what I have,
//but I've deleted them since then. You can ignore the warnings

public class proto
{

	private JFrame frame;
	/**
	 * @wbp.nonvisual location=201,-1
	 */
	private final JLabel lblMyTestWindow = DefaultComponentFactory.getInstance().createTitle("My test window");
	/**
	 * @wbp.nonvisual location=380,-1
	 */
	private final JPanel panel_1 = new JPanel();

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
					proto window = new proto();
					window.frame.setVisible(true);
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
	public proto()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		lblMyTestWindow.setLabelFor(frame.getContentPane());
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setAlignmentX(Component.RIGHT_ALIGNMENT);
		panel.setBounds(0, 0, 878, 554);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JRadioButton ft1 = new JRadioButton("File Type 1");
		ft1.setBounds(316, 66, 102, 31);
		panel.add(ft1);

		JRadioButton ft2 = new JRadioButton("File Type 2");
		ft2.setBounds(420, 66, 109, 31);
		panel.add(ft2);

		JRadioButton ft3 = new JRadioButton("File Type 3");
		ft3.setBounds(531, 66, 109, 31);
		panel.add(ft3);

		JRadioButton ft4 = new JRadioButton("File Type 4");
		ft4.setBounds(642, 66, 109, 31);
		panel.add(ft4);

		ButtonGroup fileTypeButtons = new ButtonGroup();
		fileTypeButtons.add(ft1);
		fileTypeButtons.add(ft2);
		fileTypeButtons.add(ft3);
		fileTypeButtons.add(ft4);

		JList list = new JList();
		list.setFixedCellHeight(35);
		list.setValueIsAdjusting(true);
		list.setVisibleRowCount(9);
		list.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.addListSelectionListener(new ListSelectionListener()
		{

			@Override
			public void valueChanged(ListSelectionEvent arg0)
			{
				// TODO Auto-generated method stub
			}

		});
		list.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{

			}
		});
		// these warnings below are because the list above is not given a type,
		// and it wants one. Again, can be ignored for now
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(0, 450, 43, 104);
		list.setModel(new AbstractListModel()
		{
			String[] values = new String[] { "one", "two", "three" };

			public int getSize()
			{
				return values.length;
			}

			public Object getElementAt(int index)
			{
				return values[index];
			}
		});
		panel.add(list);

		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "one", "two", "three" }));
		comboBox.setBounds(354, 33, 125, 32);
		panel.add(comboBox);

		JLabel lblSelectFileType = DefaultComponentFactory.getInstance()
				.createTitle("Select file type being created or edited in this project");
		lblSelectFileType.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblSelectFileType.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblSelectFileType.setBounds(0, 64, 316, 33);
		panel.add(lblSelectFileType);

		JLabel lblSelectAmountOf = DefaultComponentFactory.getInstance()
				.createTitle("Select amount of files being created or edited in this project");
		lblSelectAmountOf.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblSelectAmountOf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblSelectAmountOf.setBounds(0, 31, 354, 34);
		panel.add(lblSelectAmountOf);

		JLabel lblSelectPropertiesOf = DefaultComponentFactory.getInstance()
				.createTitle("Select properties of the project currently being worked on below");
		lblSelectPropertiesOf.setHorizontalTextPosition(SwingConstants.LEFT);
		lblSelectPropertiesOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectPropertiesOf.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		lblSelectPropertiesOf.setLabelFor(panel);
		lblSelectPropertiesOf.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblSelectPropertiesOf.setBounds(0, 0, 878, 33);
		panel.add(lblSelectPropertiesOf);

		JLabel lblPage = DefaultComponentFactory.getInstance().createTitle("Page:");
		lblPage.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lblPage.setBounds(3, 425, 88, 24);
		panel.add(lblPage);

		JButton b1 = new JButton("Disable button 2");
		b1.setBounds(202, 230, 140, 58);
		panel.add(b1);
		b1.setActionCommand("disable");

		JButton b2 = new JButton("Disable button 1");
		b2.setBounds(339, 230, 140, 58);
		panel.add(b2);
		b2.setActionCommand("disable");

		JButton b3 = new JButton("Enable all buttons");
		b3.setBounds(272, 354, 140, 58);
		panel.add(b3);
		b3.setActionCommand("enable");

		b1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				b2.setEnabled(false);
			}
		});
		b2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				b1.setEnabled(false);
			}
		});
		b3.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				b2.setEnabled(true);
				b1.setEnabled(true);
				b3.setEnabled(true);
			}
		});

		JLabel lblButton = DefaultComponentFactory.getInstance().createTitle("Button 1");
		lblButton.setBounds(228, 217, 88, 14);
		panel.add(lblButton);

		JLabel lblButton_1 = DefaultComponentFactory.getInstance().createTitle("Button 2");
		lblButton_1.setBounds(366, 217, 88, 14);
		panel.add(lblButton_1);
		
		JButton hb = new JButton("Hide screen");
		hb.setBounds(460, 482, 140, 72);
		panel.add(hb);
		
		JButton sb = new JButton("Show screen");
		sb.setBounds(598, 482, 142, 72);
		panel.add(sb);
		
		JButton hp = new JButton("Hide Panel");
		hp.setBounds(738, 482, 140, 72);
		panel.add(hp);
		
		hp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				panel.setVisible(false);
			}
		});
		
		hb.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ft1.setVisible(false);
				ft2.setVisible(false);
				ft3.setVisible(false);
				ft4.setVisible(false);
				list.setVisible(false);
				comboBox.setVisible(false);
				lblSelectFileType.setVisible(false);
				lblSelectAmountOf.setVisible(false);
				lblSelectPropertiesOf.setVisible(false);
				lblPage.setVisible(false);
				b1.setVisible(false);
				b2.setVisible(false);
				b3.setVisible(false);
				lblButton.setVisible(false);
				lblButton_1.setVisible(false);
			}
		});
		
		sb.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				ft1.setVisible(true);
				ft2.setVisible(true);
				ft3.setVisible(true);
				ft4.setVisible(true);
				list.setVisible(true);
				comboBox.setVisible(true);
				lblSelectFileType.setVisible(true);
				lblSelectAmountOf.setVisible(true);
				lblSelectPropertiesOf.setVisible(true);
				lblPage.setVisible(true);
				b1.setVisible(true);
				b2.setVisible(true);
				b3.setVisible(true);
				lblButton.setVisible(true);
				lblButton_1.setVisible(true);
			}
		});
		
		frame.setForeground(Color.BLACK);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 894, 593);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}