package edu.agray.maze.entities;

import java.util.ArrayList;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;
import edu.agray.maze.util.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Explorer extends Entity {

	private Direction direction;
	private Tile position;
	private ArrayList<Tile> path;
	
	public Explorer(Map map, Tile position, Direction direction) {
		
		super(map, position.getX(), position.getY(), position.getWidth(), position.getHeight());
		this.direction = direction;
		this.position = position;
		path = new ArrayList<Tile>();
		path.add(position);
		
	}
	
	public Explorer(Map map, Tile position, Direction direction, ArrayList<Tile> oldPath) {
		
		super(map, position.getX(), position.getY(), position.getWidth(), position.getHeight());
		this.direction = direction;
		this.position = position;
		path = oldPath;
		path.add(position);
		
	}

	public Explorer advance(Tile[] options, ArrayList<Explorer> scouts) {
		
//		Goes through options to see if any new scouts need to be created
		boolean canContinue = false;
		ArrayList<Tile> vertices = getVertices(position, options);
		for (Tile adjacent : vertices) {
			Direction newDirection = Direction.Calculate(position, adjacent);
			if (!newDirection.follows(direction.inverse())) {
				scouts.add(new Explorer(map, adjacent, newDirection, path));
			} else {
				position = adjacent;
				path.add(position);
				canContinue = true;
			}
		}
		
		if (!canContinue) {
			scouts.remove(this);
		}
		
		if (position.equals(map.getGoalTile())) {
			return this;
		}
		
		return null;
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GraphicsContext g) {
		g.setFill(Color.GREEN);
		g.fillOval(x*width, y*height, width, height);
	}

	private ArrayList<Tile> getVertices(Tile currentPosition, Tile[] options) {
		
//		This array will contain all the vertices that the explorer could move to by moving in a straight line
		ArrayList<Tile> vertices = new ArrayList<Tile>();
		for (Tile loopTile : options) {
			
			Tile focus = loopTile;
			Direction direction = Direction.Calculate(currentPosition, loopTile); // works out which direction the line of sight is in
			
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
	
	public Tile getPosition() {
		return position;
	}
	
	public ArrayList<Tile> getPath() {
		return path;
	}
	
}