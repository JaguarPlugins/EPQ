package edu.agray.maze;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Run implements Runnable {

	private GraphicsContext g;
	private boolean running;
	
	public Run(GraphicsContext g) {
		super();
		this.g = g;
		running = true;
	}
	
	private void tick() {
		
	}
	
	private void render() {
		
		g.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g.setStroke(Color.GREY);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g.setStroke(Color.RED);
		g.fillRect(50, 50, 100, 100);
		
	}
	
	public void interrupt() {
		
		running = false;
		
	}
	
	@Override
	public void run() {
		
//		Keeps the system running at 60 fps
		int fps = 60;
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
