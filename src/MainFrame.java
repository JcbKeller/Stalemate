import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;

public class MainFrame {

	JScrollPane scrollpane;
	JFrame frame;

	
	public MainFrame(GameSystem gameSystem){
		frame = new JFrame("Stalemate (Main Window)");

		GridView grid = new GridView(gameSystem);
		
		scrollpane = new JScrollPane(grid);
		JComponent toolbar = new Toolbar(gameSystem);

		
		JFrame frame = setupFrame(gameSystem.getStartingWindowSize());
		frame.add(scrollpane,BorderLayout.CENTER);
		frame.add(toolbar,BorderLayout.NORTH);

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
