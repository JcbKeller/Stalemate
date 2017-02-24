package stalemante;

import gridsystem.GridView;

public class Stalemate {

	private static MainFrame mainFrame;

	public static void main(String[] args){

		GridView grid = new GridView(5, 6);
		mainFrame = new MainFrame(new GameSystem(grid), grid);
	}
	
	public static void resetGame(){
		GridView grid = new GridView(5, 6);
		if(mainFrame != null){
			mainFrame.restartGame(new GameSystem(grid), grid);		
		}
	}

}
