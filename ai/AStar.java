package edu.agray.maze.ai;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;
import edu.agray.maze.util.Direction;

public class AStar extends AI {
	
	private Queue<Tile> queue;
	private Tile ghostPos;
	private Tile goal;
	
	public AStar(Map map, Tile startTile, Tile goal) {
		this.goal = goal;
		queue = new LinkedList<Tile>();
		ghostPos = startTile;
		calculateJunctions(map);
	}
	
	@Override
	public void tick(Map map, Tile currentPosition) {
		
		Tile[] options = generateOptions(map, ghostPos);
		
//		This array will contain all the vertices that the turtle could move to by moving in a straight line
		ArrayList<Tile> vertices = new ArrayList<Tile>();
		for (Tile loopTile : options) {
			
			Tile focus = loopTile;
			Direction direction = Direction.Calculate(ghostPos, loopTile); // works out which direction the line of sight is in
			
			while (map.getTile(focus, direction) != null) {
				
				Tile next = map.getTile(focus, direction);
				if (next.isSolid()) {
					break;
				}
				if (next.isJunction()) {
					vertices.add(next);
				}
				focus = next;
				
			}
			
			vertices.add(focus);
			
		}
		
		Tile bestTile = vertices.get(0);
		for (Tile tile : vertices) {
			double newCost = heuristic(map, tile, map.getGoalTile());
			double bestCost = heuristic(map, bestTile, map.getGoalTile());
			if (newCost < bestCost) {
				bestTile = tile;
			}
		}
		
		trackRoute(map, ghostPos, bestTile);
		ghostPos = bestTile;
		
	}
	
	@Override
	public Tile nextMove(Map map, Tile currentPosition) {
		
		Tile nextTile = queue.poll();
		return nextTile;
	
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
	
	private void trackRoute(Map map, Tile start, Tile end) {
//		Does not include start tile. Does include end tiles
		Direction direction = Direction.Calculate(start, end);
		Tile head = start;
		
		do {
			head = map.getTile(head, direction);
			queue.add(head);
		} while (!head.equals(end));
		
	}
	
	private void calculateJunctions(Map map) {
		
		for (Tile[] column : map.getMasterArray()) {
			for (Tile row : column) {
				if (generateOptions(map, row).length > 2) {
					row.setJunction(true);
				}
			}
		}
		
	}

}