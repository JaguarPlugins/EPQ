package edu.agray.maze.map;

import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Tile {

	private boolean solid;
	private double score;
	private int x, y;
	private double width, height;
	private boolean isGoal;
	private boolean deadEnd = false;
	private int timesVisited = 0;

	public Tile(boolean solid, int x, int y, double width, double height, boolean isGoal) {

		this.solid = solid; // determines whether or not the player can move through this tile
		this.isGoal = isGoal;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}

	public Tile(boolean solid, int x, int y, double width, double height) {

		this.solid = solid; // determines whether or not the player can move through this tile
		if (solid) {
			score = Double.MAX_VALUE; // just in case the AI tries to move to the tile, it has the worst score possible
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
		} else if (deadEnd) {
			g.setFill(Color.DARKBLUE);
		} else {
			double saturation = score/260;
			if (saturation > 1) {
				saturation = 1;
			}
			g.setFill(Color.hsb(240.0, saturation, 1));
			
		}
		
		g.fillRect(x*width, y*width, width, height);
		
		if (!solid) {
			g.setFill(Color.BLACK);
			g.setTextAlign(TextAlignment.CENTER);
			g.setTextBaseline(VPos.CENTER);
			g.setFont(new Font("calibri", 9));
			g.fillText((int) score + "", x*width + width/2, y*height + height/2);
		}
		
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}
	
	public boolean isSolid() {
		return solid;
	}

	public boolean isGoal() {
		return isGoal;
	}
	
	public double getScore() {
		return score;
	}
	
	public void setScore(double score) {
		this.score = score;
	}
	
	public boolean isDeadEnd() {
		return deadEnd;
	}

	public void setDeadEnd(boolean deadEnd) {
		this.deadEnd = deadEnd;
	}
	
	public void visit() {
		timesVisited++;
	}
	
	public int getTimesVisited() {
		return timesVisited;
	}

	public void punish() {
		score = score*1.1;
	}
	
	public void reward() {
		score = score*0.9;
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
}
