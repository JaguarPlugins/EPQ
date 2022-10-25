package edu.agray.maze.ai;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;
import javafx.scene.canvas.GraphicsContext;

public class AStar extends AI {
	
	private static final double MODIFYER = 10; // multiple by which times visited is adjusted
	private int lastUpdate = 100; // set to high value so will update at first tick
	
	public AStar() {
		
	}
	
	@Override
	public void tick(Map map, Tile currentPosition) {
		
//		Updates full map every so often so user can see all scores
		if (lastUpdate > 20) {
			for (Tile[] row : map.getMasterArray()) { // loops through full array
				for (Tile column : row) {
					updateScore(map, currentPosition, column, map.getGoalTile());
				}
			}
			lastUpdate = 0; // keeps track of number of ticks since last update
		}
		
//		Gets all the possible tiles that the AI can currently move to
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
		
//		Uses modifier to stop AStar from revisiting old tiles
		double score = 
					manhatten(map, start, node) // distance to node
					+ manhatten(map, node, map.getGoalTile()) // distance from node to target
					+ MODIFYER * node.getTimesVisited(); // discourages doubling back
		node.setScore(score);
	
	}
	
	private double manhatten(Map map, Tile start, Tile end) {
//		Calculates the Manhattan distance between 2 points
		
		int dx = Math.abs(start.getX() - end.getX());
		int dy = Math.abs(start.getY() - end.getY());
		
		return dx + dy;
		
	}

	@Override
	public void render(GraphicsContext g) {
		// TODO Auto-generated method stub
		
	}

}