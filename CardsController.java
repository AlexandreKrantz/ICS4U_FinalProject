import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public abstract class CardsController {
	protected MainPanel main;
	protected BottomPanel bottom;
	protected ArrayList<Flashcard> deck;
	protected JFrame frame;
	protected Flashcard currentCard;
	protected int index;


	
	public CardsController(JFrame f) {
		deck = new ArrayList<Flashcard>();
		frame = f;
		index = 0;


	}
	
	protected void incrementCurrentCard() {
		index++;
		if (index < deck.size()) {
			index++;
			currentCard = deck.get(index);
		} else {
			endOfDeckAction();
		}
	}
	
	protected abstract void endOfDeckAction();
	
	
	protected boolean saveCurrentCard() {
		if (main.checkInput() && index < deck.size() && currentCard.equals(deck.get(index))) {
			currentCard.setFront(main.getFrontText());
			currentCard.setBack(main.getBackText());
			return true;
		}
		else if (main.checkInput() && index < deck.size() && !currentCard.equals(deck.get(index))) {
			currentCard.setFront(main.getFrontText());
			currentCard.setBack(main.getBackText());
			deck.add(index, currentCard);
			return true;
		}
		else if (main.checkInput() && index >= deck.size()) {
			currentCard.setFront(main.getFrontText());
			currentCard.setBack(main.getBackText());
			deck.add(currentCard);
			return true;
		}
		else {
			return false;
		}
	}
}
