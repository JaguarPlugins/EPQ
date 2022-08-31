package edu.agray.maze.entities;

import edu.agray.maze.ai.AI;
import edu.agray.maze.ai.Multi;
import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Turtle extends Entity {

	private AI main;
	private long startTime;
	private boolean win = false;
	
	public Turtle(Map map, int x, int y, double width, double height) {
		super(map, x, y, width, height);
		main = new Multi();
		
//		Timer detection
		startTime = System.currentTimeMillis();
	}

	@Override
	public void tick() {
		
//		Checks to see if the game is over
		if (map.getTile(x, y).isGoal()) {
		
			if (!win) {
//				Displays timer
				long time = System.currentTimeMillis() - startTime;
				System.out.println("Time: " + (int) ((time/1000) / 60) + ":" + (int) Math.floorMod(time/1000, 60) + "." + (int) Math.floorMod(time, 1000));
				win = true;
			}
			
			return;
			
		}
		
//		AI Logic
		main.tick(map, map.getTile(x, y));
		
//		Moving to new position
		Tile newTile = main.nextMove(map, map.getTile(x, y));
		if (newTile != null) {
			move(newTile.getX(), newTile.getY());
			newTile.visit();
		}
		
	}

	@Override
	public void render(GraphicsContext g) {
		
		g.setFill(Color.RED);
		g.fillOval(x*width, y*height, width, height);
		
	}

}
