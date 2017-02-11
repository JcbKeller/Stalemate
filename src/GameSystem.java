
public class GameSystem { //Move all possible game logic to here

	public static int totalRows = 4;
	public static int totalColumns = 5;
	
	public static int squareX = 1;
	public static int squareY = 0;
	public static Tile squareTile;
	
	public static int circleX = 2;
	public static int circleY = 0;
	public static Tile circleTile;

	public static int selectedPiece = 0;

	public static void checkTile(Tile thisTile, int tileX, int tileY){
		if (tileX == squareX && tileY == squareY){ // If the Square is on the Tile, Select it
			selectedPiece = 1;
			System.out.println("Selected Green/Square");
		}else if(tileX == circleX && tileY == circleY){ // If the Circle is on the Tile, Select it
			selectedPiece = 2;
			System.out.println("Selected Blue/Circle");			
		}else if(selectedPiece == 1){ // If a square has been selected, Move the square to the empty tile
			if (squareTile!=null){
				squareTile.erasePiece();
			}
			squareTile = thisTile;
			squareTile.displayPiece(new GamePiece(selectedPiece));
			squareX = tileX;
			squareY = tileY;
			selectedPiece = 0;
		}else if(selectedPiece == 2){ // If a circle has been selected, Move the circle to the empty tile
			if (circleTile!=null){
				circleTile.erasePiece();
			}
			circleTile = thisTile;
			circleTile.displayPiece(new GamePiece(selectedPiece));
			circleX = tileX;
			circleY = tileY;
			selectedPiece = 0;			
		}
		
	}
}
