package edu.agray.maze.ui;

import edu.agray.maze.ai.Multi;
import edu.agray.maze.entities.Entity;
import edu.agray.maze.entities.Turtle;
import edu.agray.maze.map.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class MultiButton extends Button {

	public MultiButton(Map map, double x, double y, double width, double height) {
		super(map, x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Entity click() {
		return new Turtle(map, new Multi(), map.getStartX(), map.getStartY(), map.getTileWidth(), map.getTileHeight());
	}

	@Override
	public void render(GraphicsContext g) {
		
		g.setFill(Color.DARKGREEN);
		if (hover) {
			g.setFill(Color.GREEN);
		}
		g.fillRoundRect(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight(), SMOOTH, SMOOTH);
		g.setFill(Color.WHITE);
		g.setFont(new Font("calibri", 30));
		g.fillText("Multi", hitbox.getX() + hitbox.getWidth()/2, hitbox.getY() + hitbox.getHeight()/2);
		
	}

}
