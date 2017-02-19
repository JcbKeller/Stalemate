package gridsystem;
import java.awt.Graphics;

import stalemante.Team;

public abstract class Piece {

	protected int[] lastCoordinates;
	protected final Team team;

	public Piece(Team team) {
		super();
		this.team = team;
	}

	public int[] getPreviousCoordinates(){
		return this.lastCoordinates;
	}
	
	public Team getTeam(){
		return this.team;
	}

	public abstract boolean checkIfValidMove(Tile from, Tile to, int totalColumns);

	protected abstract void paint(Graphics g);

	@Override
	public String toString() {
		return team.name() + " " + getClass().getSimpleName() ;
	}
}
