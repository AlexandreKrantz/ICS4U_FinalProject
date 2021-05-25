import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Panel {
	protected JPanel panel;
	
	Panel() {
		panel = new JPanel();

	}
	
	
	public JPanel getPanel() {
		return panel;
		
	}
	
	public void addPanel (JFrame frame) {
		frame.add(panel, BorderLayout.CENTER);
		frame.repaint();
		frame.revalidate();
	}
	
	public void removePanel(JFrame frame) {
		frame.remove(panel);
	}
}
