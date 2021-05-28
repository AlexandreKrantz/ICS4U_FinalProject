import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * 
 * @author Alex Krantz
 * Due date: May 28th
 * Class description: defines how panel objects interact with the frame
 */
public abstract class Panel {
	protected JPanel panel;
	
	Panel() {
		panel = new JPanel();
	}
	
	
	public JPanel getPanel() {
		return panel;
		
	}
	
	/**
	 * adds panel to frame
	 * @param frame
	 */
	public void addPanel (JFrame frame) {
		// Add panel to center of frame by default
		frame.add(panel, BorderLayout.CENTER);
		// Refresh frame so changes will be visible
		frame.repaint();
		frame.revalidate();
	}
	
	/**
	 * removes panel from frame
	 * @param frame
	 */
	public void removePanel(JFrame frame) {
		frame.remove(panel);
	}
}
