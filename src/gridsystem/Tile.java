package gridsystem;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import stalemante.Team;


public class Tile extends JComponent {
	public interface Listener {
		void tileClicked(Tile tile);
	}
	
	private final int[] coordinates;
	private Piece containedPiece;
	
	private Team winningSpotForTeam = null;
	
	private boolean moveable = false;
	private Listener tileClickedListener;
	
	public Tile(int[] tileCoordinates){	
		this.coordinates = tileCoordinates;
		Tile tile = this;
		this.setPreferredSize(new Dimension(103,103));
		this.setMaximumSize(new Dimension(105,105));
		this.setMinimumSize(new Dimension(100,100));

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tileClickedListener.tileClicked(tile);

			}
		});
	}

	public void setListener(Listener listener){
		this.tileClickedListener = listener;
	}
	public void setWinningSpotForTeam(Team type){
		winningSpotForTeam = type;
	}
	
	public Team winningSpotForTeam(){
		return winningSpotForTeam;
	}
	
	public void setMoveable(boolean movability){
		moveable = movability;
	}
	
	public boolean checkIfMoveable(){
		return moveable;
	}
	
	public void setPiece(Piece pieceValue){
		containedPiece = pieceValue;
	}

	public Piece getPiece(){
		return containedPiece;
	}

	public int[] getCoordinates(){
		return this.coordinates;
	}
	
	public void setAsWinnableForTeam(Team team){
		winningSpotForTeam = team;
	}

	public void paint(Graphics g){
		if(moveable == false){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(2,2,100,100);			
		}else{
			g.setColor(Color.WHITE);
			g.fillRect(2,2,100,100);
		}

		Color color;
		if(winningSpotForTeam==null){
			color = Color.BLACK;
		}else{
			color = winningSpotForTeam.color();
		}
		
		g.setColor(color);
		g.drawRect(2,2,100,100);

		if(containedPiece != null){
			containedPiece.paint(g);
		}
	}

	
	@Override
	public String toString() {
		return "Tile " + coordinates[0] + ", " + coordinates[1];
	}
	

}


