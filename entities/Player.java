package edu.agray.maze.entities;

import java.util.Arrays;

import edu.agray.maze.map.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player extends Entity {
	
	public Player(Map map, double x, double y, double width, double height) {
		super(map, x, y, width, height);
	}

	@Override
	public void tick() {
		
		double newX = x, newY = y;
		
		if (keys[0]) { //up
			newY = y - height;
		}
		if (keys[1]) {// right
			newX = x + width;
		}
		if (keys[2]) {// down
			newY = y + height;
		}
		if (keys[3]) {// left
			newX = x - width;
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
		g.fillOval(x, y, width, height);
		
	}
	
}
