package edu.agray.maze.ai;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;

public class AStar extends AI {
	
	private static final double MODIFYER = 10; // multiple by which times visited is adjusted
	private int lastUpdate = 100; // set to high value so will update at first tick
	
	public AStar() {
		
	}
	
	@Override
	public void tick(Map map, Tile currentPosition) {
		
//		Updates full map every so often
		if (lastUpdate > 20) {
			for (Tile[] row : map.getMasterArray()) {
				for (Tile column : row) {
					updateScore(map, currentPosition, column, map.getGoalTile());
				}
			}
			lastUpdate = 0;
		}
		
		Tile [] options = generateOptions(map, currentPosition);
		
		for (Tile tile : options) {
			updateScore(map, currentPosition, tile, map.getGoalTile());
		}
		
		lastUpdate++;
		
	}
	
	@Override
	public Tile nextMove(Map map, Tile currentPosition) {
		
		Tile[] options = generateOptions(map, currentPosition);
		
		return options[0];
	
	}
	
	private void updateScore(Map map, Tile start, Tile node, Tile target) {
		
		double score = manhatten(map, start, node) + manhatten(map, node, map.getGoalTile()) + MODIFYER * node.getTimesVisited();
		node.setScore(score);
	
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