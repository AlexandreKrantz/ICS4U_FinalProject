import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Alex Krantz
 * Due date: May 28th
 * Class description: Dictates the functionality of the GUI menu
 */

public class MenuController {
	private MenuPanel menu;
	private JFrame frame;
	
	// Instantiate JButton properties
	private String[] buttonNames = {"Create", "Edit", "Study"};
	private ActionListener[] buttonListeners = {new Create(), new Edit(), new Study()};

	
	public MenuController(JFrame f) {
		menu = new MenuPanel(buttonNames, buttonListeners); // Create menu panel specified buttons
		frame = f;
		menu.addPanel(frame); // add menu panel to frame
	}
	
	// remove menu panel from frame and launch create mode
	public class Create implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			menu.removePanel(frame);
			SwingUtilities.invokeLater(new Runnable() {
				public void run () {
					new CreateCardsController(frame);
				}
			});
			
		}
		
	}
	
	// remove menu panel from frame and launch edit mode
	public class Edit implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			menu.removePanel(frame);
			SwingUtilities.invokeLater(new Runnable() {
				public void run () {
					new EditCardsController(frame);
				}
			});
		}
		
	}
	
	// remove menu panel from frame and launch edit mode
	public class Study implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			menu.removePanel(frame);
			SwingUtilities.invokeLater(new Runnable() {
				public void run () {
					new StudyCardsController(frame);
				}
			});
		}
		
	}

}
