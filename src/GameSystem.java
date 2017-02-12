import java.util.ArrayList;
import java.util.List;

public class GameSystem {

	public static int totalRows = 4;
	public static int totalColumns = 4;
	public static GridView grid;
	public static int selectedPiece = 0;
	public static List<Tile> tileList = new ArrayList<>(); //List of all tiles drawn in grid

	public static void checkTile(Tile thisTile, int[] tileCoordinates){
		if (tileCoordinates[0] == Square.coordinates[0] && tileCoordinates[1] == Square.coordinates[1]){ //If Square is clicked
			selectedPiece = Square.pieceValue; // If the Square is on the Tile, Select it
			System.out.println("Selected Green/Square");
			grid.showValidMoves();
			
		}else if(tileCoordinates[0] == Circle.coordinates[0] && tileCoordinates[1] == Circle.coordinates[1]){ //If Circle is clicked
			selectedPiece = Circle.pieceValue; // If the Circle is on the Tile, Select it
			System.out.println("Selected Blue/Circle");
			grid.showValidMoves();
			
		}else if(selectedPiece == Square.pieceValue && Square.checkMoveValidity(tileCoordinates) == true){ //If Square can move here
			if(Square.currentTile!=null){
				Square.currentTile.erasePiece();
			}
			Square.currentTile = thisTile;
			Square.currentTile.displayPiece(new GamePiece(Square.pieceValue));
			Square.coordinates = tileCoordinates;
			selectedPiece = 0;
			grid.showValidMoves();
			
		}else if(selectedPiece == Circle.pieceValue && Circle.checkMoveValidity(tileCoordinates) == true){ //If Circle can move here
			if(Circle.currentTile!=null){
				Circle.currentTile.erasePiece();
			}
			Circle.currentTile = thisTile;
			Circle.currentTile.displayPiece(new GamePiece(Circle.pieceValue));
			Circle.coordinates = tileCoordinates;
			selectedPiece = 0;
			grid.showValidMoves();			
		}else{
			selectedPiece = 0;
			grid.showValidMoves();
		}
	}
	
}
