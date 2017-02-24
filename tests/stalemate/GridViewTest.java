package stalemate;

import static org.junit.Assert.*;

import org.junit.Test;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import gridsystem.*;
import gridsystem.Tile.Listener;
import stalemante.GameSystem;
import stalemante.Team;
import stalemante.pieces.Square;
import stalemante.pieces.Triangle;


public class GridViewTest {

	class FakeTeam extends Team{
		private String name;
		private Color color;
		public FakeTeam(String startingName, Color startingColor){
			this.name = startingName;
			this.color = startingColor;
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

	class TestListener implements Listener{
		public boolean clickedEvent;
		
		public void tileClicked(Tile tile){
			clickedEvent = true;
		}
	}

	private MouseEvent fakeMouseEvent(Component c){
		return new MouseEvent(c, 0, 0, 0, 0, 0, 0, 0, 0, true, 0);
	}
	
	
	@Test
	public void MakesGridForTiles() {
		//given
		int rows = 6;
		int columns = 4;
		GridView grid;
		
		//when
		 grid = new GridView(rows,columns);

		 //then
		 assertEquals(6,grid.columns());
		 assertEquals(4,grid.rows());
		
	}

	@Test
	public void addsTilesToGrid(){
		//given
		int rows = 6;
		int columns = 5;
		GridView grid;

		//when
		 grid = new GridView(rows,columns);

		 //then
		 assertEquals(0,grid.getTile(new int[]{0,0}).getCoordinates()[0]);
		 assertEquals(30,grid.getComponentCount());
		 assertEquals(30,grid.tiles().size());
	}
	
	@Test
	public void getsTileFromPiece(){
		//given
		Team team = new FakeTeam("The Narcissistic Rhinos", Color.BLUE);
		GridView grid = new GridView(6,5);
		Triangle triangle = new Triangle(team);
		Tile tile = grid.getTile(new int[]{1,2});
		tile.setPiece(triangle);

		//when
		Tile expected = grid.getTile(triangle);

		 //then
		 assertEquals(tile,expected);
	}
	
	@Test
	public void getsTileFromCoordinates(){
		//given
		GridView grid = new GridView(6,5);
		
		//when
		Tile tileA = grid.getTile(new int[]{1,2});
		Tile tileB = grid.getTile(new int[]{3,0});

		 //then
		 assertEquals(1,tileA.getCoordinates()[0]);
		 assertEquals(2,tileA.getCoordinates()[1]);
		 assertEquals(3,tileB.getCoordinates()[0]);
		 assertEquals(0,tileB.getCoordinates()[1]);
	}
	
	@Test
	public void canSetMoveableTilesForPiece(){
		//given
		Team team = new FakeTeam("The Narcissistic Rhinos", Color.BLUE);
		Triangle triangle = new Triangle(team);

		GridView grid = new GridView(6,5);
		Tile pieceTile = grid.getTile(new int[]{0,1});
		pieceTile.setPiece(triangle);
		Tile moveableTile = grid.getTile(new int[]{1,2});
		
		//when
		grid.setMoveableTiles(triangle, 6);

		 //then
		assertEquals(true, moveableTile.checkIfMoveable());
	}
	
	@Test
	public void canSetAllTilesToUnmoveable(){
		//given
		GridView grid = new GridView(6,5);
		Tile tileA = grid.getTile(new int[]{1,2});
		tileA.setMoveable(true);
		
		//when
		grid.setAllTilesAsUnmoveable();

		//then
		assertEquals(false, tileA.checkIfMoveable());
	}
	
	@Test
	public void setsListenerForTiles(){

		//given
		GridView grid = new GridView(6,5);
		Tile tileA = grid.getTile(new int[]{1,2});
		TestListener testListener = new TestListener();

		//when
		grid.setListener(testListener);
		tileA.getMouseListeners()[0].mouseClicked(fakeMouseEvent(grid));
		
		assertEquals(true,testListener.clickedEvent);
		
	}
	
}
