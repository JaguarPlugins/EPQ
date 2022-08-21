package edu.agray.maze.entities;

import edu.agray.maze.Main;
import edu.agray.maze.ai.Scorer;
import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Turtle extends Entity {

	private Scorer aI;
	private boolean win;
	
	public Turtle(Map map, int x, int y, double width, double height) {
		super(map, x, y, width, height);
		aI = new Scorer();
		win = false;
	}

	@Override
	public void tick() {
		
		if (map.getTile(x, y).getScore() >= 1) {
			win = true;
			return;
		}
		Tile newTile = aI.nextMove(map, map.getTile(x, y));
		move(newTile.getX(), newTile.getY());
		
	}

	@Override
	public void render(GraphicsContext g) {
		
		if (win) {
//			Fills the window with green if the AI completes the maze
			g.setFill(Color.GREEN);
			g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		}
		g.setFill(Color.RED);
		g.fillOval(x*width, y*height, width, height);
		
	}

//	This is the entity controlled by the AI as opposed to the player

}
