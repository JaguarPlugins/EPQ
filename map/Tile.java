package edu.agray.maze.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tile {

	public double getScore() {
		return score;
	}

	private boolean solid;
	private double score;
	private int x, y;
	private double width, height;
	
	public Tile(boolean solid, int x, int y, double width, double height, double score) {

		this.solid = solid; // determines whether or not the player can move through this tile
		this.score = score;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	public Tile(boolean solid, int x, int y, double width, double height) {

		this.solid = solid; // determines whether or not the player can move through this tile
		if (solid) {
			score = 0; // solid blocks have a score of 0 as the AI will never move there
		} else {
			score = 0.5; // we will start off all tiles as having a score of 0.5 and then adjust those values as the player moves
		}
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}

	public void render(GraphicsContext g) {
		
		if (solid) {
			g.setFill(Color.BLACK); // Black indicates the block is solid
		} else {
			g.setFill(Color.rgb((int) (255*(1-score)), (int) (255*(1-score)), 255)); // will change the colour of the block depending on its score
		}
		
		g.fillRect(x*width, y*width, width, height);
		
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isSolid() {
		return solid;
	}

	@Override
	public String toString() {
		if (solid) {
			return "1";
		}
		return "0";
	}
	
}
