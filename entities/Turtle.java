package edu.agray.maze.entities;

import edu.agray.maze.ai.AI;
import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Turtle extends Entity {

	private AI main;
	private long startTime;
	private boolean win = false;
	private long time;
	
	public Turtle(Map map, AI aI, int x, int y, double width, double height) {
		super(map, x, y, width, height);
		main = aI;
		
//		Timer detection
		startTime = System.currentTimeMillis();
	}

	@Override
	public void tick() {
		
//		Calculation for timer
		if (!win) {
			time = System.currentTimeMillis() - startTime;
		}
	
//		Checks to see if the game is over
		if (map.getTile(x, y).isGoal()) {
		
			if (!win) {
//				Displays timer
				System.out.println("Time: " + (int) ((time/1000) / 60) + ":" + (int) Math.floorMod(time/1000, 60) + "." + (int) Math.floorMod(time, 1000));
				win = true;
				map.reset();
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
		
		g.setFill(Color.WHITE);
		g.setFont(new Font("calibri", 15));
		g.setTextAlign(TextAlignment.LEFT);
		g.setTextBaseline(VPos.TOP);
		g.fillText("Time: " + (int) ((time/1000) / 60) + ":" + (int) Math.floorMod(time/1000, 60) + "." + (int) Math.floorMod(time, 1000),
				0, 0);
		
		main.render(g);
		
	}

}
