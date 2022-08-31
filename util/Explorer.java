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
		path = oldPath;
		path.add(position);
		
	}

	public ArrayList<Explorer> generateChildren(Tile[] options) {
		
		ArrayList<Explorer> output = new ArrayList<Explorer>(); // list to be returned
		
//		Goes through options to see if any new scouts need to be created
		ArrayList<Tile> vertices = getVertices(position, options);
		System.out.println("Vertices: " + vertices);
		for (Tile adjacent : vertices) {
			
			Direction newDirection = Direction.Calculate(position, adjacent);
			if (!newDirection.follows(direction.inverse())) {
				output.add(new Explorer(map, adjacent, newDirection, path));
			}
			
		}

		if (position.equals(map.getGoalTile())) {
//			TODO work out what to do when reaching goal
		}
		
		return output;
		
	}

	private ArrayList<Tile> getVertices(Tile currentPosition, Tile[] options) {
		
//		This array will contain all the vertices that the explorer could move to by moving in a straight line
		ArrayList<Tile> vertices = new ArrayList<Tile>();
		for (Tile loopTile : options) {
			
			Tile focus = loopTile;
			Direction direction = Direction.Calculate(currentPosition, loopTile); // works out which direction the line of sight is in
			
			if (focus.isJunction()) {
				vertices.add(focus);
			}
			
			while (map.getTile(focus, direction) != null) {
				
				Tile next = map.getTile(focus, direction);
				if (next.isSolid()) {
					break;
				}
				if (next.isJunction()) {
					vertices.add(next);
				}
				focus = next;
				
			}
			
			vertices.add(focus);
			
		}
		
		return vertices;
		
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