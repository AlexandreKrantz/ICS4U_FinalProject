import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class CreateCardsController extends CardsController {
	private String[] buttonNames = {"Next", "Done"};
	private ActionListener[] buttonListeners =  {new Next(), new Done()};
	
	
	public CreateCardsController(JFrame f) {
		super(f);
		main = new MainPanel();
		bottom = new BottomPanel(buttonNames, buttonListeners);
		main.addPanel(frame);
		bottom.addPanel(frame);
	}
	
	public class Next implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (main.checkInput()) {
				// Create new flashcard object and add to deck
				deck.add(new Flashcard(main.getFrontText(), main.getBackText()));
				System.out.println("Deck size = " + deck.size());
				main.resetText();
			}
		}
	}
	
	public class Done implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			// Save current card
			deck.add(new Flashcard(main.getFrontText(), main.getBackText()));
			// Save deck
			JFileChooser fileInput = new JFileChooser();
			fileInput.showSaveDialog(frame);
			File file = fileInput.getSelectedFile();
			try {
				IO.saveDeck(deck, file);
			} catch (IOException err) {
				// TODO: GUI alert message
				err.printStackTrace();
			}
			// Remove panels from pane
			main.removePanel(frame);
			bottom.removePanel(frame);
			// Load menu
			SwingUtilities.invokeLater(new Runnable() {
				public void run () {
					new MenuController(frame);
				}
			});
		}
	}

	protected void endOfDeckAction() {
		currentCard = new Flashcard("", "");
	}
}
