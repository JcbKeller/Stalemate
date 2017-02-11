import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Tile extends JComponent {//This Class handles individual tile responses

	private final Integer tileX, tileY;
	private GamePiece tilePiece = null;


	public Tile(GridView grid, int[] tileCoordinates){	
		this.tileY = tileCoordinates[1];
		this.tileX = tileCoordinates[0];
		this.setPreferredSize(new Dimension(103,103));
		this.setMaximumSize(new Dimension(105,105));
		this.setMinimumSize(new Dimension(100,100));

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse click at ("+ tileX.toString()+","+ tileY.toString()+")");
				GameSystem.checkTile(Tile.this, tileCoordinates);
			}
		});
	}

	public void displayPiece(GamePiece requestedPiece) {//
		this.tilePiece = requestedPiece;
		repaint();
	}

	public void erasePiece(){
		this.tilePiece = null;
		repaint();
	}

	public void paint(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect(2,2,100,100);
		g.setColor(Color.BLACK);
		g.drawRect(2,2,100,100);

		if(tilePiece!=null){
			System.out.println("Painting piece at " + tileX + "," + tileY);
			tilePiece.paint(g);
		}
	}

}


