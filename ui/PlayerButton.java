package edu.agray.maze.ui;

import edu.agray.maze.entities.Entity;
import edu.agray.maze.entities.Player;
import edu.agray.maze.map.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayerButton extends Button {

	public PlayerButton(Map map, double x, double y, double width, double height) {
		super(map, x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Entity click() {
		return new Player(map, map.getStartX(), map.getStartY(), map.getTileWidth(), map.getTileHeight());
	}

	@Override
	public void render(GraphicsContext g) {
		
		g.fillRoundRect(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight(), SMOOTH, SMOOTH);
		g.setFill(Color.WHITE);
		g.fillText("Player", hitbox.getX(), hitbox.getY());
		
	}

}
