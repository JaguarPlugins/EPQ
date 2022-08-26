package edu.agray.maze.util;

import edu.agray.maze.map.Tile;

public class Direction {

//	PUBLIC STATICS
	public static Direction
			UP = new Direction(0, -1),
			RIGHT = new Direction(1, 0),
			DOWN = new Direction(0, 1),
			LEFT = new Direction(-1, 0);
			
	public static Direction Calculate(Tile centre, Tile target) {
		
		int xOffset = Integer.signum(target.getX() - centre.getX());
		int yOffset = Integer.signum(target.getY() - centre.getY());
		return new Direction(xOffset, yOffset);
		
	}
	
	
//	CLASS SETUP
	private int dx, dy;
	
	private Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}
	
}