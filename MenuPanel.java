import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MenuPanel extends Panel {
	JButton[] buttons;

	public MenuPanel(String[] buttonNames, ActionListener[] listeners) {
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS)); // Vertical BoxLayout
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Border spacing
		
		buttons = new JButton[buttonNames.length];

		for (int i = 0; i < buttonNames.length; i++) {
			JButton temp = new JButton(buttonNames[i]);
			temp.addActionListener(listeners[i]);
			buttons[i] = temp;
			panel.add(Box.createRigidArea(new Dimension(0, 10))); // Vertical spacing between buttons
			panel.add(temp);
			temp.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		
	}
	public JButton[] getButtons() {
		return buttons;
	}
}
