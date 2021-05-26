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

public class GUI {
	private JFrame frame;
	
	
	GUI () {
		frame = new JFrame("App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(300,300);
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run () {
				new MenuController(frame);
			}
		});

	}
	

}
