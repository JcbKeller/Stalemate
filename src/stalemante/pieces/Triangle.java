package stalemante.pieces;
import java.awt.Graphics;

import gridsystem.Piece;
import gridsystem.Tile;
import stalemante.Team;

public class Triangle extends Piece {

	private int[] xPoints = {5,50,95};
	private int[] yPoints = {90,10,90};
	private int nPoints = 3;

	public Triangle(Team team) {
		super(team);
	}

	public boolean checkIfValidMove(Tile from, Tile to, int totalX) {
		if(xDistance(from,to) == 1 && + yDistance(from,to) == 1){
			return true;
		}else{
			if(wrapXDistance(from,to, totalX) == 1 && + yDistance(from,to) == 1){
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
	
	public void paint(Graphics g) {
		g.setColor(team.color());
		g.fillPolygon(xPoints,yPoints,nPoints);
	}

}
