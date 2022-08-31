package edu.agray.maze.entities;

import java.util.ArrayList;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;
import edu.agray.maze.util.Direction;
import javafx.scene.canvas.GraphicsContext;

public class Explorer extends Entity {

	private Direction direction;
	private Tile position;
	
	public Explorer(Map map, Tile position, Direction direction) {
		
		super(map, position.getX(), position.getY(), position.getWidth(), position.getHeight());
		this.direction = direction;
		this.position = position;
		
	}

	public ArrayList<Explorer> advance(Tile[] options, ArrayList<Explorer> scouts) {
		
		ArrayList<Explorer> output = new ArrayList<Explorer>(); //list to be returned
		
//		Goes through options to see if any new scouts need to be created
		boolean canContinue = false;
		for (Tile adjacent : getVertices(position, options)) {
			Direction newDirection = Direction.Calculate(position, adjacent);
			if (!newDirection.follows(direction.inverse())) {
				output.add(new Explorer(map, adjacent, newDirection));
			} else {
				position = adjacent;
				canContinue = true;
			}
		}
		
		if (!canContinue) {
			scouts.remove(this);
		}
		
		if (position.equals(map.getGoalTile())) {
			return null;
		}
		
		return output;
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(GraphicsContext g) {

	}

	private ArrayList<Tile> getVertices(Tile currentPosition, Tile[] options) {
		
//		This array will contain all the vertices that the explorer could move to by moving in a straight line
		ArrayList<Tile> vertices = new ArrayList<Tile>();
		for (Tile loopTile : options) {
			
			Tile focus = loopTile;
			Direction direction = Direction.Calculate(currentPosition, loopTile); // works out which direction the line of sight is in
			
			while (!map.getTile(focus, direction).isSolid()) {
				
				Tile next = map.getTile(focus, direction);
				if (next == null) {
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
	
}