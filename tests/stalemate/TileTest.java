package stalemate;
import static org.junit.Assert.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import org.junit.Test;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;

import stalemante.Team;
import gridsystem.Tile;
import gridsystem.Tile.Listener;
import gridsystem.Piece;
import stalemante.pieces.*;

public class TileTest {
	class FakeTeam extends Team {
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
	public void DrawsGraySquaresForTiles() {
		//given
		List<Object> graphicsCommands = new ArrayList<>();
		Tile tile = new Tile(new int[]{1,1});
		
		Graphics g = new DummyGraphics(){
			@Override
			public void fillRect(int x, int y, int width, int height) {
				graphicsCommands.add("fillRect " + x + " " + y + " " + width + " " + height);
			}
			public void drawRect(int x, int y, int width, int height) {
				graphicsCommands.add("drawRect " + x + " " + y + " " + width + " " + height);
			}
			public void setColor(Color c) {
				graphicsCommands.add(c);
			}
		};
		
		//when
		tile.paint(g);
		
		//then
		assertEquals(4, graphicsCommands.size());
		assertEquals(Color.LIGHT_GRAY, graphicsCommands.get(0));
		assertEquals("fillRect 2 2 100 100", graphicsCommands.get(1));

	}
	
	@Test
	public void DrawsMoveableSquaresWhite() {
		//given
		List<Object> graphicsCommands = new ArrayList<>();
		Tile tile = new Tile(new int[]{1,1});
		tile.setMoveable(true);
		
		Graphics g = new DummyGraphics(){
		@Override
		public void fillRect(int x, int y, int width, int height) {
			graphicsCommands.add("fillRect " + x + " " + y + " " + width + " " + height);
		}
		@Override
		public void setColor(Color c) {
			graphicsCommands.add(c);
		}
	};
		
		//when
		tile.paint(g);
		
		//then
		assertEquals(3, graphicsCommands.size());
		assertEquals(Color.WHITE, graphicsCommands.get(0));
		assertEquals("fillRect 2 2 100 100", graphicsCommands.get(1));

	}
	
	@Test
	public void DrawsTileOutlinesBlack() {
		//given
		List<Object> graphicsCommands = new ArrayList<>();
		Tile tile = new Tile(new int[]{1,1});
		
		Graphics g = new DummyGraphics(){
			@Override
			public void fillRect(int x, int y, int width, int height) {
				graphicsCommands.add("fillRect " + x + " " + y + " " + width + " " + height);
			}
			
			public void drawRect(int x, int y, int width, int height) {
				graphicsCommands.add("drawRect " + x + " " + y + " " + width + " " + height);
			}
	
			@Override
			public void setColor(Color c) {
				graphicsCommands.add(c);
			}
		};
		
		//when
		tile.paint(g);
		
		//then
		assertEquals(4, graphicsCommands.size());
		assertEquals(Color.BLACK, graphicsCommands.get(2));
		assertEquals("drawRect 2 2 100 100", graphicsCommands.get(3));

	}
	
	@Test
	public void DrawsTeamTilesTeamColors() {
		//given
		Team team = new FakeTeam("Angelic Wizards", Color.WHITE);
		List<Object> graphicsCommands = new ArrayList<>();
		Tile tile = new Tile(new int[]{1,1});

		tile.setAsWinnableForTeam(team);
		
		
		Graphics g = new DummyGraphics(){
		@Override
		public void fillRect(int x, int y, int width, int height) {
			graphicsCommands.add("fillRect " + x + " " + y + " " + width + " " + height);
		}
		
		public void drawRect(int x, int y, int width, int height) {
			graphicsCommands.add("drawRect " + x + " " + y + " " + width + " " + height);
		}

		@Override
		public void setColor(Color c) {
			graphicsCommands.add(c);
		}
	};
		
		//when
		tile.paint(g);
		
		//then
		assertEquals(4, graphicsCommands.size());
		assertEquals(Color.WHITE, graphicsCommands.get(2));
		assertEquals("drawRect 2 2 100 100", graphicsCommands.get(3));

	}
	
