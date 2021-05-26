import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class StudyCardsController extends CardsController {
	private JFileChooser fileInput;
	private File file;
	
	// bottom
	private String[] buttonNames = {"Show back", "Done"};
	private ActionListener[] buttonListeners = {new showBack(), new Done()};
	
	// bottom2
	protected BottomPanel bottom2;
	private String[] buttonNames2 = {"Incorrect", "Correct"};
	private ActionListener[] buttonListeners2 = {new Incorrect(), new Correct()};
	
	private ArrayList<Flashcard> highPriority = new ArrayList<Flashcard>();
	private ArrayList<Flashcard> mediumPriority = new ArrayList<Flashcard>();
	private ArrayList<Flashcard> lowPriority = new ArrayList<Flashcard>();
		
	public StudyCardsController(JFrame f) {
		super(f);
		
		main = new MainPanel();
		bottom = new BottomPanel(buttonNames, buttonListeners);
		main.addPanel(frame);
		bottom.addPanel(frame);
		
		bottom2 = new BottomPanel(buttonNames2, buttonListeners2);
		
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
		
		for (int i = 0; i < deck.size(); i++) {
			assignPriorityDeck(deck.get(i));
		}
		
		deck = getHighestPriorityDeck();
		currentCard = deck.get(0);
		main.showCard(currentCard);
		main.disableInput();
		main.hideBack();
		
	}
	
	class showBack implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			main.showBack();
			bottom.removePanel(frame);
			bottom2.addPanel(frame);
		}
		
	}
	
	class Incorrect implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			currentCard.increasePriority();
			deck.remove(0);
			assignPriorityDeck(currentCard);
			deck = getHighestPriorityDeck();
			currentCard = deck.get(0);
			main.showCard(currentCard);
			main.hideBack();
			bottom2.removePanel(frame);
			bottom.addPanel(frame);
		}
		
	}
	
	class Correct implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			currentCard.decreasePriority();
			deck.remove(0);
			assignPriorityDeck(currentCard);
			deck = getHighestPriorityDeck();
			currentCard = deck.get(0);
			main.showCard(currentCard);
			main.hideBack();
			bottom2.removePanel(frame);
			bottom.addPanel(frame);
		}
		
	}
	
	class Done implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			ArrayList<Flashcard> temp = new ArrayList<Flashcard>();
			for (Flashcard card : highPriority) {
				temp.add(card);
			}
			for (Flashcard card : mediumPriority) {
				temp.add(card);
			}
			for (Flashcard card: lowPriority) {
				temp.add(card);
			}
			try {
				IO.saveDeck(temp, file);
			} catch (IOException err) {
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
		index = 0;
		deck = getHighestPriorityDeck();
		currentCard = deck.get(index);
	}
	
	protected ArrayList<Flashcard> getHighestPriorityDeck() {
		if (highPriority.size() != 0) {
			return highPriority;
		} else if (mediumPriority.size() !=0 ) {
			return mediumPriority;
		} else {
			return lowPriority;
		}
	}
	
	protected void assignPriorityDeck(Flashcard card) {
		if (card.getPriority() == 2) {
			highPriority.add(card);
		} else if (card.getPriority() == 1) {
			mediumPriority.add(card);
		} else if (card.getPriority() == 0) {
			lowPriority.add(card);
		}
	}
	
	

}
