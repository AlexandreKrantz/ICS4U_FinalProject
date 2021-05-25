import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class MainPanel extends Panel {
	private JTextArea frontTextArea;
	private JTextArea backTextArea;
	private JLabel frontLabel;
	private JLabel backLabel;
	
	
	public MainPanel() {
		frontLabel = new JLabel("Front");
		backLabel = new JLabel("Back");
		frontTextArea = new JTextArea();
		backTextArea = new JTextArea();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS)); // Vertical BoxLayout
		panel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Border spacing
		
		// Front
		panel.add(frontLabel);
		panel.add(prepInput(frontTextArea, new JScrollPane()));
		
		// Vertical spacing between front and back
		panel.add(Box.createRigidArea(new Dimension(0, 10))); 
		
		// Back
		panel.add(backLabel);
		panel.add(prepInput(backTextArea, new JScrollPane()));
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
		// Focus on frontTextArea
		frontTextArea.requestFocus();
	}
	
	public void focusFront() {
		frontTextArea.requestFocus();

	}

	public void focusBack() {
		backTextArea.requestFocus();
	}
	
	// Prepares text areas
	public JScrollPane prepInput(JTextArea input, JScrollPane container) {
		input.setLineWrap(true);
		container = new JScrollPane(input);
		return container;
	}
	
	public boolean checkInput() {
		if ((frontTextArea.getText().length() != 0) & (backTextArea.getText().length() != 0)) {
			return true;
		} else {
			return false;
		}
	}

	public void allowInput(boolean b) {
		frontTextArea.setEditable(b);
		backTextArea.setEditable(b);
	}
	
	
	public void showCard(Flashcard card) {
		frontTextArea.setText(card.getFront());
		backTextArea.setText(card.getBack());
	}

	public void showFront(Flashcard card) {
		frontTextArea.setText(card.getFront());
	}

	public void showBack(Flashcard card) {
		backTextArea.setText(card.getBack());
	}
	
}
