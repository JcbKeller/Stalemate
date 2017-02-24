package stalemate;

import static org.junit.Assert.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import gridsystem.Tile;
import stalemante.Team;
import stalemante.pieces.Square;

public class SquareTest {
	
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
	public void drawsTeamColoredSquares(){
		// given
		List<Object> graphicsCommands = new ArrayList<>();
		Team team = new FakeTeam("The Evil Cerberus", Color.GREEN);
		Square square = new Square(team);
		Graphics g = new DummyGraphics(){
			@Override
			public void fillRect(int x, int y, int width, int height) {
				graphicsCommands.add("fillOval " + x + " " + y + " " + width + " " + height);
			}
			@Override
			public void setColor(Color c) {
				graphicsCommands.add(c);
			}
		};

		// when
		square.paint(g);

		// then
		assertEquals(2, graphicsCommands.size());
		assertEquals(Color.GREEN, graphicsCommands.get(0));
		assertEquals("fillOval 10 10 80 80", graphicsCommands.get(1));
	}
	
	@Test
	public void canMoveHorizontally(){
		//given
		Team team = new FakeTeam("The Evil Cerberus", Color.GREEN);
		Square square = new Square(team);
		Tile from = new Tile(new int[]{1, 1});
		Tile to = new Tile(new int[]{2, 1});
		int horizontalDistance = 7;
		
		//when
		boolean result = square.checkIfValidMove(from, to, horizontalDistance);
		
		//then
		assertEquals(true, result);
	}

	@Test
	public void cantMoveTwoHorizontalTiles(){
		//given
		Team team = new FakeTeam("The Evil Cerberus", Color.GREEN);
		Square square = new Square(team);
		Tile from = new Tile(new int[]{1, 1});
		Tile to = new Tile(new int[]{3, 1});
		int horizontalDistance = 7;
		
		//when
		boolean result = square.checkIfValidMove(from, to, horizontalDistance);
		
		//then
		assertEquals(false, result);
	}
	
	@Test
	public void canMoveVertically(){
		//given
		Team team = new FakeTeam("The Evil Cerberus", Color.GREEN);
		Square square = new Square(team);
		Tile from = new Tile(new int[]{1, 1});
		Tile to = new Tile(new int[]{1, 2});
		int horizontalDistance = 7;
		
		//when
		boolean result = square.checkIfValidMove(from, to, horizontalDistance);
		
		//then
		assertEquals(true, result);
	}
	
	@Test
	public void cantMoveDiagonally(){
		//given
		Team team = new FakeTeam("The Evil Cerberus", Color.GREEN);
		Square square = new Square(team);
		Tile from = new Tile(new int[] {1,1});
		Tile to = new Tile(new int[] {2,2});
		int horizontalMax = 5;
		
		//when
		boolean result = square.checkIfValidMove(from, to, horizontalMax);
		
		//then
		assertEquals(false, result);
	}
	
	@Test
	public void canWrapLeftSide(){
		//given
		Team team = new FakeTeam("The Evil Cerberus", Color.GREEN);
		Square square = new Square(team);
		Tile from = new Tile(new int[] {0,1});
		Tile to = new Tile(new int[] {4,1});
		int horizontalRows = 5;
		
		//when
		boolean result = square.checkIfValidMove(from, to, horizontalRows);
		
		//then
		assertEquals(true, result);
	}
	
	@Test
	public void canWrapRightSide(){
		//given
		Team team = new FakeTeam("The Evil Cerberus", Color.GREEN);
		Square square = new Square(team);
		Tile from = new Tile(new int[] {4,1});
		Tile to = new Tile(new int[] {0,1});
		int horizontalRows = 5;
		
		//when
		boolean result = square.checkIfValidMove(from, to, horizontalRows);
		
		//then
		assertEquals(true, result);
	}
	
	
	
}
