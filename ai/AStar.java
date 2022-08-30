package edu.agray.maze.ai;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;

public class AStar extends AI {
	
	public AStar(Map map, Tile startTile) {
		
		for (Tile[] row : map.getMasterArray()) {
			for (Tile column : row) {
				column.setScore(manhatten(map, startTile, column) + manhatten(map, column, map.getGoalTile()));
			}
		}
		
	}
	
	@Override
	public void tick(Map map, Tile currentPosition) {
		
		Tile [] options = generateOptions(map, currentPosition);
		
		for (Tile tile : options) {
			tile.setScore(1 + manhatten(map, tile, map.getGoalTile()));
		}
		
	}
	
	@Override
	public Tile nextMove(Map map, Tile currentPosition) {
		
		Tile[] options = generateOptions(map, currentPosition);
		
		return options[0];
	
	}
	
	private double manhatten(Map map, Tile start, Tile end) {
		
		int dx = Math.abs(start.getX() - end.getX());
		int dy = Math.abs(start.getY() - end.getY());
		
		return dx + dy;
		
//		CODE FOR USING HIGHER SCORE AND MORE FAVOURABLE
//		When doing the setups for the tile scoring system I did not realise that in AStar the lowest score is the best value,
//		Doing 1 - the fraction converts it into the style of my tile structure
//		return 1 - (1/(map.getMapTileWidth() + map.getMapTileHeight())) * (dx + dy);
//		As a side effect of this reciprocation, the score will have a greater effect the close the AI gets to the target
		
	}

}