import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame { //This Class manages the display frame and views
	
	public MainFrame(JComponent mainView, JComponent toolbar){
		
		JScrollPane scrollpane = new JScrollPane(mainView);

		JFrame frame = setupFrame();
		frame.add(scrollpane,BorderLayout.CENTER);
		frame.add(toolbar,BorderLayout.NORTH);
	}
	
	private JFrame setupFrame(){
		JFrame frame = new JFrame("Stalemate (Main Window)");		
		frame.setVisible(true);
		frame.setSize(GameSystem.windowX, GameSystem.windowY);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMaximumSize(new Dimension(4500,5500));
		frame.setMinimumSize(new Dimension(300,350));
		return frame;
	}
	
}
