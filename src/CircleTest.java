import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CircleTest {

//	static final NumberWrapper n = new NumberWrapper(4);



	@Test
	public void drawsBlueCirlesForTeam1(){
		// given
		List<Object> graphicsCommands = new ArrayList<>();
		Circle circle = new Circle(1);
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
		assertEquals(Color.BLUE, graphicsCommands.get(0));
		assertEquals("fillOval 10 10 85 85", graphicsCommands.get(1));
	}

	@Test
	public void drawsRedCirlesForTeam2(){
		// given
		List<Object> graphicsCommands = new ArrayList<>();
		Circle circle = new Circle(1);
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
		assertEquals(Color.RED, graphicsCommands.get(0));
		assertEquals("fillOval 10 10 85 85", graphicsCommands.get(1));
	}
//	@Test
//	public void invalidMovesAreDetected() {
//		// given
////		GridView grid = new GridView();
//		Circle circle = new Circle();
//		int [] coordinates = {1, 1};
//		Tile tile = new Tile(grid, coordinates);
//		circle.setPieceCoordinates(new int[]{-4, Integer.MAX_VALUE});
//
//		// when
//		boolean result = circle.checkIfValidMove(tile);
//
//		// then
//		boolean expected = false;
//		assertEquals(result, expected);
//	}

//	@Test
//	public void validHorizontalMovesAreDetected() {
//		// given
////		GridView grid = new GridView();
//		Circle circle = new Circle();
//		int [] coordinates = {2, GameSystem.getTotalRows()-1};
//		Tile tile = new Tile(grid, coordinates);
//		circle.setPieceCoordinates(new int[]{4, GameSystem.getTotalRows()-1});
//
//		// when
//		boolean result = circle.checkIfValidMove(tile);
//
//		// then
//		boolean expected = true;
//		assertEquals(result, expected);
//	}

}
