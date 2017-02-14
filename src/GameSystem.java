import java.util.ArrayList;
import java.util.List;

public class GameSystem {

	public static final int totalRows = 4;
	public static final int totalColumns = 4;
	public static final List<Piece> pieceList = new ArrayList<>();
	public static final List<Tile> tileList = new ArrayList<>();
	public static GridView grid;
	
	public static int currentPiece = -1;
	
	public static void addPieceToGame(Piece newPiece, int[] startingCoordinates){
		newPiece.setPieceCoordinates(startingCoordinates);
		pieceList.add(newPiece);
		grid.undrawPieces();
		grid.unvalidateMoves();
		grid.drawPieces();
	}
	public static void changePieceCoordinates(int[] newCoordinates){
		pieceList.get(currentPiece).setPieceCoordinates(newCoordinates);
		currentPiece = -1;
		grid.undrawPieces();
		grid.unvalidateMoves();
		grid.drawPieces();
		
	}

}
