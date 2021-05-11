import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import java.io.*;

public class GUI {
	private JFrame frame = new JFrame("App");
	
	
	GUI () {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(300,300);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run () {
				new Menu();
			}
		});

	}
	
	class Menu implements ActionListener {
		JPanel menuPanel = new JPanel();
		JButton createButton = new JButton("Create");
		JButton editButton = new JButton("Edit");
		JButton studyButton = new JButton("Study");
		
		Menu () {
			createButton.addActionListener(this);
			editButton.addActionListener(this);
			studyButton.addActionListener(this);
			
			menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS)); // Vertical BoxLayout
			menuPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Border spacing
			
			
			menuPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Vertical spacing between buttons
			menuPanel.add(createButton);
			menuPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Vertical spacing between buttons
			menuPanel.add(editButton);
			menuPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
			menuPanel.add(studyButton);
			
			createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			studyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			frame.add(menuPanel, BorderLayout.CENTER);
			frame.repaint();
			frame.revalidate();

		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "Create") {
				System.out.println("Create cards");
				frame.remove(menuPanel);
				SwingUtilities.invokeLater(new Runnable() {
					public void run () {
						new CreateCards();
					}
				});
			}
			if (e.getActionCommand() == "Edit") {
				System.out.println("Edit cards");
				frame.remove(menuPanel);
				SwingUtilities.invokeLater(new Runnable() {
					public void run () {
						new EditCards();
					}
				});
			}
		}
	}
	
	class CreateCards implements ActionListener {
		ArrayList<Flashcard> deck = new ArrayList<Flashcard>();
		JPanel mainPanel;
		JPanel bottomPanel;
		JTextArea frontTextArea = new JTextArea();
		JTextArea backTextArea = new JTextArea();
		JButton nextButton = new JButton("Next");
		JButton previousButton = new JButton("Previous");
		JButton doneButton = new JButton("Done");
		JLabel frontLabel = new JLabel("Front");
		JLabel backLabel = new JLabel("Back");
		
		CreateCards() {			
			previousButton.addActionListener(this);
			nextButton.addActionListener(this);
			doneButton.addActionListener(this);
			
			// Prepare mainPanel
			mainPanel = prepMain(mainPanel, frontLabel, backLabel, frontTextArea, backTextArea);
			
			// Add mainPanel to pane
			frame.add(mainPanel, BorderLayout.CENTER);
			
			// Prepare bottomPanel
			bottomPanel = prepBottom(bottomPanel, previousButton, nextButton, doneButton);
			
			// Add bottomPanel to pane
			frame.add(bottomPanel, BorderLayout.PAGE_END);
			
			frame.repaint();
			frame.revalidate();
		}
		
		// Button actions
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand() == "Next" & checkInput(frontTextArea, backTextArea)) {
				// Create new flashcard object and add to deck
				deck.add(new Flashcard(frontTextArea.getText(), backTextArea.getText()));
				System.out.println("Deck size = " + deck.size());
				// Clear text areas
				frontTextArea.setText("");
				backTextArea.setText("");
				// Focus on frontTextArea
				frontTextArea.requestFocus();
				// TODO: Give an indication to the user that the card has been created

			}
			if (e.getActionCommand() == "Previous" & checkInput(frontTextArea, backTextArea)) {
				// TODO: Save current card then retrieve previous flashcard
				
				
			}
			if (e.getActionCommand() == "Done" & checkInput(frontTextArea, backTextArea)) {
				// Save current card
				deck.add(new Flashcard(frontTextArea.getText(), backTextArea.getText()));
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
				frame.remove(mainPanel);
				frame.remove(bottomPanel);
				// Load menu
				SwingUtilities.invokeLater(new Runnable() {
					public void run () {
						new Menu();
					}
				});
				
			}
		}
	}
	
	class EditCards implements ActionListener {
		ArrayList<Flashcard> deck;
		Iterator<Flashcard> it;
		Flashcard currentCard;
		JPanel mainPanel;
		JPanel bottomPanel;
		JTextArea frontTextArea = new JTextArea();
		JTextArea backTextArea = new JTextArea();
		JButton nextButton = new JButton("Next");
		JButton previousButton = new JButton("Previous");
		JButton doneButton = new JButton("Done");
		JLabel frontLabel = new JLabel("Front");
		JLabel backLabel = new JLabel("Back");
		JFileChooser fileInput;
		File file;
		
		EditCards() {			
			previousButton.addActionListener(this);
			nextButton.addActionListener(this);
			doneButton.addActionListener(this);
			
			// Prepare mainPanel
			mainPanel = prepMain(mainPanel, frontLabel, backLabel, frontTextArea, backTextArea);
			
			// Add mainPanel to pane
			frame.add(mainPanel, BorderLayout.CENTER);
			
			// Prepare bottomPanel
			bottomPanel = prepBottom(bottomPanel, previousButton, nextButton, doneButton);
			
			// Add bottomPanel to pane
			frame.add(bottomPanel, BorderLayout.PAGE_END);
			
			frame.repaint();
			frame.revalidate();
			
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
			it = deck.iterator();
			currentCard = it.next();
			showCard(currentCard);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// Iterator keeps track of currently displayed flashcard and edit ArrayList accordingly
			if (e.getActionCommand() == "Next" & checkInput(frontTextArea, backTextArea) & it.hasNext())  {
				saveCard(currentCard);
				currentCard = it.next();
				showCard(currentCard);
			} else if (e.getActionCommand() == "Next" & checkInput(frontTextArea, backTextArea) & !it.hasNext()) {
				System.out.println("New card");
				// If there are no more old flashcards to edit, create a new flashcard
				saveCard(currentCard);
				deck.add(new Flashcard("", ""));
				currentCard = it.next();
				System.out.println("Deck size = " + deck.size());
				// Clear text areas
				frontTextArea.setText("");
				backTextArea.setText("");
				// Focus on frontTextArea
				frontTextArea.requestFocus();
			}
			if (e.getActionCommand() == "Previous" & checkInput(frontTextArea, backTextArea)) {
				
			}
			if (e.getActionCommand() == "Done" & checkInput(frontTextArea, backTextArea)) {
				saveCard(currentCard);
				try {
					IO.saveDeck(deck, file);
				} catch (IOException err) {
					err.printStackTrace();
				}
				// Remove panels from pane
				frame.remove(mainPanel);
				frame.remove(bottomPanel);
				// Load menu
				SwingUtilities.invokeLater(new Runnable() {
					public void run () {
						new Menu();
					}
				});
			}
			
		}
		public void saveCard(Flashcard card) {
			currentCard.setFront(frontTextArea.getText());
			currentCard.setBack(backTextArea.getText());
		}
		
		public void showCard(Flashcard card) {
			frontTextArea.setText(card.getFront());
			backTextArea.setText(card.getBack());
		}
		
	}
	
	class StudyCards implements ActionListener {
		ArrayList<Flashcard> deck;
		Iterator<Flashcard> it;
		Flashcard currentCard;
		JPanel mainPanel;
		JPanel bottomPanel;
		JTextArea frontTextArea = new JTextArea();
		JTextArea backTextArea = new JTextArea();
		JButton nextButton = new JButton("Next");
		JButton previousButton = new JButton("Previous");
		JButton doneButton = new JButton("Done");
		JLabel frontLabel = new JLabel("Front");
		JLabel backLabel = new JLabel("Back");
		JFileChooser fileInput;
		File file;
		
		StudyCards() {
			
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public JPanel prepMain(JPanel mainPanel, JLabel frontLabel, JLabel backLabel, JTextArea frontTextArea, JTextArea backTextArea) {
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS)); // Vertical BoxLayout
		mainPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10)); // Border spacing
		
		// Front
		mainPanel.add(frontLabel);
		mainPanel.add(prepInput(frontTextArea, new JScrollPane()));
		
		// Vertical spacing between front and back
		mainPanel.add(Box.createRigidArea(new Dimension(0, 10))); 
		
		// Back
		mainPanel.add(backLabel);
		mainPanel.add(prepInput(backTextArea, new JScrollPane()));
		return mainPanel;
	}
	
	public JPanel prepBottom(JPanel bottomPanel, JButton previousButton, JButton nextButton, JButton doneButton) {
		bottomPanel = new JPanel();
		
		// Prepare bottom panel with buttons
		bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.LINE_AXIS)); // Horizontal BoxLayout
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Overall bottomPanel spacing
		bottomPanel.add(Box.createHorizontalGlue()); // Space buttons to the right
		bottomPanel.add(previousButton);
		bottomPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Spacing between buttons
		bottomPanel.add(nextButton);
		bottomPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Spacing between buttons
		bottomPanel.add(doneButton);
		
		return bottomPanel;
	}
	
	// Prepares text areas
	public JScrollPane prepInput(JTextArea input, JScrollPane container) {
		input.setLineWrap(true);
		container = new JScrollPane(input);
		return container;
	}
	
	public boolean checkInput(JTextArea front, JTextArea back) {
		if ((front.getText().length() != 0) & (back.getText().length() != 0)) {
			return true;
		} else {
			return false;
		}
	}

}
