package edu.agray.maze.ai;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;
import edu.agray.maze.util.Direction;
import edu.agray.maze.util.Explorer;
import javafx.scene.canvas.GraphicsContext;

public class Multi extends AI {

	private ArrayList<Explorer> scouts;
	private Queue<Tile> queue;
	private Tile backOfQueue;
	
	public Multi() {
		
		scouts = new ArrayList<Explorer>();
		queue = new LinkedList<Tile>();
		
	}
	
	@Override
	public void tick(Map map, Tile currentPosition) {
		
		if (queue.size() == 0) {
		
//			At the start, creates one explorer that will go on to make more explorers
			if (scouts.size() == 0) {

//				Works out all the junctions so that the explorers know where to go
				calculateJunctions(map);
//				Saves the starting tile
				backOfQueue = currentPosition;

//				Works out direction that first explorer must move in
				Tile firstStep = generateOptions(map, currentPosition)[0];
				scouts.add(new Explorer(map, currentPosition, Direction.Calculate(currentPosition, firstStep)));

			}

			ArrayList<Explorer> children = new ArrayList<Explorer>();

			for (Explorer scout : scouts) {
				children.addAll(scout.generateChildren(generateOptions(map, scout.getPosition())));
				if (children.size() == 1 && children.get(0).getPosition().isGoal()) {
//					Adds all the saved moves from the winning explorer to the queue
					System.out.println("Vertices: " + children.get(0).getPath());
					for (Tile vertex : children.get(0).getPath()) {
						trackRoute(map, backOfQueue, vertex);
						backOfQueue = vertex;
					}

					return;
				}
			}

			scouts = children;

			System.out.println(scouts.size());
			
		}
		
	}

	@Override
	public Tile nextMove(Map map, Tile currentPosition) {

		if (!queue.isEmpty()) {
			return queue.poll();
		}
		
		return null;
		
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
	
	private void trackRoute(Map map, Tile start, Tile end) {
		
//		Does not include start tile. Does include end tiles
		Direction direction = Direction.Calculate(start, end);
		Tile head = start;
		
		do {
			head = map.getTile(head, direction);
			queue.add(head);
		} while (!head.equals(end));
		
	}

	@Override
	public void render(GraphicsContext g) {

		for (Explorer scout : scouts) {
			scout.render(g);
		}

	}
	
}
