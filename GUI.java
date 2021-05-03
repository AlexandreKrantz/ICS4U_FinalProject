import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI {
	public int scale = 1;
	JPanel mainPanel;
	JPanel bottomPanel;
	JFrame frame;
	JTextArea frontTextArea;
	JTextArea backTextArea;
	JButton nextButton = new JButton("Next");
	JButton previousButton = new JButton("Previous");
	JButton doneButton = new JButton("Done");
	JLabel frontLabel = new JLabel("Front");
	JLabel backLabel = new JLabel("Back");
	
	
	GUI () {
		frame = new JFrame("App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(300,300);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run () {
				new CreateCards();
			}
		});
	}
	
	public JScrollPane prepInput(JTextArea input, JScrollPane container) {
		input = new JTextArea();
		input.setLineWrap(true);
		container = new JScrollPane(input);
		return container;
	}
	
	class CreateCards implements ActionListener {
		
		CreateCards() {
			previousButton.addActionListener(this);
			nextButton.addActionListener(this);
			doneButton.addActionListener(this);
			
			Container pane = frame.getContentPane();
			
			mainPanel = new JPanel();
			mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS)); // Vertical BoxLayout
			mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Border spacing

			
			// Front
			mainPanel.add(frontLabel);
			mainPanel.add(prepInput(frontTextArea, new JScrollPane()));
			
			// Vertical spacing between front and back
			mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
			
			// Back
			mainPanel.add(backLabel);
			mainPanel.add(prepInput(backTextArea, new JScrollPane()));
			
			// Add mainPanel to pane
			pane.add(mainPanel, BorderLayout.CENTER);
			
			bottomPanel = new JPanel();
			bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS)); // Horizontal BoxLayout
			bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Overall bottomPanel spacing
			bottomPanel.add(Box.createHorizontalGlue()); // Space buttons to the right
			bottomPanel.add(previousButton);
			bottomPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Spacing between buttons
			bottomPanel.add(nextButton);
			bottomPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Spacing between buttons
			bottomPanel.add(doneButton);
			
			// Add bottomPanel to pane
			pane.add(bottomPanel, BorderLayout.PAGE_END);
		}
		
		// Button actions
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "Next") {
				System.out.println(e.getActionCommand());
			}
			else if (e.getActionCommand() == "Previous") {

			}
			else if (e.getActionCommand() == "Done") {

			}
		}
		
	}

}
