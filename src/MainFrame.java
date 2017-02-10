import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame {
	public MainFrame(JComponent mainView, JComponent toolbar){
		
		JScrollPane scrollpane = new JScrollPane(mainView);
		
		JFrame frame = new JFrame("Mega Grid");
		frame.setVisible(true);
		frame.setSize(425, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMaximumSize(new Dimension(4500,5500));
		frame.setMinimumSize(new Dimension(300,350));
		
		frame.add(scrollpane,BorderLayout.CENTER);
		frame.add(toolbar,BorderLayout.NORTH);
	}
	
}
