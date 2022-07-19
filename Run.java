package edu.agray.maze;

import edu.agray.maze.entities.Player;
import edu.agray.maze.map.Map;
import javafx.scene.canvas.GraphicsContext;

public class Run implements Runnable {

	private GraphicsContext g;
	private boolean running;
	private Map map;
	private Player player;
	
	public Run(GraphicsContext g) {
		
		super();
		this.g = g;
		running = true;
		
		map = new Map("src/edu/agray/maze/map/small.txt");
		player = new Player(40, 40, map.getTileWidth() - 5, map.getTileHeight() - 5);
		
	}
	
	private void tick() {
		
	}
	
	private void render() {
		
		g.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
		
		map.render(g);
		player.render(g);
		
	}
	
	public void interrupt() {
		
		running = false;
		
	}
	
	@Override
	public void run() {
		
//		Keeps the system running at 60 fps
		int fps = 30;
		double timePerTick = 1_000_000_000 / fps;
		double delta = 0;
		long currentTime;
		long previousTime = System.nanoTime();
		long timer = 0;

		while(running) {
			
			currentTime = System.nanoTime();
			delta += (currentTime - previousTime) / timePerTick;
			timer += currentTime - previousTime;
			previousTime = currentTime;
			
			if(delta > 1) {
				tick();
				render();
				delta --;
			}
			
			if(timer >= 1_000_000_000) {
				timer = 0;
			}
			
		}
		
	}

}
