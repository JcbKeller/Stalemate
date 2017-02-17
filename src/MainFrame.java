import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.JTabbedPane;
import javax.swing.JComponent;
import java.awt.BorderLayout;
import java.awt.Dimension;


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
		frame.getContentPane().removeAll();
		scrollpane = new JScrollPane(grid);
		JComponent toolbar = new Toolbar(gameSystem);
		JFrame frame = setupFrame(gameSystem.getStartingWindowSize());
		frame.add(scrollpane,BorderLayout.CENTER);
		frame.add(toolbar,BorderLayout.NORTH);
		frame.revalidate();
		frame.repaint();

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
