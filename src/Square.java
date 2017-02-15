import java.awt.Color;
import java.awt.Graphics;

public class Square extends Piece{
	
	public boolean checkIfValidMove(Tile tile){
		if(xDistance(tile,this) ==1 && yDistance(tile,this) <=0 || xDistance(tile,this) <=0 && yDistance(tile,this) ==1 ){
			return true;
		}else{
			return false;
		}
	}
//	private int xDistance(Tile tile, Piece piece){ //CHANGE TO THIS TO DISABLE SCREEN WRAP!!
//		return Math.abs(tile.getCoordinates()[0]-piece.getPieceCoordinates()[0]);
//	}
	private int xDistance(Tile tile, Piece piece){
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
	
	// MOVEMENT FOR TRIANGLE: tile.getCoordinates()[0] + tile.getCoordinates()[1] == coordinates[0] + coordinates[1] || tile.getCoordinates()[0] - tile.getCoordinates()[1] == coordinates[0] - coordinates[1]	

	public void paint(Graphics g){
		if(team == 1){
			g.setColor(Color.BLUE);			
		}else{
			g.setColor(Color.RED);	
		}
		g.fillRect(10, 10, 80, 80);
	}

}
