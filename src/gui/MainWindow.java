package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.DefaultListModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Component;

public class MainWindow {

	private JFrame frame;
	private JLabel label;
	private ImageIcon picture;
	private int scale = 10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmRestart = new JMenuItem("Restart");
		mntmRestart.addActionListener(new MenuActionListener());
		mntmRestart.setActionCommand("Restart");
		mnFile.add(mntmRestart);

		JMenuItem mntmExport = new JMenuItem("Export");
		mntmExport.addActionListener(new MenuActionListener());
		mntmExport.setActionCommand("Export");
		mnFile.add(mntmExport);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmInstructions = new JMenuItem("Instructions");
		mntmInstructions.addActionListener(new MenuActionListener());
		mntmInstructions.setActionCommand("Instructions");
		mnHelp.add(mntmInstructions);

		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.addActionListener(new MenuActionListener());
		mntmAbout.setActionCommand("About");
		mnHelp.add(mntmAbout);

		JScrollPane scrollPane = new JScrollPane();

		JScrollPane scrollPane_1 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 194,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 794, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
				.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE));

		label = new JLabel("");
		picture = new ImageIcon("C:\\Users\\hennesbm\\Desktop\\CSSE374\\CSSE374V0id\\images\\Test.png");
		label.setIcon(picture);
		scrollPane_1.setViewportView(label);

		JPanel panel = new JPanel();
		scrollPane_1.setColumnHeaderView(panel);

		JLabel lblZoom = new JLabel("Zoom:");
		lblZoom.setAlignmentY(Component.TOP_ALIGNMENT);

		JButton button = new JButton("+");
		button.setAlignmentY(Component.TOP_ALIGNMENT);
		button.addActionListener(new ZoomActionListener());
		button.setActionCommand("Zoom In");

		JButton button_1 = new JButton("-");
		button_1.addActionListener(new ZoomActionListener());
		button_1.setActionCommand("Zoom Out");

		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(lblZoom);
		panel.add(button);
		panel.add(button_1);

		DefaultListModel<JCheckBox> model = new DefaultListModel<JCheckBox>();
		JCheckBoxList checkBoxList = new JCheckBoxList(model);
		model.addElement(new JCheckBox("Adapter"));
		model.addElement(new JCheckBox("Composite"));
		model.addElement(new JCheckBox("Decorator"));
		model.addElement(new JCheckBox("Singleton"));
		scrollPane.setViewportView(checkBoxList);
		frame.getContentPane().setLayout(groupLayout);
	}

	class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if ("Restart".equals(e.getActionCommand())) {
				System.out.println("Restart");
			} else if ("Export".equals(e.getActionCommand())) {
				System.out.println("Export");
			} else if ("Instructions".equals(e.getActionCommand())) {
				InstructionWindow.main(null);
			} else if ("About".equals(e.getActionCommand())) {
				AboutWindow.main(null);
			} else {
				throw new UnsupportedOperationException();
			}
		}
	}

	class ZoomActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if ("Zoom In".equals(e.getActionCommand())) {
				if (scale < 20) {
					scale++;
					resizeImage();
				}
			} else if ("Zoom Out".equals(e.getActionCommand())) {
				if (scale > 1) {
					scale--;
					resizeImage();
				}
			} else {
				throw new UnsupportedOperationException();
			}
		}
	}

	private void resizeImage() {
		Image image = picture.getImage();
		Image newimg = image.getScaledInstance((int) (picture.getIconWidth() * (scale / 10.0)),
				(int) (picture.getIconHeight() * (scale / 10.0)), java.awt.Image.SCALE_SMOOTH);
		ImageIcon newImage = new ImageIcon(newimg);
		label.setIcon(newImage);
		label.repaint();
	}
}
