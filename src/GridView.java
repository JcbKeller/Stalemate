import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GridView extends JPanel {
	
	final GridBagConstraints c = constraints();
	final int numberOfRows = 4;
	final int numberOfColumns = 4;

	GameSystem gameSystem;

//	private Tile tile;
	private Piece piece;

	private Tile.Listener tileListener = new Tile.Listener(){
		@Override
		public void tileClicked(int[] coordinates, Piece containedPiece, boolean moveable, int tileType){
			System.out.println("Mouse click at ("+ coordinates[0]+","+ coordinates[1]+")");
			System.out.println("ContainedPiece = "+containedPiece);
			GridView grid = GridView.this;
			
			if(containedPiece != null){
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
				grid.gameSystem.setCurrentPiece(null);
				grid.unvalidateMoves();
//				grid.undrawPieces();
//				grid.drawPieces();
				grid.repaint();
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
	};
	
	private List<Tile> tiles = new ArrayList<>();
	
	public GridView(GameSystem newGameSystem, final int columns, final int rows){
		super(new GridBagLayout());
		System.out.println("Created Instance of GridView");
		this.setMinimumSize(new Dimension(10,10));
		this.setMaximumSize(new Dimension(10,10));
		setBackground(Color.GRAY);
		gameSystem = newGameSystem;
		
		for(int column = 0; column < columns; column ++){
			c.gridx = column;
			for(int row = 0; row < rows; row ++){
				c.gridy = row;
				Tile newTile = new Tile(new int[] {column,row}, tileListener);
				newTile.setAsWinnable(checkForWinnableTile(column,row));
				this.add(newTile,c);
				this.tiles.add(newTile);
				gameSystem.addTileToList(newTile);
			}
		}
		this.repaint();
		this.revalidate();
	}

	public GridBagConstraints constraints(){
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		return c;
	}

	
	public Tile getTile(int[] coordinates){
		for(Tile t : tiles){
			final int[] coords = t.getCoordinates();
			System.out.println("Passed coords: " + coordinates);
			if(coords[0] == coordinates[0] && coords[1] == coordinates[1]){
				return t;
			}
		}
		throw new RuntimeException("No tile for " + coordinates[0] + ", " + coordinates[1]);
	}

//	public void undrawPieces(){
//		for(Tile tile : tiles){
//			tile.setPiece(null);
//		}
//		this.repaint();
//	}

	public void unvalidateMoves(){
		for(Tile tile : tiles){
			tile.setMoveable(false);
		}
		this.repaint();
	}

	public void showValidMoves(Piece pieceValue){
		gameSystem.setCurrentPiece(pieceValue);
		piece = pieceValue;
		for(Tile tile : tiles){
			if(piece.checkIfValidMove(tile) == true){
				tile.setMoveable(true);
			}else{
				tile.setMoveable(false);
			}
		}
		this.repaint();
	}

	public int checkForWinnableTile(int x, int y){
		if (y == 0){
			if(x == 1|| x == 2|| x == 3){
				return 2;
			}else{
				return 0;
			}
		}else if(y == gameSystem.getTotalRows()-1){
			if(x == 1|| x == 2|| x == 3){
				return 1;
			}else{
				return 0;
			}
		}else{
			return 0;
		}
	}

	public boolean checkForIdenticalCoordinates(Tile tile, Piece piece){
		if(tile.getCoordinates()[0] == piece.getPieceCoordinates()[0] && tile.getCoordinates()[1] == piece.getPieceCoordinates()[1]){
			return true;
		}else{
			return false;
		}
	}

}
