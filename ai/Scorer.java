package edu.agray.maze.ai;

import java.util.ArrayList;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;
import javafx.scene.canvas.GraphicsContext;

public class Scorer extends AI {

	private boolean deadEnd;
	
	public Scorer() {
		deadEnd = false;
	}

	@Override
	public void tick(Map map, Tile currentPosition) {
		
//		Generates options of where the turtle can move
		Tile[] options = generateOptions(map, currentPosition);
		
//		Dead end detection
		if (options.length < 2) { // if the AI only has one tile it can move to, it is at a dead end
			deadEnd = true;
		}
		
		if (deadEnd) {
			if (options.length > 1) {
				deadEnd = false; // disables dead end mode once it has reached a cross-roads again
			} else {
				currentPosition.setDeadEnd(true); // marks current tile as a dead end
			}
			
		}
		
	}
	
	@Override
	public Tile nextMove(Map map, Tile currentPosition) {
		
//		Generates options of where the turtle can move
		Tile[] options = generateOptions(map, currentPosition);
		
//		To enhance performance does not bother randomising choices if there is only 1 choice
		if (options.length < 2) {
			return options[0];
		}
		
//		Putting all equally valuable tiles in to 1 list to avoid bias to top right
		ArrayList<Tile> equalOptions = new ArrayList<Tile>();
		double bestScore = options[0].getScore();
		for (Tile tile : options) {
			if (tile.getScore() == bestScore) {
				equalOptions.add(tile);
			} else {
				break;
			}
		}
		
//		Increases the score of tiles that it has already passed to decrease the chance of going back there
		currentPosition.punish();
//		Picks a random tile to move to out of the most favourable options
		return equalOptions.get((int) (Math.random() * equalOptions.size())); 
		
	}

	@Override
	public void render(GraphicsContext g) {
		// TODO Auto-generated method stub
		
	}
	
}
