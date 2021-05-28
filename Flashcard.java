
/**
 * 
 * @author Alex Krantz
 * Due date: May 28th
 * Class description: Object containing the properties of a flashcard and getter and setters that limit priority values
 */
public class Flashcard {
	private String front;
	private String back;
	private int priority;
	
	/**
	 * Inializes flashcard variables
	 * @param frontInput text for the front of the flashcard
	 * @param backInput text for the back of the flashcard
	 */
	public Flashcard(String frontInput, String backInput) {
		front = frontInput;
		back = backInput;
		// High priority by default; 0 is low priority
		priority = 2;
	}
	
	// Getters and setters
	
	public String getFront() {
		return front;
	}
	
	public void setFront(String frontInput) {
		front = frontInput;
	}
	
	public String getBack() {
		return back;
	}
	
	public void setBack(String backInput) {
		back = backInput;
	}
	
	public int getPriority() {
		return priority;
	}
	
	/**
	 * Priority must stay between 0 and 2
	 * @param num
	 */
	public void setPriority(int num) {
		if (0 <= num & num <= 2) {
			priority = num;
		}
	}
	
	public void increasePriority() {
		if (priority < 2 ) {
			priority++;
		}
	}
	
	public void decreasePriority() {
		if (priority > 0) {
			priority--;
		}
	}
	
}
