
public class GameSystem { //Move all possible game logic to here

	public static int totalRows = 4;
	public static int totalColumns = 4;
	
	public static int[] squareCoordinates = {1,0};
	public static Tile squareTile;
	
	public static int[] circleCoordinates = {2,0};
	public static Tile circleTile;

	public static int selectedPiece = 0;

	public static void checkTile(Tile thisTile, int[] tileCoordinates){
		if (tileCoordinates[0] == squareCoordinates[0] && tileCoordinates[1] == squareCoordinates[1]){ // If the Square is on the Tile, Select it
			selectedPiece = 1;
			System.out.println("Selected Green/Square");
		}else if(tileCoordinates[0] == circleCoordinates[0] && tileCoordinates[1] == circleCoordinates[1]){ // If the Circle is on the Tile, Select it
			selectedPiece = 2;
			System.out.println("Selected Blue/Circle");			
		}else if(selectedPiece == 1){ // If a square has been selected, Move the square to the empty tile
			if (squareTile!=null){
				squareTile.erasePiece();
			}
			squareTile = thisTile;
			squareTile.displayPiece(new GamePiece(selectedPiece));
			squareCoordinates[0] = tileCoordinates[0];
			squareCoordinates[1] = tileCoordinates[1];
			selectedPiece = 0;
		}else if(selectedPiece == 2){ // If a circle has been selected, Move the circle to the empty tile
			if (circleTile!=null){
				circleTile.erasePiece();
			}
			circleTile = thisTile;
			circleTile.displayPiece(new GamePiece(selectedPiece));
			circleCoordinates[0] = tileCoordinates[0];
			circleCoordinates[1] = tileCoordinates[1];
			selectedPiece = 0;			
		}
		
	}
}
