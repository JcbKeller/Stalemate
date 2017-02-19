import java.awt.Color;
import java.awt.Graphics;

public class Square extends Piece{
	
	public Square(int team) {
		super(team);
	}

	public boolean checkIfValidMove(Tile tile, int totalX){
		if(xDistance(tile,this) == 1 && yDistance(tile,this) <= 0 || xDistance(tile,this) <= 0 && yDistance(tile,this) == 1){
			return true;
		}else{
			if(wrapXDistance(tile,this, totalX) == 1 && yDistance(tile,this) <= 0 || wrapXDistance(tile,this, totalX) <= 0 && yDistance(tile,this) == 1){
				return true;
			}else{
				return false;
			}
		}
	}
	
	private int xDistance(Tile tile, Piece piece){
		return Math.abs(tile.getCoordinates()[0]-piece.getPieceCoordinates()[0]);
	}
	
	private int wrapXDistance(Tile tile, Piece piece, int totalX){
		if(this.coordinates[0] > totalX-2){
			if(tile.getCoordinates()[0] < 2){
				return Math.abs(tile.getCoordinates()[0]+totalX-piece.getPieceCoordinates()[0]);			
			}else{
				return Math.abs(tile.getCoordinates()[0]-piece.getPieceCoordinates()[0]);			
			}
		}else if (this.coordinates[0] < 2){
			if(tile.getCoordinates()[0] > totalX-2){
				return Math.abs(tile.getCoordinates()[0]-totalX-piece.getPieceCoordinates()[0]);			
			}else{
				return Math.abs(tile.getCoordinates()[0]-piece.getPieceCoordinates()[0]);			
			}
		}else{
			return Math.abs(tile.getCoordinates()[0]-piece.getPieceCoordinates()[0]);						
		}
	}
	
	private int yDistance(Tile tile, Piece piece){
		return Math.abs(tile.getCoordinates()[1]-piece.getPieceCoordinates()[1]);
	}

	public void paint(Graphics g){
		if(team == 1){
			g.setColor(Color.BLUE);			
		}else{
			g.setColor(Color.RED);	
		}
		g.fillRect(10, 10, 80, 80);
	}

}
