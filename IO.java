import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * 
 * @author Alex Krantz
 * Due date: May 28th
 * Class description: provides helper functions for reading and writing a deck of flashcards to and from local files
 */
public class IO {
	
	// This method loads an ArrayList of flashcards from a local text file
	public static ArrayList<Flashcard> loadDeck(File file) throws IOException{
		ArrayList<Flashcard> deck = new ArrayList<Flashcard>();
		FileReader fr = null;
		BufferedReader br = null;
		String str;
		String[] arr;
		int priority;
		String front;
		String back;
		
		try {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			// For each line of text in the file
			while((str = br.readLine()) != null) {
				// Split the line of text several strings using "|" as a delimiter and store these strings in an array
				arr = str.split("\\|");
				priority = Integer.parseInt(arr[0]); // The first string should contain the priority int
				front = arr[1]; // Second string contains front card text
				back = arr[2]; // Third contains back card text
				// Instantiate a new flashcard object with this data
				Flashcard card = new Flashcard(front, back);
				card.setPriority(priority);
				// Add it to the local deck ArrayList
				deck.add(card);
			}
		}
		// Close the readers if they were successfully instantiated
		finally {
			if (fr != null) {
				fr.close();
			}
			if (br != null) {
				br.close();
			}
		}
		
		// return ArrayList of flashcards
		return deck;
	}
	
	// Save ArrayList of Flashcard objects to a local file
	public static void saveDeck (ArrayList<Flashcard> deck, File file) throws IOException {
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			fw = new FileWriter(file);
			pw = new PrintWriter(fw);
			for (Flashcard card : deck) {
				// Create a string containing card data separated by "|"
				String str = card.getPriority() + "|" + card.getFront() + "|" + card.getBack();
				// Save string as a new line to local file
				pw.println(str);			
			}
		}
		// If file writers were successfully instantiated, close them
		finally {
			if (fw != null) {
				fw.close();
			}
			if (pw != null) {
				pw.close();
			}
		}
	}
}
