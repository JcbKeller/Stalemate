package stalemante.pieces;
import java.awt.Graphics;

import gridsystem.Piece;
import gridsystem.Tile;
import stalemante.Team;

public class Circle extends Piece {

	public Circle(Team team) {
		super(team);
	}

	public boolean checkIfValidMove(Tile from, Tile to, int totalX){
//		if(xDistance(tile,from) == 2 && yDistance(tile,from) == 0 || xDistance(tile,from) == 0 && yDistance(tile,from) == 2){
//			return true;			
//		}else{
//			if(wrapXDistance(tile,from, totalX) == 2 && yDistance(tile,from) == 0 || wrapXDistance(tile,from, totalX) == 0 && yDistance(tile,from) == 2){
//				return true;
//			}else{
//				return false;				
//			}
//		}
		
		
		
		int yDistance = yDistance(from, to);
		int xDistance = xDistance(from, to);
		
		if(yDistance > 0 && xDistance > 0){
			return false;
		}else if(xDistance == 2 || yDistance ==2 ){
			return true;
		}else{
			return false;
		}
	}
	
	private int xDistance(Tile tile, Tile from){
		return Math.abs(tile.getCoordinates()[0]-from.getCoordinates()[0]);
	}
//	
//	private int wrapXDistance(Tile tile, Tile from, int totalX){
//		if(from.getCoordinates()[0] > totalX-3){
//			if(tile.getCoordinates()[0] < 2){
//				return Math.abs(tile.getCoordinates()[0]+totalX-from.getCoordinates()[0]);			
//			}else{
//				return Math.abs(tile.getCoordinates()[0]-from.getCoordinates()[0]);			
//			}
//		}else if (from.getCoordinates()[0] < 2){
//			if(tile.getCoordinates()[0] > totalX-3){
//				return Math.abs(tile.getCoordinates()[0]-totalX-from.getCoordinates()[0]);			
//			}else{
//				return Math.abs(tile.getCoordinates()[0]-from.getCoordinates()[0]);			
//			}
//		}else{
//			return Math.abs(tile.getCoordinates()[0]-from.getCoordinates()[0]);						
//		}
//	}
//	
	private int yDistance(Tile tile, Tile from){
		return Math.abs(tile.getCoordinates()[1]-from.getCoordinates()[1]);
	}

	public void paint(Graphics g){
		g.setColor(team.color());	
		g.fillOval(10, 10, 85, 85);
	}

}
