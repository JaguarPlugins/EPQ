package edu.agray.maze.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile {

	private boolean solid;
	private double score;
	private double x, y, width, height;
	private Rectangle hitbox;
	
	public Tile(boolean solid, double x, double y, double width, double height, double score) {

		this.solid = solid; // determines whether or not the player can move through this tile
		this.score = score;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		hitbox = new Rectangle(x, y, width, height);
		
	}

	public void render(GraphicsContext g) {
		
		if (solid) {
			g.setFill(Color.BLACK); // Black indicates the block is solid
		} else {
			g.setFill(Color.rgb((int) (255*(1-score)), 255, (int) (255*(1-score)))); // will change the colour of the block depending on its score
		}
		
		g.fillRect(x, y, width, height);
		
	}

	public boolean checkCollision(double xPos, double yPos) {
		
//		Lets the AI know if it is about to move into a solid block
		if (hitbox.intersects(xPos, yPos, 1, 1)) {
			return true;
		}
		return false;
		
	}
	
}
