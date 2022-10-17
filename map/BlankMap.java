package edu.agray.maze.map;

import edu.agray.maze.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class BlankMap extends Map {

	private int mapSizeWidth, mapSizeHeight;
	
	public BlankMap(int width, int height) {
		super(width, height);
		mapSizeWidth = width;
		mapSizeHeight = height;
		tileWidth = Main.WIDTH / width;
		tileHeight = Main.WIDTH / height;
		locked = false;
		
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[y][x] = new Tile(false, x, y, Main.WIDTH / width, Main.HEIGHT / height);
			}
		}
	}
	
	@Override
	public void render(GraphicsContext g) {
		
		for (Tile[] rows : tiles) {
			for (Tile column : rows) {
				column.render(g);
			}
		}
		
		for (int x = 0; x < mapSizeWidth; x++) {
			g.setFill(Color.BLACK);
			g.fillRect(x*tileWidth, 0, 1, Main.WIDTH);
		}

		for (int y = 0; y < mapSizeHeight; y++) {
			g.setFill(Color.BLACK);
			g.fillRect(0, y*tileWidth, Main.WIDTH, 1);
		}
		
	}

}