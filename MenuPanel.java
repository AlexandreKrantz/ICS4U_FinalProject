import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 
 * @author Alex Krantz
 * Due date: May 28th
 * Class description: Creates a simple GUI menu.
 */
public class MenuPanel extends Panel {
	JButton[] buttons;
	
	/**
	 * Creates a function menu JPanel
	 * @param buttonNames names of the buttons
	 * @param listeners actionlisteners to be added to buttons
	 */
	public MenuPanel(String[] buttonNames, ActionListener[] listeners) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS)); // Vertical BoxLayout (sub components will be vertically stacked)
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Border spacing
		
		buttons = new JButton[buttonNames.length];
		
		for (int i = 0; i < buttonNames.length; i++) {
			// Instantiate buttons using buttonNames and listeners
			JButton temp = new JButton(buttonNames[i]);
			temp.addActionListener(listeners[i]);
			buttons[i] = temp;
			panel.add(Box.createRigidArea(new Dimension(0, 10))); // Vertical spacing between buttons
			panel.add(temp); // Add button to panel
			temp.setAlignmentX(Component.CENTER_ALIGNMENT); // Horizontally center each button
		}
		
	}
	public JButton[] getButtons() {
		return buttons;
	}
}
