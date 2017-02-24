
package stalemate;

import static org.junit.Assert.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import gridsystem.Tile;
import stalemante.Team;
import stalemante.pieces.Triangle;

public class TriangleTest {

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
	
	@Test
	public void drawsTeamColoredPieces() { 
		//given
		List<Object> graphicsCommands = new ArrayList<>();
		Team team = new FakeTeam("The Narcissistic Rhinos", Color.BLUE);
		Triangle triangle = new Triangle(team);
		int[] xPoints = {5,50,95};
		int[] yPoints = {90,10,90};
		int nPoints = 3;
		Graphics g = new DummyGraphics(){
			@Override
			public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
//				graphicsCommands.add("fillPolygon " + print(xPoints)  + " " + yPoints + " " + nPoints);
			}
			@Override
			public void setColor(Color c) {
				graphicsCommands.add(c);
			}
			

		};
		//when
		triangle.paint(g);
		
		//then
//		assertEquals(2, graphicsCommands.size());
//		assertEquals(Color.BLUE,graphicsCommands.get(0));
//		assertEquals("fillPolygon {5,50,95} {90 10 90} 3", graphicsCommands.get(1));

	}
	
	@Test
	public void cantMoveHorizontally(){
		//given
		Team team = new FakeTeam("The Narcissistic Rhinos", Color.BLUE);
		Triangle triangle = new Triangle(team);
		Tile from = new Tile(new int[] {1,1});
		Tile to = new Tile(new int[] {2,1});
		int totalX = 6;
		
		//when
		boolean result = triangle.checkIfValidMove(from, to, totalX);
		
		//then
		assertEquals(false, result);
		
	}
	
	@Test
	public void canMoveDiagonally(){
		//given 
		Team team = new FakeTeam("The Narcissistic Rhinos", Color.BLUE);
		Triangle triangle = new Triangle(team);
		Tile from = new Tile(new int[] {1,1});
		Tile to = new Tile(new int[] {2,2});
		int totalX = 6;
		
		//when
		boolean result = triangle.checkIfValidMove(from, to, totalX);
		
		//then
		assertEquals(true, result);
		
	}
	
	@Test
	public void cantMoveVertically(){
		//given
		Team team = new FakeTeam("The Narcissistic Rhinos", Color.BLUE);
		Triangle triangle = new Triangle(team);
		Tile from = new Tile(new int[] {1,1});
		Tile to = new Tile(new int[] {2,1});
		int totalX = 6;
		
		//when
		boolean result = triangle.checkIfValidMove(from, to, totalX);
		
		//then
		assertEquals(false, result);
		
	}
	
	@Test
	public void cantMove2DiagonalSquares(){
		//given
		Team team = new FakeTeam("The Narcissistic Rhinos", Color.BLUE);
		Triangle triangle = new Triangle(team);
		Tile from = new Tile(new int[] {1,1});
		Tile to = new Tile(new int[] {3,3});
		int totalX = 6;
		
		//when
		boolean result = triangle.checkIfValidMove(from, to, totalX);
		
		//then
		assertEquals(false, result);
		
	}
	
	@Test
	public void canWrapLeftSide(){
		//given
		Team team = new FakeTeam("The Narcissistic Rhinos", Color.BLUE);
		Triangle triangle = new Triangle(team);
		Tile from = new Tile(new int[] {0,1});
		Tile to = new Tile(new int[] {4,2});
		int totalX = 5;
		
		//when
		boolean result = triangle.checkIfValidMove(from, to, totalX);
		
		//then
		assertEquals(true, result);
		
	}
	
	@Test
	public void canWrapRightSide(){
		//given
		Team team = new FakeTeam("The Narcissistic Rhinos", Color.BLUE);
		Triangle triangle = new Triangle(team);
		Tile from = new Tile(new int[] {4,1});
		Tile to = new Tile(new int[] {0,2});
		int totalX = 5;
		
		//when
		boolean result = triangle.checkIfValidMove(from, to, totalX);
		
		//then
		assertEquals(true, result);
		
	}
}
