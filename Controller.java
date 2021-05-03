import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class Controller implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getActionCommand() == "Next") {
			nextCard();
		}
		else if (e.getActionCommand() == "Previous") {
			previousCard();
		}
		else if (e.getActionCommand() == "Done") {
			closeDeck();
		}
	}
	public abstract void nextCard();
	public abstract void previousCard();
	public abstract void closeDeck();
}
