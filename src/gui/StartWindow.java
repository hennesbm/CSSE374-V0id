package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.FlowLayout;

public class StartWindow {

	private JFrame frame;
	private Properties properties = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow window = new StartWindow();
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
	public StartWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel(" ");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel_1.add(label);
		
		JProgressBar progressBar = new JProgressBar();
		panel_1.add(progressBar);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 100));
		
		JButton btnLoadConfiguration = new JButton("Load Configuration");
		btnLoadConfiguration.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(btnLoadConfiguration);
		
		JButton btnAnalyze = new JButton("Analyze");
		btnAnalyze.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnAnalyze.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(properties != null) {
					label.setText(" ");
				} else {
					label.setText("No property file defined. Please load a configuration file.");
				}
			}
			
		});
		panel.add(btnAnalyze);
	}

}
