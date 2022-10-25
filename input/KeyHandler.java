package edu.agray.maze.input;

import java.util.ArrayList;

import edu.agray.maze.entities.Entity;
import edu.agray.maze.map.BlankMap;
import edu.agray.maze.map.Map;
import edu.agray.maze.util.MapHandler;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
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
			choices.add("BLANK 10x10");
			choices.add("BLANK 20x20");
			choices.add("BLANK 30x30");
			choices.add("BLANK 40x40");
			choices.add("BLANK 50x50");
			choices.add("BLANK 60x60");
			choices.addAll(Map.lookupMaps());
			dialog.showAndWait();
			String selected = dialog.getResult();
			Map newMap = new Map();
			
			if (newMap.loadMap("maps\\" + selected)) {
				mapHandler.setMap(newMap);
				entities.clear();
			} else if (selected.split(" ")[0].equalsIgnoreCase("BLANK")) {
				
				try {
					int width = Integer.parseInt(selected.split("x")[1]);
					newMap = new BlankMap(width, width);
					mapHandler.setMap(newMap);
					entities.clear();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
		if (event.getCode().equals(KeyCode.S)) {
			
			TextInputDialog textInputDialog = new TextInputDialog("Enter name of file");
			textInputDialog.showAndWait();
			String result = textInputDialog.getResult();
			if (result != null) {
				mapHandler.getMap().saveMap(result + ".txt");
			}
			
		}
		
		for (Entity entity : entities) {
			entity.handle(event);
		}
		
	}
	
}
