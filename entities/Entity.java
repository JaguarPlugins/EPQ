package edu.agray.maze.entities;

import edu.agray.maze.map.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public abstract class Entity {

	protected int x, y;
	protected double width, height;
	protected boolean[] keys = {false, false, false, false};
	protected Map map;
	
	public Entity(Map map, int x, int y, double width, double height) {
		
		this.map = map;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	public abstract void tick();
	
	public abstract void render(GraphicsContext g);
	
	protected boolean move(int xPos, int yPos) {
		
		if (!map.checkCollision(xPos, yPos)) {
			x = xPos;
			y = yPos;
			return true;
		}
		
		System.out.println("collion detected at: " + xPos + ", " + yPos);
		
		return false; // blocks the movement and then sends false to let the AI know that this move is not allowed
		
	}
	
	public void handle(KeyEvent e){
	
		// sets an array to which keys are pressed so that movement can be handled uniformly in the tick method
		if (e.getCode().equals(KeyCode.UP)) {
			keys[0] = e.getEventType().equals(KeyEvent.KEY_PRESSED);
		}
		if (e.getCode().equals(KeyCode.RIGHT)) {
			keys[1] = e.getEventType().equals(KeyEvent.KEY_PRESSED);
		}
		if (e.getCode().equals(KeyCode.DOWN)) {
			keys[2] = e.getEventType().equals(KeyEvent.KEY_PRESSED);
		}
		if (e.getCode().equals(KeyCode.LEFT)) {
			keys[3] = e.getEventType().equals(KeyEvent.KEY_PRESSED);
		}

	}
	
}
