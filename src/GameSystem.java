import java.util.ArrayList;
import java.util.List;

public class GameSystem {
	//final List<Piece> pieceList = new ArrayList<>();

	public GameSystem(){
		System.out.println("Created Instance of GameSystem");
		}
	
	private final List<Piece> pieceList = new ArrayList<>();
	private final List<Tile> tileList = new ArrayList<>();
	private final int totalRows = 6;
	private final int totalColumns = 5;
	private final int[] startingWindowSize = new int[] {800,800};

	private GridView grid;
	private int currentTeam = 1;
	private int currentPiece = -1;

	public void changeTurns(){
		if(currentTeam == 1){
			currentTeam = 2;
		}else{
			currentTeam = 1;
		}
		System.out.println("New Turn, team " + currentTeam);
	}

	public int getCurrentTeam(){
		return currentTeam;
	}

	public void movePiece(int[] coordinates){
		changePieceCoordinates(coordinates);
		changeTurns();
		grid.undrawPieces();
		grid.drawPieces();
	}

	public void addPieceToGame(Piece newPiece, int[] startingCoordinates, int pieceTeam){
		newPiece.setPieceCoordinates(startingCoordinates);
		newPiece.setTeam(pieceTeam);
		newPiece.setGameSystem(this);
		pieceList.add(newPiece);
		grid.undrawPieces();
		grid.unvalidateMoves();
		grid.drawPieces();
	}

	public void changePieceCoordinates(int[] newCoordinates){
		pieceList.get(currentPiece).setPieceCoordinates(newCoordinates);
		currentPiece = -1;
		grid.undrawPieces();
		grid.unvalidateMoves();
		grid.drawPieces();
	}
	
	public Piece getPiece(int pieceValue){
		return pieceList.get(pieceValue);
	}
	
	public int getNumberOfPieces(){
		return pieceList.size();
	}
	
	public boolean checkForCurrentTeam(int pieceValue) {
		if(pieceList.get(pieceValue).team == getCurrentTeam()){
			return true;			
		}else{
			return false;
		}
	}

	public void respondToPieceClick(int pieceValue){
		currentPiece = pieceValue;
		grid.showValidMoves(pieceValue);
		System.out.println("Showing Valid Moves");
	}

	public void capturePiece(int pieceValue, int[] tileCoordinates){
		pieceList.get(pieceValue).setPieceCoordinates(new int[] {100,100});
		movePiece(tileCoordinates);								
	}

	public boolean checkForWin(int teamNumber){
		if(pieceList.get(currentPiece).getTeam() == teamNumber){
			return true;
		}else{
			return false;
		}
	}
	
	public void setCurrentPiece(int pieceValue){
		currentPiece = pieceValue;
	}
	
	public int getTotalRows(){
		return totalRows;
	}
	
	public int getTotalColumns(){
		return totalColumns;
	}
	
	public int[] getStartingWindowSize(){
		return startingWindowSize;
	}
	
	public GridView getGrid(){
		return grid;
	}
	
	public void setGrid(GridView newGrid){
		grid = newGrid;
	}
	public Tile getTile(int tileValue){
		return tileList.get(tileValue);
	}
	public int getNumberOfTiles(){
		return tileList.size();
	}
	public void addTileToList(Tile tile){
		tileList.add(tile);
	}
}
