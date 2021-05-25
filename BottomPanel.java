import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BottomPanel extends Panel {
	JButton[] buttons;
	
	public BottomPanel (String[] buttonNames, ActionListener[] listeners) {
		panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
		
		buttons = new JButton[buttonNames.length];
		
		for (int i = 0; i < buttonNames.length; i++) {
			JButton temp = new JButton(buttonNames[i]);
			temp.addActionListener(listeners[i]);
			buttons[i] = temp;
			panel.add(temp);
		}		
	}
	
	public JButton[] getButtons() {
		return buttons;
	}
	
	public void addPanel (JFrame frame) {
		frame.add(panel, BorderLayout.PAGE_END);
		frame.repaint();
		frame.revalidate();
	}
	
}
