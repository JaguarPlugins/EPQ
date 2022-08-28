package edu.agray.maze.map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Tile {

	private boolean solid;
	private double score;
	private int x, y;
	private double width, height;
	private boolean junction = false;

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
			score = 0.7; // we will start off all tiles as having a score of 0.5 and then adjust those values as the player moves
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

	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	public boolean isJunction() {
		return junction;
	}

	public void setJunction(boolean junction) {
		this.junction = junction;
	}

	public void punish() {
		score = score*0.9;
		if (score < 0) {
			score = 0;
		}
	}
	
	public void reward() {
		score = score*1.1;
		if (score > 1) {
			score = 1;
		}
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
}
