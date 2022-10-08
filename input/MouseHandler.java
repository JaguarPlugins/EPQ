package edu.agray.maze.input;

import edu.agray.maze.ui.Button;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseHandler implements EventHandler<MouseEvent> {

	private Button[] buttons;
	
	public MouseHandler(Button[] buttons) {
		this.buttons = buttons;
	}

	@Override
	public void handle(MouseEvent event) {
		
		for (Button b : buttons) {
			b.handle(event);
		}
		
	}

}