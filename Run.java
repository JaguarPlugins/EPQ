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
	private long lastTime;
	
	public Run(GraphicsContext g) {
		
		super();
		this.g = g;
		
		map = new Map("src/edu/agray/maze/map/medium.txt", 37, 0);
		active = new Turtle(map, 0, 11, map.getTileWidth(), map.getTileHeight());
		lastTime = 0;
		
	}
	
	@Override
	public void handle(long now) {
		
		if (now - lastTime > 100_000_000) {
			tick();
			render();
			lastTime = now;
		}
		
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
