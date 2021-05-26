import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;

/**
 * 
 * @author Alex Krantz
 * Due date: May 28th
 * Class description: prepares a JPanel containing all the elements required for displaying a flashcard in the GUI and methods for modifying these elements and getting user input.
 */
public class MainPanel extends Panel {
	private JTextArea frontTextArea;
	private JTextArea backTextArea;
	private JLabel frontLabel;
	private JLabel backLabel;
	private JScrollPane frontScrollPane;
	private JScrollPane backScrollPane;
	
	
	public MainPanel() {
		frontLabel = new JLabel("Front");
		backLabel = new JLabel("Back");
		frontTextArea = new JTextArea();
		backTextArea = new JTextArea();
		frontScrollPane = new JScrollPane();
		backScrollPane = new JScrollPane();
		
		// Panel
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS)); // Vertical BoxLayout
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Border spacing
		
		// Front of card
		panel.add(frontLabel);
		panel.add(prepInput(frontTextArea, frontScrollPane));
		
		// Vertical spacing between front and back
		panel.add(Box.createRigidArea(new Dimension(0, 10))); 
		
		// Back of card
		panel.add(backLabel);
		panel.add(prepInput(backTextArea, backScrollPane));
	}
	
	public String getFrontText() {
		return frontTextArea.getText();
	}
	
	public String getBackText() {
		return backTextArea.getText();
	}
	
	public void setFrontText(String str) {
		frontTextArea.setText(str);
	}
	
	public void setBackText(String str) {
		backTextArea.setText(str);
	}
	
	public void resetText() {
		// Clear text areas
		frontTextArea.setText("");
		backTextArea.setText("");
	}
	
	public void focusFront() {
		frontTextArea.requestFocus();

	}

	public void focusBack() {
		backTextArea.requestFocus();
	}
	
	public void hideBack() {
		backLabel.setText("Back (hidden)");
		backTextArea.setVisible(false);

	}
	
	public void showBack() {
		backLabel.setText("Back");
		backTextArea.setVisible(true);
	}
	
	// Prepares text areas
	public JScrollPane prepInput(JTextArea input, JScrollPane container) {
		input.setLineWrap(true); //input wraps to a new line
		container = new JScrollPane(input); // Enables user can scroll if input exceeds TextArea size
		return container; 
	}
	
	// Returns true if the input for the front and back is not empty.
	public boolean checkInput() {
		if ((frontTextArea.getText().length() != 0) & (backTextArea.getText().length() != 0)) {
			return true;
		} else {
			return false;
		}
	}
	
	// Restricts user from modifying text areas
	public void disableInput() {
		frontTextArea.setEditable(false);
		backTextArea.setEditable(false);
	}
	
	// Gets front and back strings from flashCard object and displays them in TextAreas
	public void showCard(Flashcard card) {
		frontTextArea.setText(card.getFront());
		backTextArea.setText(card.getBack());
	}
	
	
}
