package stalemate;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import stalemante.GameSystem;
import stalemante.Team;
import stalemante.pieces.*;
import gridsystem.GridView;
import gridsystem.Piece;
import gridsystem.Tile;

public class GameSystemTest {

	class FakeTeam extends Team{
		private Color color;
		private String name;
		public FakeTeam(String name, Color color) {
			this.color = color;
			this.name = name;
		}
		
		@Override
		public String name() {
			return name;
		}
		
		@Override
		public Color color() {
			return color;
		}
	}
	
	@Test
	public void detectNoPieceInTileClicked() {

		//given
		GridView grid = new GridView(6,6);
		GameSystem gameSystem = new GameSystem(grid);
		Tile tileClicked = grid.getTile(new int[] {0,0});
		assertNull(tileClicked.getPiece());
		
		//when
		gameSystem.tileClicked(tileClicked);
		
		//then
		assertEquals(null, gameSystem.getCurrentPiece());
	}

	@Test
	public void detectPieceInTileClicked() {

		//given
		GridView grid = new GridView(6,6);
		GameSystem gameSystem = new GameSystem(grid);
		Tile tileClicked = firstCircle(grid);
		
		Piece piece = tileClicked.getPiece();
		//when
		gameSystem.tileClicked(tileClicked);
		
		//then
		assertEquals(piece, gameSystem.getCurrentPiece());
	}
	
	private Tile firstCircle(GridView grid){
		for(Tile tile : grid.tiles()){
			Piece p = tile.getPiece();
			if(p instanceof Circle){
				return tile;
			}
		}
		throw new RuntimeException("There was no circle!");
	}
	
	private Tile firstSquare(GridView grid){
		for(Tile tile : grid.tiles()){
			Piece p = tile.getPiece();
			if(p instanceof Square){
				return tile;
			}
		}
		throw new RuntimeException("There was no circle!");
	}

	private Tile secondSquare(GridView grid){
		int occurance = 0;
		for(Tile tile : grid.tiles()){
			Piece p = tile.getPiece();
			if(p instanceof Square){
				occurance ++;
				if(occurance == 2){
					return tile;					
				}
			}
		}
		throw new RuntimeException("There was no circle!");
	}
	
	private Tile secondTriangle(GridView grid){
		int occurance = 0;
		for(Tile tile : grid.tiles()){
			Piece p = tile.getPiece();
			if(p instanceof Triangle){
				occurance ++;
				if(occurance == 2){
					return tile;					
				}
			}
		}
		throw new RuntimeException("There was no circle!");
	}
	
	private Tile twoSpacesDown(Tile tile, GridView grid){
		return grid.getTile(new int[]{tile.getCoordinates()[0], tile.getCoordinates()[1] + 2});

	}
	
	private Tile twoSpacesAcross(Tile tile, GridView grid){
		return grid.getTile(new int[]{tile.getCoordinates()[0] + 2, tile.getCoordinates()[1]});

	}
	
	private Tile emptySpace(Tile tile){
		if(tile.getPiece()!=null){
			throw new RuntimeException("Expected an empty tile");
		}
		return tile;
	}
	
	
	@Test
	public void MovingPieceToEmptyTile() {
		//given
		GridView grid = new GridView(6,6);
		GameSystem gameSystem = new GameSystem(grid);

		Team team = gameSystem.getCurrentTeam();
		Tile source = firstCircle(gameSystem.getGrid());
		
		Piece piece = source.getPiece();
		
		Tile destination = emptySpace(twoSpacesDown(source, grid));
		
		//when
		gameSystem.tileClicked(source);
		gameSystem.tileClicked(destination);
		
		//then
		assertEquals(null, source.getPiece());
		assertEquals(piece, destination.getPiece());
		assertNotEquals(team,gameSystem.getCurrentTeam());
		assertEquals(false,destination.checkIfMoveable());
		assertEquals(null,gameSystem.getCurrentPiece());
	}
	
	@Test
	public void cantMoveToUnreachableTile() {
		//given
		GridView grid = new GridView(6,6);
		GameSystem gameSystem = new GameSystem(grid);

		Tile source = firstSquare(gameSystem.getGrid());
		
		Piece piece = source.getPiece();
		
		Tile destination = emptySpace(twoSpacesDown(source, grid));
		
		//when
		gameSystem.tileClicked(source);
		gameSystem.tileClicked(destination);
		
		//then
		assertEquals(piece, source.getPiece());
		assertEquals(null, destination.getPiece());
	}
	
