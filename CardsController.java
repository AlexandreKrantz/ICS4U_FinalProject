import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * 
 * @author Alex Krantz
 * Due date: May 28th
 * Class description: provides the basic variables and functions that are shared amongst the various features that display card decks
 */
public abstract class CardsController {
	protected MainPanel main; //Initialized in child class
	protected BottomPanel bottom; //Initialized in child class
	protected ArrayList<Flashcard> deck;
	protected JFrame frame;
	protected Flashcard currentCard;
	protected int index; // Index of currentCard

	/**
	 * Initializes variables
	 * @param f
	 */
	public CardsController(JFrame f) {
		deck = new ArrayList<Flashcard>();
		frame = f;
		index = 0; // index of currentCard is zero by default
	}
	
	/**
	 * Get next card in deck if available
	 */
	protected void incrementCurrentCard() {
		index++;
		if (index < deck.size()) {
			currentCard = deck.get(index);
		} else {
			// Action performed if index exceeds deck size; to be implemented in child class
			endOfDeckAction();
		}
	}
	
	
	protected abstract void endOfDeckAction();
	
	/**
	 * Attempts to save currentCard flashcard object to deck
	 * @return boolean indicating whether or not save was successful
	 */
	protected boolean saveCurrentCard() {
		// If currentCard object is in the deck and input is valid
		if (main.checkInput() && index < deck.size() && currentCard.equals(deck.get(index))) {
			// update currentCard object with user input
			currentCard.setFront(main.getFrontText());
			currentCard.setBack(main.getBackText());
			return true;
		}
		// If currentCard object is not in the deck (it is newly created) and input is valid
		else if (main.checkInput() && index < deck.size() && !currentCard.equals(deck.get(index))) {
			// update object with user input and add this new object to the deck at the current index
			currentCard.setFront(main.getFrontText());
			currentCard.setBack(main.getBackText());
			deck.add(index, currentCard);
			return true;
		}
		// If the current index is greater than the deck size and input is valid
		else if (main.checkInput() && index >= deck.size()) {
			// update object with user input and add this new object to the end of the deck
			currentCard.setFront(main.getFrontText());
			currentCard.setBack(main.getBackText());
			deck.add(currentCard);
			return true;
		}
		// In this case the input is probably invalid, so no flashcard is saved
		else {
			return false;
		}
	}
}
