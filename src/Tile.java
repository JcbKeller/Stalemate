import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Tile extends JComponent {
	public interface Listener {
		void tileClicked(Tile tile);
	}
	
	private int[] coordinates;
	private Piece containedPiece;
	private int tileType = 0;
	private boolean moveable = false;
	private boolean safe = false;
	
	
	public Tile(int[] tileCoordinates, Listener tileClickedListener){	
		this.coordinates = tileCoordinates;
		Tile tile = this;
		this.setPreferredSize(new Dimension(103,103));
		this.setMaximumSize(new Dimension(105,105));
		this.setMinimumSize(new Dimension(100,100));

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tileClickedListener.tileClicked(tile);

			}
		});
	}

	public void setType(int type){
		tileType = type;
	}
	
	public int getType(){
		return tileType;
	}
	
	public void setMoveable(boolean movability){
		moveable = movability;
	}
	
	public boolean checkIfMoveable(){
		return moveable;
	}
	
	public void setPiece(Piece pieceValue){
		containedPiece = pieceValue;
	}

	public Piece getPiece(){
		return containedPiece;
	}

	public int[] getCoordinates(){
		return this.coordinates;
	}
	
	public void setAsWinnable(int team){
		tileType = team;
	}
	
	public void setAsSafe(boolean safety){
		safe = safety;
	}
	
	public boolean checkIfSafe(){
		return safe;
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

	
	@Override
	public String toString() {
		return "Tile " + coordinates[0] + ", " + coordinates[1];
	}
	

}


