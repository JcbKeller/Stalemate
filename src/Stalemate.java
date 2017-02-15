import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.swing.JLabel;

public class Stalemate {
	final static List<Piece> pieceList = Collections.unmodifiableList(Arrays.asList(new Square(), new Circle()));
	
	public static void main(String[] args){
		
		new MainFrame(new GridView(),new Toolbar());
		
		GameSystem.addPieceToGame(new Square(), new int[] {1,0},1);
		GameSystem.addPieceToGame(new Circle(), new int[] {2,0},1);
		GameSystem.addPieceToGame(new Triangle(), new int[] {3,0},1);

		GameSystem.addPieceToGame(new Square(), new int[] {1,5},2);
		GameSystem.addPieceToGame(new Circle(), new int[] {2,5},2);
		GameSystem.addPieceToGame(new Triangle(), new int[] {3,5},2);
		
		
	}
	
}
