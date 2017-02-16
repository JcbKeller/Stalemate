
public class Stalemate {

	static MainFrame mainframe;

	public static void main(String[] args){

		mainframe = new MainFrame(new GridView(),new Toolbar());

		GameSystem.addPieceToGame(new Square(), new int[] {1,0},1);
		GameSystem.addPieceToGame(new Circle(), new int[] {2,0},1);
		GameSystem.addPieceToGame(new Triangle(), new int[] {3,0},1);

		GameSystem.addPieceToGame(new Square(), new int[] {1,5},2);
		GameSystem.addPieceToGame(new Circle(), new int[] {2,5},2);
		GameSystem.addPieceToGame(new Triangle(), new int[] {3,5},2);


	}

	public static void resetGame(){
		new MainFrame(new GridView(),new Toolbar());
		GameSystem.addPieceToGame(new Square(), new int[] {1,0},1);
		GameSystem.addPieceToGame(new Circle(), new int[] {2,0},1);
		GameSystem.addPieceToGame(new Triangle(), new int[] {3,0},1);

		GameSystem.addPieceToGame(new Square(), new int[] {1,5},2);
		GameSystem.addPieceToGame(new Circle(), new int[] {2,5},2);
		GameSystem.addPieceToGame(new Triangle(), new int[] {3,5},2);

	}

}
