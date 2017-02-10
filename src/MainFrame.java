import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame {
	
	public MainFrame(JComponent mainView, JComponent toolbar){
		
		JFrame frame = setupFrame();
		
		JScrollPane scrollpane = new JScrollPane(mainView);
		
		frame.add(scrollpane,BorderLayout.CENTER);
		frame.add(toolbar,BorderLayout.NORTH);
	}
	
	private JFrame setupFrame(){
		JFrame frame = new JFrame("Stalemate(Main Window)");		
		frame.setVisible(true);
		frame.setSize(425, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMaximumSize(new Dimension(4500,5500));
		frame.setMinimumSize(new Dimension(300,350));
		return frame;
	}
	
}
