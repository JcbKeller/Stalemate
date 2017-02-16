import javax.swing.JFrame;
import java.awt.*;
import javax.swing.*;

public class MainFrame {

	public MainFrame(GameSystem gameSystem){
		
		GridView grid = new GridView(gameSystem);
		
		JScrollPane scrollpane = new JScrollPane(grid);
		JComponent toolbar = new Toolbar(gameSystem);

		
		JFrame frame = setupFrame(gameSystem.getStartingWindowSize());
		frame.add(scrollpane,BorderLayout.CENTER);
		frame.add(toolbar,BorderLayout.NORTH);

	}

	private JFrame setupFrame(int[] windowSize){
		JFrame frame = new JFrame("Stalemate (Main Window)");
		frame.setVisible(true);
		frame.setSize(windowSize[0],windowSize[1]);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMaximumSize(new Dimension(4500,5500));
		frame.setMinimumSize(new Dimension(300,350));
		return frame;
	}

}
