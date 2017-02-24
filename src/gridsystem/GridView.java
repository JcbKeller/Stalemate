package gridsystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JPanel;

public class GridView extends JPanel {
	private final int rows;
	private final int columns;
	
	private Tile.Listener tileListener;
	private List<Tile> tiles = new ArrayList<>();
	
	public GridView(final int columns, final int rows){
		super(new GridBagLayout());
		this.rows = rows;
		this.columns = columns;
		
		System.out.println("Created Instance of GridView");
		this.setMinimumSize(new Dimension(10,10));
		this.setMaximumSize(new Dimension(10,10));
		setBackground(Color.GRAY);
		
		
		GridBagConstraints c = constraints();

		for(int column = 0; column < columns; column ++){
			for(int row = 0; row < rows; row ++){
				Tile tile = new Tile(new int[] {column,row});
// -------- Ask About Testing These --------
				int[] coords = tile.getCoordinates();
				c.gridx = coords[0];
				c.gridy = coords[1];
// -----------------------------------------				
				this.add(tile,c);
				this.tiles.add(tile);
			}
		}
		
		
		this.repaint();
		this.revalidate();
	}
	
	
	public void setListener(Tile.Listener listener){
		this.tileListener = listener;
		for(Tile t: tiles){
			t.setListener(listener);
		}
	}
	
	public int rows() {
		return rows;
	}
	
	public int columns() {
		return columns;
	}
	
	public List<Tile> tiles(){
		return this.tiles;
	}
	
	private GridBagConstraints constraints(){
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0;
		c.weighty = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.anchor = GridBagConstraints.CENTER;
		return c;
	}
	
	public Tile getTile(Piece p){
		
		return tiles.stream()
				.filter( t -> t.getPiece() == p)
				.findFirst()
				.get();
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
	
	public void setAllTilesAsUnmoveable(){
		for(Tile tile : tiles){
			tile.setMoveable(false);
		}
		this.repaint();
	}

	public void setMoveableTiles(Piece piece, int totalX){
		final Tile currentTile = getTile(piece);
		for(Tile tile : tiles){
			
			if(piece.checkIfValidMove(currentTile, tile, totalX) == true){
				tile.setMoveable(true);
			}else{
				tile.setMoveable(false);
			}
		}
		this.repaint();
	}
	

}
