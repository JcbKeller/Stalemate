import java.awt.Graphics;

public abstract class Piece {

	protected int[] coordinates;

	protected int team;

	public int[] getPieceCoordinates(){
		return this.coordinates;
	}

	public void setTeam(int newTeam){
		this.team = newTeam;
	}
	public int getTeam(){
		return this.team;
	}	
	public void setPieceCoordinates(int[] newCoordinates){
		this.coordinates = newCoordinates;
	}

	protected abstract boolean checkIfValidMove(Tile tile);

	protected abstract void paint(Graphics g);

}
