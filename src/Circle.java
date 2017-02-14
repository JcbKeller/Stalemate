import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Piece{
	
	private int[] coordinates;

	public boolean checkIfValidMove(Tile tile){
		if(tile.getCoordinates()[1] == coordinates[1]){
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
		g.setColor(Color.BLUE);
		g.fillOval(2, 2, 100, 100);
	}

}
