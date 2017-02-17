import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GridView extends JPanel {
	final GridBagConstraints c = constraints();
	final Tile.Listener tileListener;
	private List<Tile> tiles = new ArrayList<>();
	private final int rows;
	
	public GridView(Tile.Listener listener, final int columns, final int rows){
		super(new GridBagLayout());
		this.tileListener = listener;
		this.rows = rows;
		System.out.println("Created Instance of GridView");
		this.setMinimumSize(new Dimension(10,10));
		this.setMaximumSize(new Dimension(10,10));
		setBackground(Color.GRAY);
		
		for(int column = 0; column < columns; column ++){
			c.gridx = column;
			for(int row = 0; row < rows; row ++){
				c.gridy = row;
				Tile newTile = new Tile(new int[] {column,row}, tileListener);
				newTile.setAsWinnable(checkForWinnableTile(column,row));
				this.add(newTile,c);
				this.tiles.add(newTile);
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
			if(coords[0] == coordinates[0] && coords[1] == coordinates[1]){
				return t;
			}
		}
		throw new RuntimeException("No tile for " + coordinates[0] + ", " + coordinates[1]);
	}

	public void unvalidateMoves(){
		for(Tile tile : tiles){
			tile.setMoveable(false);
		}
		this.repaint();
	}

	public void showValidMoves(Piece piece){
		for(Tile tile : tiles){
			if(piece.checkIfValidMove(tile) == true){
				tile.setMoveable(true);
			}else{
				tile.setMoveable(false);
			}
		}
		this.repaint();
	}

	/**
	 * TODO: Should move to GameSystem
	 */
	public int checkForWinnableTile(int x, int y){
		if (y == 0){
			if(x == 1|| x == 2|| x == 3){
				return 2;
			}else{
				return 0;
			}
		}else if(y == rows-1){
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
