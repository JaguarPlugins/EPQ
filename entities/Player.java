package edu.agray.maze.entities;

import java.util.Arrays;

import edu.agray.maze.Main;
import edu.agray.maze.ai.Multi;
import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;
import edu.agray.maze.util.Direction;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Player extends Entity {
	
	private long time;
	private long startTime;
	private boolean win = false;
	
	public Player(Map map, int x, int y, double width, double height) {
		super(map, x, y, width, height);
		Multi multi = new Multi();
		multi.calculateJunctions(map);
		startTime = System.currentTimeMillis();
	}

	@Override
	public void tick() {
		
//		Calculation for timer
		if (!win) {
			time = System.currentTimeMillis() - startTime;
		}
		
		int newX = x, newY = y;
		Tile currentPosition = map.getTile(x, y);
		if (currentPosition.isGoal()) {
			win = true;
		}
		
		if (keys[0]) { //up
			newY = travel(currentPosition, Direction.UP).getY();
		}
		if (keys[1]) {// right
			newX = travel(currentPosition, Direction.RIGHT).getX();
		}
		if (keys[2]) {// down
			newY = travel(currentPosition, Direction.DOWN).getY();
		}
		if (keys[3]) {// left
			newX = travel(currentPosition, Direction.LEFT).getX();
		}
		
		if (!map.checkCollision(newX, newY)) {
			x = newX;
			y = newY;
		}
		
		Arrays.fill(keys, false); // resets the list of key strokes
		
	}
	
	@Override
	public void render(GraphicsContext g) {
		
		g.setFill(Color.BLUE);
		g.fillOval(x*width, y*height, width, height);
		
		g.setFill(Color.WHITE);
		g.setFont(new Font("calibri", 15));
		g.setTextAlign(TextAlignment.RIGHT);
		g.setTextBaseline(VPos.TOP);
		g.fillText("Time: " + (int) ((time/1000) / 60) + ":" + (int) Math.floorMod(time/1000, 60) + "." + (int) Math.floorMod(time, 1000),
				Main.WIDTH, 0);
		
	}
	
	private Tile travel(Tile startTile, Direction travelDirection) {
		
//		Returns the fist "interesting" tile that it reaches in that direction
//		Start tile is not included
		
		Tile focus = startTile;
		
		while (map.getTile(focus, travelDirection) != null) {
		
			Tile next = map.getTile(focus, travelDirection);
			if (next.isSolid()) {
				return focus;
			}
			if (next.isJunction()) {
				return next;
			}
			
			focus = next;
			
		}
		
		return focus;
		
	}
	
}
