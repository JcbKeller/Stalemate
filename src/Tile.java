import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Tile extends JComponent {

	final Integer gridX, gridY;
	GamePiece thePiece = null;
	
	public Tile(GridView grid, Integer gridX, Integer gridY){
		this.gridY = gridY;
		this.gridX = gridX;
		this.setPreferredSize(new Dimension(103,103));
		this.setMaximumSize(new Dimension(105,105));
		this.setMinimumSize(new Dimension(100,100));

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse click at ("+ gridX.toString()+","+ gridY.toString()+")");
				grid.tileClicked(Tile.this);
				//repaint();
			}

		});

	}
	
	public void setPiece(GamePiece thePiece) {
		this.thePiece = thePiece;
		repaint();
	}
	
	public void unsetPiece(){
		this.thePiece = null;
		repaint();
	}

	public void paint(Graphics g){


		g.setColor(Color.WHITE);
		g.fillRect(2,2,100,100);
		g.setColor(Color.BLACK);
		g.drawRect(2,2,100,100);

		if(thePiece!=null){
			System.out.println("Painting piece at " + gridX + "," + gridY);
			thePiece.paint(g);
		}
	}

}


