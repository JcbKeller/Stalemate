
public class Circle {
	public static int[] coordinates = {2,0};
	public static Tile currentTile;
	public static int pieceValue = 2;
	
	public static boolean checkMoveValidity(int[] tileLocation){
		if(tileLocation[1] == coordinates[1]){
			System.out.println("Circle can Move Here");
			return true;
		}else{
			return false;			
		}
	}
}
