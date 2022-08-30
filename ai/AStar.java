package edu.agray.maze.ai;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;

public class AStar extends AI {
	
	private Tile goal;
	
	public AStar(Map map, Tile goal) {
		this.goal = goal;
	}
	
	@Override
	public void tick(Map map, Tile currentPosition) {
		
		
		
	}
	
	@Override
	public Tile nextMove(Map map, Tile currentPosition) {
		
		return null;
	
	}
	
	private double heuristic(Map map, Tile node, Tile goal) {
		
		int dx = Math.abs(node.getX() - goal.getX());
		int dy = Math.abs(node.getY() - goal.getY());
		
		return dx + dy;
		
//		CODE FOR USING HIGHER SCORE AND MORE FAVOURABLE
//		When doing the setups for the tile scoring system I did not realise that in AStar the lowest score is the best value,
//		Doing 1 - the fraction converts it into the style of my tile structure
//		return 1 - (1/(map.getMapTileWidth() + map.getMapTileHeight())) * (dx + dy);
//		As a side effect of this reciprocation, the score will have a greater effect the close the AI gets to the target
		
	}

}