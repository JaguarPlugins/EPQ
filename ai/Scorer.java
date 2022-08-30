package edu.agray.maze.ai;

import java.util.ArrayList;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;

public class Scorer extends AI {

	private boolean deadEnd;
	
	public Scorer() {
		deadEnd = false;
	}

	@Override
	public void tick(Map map, Tile currentPosition) {
		
		int xPos = currentPosition.getX();
		int yPos = currentPosition.getY();

//		Generates options of where the turtle can move
		Tile[] options = generateOptions(map, currentPosition);
		
//		Dead end detection
		if (options.length < 2) {
			deadEnd = true;
		}
		
		if (deadEnd) {
			if (options.length > 1) {
				deadEnd = false;
			} else {
				map.getTile(xPos, yPos).setDeadEnd(true);
			}
			
		} else {
//			Increases the score of tiles that it has already passed to decrease the chance of going back there
			map.getTile(xPos, yPos).punish();
		}
		
	}
	
	@Override
	public Tile nextMove(Map map, Tile currentPosition) {
		
//		Generates options of where the turtle can move
		Tile[] options = generateOptions(map, currentPosition);
		
//		To enhance performance does not bother randomising choices if there is only 1 choice
		if (options.length < 2) {
			return options[0]; // selects the first (most favourable) position in the choice of options
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
		
		return equalOptions.get((int) (Math.random() * equalOptions.size())); 
		
	}
	
}
