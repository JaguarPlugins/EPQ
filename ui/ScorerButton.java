package edu.agray.maze.ui;

import edu.agray.maze.ai.Scorer;
import edu.agray.maze.entities.Entity;
import edu.agray.maze.entities.Turtle;
import edu.agray.maze.map.Map;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class ScorerButton extends Button {

	public ScorerButton(Map map, double x, double y, double width, double height) {
		super(map, x, y, width, height);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Entity click() {
		return new Turtle(map, new Scorer(), map.getStartX(), map.getStartY(), map.getTileWidth(), map.getTileHeight());
	}

	@Override
	public void render(GraphicsContext g) {
		
		g.setFill(Color.CORNFLOWERBLUE.darker());
		if (hover) {
			g.setFill(Color.CORNFLOWERBLUE);
		}
		g.fillRoundRect(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight(), SMOOTH, SMOOTH);
		g.setFill(Color.WHITE);
		g.setFont(new Font("calibri", 30));
		g.setTextAlign(TextAlignment.CENTER);
		g.setTextBaseline(VPos.CENTER);
		g.fillText("Scorer", hitbox.getX() + hitbox.getWidth()/2, hitbox.getY() + hitbox.getHeight()/2);
		
	}

}
