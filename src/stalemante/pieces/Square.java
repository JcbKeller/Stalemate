package stalemante.pieces;
import java.awt.Graphics;

import gridsystem.Piece;
import gridsystem.Tile;
import stalemante.Team;

public class Square extends Piece{
	
	public Square(Team team) {
		super(team);
	}
	
	
	public boolean checkIfValidMove(Tile from, Tile tile, int totalX){
		if(xDistance(tile,from) == 1 && yDistance(tile,from) == 0 || xDistance(tile,from) == 0 && yDistance(tile,from) == 1){
			return true;
		}else{
			if(wrapXDistance(tile,from, totalX) == 1 && yDistance(tile,from) == 0 || wrapXDistance(tile,from, totalX) == 0 && yDistance(tile,from) == 1){
				return true;
			}else{
				return false;
			}
		}
	}
	
	private int xDistance(Tile tile, Tile from){
		return Math.abs(tile.getCoordinates()[0]-from.getCoordinates()[0]);
	}
	
	private int wrapXDistance(Tile tile, Tile from, int totalX){
		if(from.getCoordinates()[0] > totalX-2){
			if(tile.getCoordinates()[0] < 2){
				return Math.abs(tile.getCoordinates()[0]+totalX-from.getCoordinates()[0]);			
			}else{
				return Math.abs(tile.getCoordinates()[0]-from.getCoordinates()[0]);			
			}
		}else if (from.getCoordinates()[0] < 2){
			if(tile.getCoordinates()[0] > totalX-2){
				return Math.abs(tile.getCoordinates()[0]-totalX-from.getCoordinates()[0]);			
			}else{
				return Math.abs(tile.getCoordinates()[0]-from.getCoordinates()[0]);			
			}
		}else{
			return Math.abs(tile.getCoordinates()[0]-from.getCoordinates()[0]);						
		}
	}
	
	private int yDistance(Tile tile, Tile from){
		return Math.abs(tile.getCoordinates()[1]-from.getCoordinates()[1]);
	}

	public void paint(Graphics g){
		g.setColor(team.color());
		g.fillRect(10, 10, 80, 80);
	}

}
