import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Piece{
	
	public boolean checkIfValidMove(Tile tile){
		if(xDistance(tile,this) == 2 && yDistance(tile,this) == 0 || xDistance(tile,this) == 0 && yDistance(tile,this) == 2){
			return true;			
		}else{
			return false;
		}
	}
	private int xDistance(Tile tile, Piece piece){
		return Math.abs(tile.getCoordinates()[0]-piece.getPieceCoordinates()[0]);
	}
	private int yDistance(Tile tile, Piece piece){
		return Math.abs(tile.getCoordinates()[1]-piece.getPieceCoordinates()[1]);
	}

	public void paint(Graphics g){
		g.setColor(Color.RED);
		g.fillOval(10, 10, 85, 85);
	}

}
