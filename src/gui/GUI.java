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

public class GUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
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

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\hennesbm\\Desktop\\CSSE374\\CSSE374V0id\\images\\Test.png"));
		scrollPane_1.setViewportView(label);

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
				System.out.println("Instructions");
			} else if ("About".equals(e.getActionCommand())) {
				System.out.println("About");
			} else {
				throw new UnsupportedOperationException();
			}
		}
	}
}
