
public class Stalemate {

	static MainFrame mainFrame;
	static GameSystem gameSystem;
	
	public static void main(String[] args){		
		gameSystem = new GameSystem();
		mainFrame = new MainFrame(new GameSystem());
	}
	
	public static void resetGame(){

		mainFrame.restartGame(new GameSystem());
//		mainFrame = new MainFrame(new GameSystem());
//		gameSystem = new GameSystem();
//		mainFrame.scrollpane.removeAll();
//		mainFrame.scrollpane.add(new GridView(gameSystem));
		
	}

}
