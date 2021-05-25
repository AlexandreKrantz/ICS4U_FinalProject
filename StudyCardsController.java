import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class StudyCardsController extends CardsController {
	private JFileChooser fileInput;
	private File file;
	
	//private String[] buttonNames = {"Create", "Delete", "Previous", "Next", "Done"};
	private String[] buttonNames = {"Next", "Done"};
	private ActionListener[] buttonListeners = {new Next(), new Done()};
	
	public StudyCardsController(JFrame f) {
		super(f);
		
	}
	
	class Next implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class Done implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}
