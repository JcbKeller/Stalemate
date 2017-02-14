import java.awt.Graphics;
import javax.swing.JLabel;

public class Stalemate {
	
	public static void main(String[] args){
		
		new MainFrame(new GridView(),new Toolbar());
		
		GameSystem.addPieceToGame(new Square(), new int[] {1,0});
		GameSystem.addPieceToGame(new Circle(), new int[] {2,0});
		GameSystem.addPieceToGame(new Circle(), new int[] {3,0});
	}
	
}
