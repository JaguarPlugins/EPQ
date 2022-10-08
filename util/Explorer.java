package edu.agray.maze.util;

import java.util.ArrayList;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Explorer {

	private Map map;
	private Direction direction;
	private Tile position;
	private ArrayList<Tile> path;
	
	public Explorer(Map map, Tile position, Direction direction) {
		
		this.map = map;
		this.direction = direction;
		this.position = position;
		path = new ArrayList<Tile>();
		path.add(position);
		
	}
	
	public Explorer(Map map, Tile position, Direction direction, ArrayList<Tile> oldPath) {
		
		this.map = map;
		this.direction = direction;
		this.position = position;
		path = new ArrayList<Tile>();
		path.addAll(oldPath);
		path.add(position);
		
	}

	public ArrayList<Explorer> generateChildren(Tile[] options) {
		
		ArrayList<Explorer> output = new ArrayList<Explorer>(); // list to be returned
		
		if (position.isGoal()) {
//			if the next move is simply the goal tile, then just this will be returned to the Turtle
			output.add(this);
			return output;
		}
		
		for (Tile loopTile : options) {
//			iterates through tiles in this direction until it reaches a "vertex"
			
			Direction newDirection = Direction.Calculate(position, loopTile); // works out the direction of the line of sight
			Tile vertex = travel(position, newDirection);
			
			if (!newDirection.follows(direction.inverse())) { // ensures explorers don't double back on themselves
				output.add(new Explorer(map, vertex, newDirection, path)); // creates a new explorer
			}
			
		}
		
		return output;
		
	}
	
	private Tile travel(Tile startTile, Direction travelDirection) {
		
//		Returns the fist "interesting" tile that it reaches in that direction
//		Start tile is not included
		
		Tile focus = startTile;
		
		while (map.getTile(focus, travelDirection) != null) {
		
			Tile next = map.getTile(focus, travelDirection);
			if (next.isSolid()) {
				return focus;
			}
			if (next.isJunction()) {
				return next;
			}
			
			focus = next;
			
		}
		
		return focus;
		
	}
	
	public void render(GraphicsContext g) {
		g.setFill(Color.GREEN);
		g.fillOval(position.getX()*position.getWidth(), position.getY()*position.getHeight(), position.getWidth(), position.getHeight());
	}
	
	public Tile getPosition() {
		return position;
	}
	
	public ArrayList<Tile> getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "(" + position.getX() + ", " + position.getY() + ")";
	}
	
}