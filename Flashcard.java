
public class Flashcard {
	private String front;
	private String back;
	private int priority;
	
	Flashcard(String frontInput, String backInput) {
		front = frontInput;
		back = backInput;
		priority = 2;
	}
	
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
