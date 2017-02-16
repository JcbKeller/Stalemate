import java.awt.Color;
import java.awt.Graphics;

public class Triangle extends Piece {

	private int[] xPoints = {5,50,95};
	private int[] yPoints = {90,10,90};
	private int nPoints = 3;

	protected boolean checkIfValidMove(Tile tile) {
		if(xDistance(tile,this) == 1 && + yDistance(tile,this) == 1){
			return true;
		}else{
			if(wrapXDistance(tile,this) == 1 && + yDistance(tile,this) == 1){
				return true;
			}else{
				return false;
			}
		}
	}
	private int xDistance(Tile tile, Piece piece){
		return Math.abs(tile.getCoordinates()[0]-piece.getPieceCoordinates()[0]);
	}
	private int wrapXDistance(Tile tile, Piece piece){
		if(this.coordinates[0] > GameSystem.totalColumns-2){
			if(tile.getCoordinates()[0] < 2){
				return Math.abs(tile.getCoordinates()[0]+GameSystem.totalColumns-piece.getPieceCoordinates()[0]);			
			}else{
				return Math.abs(tile.getCoordinates()[0]-piece.getPieceCoordinates()[0]);			

			}
		}else if (this.coordinates[0] < 2){
			if(tile.getCoordinates()[0] > GameSystem.totalColumns-2){
				return Math.abs(tile.getCoordinates()[0]-GameSystem.totalColumns-piece.getPieceCoordinates()[0]);			
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
	protected void paint(Graphics g) {
		if(team == 1){
			g.setColor(Color.BLUE);			
		}else{
			g.setColor(Color.RED);	
		}
		g.fillPolygon(xPoints,yPoints,nPoints);
	}

}
