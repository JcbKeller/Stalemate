import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GridView extends JPanel { //This Class handles the layout of tiles
	final GridBagConstraints c = constraints();

	public GridView(){
		super(new GridBagLayout());
		this.setMinimumSize(new Dimension(10,10));
		this.setMaximumSize(new Dimension(10,10));
		setBackground(Color.PINK);
		drawTiles();
		GameSystem.grid = this;
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
	
	public void drawTiles(){
		System.out.println("Running Grid Draw Function");
		for(int row = 0; row < GameSystem.totalRows; row++){ //Iterate and create grid tiles
			c.gridy = row;
			for(int column = 0; column < GameSystem.totalColumns; column++){
				c.gridx = column;
				Tile newTile = new Tile(this,new int[] {column,row}); //Make tiles
				GameSystem.tileList.add(newTile);
				this.add(newTile,c);//Add Tiles
				if (column == Square.coordinates[0] && row == Square.coordinates[1]){
					newTile.displayPiece(new GamePiece(1));
					Square.currentTile = newTile; //Create the starting Square piece
				}else if(column == Circle.coordinates[0] && row == Circle.coordinates[1]){
					newTile.displayPiece(new GamePiece(2));					
					Circle.currentTile = newTile; //Create the starting Circle piece
				}
			}
		}
	}
	
	public void showValidMoves(){
		this.removeAll();
		this.revalidate();
		System.out.println("Drawing Grid With Moves");
		for(int row = 0; row < GameSystem.totalRows; row++){ //Iterate and create grid tiles
			c.gridy = row;
			for(int column = 0; column < GameSystem.totalColumns; column++){
				c.gridx = column;
				Tile newTile = new Tile(this,new int[] {column,row}); //Make tiles
				GameSystem.tileList.add(newTile);
				this.add(newTile,c);//Add Tiles
				if (column == Square.coordinates[0] && row == Square.coordinates[1]){
					newTile.displayPiece(new GamePiece(1));
					Square.currentTile = newTile; //Create the Square piece
				}else if(column == Circle.coordinates[0] && row == Circle.coordinates[1]){
					newTile.displayPiece(new GamePiece(2));					
					Circle.currentTile = newTile; //Create the Circle piece
				}else if(GameSystem.selectedPiece == 1 && Square.checkMoveValidity(new int[]{column,row}) == true){
					newTile.setAsMovable();
				}else if(GameSystem.selectedPiece == 2 && Circle.checkMoveValidity(new int[]{column,row}) == true){
					newTile.setAsMovable();
				}else{
					newTile.setAsImmovable();					
				}
			}
		}
		this.revalidate();
	}
}
