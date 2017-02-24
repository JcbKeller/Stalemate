package stalemante;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import gridsystem.GridView;
import gridsystem.Piece;
import gridsystem.Tile;
import stalemante.pieces.Circle;
import stalemante.pieces.Square;
import stalemante.pieces.Triangle;

public class GameSystem implements Tile.Listener {
	

	private List<Tile> tiles = new ArrayList<>();
	
	private final GridView grid;
	
	private List<Team> teams;
	
	private Team currentTeam;
	private Piece currentPiece = null;
	private Piece lastMovedPiece = null;

	public GameSystem(GridView grid){
		
		grid.setListener(this);
		
		this.grid = grid;
		
		final Team blueTeam = new Team(){
			@Override
			public Color color() {
				return Color.BLUE;
			}
			@Override
			public String name() {
				return "Blue";
			}
		};
		
		final Team redTeam = new Team(){
			@Override
			public Color color() {
				return Color.RED;
			}
			@Override
			public String name() {
				return "Red";
			}
		};
		
		this.teams = Arrays.asList(blueTeam, redTeam);
		currentTeam = teams.stream().findFirst().get();
		
		
		addPieceToGame(new Square(blueTeam), new int[] {1,0});
		addPieceToGame(new Circle(blueTeam), new int[] {2,0});
		addPieceToGame(new Triangle(blueTeam), new int[] {3,0});
		
		addPieceToGame(new Square(redTeam), new int[] {1,5});
		addPieceToGame(new Circle(redTeam), new int[] {2,5});
		addPieceToGame(new Triangle(redTeam), new int[] {3,5});
		
		
//		grid.getTile(new int[]{1, 0}).setWinningSpotForTeam(redTeam);
//		grid.getTile(new int[]{2, 0}).setWinningSpotForTeam(redTeam);
//		grid.getTile(new int[]{3, 0}).setWinningSpotForTeam(redTeam);
//		
//		
//		grid.getTile(new int[]{1, 5}).setWinningSpotForTeam(blueTeam);
//		grid.getTile(new int[]{2, 5}).setWinningSpotForTeam(blueTeam);
//		grid.getTile(new int[]{3, 5}).setWinningSpotForTeam(blueTeam);
		
	}
	
	
	public List<Team> teams() {
		return teams;
	}
	
	public void changeTurns(){
		
		int index = teams.indexOf(currentTeam);
		
		if(index>=teams.size()-1){
			currentTeam = teams.stream().findFirst().get();
		}else{
			currentTeam = teams.get(index +1);
		}
		
		System.out.println("New Turn, team " + currentTeam);
	}
	
	public Team getCurrentTeam(){
		return currentTeam;
	}
	
	
	public void addPieceToGame(Piece newPiece, int[] startingCoordinates){
		grid.setAllTilesAsUnmoveable();
		grid.getTile(startingCoordinates).setPiece(newPiece);
	}
	
	public boolean checkForCurrentTeam(Piece piece) {
		if(piece == null){
			return false;
		}else if(piece.getTeam() == getCurrentTeam()){
			return true;			
		}else{
			return false;
		}
	}
	
	
	public void capturePiece(Piece pieceValue, int[] tileCoordinates){
		movePiece(tileCoordinates);								
	}
	
	public boolean checkForWin(Team teamNumber){
		if(currentPiece.getTeam() == teamNumber){
			return true;
		}else{
			return false;
		}
	}
	
	public void setCurrentPiece(Piece pieceValue){
		System.out.println("Piece Selected");
		currentPiece = pieceValue;
	}
	
	public Piece getCurrentPiece(){
		return currentPiece;
	}

	
	public GridView getGrid(){
		return grid;
	}
	

	@Override
	public void tileClicked(Tile tile){
		
		System.out.println("Mouse click at ("+ tile.getCoordinates()[0]+","+ tile.getCoordinates()[1]+")");
		System.out.println("ContainedPiece = "+tile.getPiece());
		
		if(currentPiece == null){
			if(checkForCurrentTeam(tile.getPiece()) == true){
				currentPiece = tile.getPiece();
				grid.setMoveableTiles(tile.getPiece(), grid.columns());
			}else{
				System.out.println("No Movable Piece on tile");
			}
		}else if(tile.checkIfMoveable() == false){
			System.out.println("No Piece or Valid Move");
			this.setCurrentPiece(null);
			this.getGrid().setAllTilesAsUnmoveable();
		}else if(tile.getPiece() == null){
			System.out.println("Tile contains nothing");

			if(tile.winningSpotForTeam() == currentTeam){
				this.showTeamWin(currentTeam);	
				movePiece(tile.getCoordinates());			
			}else{

			movePiece(tile.getCoordinates());
				System.out.println("Current Piece: "+currentPiece);
			}
		}else if(this.checkForCurrentTeam(tile.getPiece()) == true){
			System.out.println("Tile contains Ally piece");
			swapPieces(currentPiece, tile.getPiece());
		}else{
			System.out.println("Tile contains Enemy piece");
			
			if(tile.winningSpotForTeam() == currentTeam){
				System.out.println("Cannot Capture Base Pieces");
			}else{
				System.out.println("Checking for Piece Protection");
				if(checkIfProtecting(tile) == false){
			
					System.out.println("Piece is vulnerable!");
					this.showTeamWin(currentTeam);		
					this.capturePiece(tile.getPiece(),tile.getCoordinates());
					
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
			final Tile thisPieceTile = grid.getTile(thisPiece);
			if(thisPiece.getTeam() != currentTeam){
				if(thisPiece.checkIfValidMove(thisPieceTile, tile, grid.columns()) == true){
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
	
	// --------- FIX Broken Undo Move ----------
	// NOTE:  Allow for undoing swap moves
	
	public void undoLastMove(){
//		if(lastMovedPiece == null){
//			System.out.println("No Previous Moves");
//		}else{
//			System.out.println("Attempting Undo");
//			currentPiece = lastMovedPiece;
//			movePiece(lastMovedPiece.getPreviousCoordinates());
//		}
	}
	
	public void showTeamWin(Team winningTeam){
		System.out.println("Team " + winningTeam + " wins");
		
		Stalemate.resetGame();
		JFrame winFrame = new JFrame("Game Over");
		winFrame.setVisible(true);
		winFrame.setSize(300,200);
		winFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		winFrame.setMaximumSize(new Dimension(600,400));
		winFrame.setMinimumSize(new Dimension(300,100));
		JComponent notificationPanel = new JLabel("Congratulations, " + winningTeam.name() + " Wins!");
		winFrame.add(notificationPanel,BorderLayout.CENTER);
	}
	
	public void movePiece(int[] coordinates){
		
		Tile sourceTile = grid.getTile(currentPiece);
		Tile destinationTile = grid.getTile(coordinates);
		
		sourceTile.setPiece(null);
		destinationTile.setPiece(currentPiece);
		lastMovedPiece = currentPiece;
		currentPiece = null;
		grid.setAllTilesAsUnmoveable();
		changeTurns();
		
	}
	
	public void swapPieces(Piece selectedPiece,Piece clickedPiece){	
		Tile sourceTile = grid.getTile(currentPiece);
		Tile destinationTile = grid.getTile(clickedPiece);
		
		sourceTile.setPiece(clickedPiece);
		destinationTile.setPiece(selectedPiece);
				
		changeTurns();
		setCurrentPiece(null);
		getGrid().setAllTilesAsUnmoveable();
	}
}
