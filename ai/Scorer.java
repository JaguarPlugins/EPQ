package edu.agray.maze.ai;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;

public class Scorer extends AI {

	@Override
	public Tile nextMove(Map map, Tile currentPosition) {
		
//		Logic
		
		
//		Moving the player
		int xPos = currentPosition.getX();
		int yPos = currentPosition.getY();
		
		map.getTile(xPos, yPos).punish();
		
		return generateOptions(map, 
				map.getTile(xPos, yPos - 1), 
				map.getTile(xPos + 1, yPos),
				map.getTile(xPos, yPos + 1),
				map.getTile(xPos - 1, yPos))
				[0]; // selects the first (most favourable position in the choice of options
		
	}
	
}
