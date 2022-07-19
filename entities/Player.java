package edu.agray.maze.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Player {

	private double x, y, width, height;

	public Player(double x, double y, double width, double height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	
	}
	
	public void render(GraphicsContext g) {
		
		g.setFill(Color.BLUE);
		g.fillOval(x, y, width, height);
		
	}
	
}
