
public class Stalemate {

	private static MainFrame mainFrame;

	public static void main(String[] args){
		new GameSystem();
		mainFrame = new MainFrame(new GameSystem());
	}
	
	public static void resetGame(){
		mainFrame.restartGame(new GameSystem());		
	}

}
