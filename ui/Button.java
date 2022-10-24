package edu.agray.maze.ui;

import edu.agray.maze.entities.Entity;
import edu.agray.maze.util.MapHandler;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public abstract class Button {

	protected Rectangle hitbox;
	protected boolean hover;
	protected final static double BUFFER = 5, SMOOTH = 15;
	protected MapHandler mapHandler;
	private Entity entity;
	
	public Button(MapHandler mapHandler, double x, double y, double width, double height) {
		hitbox = new Rectangle(x + 4*BUFFER, y + BUFFER, width - 8*BUFFER, height - 2*BUFFER);
		this.mapHandler = mapHandler;
	}
	
	public abstract Entity click();
	
	public abstract void render(GraphicsContext g);
	
	public void handle(MouseEvent e) {
		
		hover = false;
		
		if (hitbox.intersects(e.getSceneX(), e.getSceneY(), 1, 1)) {
			
			if (e.getEventType().equals(MouseEvent.MOUSE_MOVED)) {
				hover = true;
			}
			
			if (e.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
				entity = click();
			}
			
		}
		
	}
	
	public Entity getEntity() {
		Entity temp = entity;
		entity = null;
		return temp;
	}
	
}