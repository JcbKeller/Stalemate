import java.awt.Color;
import java.awt.Graphics;

public class Square extends Piece{
	
	private int[] coordinates;
	
	public boolean checkIfValidMove(Tile tile){
		if(tile.getCoordinates()[0] == coordinates[0]){
			return true;			
		}else{
			return false;
		}
	}
	
	public int[] getPieceCoordinates(){
		return this.coordinates;
	}
	
	public void setPieceCoordinates(int[] newCoordinates){
		this.coordinates = newCoordinates;
	}
	
	public void paint(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(5, 5, 95, 95);
	}

}
