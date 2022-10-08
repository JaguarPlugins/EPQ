package edu.agray.maze.input;

import java.util.ArrayList;

import edu.agray.maze.entities.Entity;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {

	private ArrayList<Entity> entities;
	
	public KeyHandler(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	@Override
	public void handle(KeyEvent event) {
		for (Entity entity : entities) {
			entity.handle(event);
		}
	}
	
}
