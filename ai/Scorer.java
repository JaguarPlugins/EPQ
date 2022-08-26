package edu.agray.maze.ai;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;

public class Scorer extends AI {

	private boolean deadEnd;
	
	public Scorer() {
		deadEnd = false;
	}

	@Override
	public Tile nextMove(Map map, Tile currentPosition) {
		
		int xPos = currentPosition.getX();
		int yPos = currentPosition.getY();

//		Generates options of where the turtle can move
		Tile[] options = generateOptions(map, 
				map.getTile(xPos, yPos - 1), 
				map.getTile(xPos + 1, yPos),
				map.getTile(xPos, yPos + 1),
				map.getTile(xPos - 1, yPos));
		
//		Dead end detection
		if (options.length < 2) {
			deadEnd = true;
		}
		
		if (deadEnd) {
			if (options.length > 1) {
//				System.out.println("DISABLING DEAD END");
				deadEnd = false;
			} else {
				map.getTile(xPos, yPos).setScore(0);
			}
			
		} else {
			map.getTile(xPos, yPos).punish();
		}
		
		return options[0]; // selects the first (most favourable position in the choice of options
		
	}
	
}
