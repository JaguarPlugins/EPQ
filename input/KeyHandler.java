package edu.agray.maze.input;

import java.util.ArrayList;

import edu.agray.maze.entities.Entity;
import edu.agray.maze.map.Map;
import edu.agray.maze.util.MapHandler;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler implements EventHandler<KeyEvent> {

	private ArrayList<Entity> entities;
	private MapHandler mapHandler;
	
	public KeyHandler(ArrayList<Entity> entities, MapHandler mapHandler) {
		this.entities = entities;
		this.mapHandler = mapHandler;
	}
	
	@Override
	public void handle(KeyEvent event) {
		
		if (event.getCode().equals(KeyCode.L)) {
			ChoiceDialog<String> dialog = new ChoiceDialog<String>();
			ObservableList<String> choices = dialog.getItems();
			choices.add("Blank 20x20");
			choices.addAll(Map.lookupMaps());
			dialog.showAndWait();
			String selected = dialog.getResult();
			Map newMap = new Map("maps\\" + selected);
			if (newMap != null) {
				mapHandler.setMap(newMap);
				entities.clear();
			}
			System.out.println("NEW MAP");
		}
		
		for (Entity entity : entities) {
			entity.handle(event);
		}
		
	}
	
}
