import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MenuController {
	private MenuPanel menu;
	private JFrame frame;
	private String[] buttonNames = {"Create", "Edit", "Study"};
	private ActionListener[] buttonListeners = {new Create(), new Edit(), new Study()};

	
	public MenuController(JFrame f) {
		menu = new MenuPanel(buttonNames, buttonListeners);
		frame = f;
		menu.addPanel(frame);
	}
	
	public class Create implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			menu.removePanel(frame);
			SwingUtilities.invokeLater(new Runnable() {
				public void run () {
					new CreateCardsController(frame);
				}
			});
			
		}
		
	}
	
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
	
	public class Study implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			menu.removePanel(frame);
			SwingUtilities.invokeLater(new Runnable() {
				public void run () {
					new StudyCardsController(frame);
				}
			});
		}
		
	}

}
