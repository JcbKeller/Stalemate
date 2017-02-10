import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GridView extends JPanel { //This Class handles the layout of tiles

	private List<Tile> tileList = new ArrayList<>(); //List of all tiles drawn in grid
	private int pieceX = 0;
	private int pieceY = 0;
	
	public GridView(){ //Constructor for grid
		super(new GridBagLayout());
		final GridBagConstraints c = constraints(); //Constraints for grid
		this.setMinimumSize(new Dimension(10,10));
		this.setMaximumSize(new Dimension(10,10));
		setBackground(Color.PINK);
		drawTiles(c, 4, 4);
		this.revalidate();
	}

	public void tileClicked(Tile clicked,int gridX,int gridY){ //How Grid Reacts when told a tile got clicked
		for(Tile thisTile: tileList){
			if(thisTile == clicked){
				if(gridX == pieceX && gridY == pieceY){
					thisTile.displayPiece(new GamePiece(2));//Add a new piece at the clicked square										
				}else{
					thisTile.displayPiece(new GamePiece(1));//Add a new piece at the clicked square					
				}
			}else{
				thisTile.erasePiece();
			}
		}
		pieceX = gridX;
		pieceY = gridY;
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
	
	private void drawTiles(GridBagConstraints c, int tileRows, int tileColumns){
		for(int row = 0; row < tileRows; row++){ //Iterate and create grid tiles
			c.gridy = row;
			for(int column = 0; column < tileColumns; column++){
				c.gridx = column;
				Tile newTile = new Tile(this,column,row); //Make tiles
				this.add(newTile,c);//Add Tiles
				tileList.add(newTile);//Update List of tiles
			}
		}
	}


}
