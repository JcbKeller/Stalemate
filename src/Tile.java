import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Tile extends JComponent {
	public interface Listener {
		void tileClicked(int[] coordinates, Piece containedPiece, boolean moveable, int tileType);
	}
	private int[] coordinates;
	private Piece containedPiece;
	private int tileType = 0;
	private boolean moveable = false;

	public Tile(int[] tileCoordinates, Listener tileClickedListener){	
		this.coordinates = tileCoordinates;
		this.setPreferredSize(new Dimension(103,103));
		this.setMaximumSize(new Dimension(105,105));
		this.setMinimumSize(new Dimension(100,100));

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tileClickedListener.tileClicked(coordinates, containedPiece, moveable, tileType);

			}
		});
	}

	public void setMoveable(boolean movability){
		moveable = movability;
	}

	public void setPieceValue(Piece pieceValue){
		containedPiece = pieceValue;
	}

	public int[] getCoordinates(){
		return this.coordinates;
	}

	public void setAsWinnable(int team){
		tileType = team;
	}

	public void paint(Graphics g){
		if(moveable == false){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(2,2,100,100);			
		}else{
			g.setColor(Color.WHITE);
			g.fillRect(2,2,100,100);
		}

		if(tileType == 1){
			g.setColor(Color.RED);
			g.drawRect(2,2,100,100);			
		}else if(tileType == 2){
			g.setColor(Color.BLUE);
			g.drawRect(2,2,100,100);			
		}else{
			g.setColor(Color.BLACK);
			g.drawRect(2,2,100,100);
		}

		if(containedPiece != null){
			containedPiece.paint(g);
		}
	}


}


