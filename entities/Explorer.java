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
		
		Tile nextTile = map.getTile(position, direction); // works out the next tile THIS scout needs to move to
		
//		Goes through options to see if any new scouts need to be created
		for (Tile adjacent : options) {
			if (adjacent.equals(nextTile)) {
				output.add(new Explorer(map, adjacent, Direction.Calculate(position, adjacent)));
			}
		}
		
		if (nextTile != null && !nextTile.isSolid()) {
			position = map.getTile(position, direction);
			if (position.equals(map.getGoalTile())) {
				return null;
			}
		} else {
			scouts.remove(this);
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

	public Tile getPosition() {
		return position;
	}
	
}