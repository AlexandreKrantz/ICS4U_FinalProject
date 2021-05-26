import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

// Alex Krantz, due date, description
/**
 * 
 * @author Alex Krantz
 * Due date: May 28th
 * Class description: prepares a bottom panel JPanel that contains buttons with names and action listeners that are passed in as parameters
 */
public class BottomPanel extends Panel {
	private JButton[] buttons;
	
	/**
	 * Each index of buttonNames and listeners contains properties intended for the same button
	 * @param buttonNames
	 * @param listeners
	 */
	public BottomPanel (String[] buttonNames, ActionListener[] listeners) {
		
		// Create JPanel
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Padding on the edges
		
		buttons = new JButton[buttonNames.length]; // Initializes new JButton array
		
		// Assumes buttonNames and listeners have the same length
		for (int i = 0; i < buttonNames.length; i++) {
			JButton temp = new JButton(buttonNames[i]); // Create a new button
			temp.addActionListener(listeners[i]); // Add ActionListener to button
			buttons[i] = temp; // Add button to array
			panel.add(temp); // Add button to panel
		}		
	}
	
	/**
	 * @return buttons array
	 */
	public JButton[] getButtons() {
		return buttons;
	}
	
	/**
	 *  Overrides parent addPanel method
	 */
	public void addPanel (JFrame frame) {
		frame.add(panel, BorderLayout.PAGE_END); // Add panel to bottom rather than center
		frame.repaint();
		frame.revalidate();
	}
	
}
