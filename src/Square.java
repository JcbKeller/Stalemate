
public class Square {
	public static int[] coordinates = {1,0};
	public static Tile currentTile;
	public static int pieceValue = 1;
	
	public static boolean checkMoveValidity(int[] tileLocation){
		if(tileLocation[0] == coordinates[0]){
			System.out.println("Square can Move Here");
			return true;
		}else{
			return false;			
		}
	}
	
}
