package edu.agray.maze.input;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;
import edu.agray.maze.ui.Button;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseHandler implements EventHandler<MouseEvent> {

	private Button[] buttons;
	private Map map;
	private boolean mouseHeld;
	private Tile oldTile;
	
	public MouseHandler(Button[] buttons, Map map) {
		this.buttons = buttons;
		this.map = map;
		mouseHeld = false;
	}

	@Override
	public void handle(MouseEvent event) {
		
		if (!map.isLocked()) {

			if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
				mouseHeld = true;
			} 
			if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
				mouseHeld = false;
			}
			
			
			Tile target = map.getTile((int) (event.getX()/map.getTileWidth()), (int) (event.getY()/map.getTileHeight()));
			if (target != null) {
				if (mouseHeld && !target.equals(oldTile)) {
					if (event.getEventType().equals(MouseEvent.MOUSE_DRAGGED)) {
						target.setSolid(!oldTile.isSolid());
					}
					target.setSolid(!target.isSolid());
					oldTile = target;
				}
				
			}

		}
		
		for (Button b : buttons) {
			b.handle(event);
		}
		
	}

}