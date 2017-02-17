import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameSystem implements Tile.Listener {

	public GameSystem(){
		System.out.println("Created Instance of GameSystem");
	}
	
	private final int totalRows = 6;
	private final int totalColumns = 5;
	private final int[] startingWindowSize = new int[] {800,800};

	private GridView grid;
	private int currentTeam = 1;
	private Piece currentPiece = null;
	private Piece lastMovedPiece = null;

	public void changeTurns(){
		if(currentTeam == 1){
			currentTeam = 2;
		}else{
			currentTeam = 1;
		}
		System.out.println("New Turn, team " + currentTeam);
	}

	public int getCurrentTeam(){
		return currentTeam;
	}

	public void movePiece(int[] coordinates){

		Tile sourceTile = grid.getTile(currentPiece.coordinates);
		Tile destinationTile = grid.getTile(coordinates);
		
		sourceTile.setPiece(null);
		destinationTile.setPiece(currentPiece);
		
		changePieceCoordinates(coordinates);
		changeTurns();
		
	}

	public void addPieceToGame(Piece newPiece, int[] startingCoordinates){
		newPiece.setPieceCoordinates(startingCoordinates);
		
		
		newPiece.setGameSystem(this);
		grid.unvalidateMoves();
		grid.getTile(startingCoordinates).setPiece(newPiece);
	}

	public void changePieceCoordinates(int[] newCoordinates){
		currentPiece.setPieceCoordinates(newCoordinates);
		lastMovedPiece = currentPiece;
		currentPiece = null;
		grid.unvalidateMoves();
	}
	
	public boolean checkForCurrentTeam(Piece pieceValue) {
		if(pieceValue.team == getCurrentTeam()){
			return true;			
		}else{
			return false;
		}
	}

	public void respondToPieceClick(Piece pieceValue){
		currentPiece = pieceValue;
		grid.showValidMoves(pieceValue);
		System.out.println("Showing Valid Moves");
	}

	public void capturePiece(Piece pieceValue, int[] tileCoordinates){
		pieceValue.setPieceCoordinates(new int[] {100,100});
		movePiece(tileCoordinates);								
	}

	public boolean checkForWin(int teamNumber){
		if(currentPiece.getTeam() == teamNumber){
			return true;
		}else{
			return false;
		}
	}
	
	public void setCurrentPiece(Piece pieceValue){
		currentPiece = pieceValue;
	}
	
	public int getTotalRows(){
		return totalRows;
	}
	
	public int getTotalColumns(){
		return totalColumns;
	}
	
	public int[] getStartingWindowSize(){
		return startingWindowSize;
	}
	
	public GridView getGrid(){
		return grid;
	}
	
	public void setGrid(GridView newGrid){
		grid = newGrid;
	}
	

	@Override
	public void tileClicked(int[] coordinates, Piece containedPiece, boolean moveable, int tileType){
		System.out.println("Mouse click at ("+ coordinates[0]+","+ coordinates[1]+")");
		System.out.println("ContainedPiece = "+containedPiece);
		
		GameSystem gameSystem = this;
		
		if(containedPiece != null){
			if(gameSystem.checkForCurrentTeam(containedPiece) == true){
				gameSystem.respondToPieceClick(containedPiece);
			}else{
				if(moveable == true){
					System.out.println("Attempt To Capture");
					if(tileType == 0){
						gameSystem.capturePiece(containedPiece,coordinates);
					}else{
						System.out.println("Cannot Capture Base Pieces");								
					}
				}
			}
		}else if(moveable == false){
			System.out.println("No Piece or Valid Move");
			gameSystem.setCurrentPiece(null);
			gameSystem.getGrid().unvalidateMoves();
		}else{
			System.out.println("Movable Square");
			if(gameSystem.checkForWin(tileType)){ 
				gameSystem.movePiece(coordinates);
				System.out.println("Team " + tileType + " Wins!!");
				gameSystem.showTeamWin(tileType);
			}else{
				gameSystem.movePiece(coordinates);

			}
		}
		
	}
	public void undoLastMove(){
		if(lastMovedPiece == null){
			System.out.println("No Previous Moves");
		}else{
			System.out.println("Attempting Undo");
			currentPiece = lastMovedPiece;
			movePiece(lastMovedPiece.lastCoordinates);

		}
	}
	
	public void showTeamWin(int winningTeam){
		Stalemate.resetGame();
		JFrame winFrame = new JFrame("Game Over");
		winFrame.setVisible(true);
		winFrame.setSize(300,200);
		winFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		winFrame.setMaximumSize(new Dimension(600,400));
		winFrame.setMinimumSize(new Dimension(300,100));
		JComponent notificationPanel = new JLabel("Congratulations, Team " + winningTeam + " Wins!");
		winFrame.add(notificationPanel,BorderLayout.CENTER);
	}
}
