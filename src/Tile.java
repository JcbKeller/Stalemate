import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Tile extends JComponent {

	private int[] coordinates;
	private int containedPiece = -1;
	private int tileType = 0;
	private GridView grid;
	private boolean moveable = false;

	public Tile(GridView newGrid, int[] tileCoordinates){	
		grid = newGrid;
		this.coordinates = tileCoordinates;
		this.setPreferredSize(new Dimension(103,103));
		this.setMaximumSize(new Dimension(105,105));
		this.setMinimumSize(new Dimension(100,100));

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse click at ("+ coordinates[0]+","+ coordinates[1]+")");
				System.out.println("ContainedPiece = "+containedPiece);
				
				if(containedPiece != -1){
					if(grid.gameSystem.checkForCurrentTeam(containedPiece) == true){
						grid.gameSystem.respondToPieceClick(containedPiece);
					}else{
						if(moveable == true){
							System.out.println("Attempt To Capture");
							if(tileType == 0){
								grid.gameSystem.capturePiece(containedPiece,coordinates);
							}else{
								System.out.println("Cannot Capture Base Pieces");								
							}
						}
					}
				}else if(moveable == false){
					System.out.println("No Piece or Valid Move");
					grid.gameSystem.setCurrentPiece(-1);
					grid.unvalidateMoves();
					grid.undrawPieces();
					grid.drawPieces();
				}else{
					System.out.println("Movable Square");
					if(grid.gameSystem.checkForWin(tileType)){ // BUG: Occasionally throws -1 exception
						grid.gameSystem.movePiece(coordinates);
						System.out.println("Team " + tileType + " Wins!!");
						grid.gameSystem.showTeamWin(tileType);
					}else{
						grid.gameSystem.movePiece(coordinates);

					}
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

	public void setAsWinnable(int team){
		tileType = team;
	}

	public void paint(Graphics g){
		if(moveable == false){
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(2,2,100,100);			
		}else{
			g.setColor(Color.WHITE);
			g.fillRect(2,2,100,100);
		}

		if(tileType == 1){
			g.setColor(Color.RED);
			g.drawRect(2,2,100,100);			
		}else if(tileType == 2){
			g.setColor(Color.BLUE);
			g.drawRect(2,2,100,100);			
		}else{
			g.setColor(Color.BLACK);
			g.drawRect(2,2,100,100);
		}

		if(containedPiece != -1){
			grid.gameSystem.getPiece(containedPiece).paint(g);
		}
	}


}


