import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Tile extends JComponent {//This Class handles individual tile responses
	
	private int[] coordinates;
	private int containedPiece = -1;
	private boolean winnable = false;
	private boolean moveable = false;
	
	public Tile(GridView grid, int[] tileCoordinates){	
		this.coordinates = tileCoordinates;
		this.setPreferredSize(new Dimension(103,103));
		this.setMaximumSize(new Dimension(105,105));
		this.setMinimumSize(new Dimension(100,100));

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse click at ("+ coordinates[0]+","+ coordinates[1]+")");
				System.out.println("ContainedPiece = "+containedPiece);
				if(containedPiece != -1){ // Clicked on Piece
					if(GameSystem.pieceList.get(containedPiece).team == GameSystem.getCurrentTeam()){
						GameSystem.currentPiece = containedPiece;
						System.out.println("Showing Valid Moves");
						GameSystem.grid.showValidMoves(containedPiece);
					}else{
						if(moveable == true){
							System.out.println("Attempt To Capture");						
//							GameSystem.pieceList.get(containedPiece).team = 2;
							GameSystem.pieceList.get(containedPiece).setPieceCoordinates(new int[] {100,100});
							GameSystem.changePieceCoordinates(coordinates);
							GameSystem.changeTurns();
							GameSystem.grid.undrawPieces();
							GameSystem.grid.drawPieces();
						}
					}
				}else if(moveable == false){ // Selected Piece Cannot Move Here
					System.out.println("No Piece or Valid Move");
					GameSystem.currentPiece = -1;
					GameSystem.grid.unvalidateMoves();
				}else{ // Selected Piece CAN move here
					GameSystem.changePieceCoordinates(coordinates);
					System.out.println("Movable Square");
					GameSystem.changeTurns();
				}

			}
		});
	}
	public void setMoveable(boolean movability){
		moveable = movability;
	}
	public void setPieceValue(int pieceValue){
		containedPiece = pieceValue;
	}
	public int[] getCoordinates(){
		return this.coordinates;
	}
	public void setAsWinnable(){
		winnable = true;
	}
	public void paint(Graphics g){
		if(moveable == false){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(2,2,100,100);			
		}else{
			g.setColor(Color.WHITE);
			g.fillRect(2,2,100,100);
		}
		if(winnable == true){
			g.setColor(Color.GREEN);
			g.drawRect(2,2,100,100);			
		}else{
			g.setColor(Color.BLACK);
			g.drawRect(2,2,100,100);
		}

		if(containedPiece != -1){
			GameSystem.pieceList.get(containedPiece).paint(g);;
		}
	}


}


