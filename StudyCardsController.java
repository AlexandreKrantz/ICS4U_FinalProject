import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * 
 * @author Alex Krantz
 * Due date: May 28th
 * Class description: Controls the logic for the study feature
 */
public class StudyCardsController extends CardsController {
	private JFileChooser fileInput;
	private File file;
	
	// button properties for first bottom panel
	private String[] buttonNames = {"Show back", "Done"};
	private ActionListener[] buttonListeners = {new showBack(), new Done()};
	
	// button properties for second bottom panel (to show when users is shown the answer)
	protected BottomPanel bottom2; // second bottomPanel is not contained in parent class so it must be instantiated
	private String[] buttonNames2 = {"Incorrect", "Correct"};
	private ActionListener[] buttonListeners2 = {new Incorrect(), new Correct()};
	
	// instantiate seperate array lists to store cards according to priority
	private ArrayList<Flashcard> highPriority = new ArrayList<Flashcard>();
	private ArrayList<Flashcard> mediumPriority = new ArrayList<Flashcard>();
	private ArrayList<Flashcard> lowPriority = new ArrayList<Flashcard>();
	
	/**
	 * Updates GUI for studying flashcards
	 * @param f frame that panel will be added to
	 */
	public StudyCardsController(JFrame f) {
		super(f);
		
		// instantiate panels, show main and first bottom by default
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
			e.printStackTrace();
		}
		
		// assign each card in the deck to a priority deck 
		for (int i = 0; i < deck.size(); i++) {
			assignPriorityDeck(deck.get(i));
		}
		
		deck = getHighestPriorityDeck(); // set the deck equal to the highest priority deck
		currentCard = deck.get(0); // currentCard equals the first card of the highest priority deck
		// show currentCard
		main.showCard(currentCard);
		main.disableInput();
		main.hideBack();
		
	}
	
	// When user clicks "show back" button
	class showBack implements ActionListener {
		/**
		 * shows the back of the current card being studied
		 */
		public void actionPerformed(ActionEvent e) {
			main.showBack();
			// switch the bottom panels so that the user can input whether they answered correctly or incorrectly
			bottom.removePanel(frame);
			bottom2.addPanel(frame);
		}
		
	}
	
	// When user clicks "incorrect" button
	class Incorrect implements ActionListener {
		/**
		 * increases the priority of the current flashcard then shows the front of a new flashcard
		 */
		public void actionPerformed(ActionEvent e) {
			currentCard.increasePriority(); // increase card priority
			deck.remove(0); // removes currentCard from the deck that is currently being reviewed
			assignPriorityDeck(currentCard); // adds currentCard to the deck corresponding to its new priority 
			deck = getHighestPriorityDeck(); // If currentCard was the last card in the deck, moves on to the next highest priority
			currentCard = deck.get(0); // currentCard now equals the first card of the highest priority deck
			// show front of current card
			main.showCard(currentCard);
			main.hideBack();
			// switch bottom panels so user has the "show back" and "save" buttons
			bottom2.removePanel(frame);
			bottom.addPanel(frame);
		}
		
	}
	
	// When the user clicks the "correct" button
	class Correct implements ActionListener {
		/**
		 *  same function as incorrect class except it decreases card priority
		 */
		public void actionPerformed(ActionEvent e) {
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
		/**
		 * Saves the users progress in the file and returns GUI to menu
		 */
		public void actionPerformed(ActionEvent e) {
			// Adds all the cards from each of the priority array lists to one array list
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
			//saves array list of all the cards to the file where the deck was originally retrieved
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
	
	/**
	 * when the end of the deck is reached, set currentCards as the first card of the next highest priority deck
	 */
	protected void endOfDeckAction() {
		index = 0;
		deck = getHighestPriorityDeck();
		currentCard = deck.get(index);
	}
	
	
	/**
	 * returns the highest priority deck that isn't empty
	 * @return deck of flashcards
	 */
	protected ArrayList<Flashcard> getHighestPriorityDeck() {
		if (highPriority.size() != 0) {
			return highPriority;
		} else if (mediumPriority.size() !=0 ) {
			return mediumPriority;
		} else {
			return lowPriority;
		}
	}
	
	/**
	 *  assigns flashcard to priority deck corresponding to its priority number attribute
	 * @param card to be added to deck.
	 */
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
