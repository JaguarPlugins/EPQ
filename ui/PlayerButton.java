package edu.agray.maze.ui;

import edu.agray.maze.entities.Entity;
import edu.agray.maze.entities.Player;
import edu.agray.maze.map.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
		
		g.setFill(Color.BLACK);
		if (hover) {
			g.setFill(Color.DARKGRAY);
		}
		g.fillRoundRect(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight(), SMOOTH, SMOOTH);
		g.setFill(Color.WHITE);
		g.setFont(new Font("calibri", 30));
		g.fillText("Player", hitbox.getX() + hitbox.getWidth()/2, hitbox.getY() + hitbox.getHeight()/2);
		
	}

}
