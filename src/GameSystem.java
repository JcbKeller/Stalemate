import java.util.ArrayList;
import java.util.List;

public class GameSystem {
	//final List<Piece> pieceList = new ArrayList<>();

	public GameSystem(){
		System.out.println("Created Instance of GameSystem");
		//pieceList.add(new Square());
//		addPieceToGame(new Square(), new int[] {1,0},1);
//		addPieceToGame(new Circle(), new int[] {2,0},1);
//		addPieceToGame(new Triangle(), new int[] {3,0},1);
//		addPieceToGame(new Square(), new int[] {1,5},2);
//		addPieceToGame(new Circle(), new int[] {2,5},2);
//		addPieceToGame(new Triangle(), new int[] {3,5},2);
	}
	
	public static final List<Piece> pieceList = new ArrayList<>();
	public static final List<Tile> tileList = new ArrayList<>();
	
	private static GridView grid;
	
	private final int totalRows = 6;
	private static final int totalColumns = 5;
	private static final int[] startingWindowSize = new int[] {800,800};

	private static int currentTeam = 1;
	private static int currentPiece = -1;

	public static void changeTurns(){
		if(currentTeam == 1){
			currentTeam = 2;
		}else{
			currentTeam = 1;
		}
		System.out.println("New Turn, team " + currentTeam);
	}

	public static int getCurrentTeam(){
		return currentTeam;
	}

	public static void movePiece(int[] coordinates){
		changePieceCoordinates(coordinates);
		changeTurns();
		grid.undrawPieces();
		grid.drawPieces();
	}

	public void addPieceToGame(Piece newPiece, int[] startingCoordinates, int pieceTeam){
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

	public static boolean checkForCurrentTeam(int pieceValue) {
		if(pieceList.get(pieceValue).team == getCurrentTeam()){
			return true;			
		}else{
			return false;
		}
	}

	public static void respondToPieceClick(int pieceValue){
		currentPiece = pieceValue;
		grid.showValidMoves(pieceValue);
		System.out.println("Showing Valid Moves");
	}

	public static void capturePiece(int pieceValue, int[] tileCoordinates){
		pieceList.get(pieceValue).setPieceCoordinates(new int[] {100,100});
		movePiece(tileCoordinates);								
	}

	public static boolean checkForWin(int teamNumber){
		if(pieceList.get(GameSystem.currentPiece).getTeam() == teamNumber){
			return true;
		}else{
			return false;
		}
	}
	
	public static void setCurrentPiece(int pieceValue){
		currentPiece = pieceValue;
	}
	
	public int getTotalRows(){
		return totalRows;
	}
	
	public static int getTotalColumns(){
		return totalColumns;
	}
	
	public static int[] getStartingWindowSize(){
		return startingWindowSize;
	}
	
	public static GridView getGrid(){
		return grid;
	}
	
	public void setGrid(GridView newGrid){
		grid = newGrid;
	}
}
