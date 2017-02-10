import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GridView extends JPanel {
	
	private List<Tile> myTiles = new ArrayList<>();
	
	public GridView(){
		super(new GridBagLayout());
		
		System.out.println("New GridView");
		
		setBackground(Color.PINK);
		this.setMinimumSize(new Dimension(10,10));
		this.setMaximumSize(new Dimension(10,10));
		
		final GridBagConstraints c = constraints();
		for(int row = 0; row < 4; row++){
			c.gridy = row;
			for(int column = 0; column < 4; column++){
				c.gridx = column;
				Tile tile = new Tile(this,column,row);
				this.add(tile,c);
				myTiles.add(tile);
			}
		}
		this.revalidate();
	}
	
	public void tileClicked(Tile tile){
		for(Tile next: myTiles){
			if(next == tile){
				next.setPiece(new GamePiece());
			}else{
				next.unsetPiece();
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
	
	
}
