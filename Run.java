package edu.agray.maze;

import edu.agray.maze.entities.Entity;
import edu.agray.maze.entities.Turtle;
import edu.agray.maze.map.Map;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class Run extends AnimationTimer {

	private GraphicsContext g;
	private Map map;
	private Entity active;
	
	public Run(GraphicsContext g) {
		
		super();
		this.g = g;
		
		map = new Map("src/edu/agray/maze/map/medium.txt");
		active = new Turtle(map, 0, 3, map.getTileWidth(), map.getTileHeight());
		
	}
	
	@Override
	public void handle(long now) {
		
		tick();
		render();
		
	}

	private void tick() {
		
		active.tick();
		
	}
	
	private void render() {
		
		g.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
		
		map.render(g);
		active.render(g);
		
	}

}
