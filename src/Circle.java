import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Piece{
	
	public boolean checkIfValidMove(Tile tile){
		if(xDistance(tile,this) == 2 && yDistance(tile,this) == 0 || xDistance(tile,this) == 0 && yDistance(tile,this) == 2){
			return true;			
		}else{
			if(wrapXDistance(tile,this) == 2 && yDistance(tile,this) == 0 || wrapXDistance(tile,this) == 0 && yDistance(tile,this) == 2){
				return true;
			}else{
				return false;				
			}
		}
	}
	private int xDistance(Tile tile, Piece piece){ //CHANGE TO THIS TO DISABLE SCREEN WRAP!!
		return Math.abs(tile.getCoordinates()[0]-piece.getPieceCoordinates()[0]);
}
	private int wrapXDistance(Tile tile, Piece piece){
		if(this.coordinates[0] > GameSystem.totalColumns-3){
			if(tile.getCoordinates()[0] < 2){
				return Math.abs(tile.getCoordinates()[0]+GameSystem.totalColumns-piece.getPieceCoordinates()[0]);			
			}else{
				return Math.abs(tile.getCoordinates()[0]-piece.getPieceCoordinates()[0]);			

			}
		}else if (this.coordinates[0] < 2){
			if(tile.getCoordinates()[0] > GameSystem.totalColumns-3){
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

	public void paint(Graphics g){
		if(team == 1){
			g.setColor(Color.BLUE);			
		}else{
			g.setColor(Color.RED);	
		}
		g.fillOval(10, 10, 85, 85);
	}

}
