import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GridView extends JPanel { //This Class handles the layout of tiles
	final GridBagConstraints c = constraints();
	final int numberOfRows = 4;
	final int numberOfColumns = 4;
	
	private Tile tile;
	private Piece piece;
	
	public GridView(){
		super(new GridBagLayout());
		this.setMinimumSize(new Dimension(10,10));
		this.setMaximumSize(new Dimension(10,10));
		setBackground(Color.PINK);
		initializeGrid();
		this.revalidate();
		GameSystem.grid = this;
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
	public void initializeGrid(){
		for(int column = 0; column < GameSystem.totalColumns; column ++){
			c.gridx = column;
			for(int row = 0; row < GameSystem.totalRows; row ++){
				c.gridy = row;
				Tile newTile = new Tile(this,new int[] {column,row});
				this.add(newTile,c);
				GameSystem.tileList.add(newTile);
			}
		}
		this.repaint();
	}
	
	public void drawPieces(){ // Draws All Pieces on the Tiles with the same Coordinates
		for(int tileValue = 0; tileValue< GameSystem.tileList.size(); tileValue++){
			tile = GameSystem.tileList.get(tileValue);
			for(int pieceValue = 0; pieceValue< GameSystem.pieceList.size(); pieceValue++){
				piece = GameSystem.pieceList.get(pieceValue);
				if(tile.getCoordinates()[0] == piece.getPieceCoordinates()[0] && tile.getCoordinates()[1] == piece.getPieceCoordinates()[1] ){
					System.out.println("Detected Piece "+pieceValue+" at x = " + piece.getPieceCoordinates()[0] + " y = " + piece.getPieceCoordinates()[1]);
					tile.setPieceValue(pieceValue);
				}
			}
		}
		this.repaint();
	}
	
	public void undrawPieces(){ // Removes Pieces from all Tiles
		for(int tileValue = 0; tileValue< GameSystem.tileList.size(); tileValue++){
			tile = GameSystem.tileList.get(tileValue);
			tile.setPieceValue(-1);
		}
		this.repaint();
	}
	public void unvalidateMoves(){ // Sets All tiles to Immovable
		for(int tileValue = 0; tileValue< GameSystem.tileList.size(); tileValue++){
			tile = GameSystem.tileList.get(tileValue);
			tile.setMoveable(false);
		}
		this.repaint();
	}
	public void showValidMoves(int pieceValue){ // Sets all movable Tiles to Movable = true
		GameSystem.currentPiece = pieceValue;
		piece = GameSystem.pieceList.get(pieceValue);
		for(int tileValue = 0; tileValue< GameSystem.tileList.size(); tileValue++){
			tile = GameSystem.tileList.get(tileValue);
			if(piece.checkIfValidMove(tile) == true){ // CHECKIFVALIDMOVE controls which Tiles become white/moveable
				tile.setMoveable(true);
			}else{                        // Change IF statement to:   tile.setMoveable(piece.checkIfValidMove);
				tile.setMoveable(false);
			}
		}
		this.repaint();
	}
	
}
