import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
		
		main = new MainPanel();
		bottom = new BottomPanel(buttonNames, buttonListeners);
		main.addPanel(frame);
		bottom.addPanel(frame);
		
		// Get file with deck data
		fileInput = new JFileChooser();
		fileInput.showOpenDialog(frame);
		file = fileInput.getSelectedFile();
		// Load deck
		try {
			deck = IO.loadDeck(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		currentCard = deck.get(index);
		main.showFront(currentCard);
		main.allowInput(false);

		
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
