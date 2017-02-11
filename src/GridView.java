import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GridView extends JPanel { //This Class handles the layout of tiles
//	private List<Tile> tileList = new ArrayList<>(); //List of all tiles drawn in grid

	public GridView(){
		super(new GridBagLayout());
		final GridBagConstraints c = constraints();
		this.setMinimumSize(new Dimension(10,10));
		this.setMaximumSize(new Dimension(10,10));
		setBackground(Color.PINK);
		drawTiles(c, GameSystem.totalRows, GameSystem.totalColumns);
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
	
	private void drawTiles(GridBagConstraints c, int tileRows, int tileColumns){
		for(int row = 0; row < tileRows; row++){ //Iterate and create grid tiles
			c.gridy = row;
			for(int column = 0; column < tileColumns; column++){
				c.gridx = column;
				Tile newTile = new Tile(this,column,row); //Make tiles
				this.add(newTile,c);//Add Tiles
				if (column == GameSystem.squareX && row == GameSystem.squareY){
					newTile.displayPiece(new GamePiece(1));
					GameSystem.squareTile = newTile;
				}else if(column == GameSystem.circleX && row == GameSystem.circleY){
					newTile.displayPiece(new GamePiece(2));					
					GameSystem.circleTile = newTile;
				}
			}
		}
	}


}
