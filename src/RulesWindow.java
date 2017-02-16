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
import java.awt.event.KeyEvent;

public class RulesWindow extends JPanel{

	JTabbedPane rulesPane;
	JFrame rulesFrame;

	public RulesWindow() {
		rulesFrame = new JFrame("Rules Window");
		rulesFrame.setVisible(false);
		rulesFrame.setSize(500,200);
		rulesFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		rulesFrame.setMaximumSize(new Dimension(500,200));
		rulesFrame.setMinimumSize(new Dimension(500,200));
		
		rulesPane = new JTabbedPane();
		JComponent objectivePanel = new JPanel();
		objectivePanel.add(new JLabel("The Objective is to win."));
		objectivePanel.add(new JLabel("You do that by moving a piece into one of your Opponent's starting Tiles."),BorderLayout.WEST);
		JComponent piecePanel = new JPanel();
		piecePanel.add(new JLabel("Pieces all move differently."));
		piecePanel.add(new JLabel("-------------------------------------------"));
		piecePanel.add(new JLabel("Square: 1 Space Horizontally or 1 Space Vertically."),BorderLayout.WEST);
		piecePanel.add(new JLabel("Circle: 2 Spaces Horizontally or 2 Spaces Vertically."),BorderLayout.WEST);
		piecePanel.add(new JLabel("Triangle: 1 Space Diagonally."));
		piecePanel.add(new JLabel("NOTE: The Screen wraps horizontally, so plan your moves carefully!"));
		JComponent capturingPanel = new JPanel();
		capturingPanel.add(new JLabel("Pieces are not immortal."));
		capturingPanel.add(new JLabel("However, they cannot be attacked when in a starting Tile."),BorderLayout.WEST);
		JComponent tipsPanel = new JPanel();
		tipsPanel.add(new JLabel("Tip 1: Don't let any pieces past your defenses!"));
		tipsPanel.add(new JLabel("It might be a good idea to keep at least 1 piece near your starting Tiles."),BorderLayout.WEST);
		tipsPanel.add(new JLabel("----------------------------------------------"));
		tipsPanel.add(new JLabel("Tip 2: The Circle is the only piece that cannot reach the winning Tiles."));
		tipsPanel.add(new JLabel("If you need to sacrifice a piece, losing the Circle is ideal."),BorderLayout.WEST);

		rulesPane.addTab("Objective", objectivePanel);
		rulesPane.addTab("Pieces", piecePanel);
		rulesPane.addTab("Capturing", capturingPanel);
		rulesPane.addTab("Tips", tipsPanel);
		rulesFrame.add(rulesPane,BorderLayout.CENTER);
	}
	

}