	@Test
	public void DrawsPieceOnTile() {
		//given
		Team team = new FakeTeam("Angelic Wizards", Color.WHITE);
		List<Object> graphicsCommands = new ArrayList<>();
		Tile tile = new Tile(new int[]{1,1});
		Piece piece = new Square(team);
		tile.setPiece(piece);
		
		Graphics g = new DummyGraphics(){
			@Override
			public void fillRect(int x, int y, int width, int height) {
				graphicsCommands.add("fillRect " + x + " " + y + " " + width + " " + height);
			}
			
			public void drawRect(int x, int y, int width, int height) {
				graphicsCommands.add("drawRect " + x + " " + y + " " + width + " " + height);
			}
	
			@Override
			public void setColor(Color c) {
				graphicsCommands.add(c);
			}
		};
		
		//when
		tile.paint(g);
		
		//then
		assertEquals(6, graphicsCommands.size());
		assertEquals(Color.WHITE, graphicsCommands.get(4));
		assertEquals("fillRect 10 10 80 80", graphicsCommands.get(5));

	}

	@Test
	public void DrawsNoPieceOnEmptyTile(){
		//given
		List<Object> graphicsCommands = new ArrayList<>();
		Tile tile = new Tile(new int[]{1,1});
		tile.setPiece(null);

		Graphics g = new DummyGraphics(){
			@Override
			public void fillRect(int x, int y, int width, int height) {
				graphicsCommands.add("fillRect " + x + " " + y + " " + width + " " + height);
			}

			public void drawRect(int x, int y, int width, int height) {
				graphicsCommands.add("drawRect " + x + " " + y + " " + width + " " + height);
			}

			@Override
			public void setColor(Color c) {
				graphicsCommands.add(c);
			}
		};
		//when
		tile.paint(g);
		
		//then
		assertEquals(4, graphicsCommands.size());
	}

	@Test
	public void SetWinningSpot(){
		//given
		Team team = new FakeTeam("Angelic Wizards", Color.WHITE);
		Tile tile = new Tile(new int[]{1,1});
		
		//when
		tile.setWinningSpotForTeam(team);
		
		//then
		assertEquals(team, tile.winningSpotForTeam());
		
	}
	
	@Test
	public void CanBeSetToMoveable(){
		//given
		Tile tile = new Tile(new int[] {1,2});
		
		//when
		tile.setMoveable(true);

		//then
		assertEquals(true, tile.checkIfMoveable());
	}

	@Test
	public void CanBeSetToUnmoveable(){
		//given
		Tile tile = new Tile(new int[] {1,2});
		
		//when
		tile.setMoveable(false);

		//then
		assertEquals(false, tile.checkIfMoveable());
	}
	
	@Test
	public void CanGetCoordinates(){
		//given
		Tile tile = new Tile(new int[] {1,2});
		
		//when
		int[] expected = tile.getCoordinates();

		//then
		assertEquals(1,expected[0]);
		assertEquals(2,expected[1]);
	}
	
	@Test
	public void ClickedEventTriggered(){

		//given
		Tile tile = new Tile(new int[]{1,1});
		class TestListener implements Listener{
			public boolean clickedEvent;
			
			public void tileClicked(Tile tile){
				clickedEvent = true;
			}
		}

		TestListener testListener = new TestListener();
		tile.setListener(testListener);
		
		//when
		tile.getMouseListeners()[0].mouseClicked(fakeMouseEvent(tile));
			
		//then
		assertEquals(true,testListener.clickedEvent);
		
	}
	
	private MouseEvent fakeMouseEvent(Component c){
		return new MouseEvent(c, 0, 0, 0, 0, 0, 0, 0, 0, true, 0);
	}
	
	
}
