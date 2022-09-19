package edu.agray.maze.ui;

import edu.agray.maze.ai.AStar;
import edu.agray.maze.entities.Entity;
import edu.agray.maze.entities.Turtle;
import edu.agray.maze.map.Map;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class AStarButton extends Button {

	public AStarButton(Map map, double x, double y, double width, double height) {
		super(map, x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Entity click() {
		return new Turtle(map, new AStar(), map.getStartX(), map.getStartY(), map.getTileWidth(), map.getTileHeight());

	}

	@Override
	public void render(GraphicsContext g) {
		
		g.setFill(Color.HOTPINK);
		if (hover) {
			g.setFill(Color.MEDIUMVIOLETRED);
		}
		g.fillRoundRect(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight(), SMOOTH, SMOOTH);
		g.setFill(Color.WHITE);
		g.setFont(new Font("calibri", 30));
		g.fillText("Astar", hitbox.getX(), hitbox.getY());
	}

}
