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
		
		restartGame(gameSystem);
	}
	
	public void restartGame(GameSystem gameSystem){
//		final GridView.Listener gridListener = new GridView.Listener() {
//			
//			@Override
//			public void pieceClicked(Piece containedPiece, boolean moveable, int[] coordinates, int tileType) {
//				
//			}
//		};
		
		
		GridView grid = new GridView(gameSystem, gameSystem.getTotalColumns(), gameSystem.getTotalRows());
		gameSystem.setGrid(grid);
		
		frame.getContentPane().removeAll();
		scrollpane = new JScrollPane(grid);
		JComponent toolbar = new Toolbar(gameSystem);
		JFrame frame = setupFrame(gameSystem.getStartingWindowSize());
		frame.add(scrollpane,BorderLayout.CENTER);
		frame.add(toolbar,BorderLayout.NORTH);
		frame.revalidate();
		frame.repaint();
		
		gameSystem.addPieceToGame(new Square(1), new int[] {1,0});
		gameSystem.addPieceToGame(new Circle(1), new int[] {2,0});
		gameSystem.addPieceToGame(new Triangle(1), new int[] {3,0});
		
		gameSystem.addPieceToGame(new Square(2), new int[] {1,5});
		gameSystem.addPieceToGame(new Circle(2), new int[] {2,5});
		gameSystem.addPieceToGame(new Triangle(2), new int[] {3,5});
		
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