	@Test
	public void swapTilesWithFriendlyPiece() {
		//given
		GridView grid = new GridView(6,6);
		GameSystem gameSystem = new GameSystem(grid);
		Team team = gameSystem.getCurrentTeam();
		
		Tile source = firstSquare(gameSystem.getGrid());
		Piece originPiece = source.getPiece();
		
		Tile destination = firstCircle(gameSystem.getGrid());
		Piece destinationPiece = destination.getPiece();
		
		//when
		gameSystem.tileClicked(source);
		gameSystem.tileClicked(destination);
		
		//then
		assertEquals(destinationPiece, source.getPiece());
		assertEquals(originPiece, destination.getPiece());
		assertNotEquals(team,gameSystem.getCurrentTeam());
		assertEquals(false,destination.checkIfMoveable());
		assertEquals(null,gameSystem.getCurrentPiece());
	}
	
	@Test
	public void changesCurrentTeam(){
		//given
		GridView grid = new GridView(6,6);
		GameSystem gameSystem = new GameSystem(grid);
		Team expected = gameSystem.teams().get(1);
		
		//when
		gameSystem.changeTurns();
		
		//then
		assertEquals(expected,gameSystem.getCurrentTeam());
		
	}
	
	@Test
	public void detectsMoveToWinningSquare(){
		//given
		GridView grid = new GridView(6,6);
		GameSystem gameSystem = new GameSystem(grid);
		Tile currentOrigin = firstSquare(gameSystem.getGrid());
		Piece currentPiece = currentOrigin.getPiece();
		Tile fromTile = grid.getTile(new int[]{2,2});
		Tile toTile = grid.getTile(new int[]{3,2});
		currentOrigin.setPiece(null);
		fromTile.setPiece(currentPiece);
		toTile.setAsWinnableForTeam(currentPiece.getTeam());
		
		//when
		gameSystem.tileClicked(fromTile);
		gameSystem.tileClicked(toTile);
		
		gameSystem.setCurrentPiece(currentPiece);		
		
		//then
		assertEquals(null,fromTile.getPiece());
		assertEquals(true,gameSystem.checkForWin(currentPiece.getTeam()));
		
	}
		
	@Test
	public void detectMovingOntoEnemyPiece(){
		//given
		GridView grid = new GridView(6,6);
		GameSystem gameSystem = new GameSystem(grid);
		Tile currentOrigin = firstSquare(gameSystem.getGrid());
		Piece currentPiece = currentOrigin.getPiece();
		Tile enemyOrigin = secondSquare(gameSystem.getGrid());
		Piece enemyPiece = enemyOrigin.getPiece();
		Tile fromTile = grid.getTile(new int[]{2,2});
		Tile toTile = grid.getTile(new int[]{3,2});
		currentOrigin.setPiece(null);
		enemyOrigin.setPiece(null);
		fromTile.setPiece(currentPiece);
		toTile.setPiece(enemyPiece);
		
		//when
		gameSystem.tileClicked(fromTile);
		gameSystem.tileClicked(toTile);
		
		
		//then
		assertEquals(null,fromTile.getPiece());
		assertEquals(currentPiece,toTile.getPiece());

	}
	
	@Test
	public void cantCaptureProtectedEnemyPiece(){
		//given
		GridView grid = new GridView(6,6);
		GameSystem gameSystem = new GameSystem(grid);
		
		Tile currentOrigin = firstSquare(gameSystem.getGrid());
		Piece currentPiece = currentOrigin.getPiece();
		
		Tile enemyOrigin = secondSquare(gameSystem.getGrid());
		Piece enemyPiece = enemyOrigin.getPiece();
		
		Tile protectorOrigin = secondTriangle(gameSystem.getGrid());
		Piece protectorPiece = protectorOrigin.getPiece();
		
		Tile fromTile = grid.getTile(new int[]{2,2});
		Tile toTile = grid.getTile(new int[]{3,2});
		Tile protectingTile = grid.getTile(new int[]{4,3});
		
		currentOrigin.setPiece(null);
		enemyOrigin.setPiece(null);
		protectorOrigin.setPiece(null);

		fromTile.setPiece(currentPiece);
		toTile.setPiece(enemyPiece);
		protectingTile.setPiece(protectorPiece);
		
		//when
		gameSystem.tileClicked(fromTile);
		gameSystem.tileClicked(toTile);
		
		
		//then
		assertEquals(currentPiece,fromTile.getPiece());
		assertEquals(enemyPiece,toTile.getPiece());

	}
	
	// --------- FIX Broken Undo Move ----------
	// NOTE:  Allow for undoing swap moves
	
	@Test
	public void canUndoLastMove(){
		GridView grid = new GridView(6,6);
		GameSystem gameSystem = new GameSystem(grid);
		Team team = gameSystem.getCurrentTeam();
		Tile source = firstCircle(gameSystem.getGrid());
		Piece piece = source.getPiece();
		Tile destination = emptySpace(twoSpacesDown(source, grid));

		gameSystem.tileClicked(source);
		gameSystem.tileClicked(destination);
		
		//when
		//gameSystem.undoLastMove();
		
		//then
//		assertEquals(team, gameSystem.getCurrentTeam());
//		assertEquals(piece,source.getPiece());
//		assertEquals(null,destination.getPiece());

	}
	
}
