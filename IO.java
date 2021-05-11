import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class IO {
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
			while((str = br.readLine()) != null) {
				arr = str.split("\\|");
				priority = Integer.parseInt(arr[0]);
				front = arr[1];
				back = arr[2];
				Flashcard card = new Flashcard(front, back);
				card.setPriority(priority);
				deck.add(card);
			}
		}
		finally {
			if (fr != null) {
				fr.close();
			}
			if (br != null) {
				br.close();
			}
		}
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
				String str = card.getPriority() + "|" + card.getFront() + "|" + card.getBack();
				pw.println(str);			
			}
		}
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
