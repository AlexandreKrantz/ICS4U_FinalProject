import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;
import java.io.*;
/**
 * 
 * @author Alex Krantz
 * Due date: May 28th
 * Class description: Instantiates the frame object and menu
 */
public class GUI {
	private JFrame frame;
	
	
	GUI () {
		// Initialize frame
		frame = new JFrame("App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(300,300);
		
		// Initialize menu and pass on reference to frame
		SwingUtilities.invokeLater(new Runnable() {
			public void run () {
				new MenuController(frame);
			}
		});
	}
}
