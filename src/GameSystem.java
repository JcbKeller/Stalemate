import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	
	public void addPieceToGame(Piece newPiece, int[] startingCoordinates){
		newPiece.setPieceCoordinates(startingCoordinates);
		grid.setAllTilesAsUnmoveable();
		grid.getTile(startingCoordinates).setPiece(newPiece);
	}
	
	public boolean checkForCurrentTeam(Piece piece) {
		if(piece == null){
			return false;
		}else if(piece.team == getCurrentTeam()){
			return true;			
		}else{
			return false;
		}
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
	public void tileClicked(Tile tile){
		System.out.println("Mouse click at ("+ tile.getCoordinates()[0]+","+ tile.getCoordinates()[1]+")");
		System.out.println("ContainedPiece = "+tile.getPiece());
		
		if(currentPiece == null){
			if(checkForCurrentTeam(tile.getPiece()) == true){
				currentPiece = tile.getPiece();
				grid.setMoveableTiles(tile.getPiece(), totalColumns);
			}else{
				System.out.println("No Movable Piece on tile");
			}
		}else if(tile.checkIfMoveable() == false){
			System.out.println("No Piece or Valid Move");
			this.setCurrentPiece(null);
			this.getGrid().setAllTilesAsUnmoveable();
		}else if(tile.getPiece() == null){
			System.out.println("Tile contains nothing");
			if(tile.getType() == currentTeam){
				movePiece(tile.getCoordinates());
				this.showTeamWin(currentTeam);				
			}else{
				movePiece(tile.getCoordinates());
				System.out.println("Current Piece: "+currentPiece);
			}
		}else if(this.checkForCurrentTeam(tile.getPiece()) == true){
			System.out.println("Tile contains Ally piece");
			swapPieces(currentPiece, tile.getPiece());
		}else{
			System.out.println("Tile contains Enemy piece");
			if(tile.getType() == currentTeam){
				System.out.println("Cannot Capture Base Pieces");
			}else{
				System.out.println("Checking for Piece Protection");
				if(checkIfProtecting(tile) == false){
					System.out.println("Piece is vulnerable!");
					this.capturePiece(tile.getPiece(),tile.getCoordinates());
					this.showTeamWin(currentTeam);						
				}else{
					System.out.println("Piece is Protected!");
					this.setCurrentPiece(null);
					this.getGrid().setAllTilesAsUnmoveable();
				}
			}
		}
	}
	

	private List<Piece> allPieces(List<Tile> tiles){
		
		
		return tiles
					.stream()
					.map(t -> t.getPiece())
					.filter(piece -> piece !=null)
					.collect(Collectors.toList());
		
	}
	
	public boolean checkIfProtecting(Tile tile){
		int protection = 0;
		for(Piece thisPiece : allPieces(grid.tiles())){
			if(thisPiece.getTeam() != currentTeam){
				if(thisPiece.checkIfValidMove(tile, totalColumns) == true){
					protection = 1;
				}
			}
		}
		if(protection == 1){
			return true;
		}else{
			return false;
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
	
	public void movePiece(int[] coordinates){
		
		Tile sourceTile = grid.getTile(currentPiece);
		Tile destinationTile = grid.getTile(coordinates);
		
		sourceTile.setPiece(null);
		destinationTile.setPiece(currentPiece);
//		currentPiece.setPieceCoordinates(coordinates);
		lastMovedPiece = currentPiece;
		currentPiece = null;
		grid.setAllTilesAsUnmoveable();
		changeTurns();
		
	}
	
	public void swapPieces(Piece selectedPiece,Piece clickedPiece){	
		int[] selectedCoordinates = selectedPiece.coordinates;
		int[] clickedCoordinates = clickedPiece.coordinates;
		Tile sourceTile = grid.getTile(currentPiece.coordinates);
		Tile destinationTile = grid.getTile(clickedPiece.coordinates);
		
		sourceTile.setPiece(clickedPiece);
		destinationTile.setPiece(selectedPiece);
		
		clickedPiece.setPieceCoordinates(selectedCoordinates);
		selectedPiece.setPieceCoordinates(clickedCoordinates);
		
		changeTurns();
		setCurrentPiece(null);
		getGrid().setAllTilesAsUnmoveable();
	}
}
