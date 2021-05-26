import javax.swing.SwingUtilities;
/**
 * 
 * @author Alex Krantz
 * Due date: May 28th
 * Class description: launches the program by invoking the main method and instantiating the GUI class
 */
public class Launcher {

	public static void main(String[] args) {
		// Instantiate the GUI class to launch the program
		SwingUtilities.invokeLater(new Runnable() {
			public void run () {
				new GUI();
			}
		});
		
	}

}
