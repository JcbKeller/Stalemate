import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;

public class GridView extends JPanel {
	final GridBagConstraints c = constraints();
	final int numberOfRows = 4;
	final int numberOfColumns = 4;

	private Tile tile;
	private Piece piece;

	public GridView(){
		super(new GridBagLayout());
		this.setMinimumSize(new Dimension(10,10));
		this.setMaximumSize(new Dimension(10,10));
		setBackground(Color.GRAY);
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
				newTile.setAsWinnable(checkForWinnableTile(column,row));
				this.add(newTile,c);
				GameSystem.tileList.add(newTile);
			}
		}
		this.repaint();
	}

	public void drawPieces(){
		for(int tileValue = 0; tileValue< GameSystem.tileList.size(); tileValue++){
			tile = GameSystem.tileList.get(tileValue);
			for(int pieceValue = 0; pieceValue< GameSystem.pieceList.size(); pieceValue++){
				piece = GameSystem.pieceList.get(pieceValue);
				if(checkForSameCoordinates(tile, piece) == true ){
					tile.setPieceValue(pieceValue);
				}
			}
		}
		this.repaint();
	}

	public void undrawPieces(){
		for(int tileValue = 0; tileValue< GameSystem.tileList.size(); tileValue++){
			tile = GameSystem.tileList.get(tileValue);
			tile.setPieceValue(-1);
		}
		this.repaint();
	}

	public void unvalidateMoves(){
		for(int tileValue = 0; tileValue< GameSystem.tileList.size(); tileValue++){
			tile = GameSystem.tileList.get(tileValue);
			tile.setMoveable(false);
		}
		this.repaint();
	}

	public void showValidMoves(int pieceValue){
		GameSystem.currentPiece = pieceValue;
		piece = GameSystem.pieceList.get(pieceValue);
		for(int tileValue = 0; tileValue< GameSystem.tileList.size(); tileValue++){
			tile = GameSystem.tileList.get(tileValue);
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
		}else if(y == GameSystem.totalRows-1){
			if(x == 1|| x == 2|| x == 3){
				return 1;
			}else{
				return 0;
			}

		}else{
			return 0;
		}
	}

	public boolean checkForSameCoordinates(Tile tile, Piece piece){
		if(tile.getCoordinates()[0] == piece.getPieceCoordinates()[0] && tile.getCoordinates()[1] == piece.getPieceCoordinates()[1]){
			return true;
		}else{
			return false;
		}
	}

}
