package stalemate;
import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import gridsystem.Tile;
import stalemante.Team;
import stalemante.pieces.Circle;

public class CircleTest {

//	static final NumberWrapper n = new NumberWrapper(4);
	
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
	public void drawsTeamColoredCircles(){
		// given
		List<Object> graphicsCommands = new ArrayList<>();
		Team team = new FakeTeam("The Dragons", Color.PINK);
		Circle circle = new Circle(team);
		Graphics g = new DummyGraphics(){
			@Override
			public void fillOval(int x, int y, int width, int height) {
				graphicsCommands.add("fillOval " + x + " " + y + " " + width + " " + height);
			}
			@Override
			public void setColor(Color c) {
				graphicsCommands.add(c);
			}
		};

		// when
		circle.paint(g);

		// then
		assertEquals(2, graphicsCommands.size());
		assertEquals(Color.PINK, graphicsCommands.get(0));
		assertEquals("fillOval 10 10 85 85", graphicsCommands.get(1));
	}
	
	@Test
	public void canMoveHorizontally() {
		// given
		Team team = new FakeTeam("The Red Dragons", Color.PINK);
		Circle circle = new Circle(team);
		Tile from = new Tile(new int[]{1, 1});
		Tile to = new Tile(new int[]{3, 1});
		int horizontalDistance = 7;
		
		// when
		boolean result = circle.checkIfValidMove(from, to, horizontalDistance);

		// then
		assertEquals(result, true);
	}
	

	@Test
	public void canMoveVertically() {
		// given
		Team team = new FakeTeam("The Red Dragons", Color.PINK);
		Circle circle = new Circle(team);
		Tile from = new Tile(new int[]{1, 1});
		Tile to = new Tile(new int[]{1, 3});
		int horizontalDistance = 9;
		
		// when
		boolean result = circle.checkIfValidMove(from, to, horizontalDistance);

		// then
		assertEquals(result, true);
	}

	@Test
	public void cantMoveLessThan2Squares() {
		// given
		Team team = new FakeTeam("The Red Dragons", Color.PINK);
		Circle circle = new Circle(team);
		Tile from = new Tile(new int[]{1, 1});
		Tile to = new Tile(new int[]{2, 1});
		int horizontalDistance = 5;
		
		// when
		boolean result = circle.checkIfValidMove(from, to, horizontalDistance);

		// then
		assertEquals(result, false);
	}
	

	@Test
	public void cantMoveDiagonally() {
		// given
		Team team = new FakeTeam("The Red Dragons", Color.PINK);
		Circle circle = new Circle(team);
		Tile from = new Tile(new int[]{1, 1});
		Tile to = new Tile(new int[]{3, 3});
		int horizontalDistance = 6;
		
		// when
		boolean result = circle.checkIfValidMove(from, to, horizontalDistance);

		// then
		assertEquals(result, false);
	}
	
	@Test
	public void cantMoveMoreThan2Squares() {
		// given
		Team team = new FakeTeam("The Red Dragons", Color.PINK);
		Circle circle = new Circle(team);
		Tile from = new Tile(new int[]{1, 1});
		Tile to = new Tile(new int[]{4, 1});
		int horizontalDistance = 7;
		
		// when
		boolean result = circle.checkIfValidMove(from, to, horizontalDistance);

		// then
		assertEquals(result, false);
	}

	@Test
	public void canWrapFromLeftEdge(){
		//given
		Team team = new FakeTeam("The Red Dragons", Color.PINK);
		Circle circle = new Circle(team);
		Tile from = new Tile(new int[] {0,1});
		Tile to = new Tile(new int[] {4,1});
		int horizontalDistance = 6;
		
		//when
		boolean result = circle.checkIfValidMove(from, to, horizontalDistance);
		
		//then
		assertEquals(result, true);
	}
		
	@Test
	public void canWrapFromRightEdge(){
		//given
		Team team = new FakeTeam("The Red Dragons", Color.PINK);
		Circle circle = new Circle(team);
		Tile from = new Tile(new int[] {5,1});
		Tile to = new Tile(new int[] {1,1});
		int horizontalDistance = 6;
		
		//when
		boolean result = circle.checkIfValidMove(from, to, horizontalDistance);
		
		//then
		assertEquals(result, true);
	}


}
