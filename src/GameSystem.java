
public class GameSystem { //Move all possible game logic to here

	public static int squareX = 0;
	public static int squareY = 0;
	public static Tile squareTile;
	public static int circleX = 0;
	public static int circleY = 1;
	public static Tile circleTile;
	public static int selectedPiece = 0;

	public static void checkTile(Tile thisTile, int tileX, int tileY){
		if (tileX == squareX && tileY == squareY){
//			thisTile.displayPiece(new GamePiece(0));
			selectedPiece = 1;
			System.out.println("Selected Green");
		}else if(tileX == circleX && tileY == circleY){
//			thisTile.displayPiece(new GamePiece(0));
			selectedPiece = 2;
			System.out.println("Selected Blue");			
		}else if(selectedPiece == 1){
			if (squareTile!=null){
				squareTile.erasePiece();
			}
			squareTile = thisTile;
			squareTile.displayPiece(new GamePiece(selectedPiece));
			squareX = tileX;
			squareY = tileY;
			selectedPiece = 0;
		}else if(selectedPiece == 2){
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
