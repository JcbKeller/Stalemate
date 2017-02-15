import java.util.ArrayList;
import java.util.List;

public class GameSystem {

	public static final int totalRows = 6;
	public static final int totalColumns = 5;
	public static final List<Piece> pieceList = new ArrayList<>();
	public static final List<Tile> tileList = new ArrayList<>();
	public static GridView grid;
	public static final int windowX = 800;
	public static final int windowY = 800;

	private static int turn = 1;
	
	public static int currentPiece = -1; //Make this variable only accessible through method calls
	public static void changeTurns(){
		if(turn == 1){
			turn = 2;
		}else{
			turn = 1;
		}
		System.out.println("New Turn, team " + turn);
	}
	public static int getCurrentTeam(){
		return turn;
	}
	
	public static void addPieceToGame(Piece newPiece, int[] startingCoordinates, int pieceTeam){
		newPiece.setPieceCoordinates(startingCoordinates);
		newPiece.setTeam(pieceTeam);
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
