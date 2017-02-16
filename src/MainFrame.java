import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class MainFrame {

	JScrollPane scrollpane;
	JFrame frame;

	JTabbedPane rulesPane;
	JFrame rulesFrame;
	
	public MainFrame(GameSystem gameSystem){
		frame = new JFrame("Stalemate (Main Window)");

		GridView grid = new GridView(gameSystem);		
		scrollpane = new JScrollPane(grid);
		JComponent toolbar = new Toolbar(gameSystem);

		JFrame frame = setupFrame(gameSystem.getStartingWindowSize());
		frame.add(scrollpane,BorderLayout.CENTER);
		frame.add(toolbar,BorderLayout.NORTH);
//		frame.add(rules);

//		rulesPane = new JTabbedPane();
//		JComponent panel1 = new JLabel("The Objective is to win.");
//		rulesPane.addTab("Objective", panel1);
//		rulesFrame.add(rulesPane);
	}

	public void restartGame(GameSystem gameSystem){
		GridView grid = new GridView(gameSystem);
		frame.setVisible(false);
		frame.removeAll();
		frame = new JFrame("Stalemate (Main Window)");
		scrollpane = new JScrollPane(grid);
		JComponent toolbar = new Toolbar(gameSystem);
		JFrame frame = setupFrame(gameSystem.getStartingWindowSize());
		frame.add(scrollpane,BorderLayout.CENTER);
		frame.add(toolbar,BorderLayout.NORTH);
	}
	
	private JFrame setupFrame(int[] windowSize){
		frame.setVisible(true);
		frame.setSize(windowSize[0],windowSize[1]);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMaximumSize(new Dimension(4500,5500));
		frame.setMinimumSize(new Dimension(300,350));
		return frame;
	}

}
