package edu.agray.maze.map;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import edu.agray.maze.Main;
import edu.agray.maze.util.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Map {

	protected Tile[][] tiles; // NOTE: DUE TO READING OF FILES, X AND Y ARE THE WRONG WAY AROUND. WILL CORRECT IN GETTERS
	protected double tileWidth, tileHeight;
	protected int endX, endY;
	protected int startX, startY;
	protected boolean locked;
	
	public Map(String fileName, int startX, int startY, int endX, int endY) {
		
		tiles = loadMap(fileName);
		this.endX = endX;
		this.endY = endY;
		this.startX = startX;
		this.startY = startY;
		locked = true;
		
	}
	
	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public Map(int width, int height) {
		tiles = new Tile[width][height];
	}

	public void render(GraphicsContext g) {
		
		for (Tile[] rows : tiles) {
			for (Tile column : rows) {
				column.render(g);
			}
		}
		
	}
	
	public Tile getTile(int x, int y) {
		
		if (x < 0 || y < 0 || x >= tiles[0].length || y >= tiles.length) {
			return null;
		}
		return tiles[y][x];
		
	}
	
//	Left in just in case it is needed. Probably will not be
	public Tile getTile(Tile oldTile, Direction direction) {
		
		int y = oldTile.getY() + direction.getDy();
		int x = oldTile.getX() + direction.getDx();
		
		if (x < 0 || y < 0 || x >= tiles[0].length || y >= tiles.length) {
			return null;
		}
		
		return tiles[y][x];
		
	}
	
	public Tile getTile(double x, double y) {
		
		if (x < 0 || y < 0 || x >= Main.WIDTH || y >= Main.WIDTH) {
			return null;
		}
		return tiles[(int) (y/tileHeight)][(int) (x/tileWidth)];
		
	}
	
	public void setStartX(int startX) {
		if (!locked) {
			this.startX = startX;
		}
		
	}

	public void setStartY(int startY) {
		if (!locked) {
			this.startY = startY;
		}
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public boolean checkCollision(int xPos, int yPos) {
		
		// if player is outside the map
		if (xPos < 0 || yPos < 0) {
			return true;
		}
		
		if (xPos >= tiles[0].length || yPos >= tiles.length) {
			return true;
		}
		
		// if player is going to collide with a tile
		// divides and rounds to convert coords to grip position
		return (getTile(xPos, yPos).isSolid());
		
	}
	
	protected Tile[][] loadMap(String fileName) {
		
//		File loading often throws errors so we need to surround it with a try statement to avoid crashes
		try {
			
//			Opens the file and starts reading it
			File file = new File(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
//			ArrayList does not have a pre-specified length so can easily be added to regardless of length of file
			ArrayList<Tile[]> outputList = new ArrayList<Tile[]>();
			
//			Converts text file into array of tiles
			String line;
			int y = 0;
			while ((line = reader.readLine()) != null) { // saves the contents of each line to a variable
				
				char[] charList = line.toCharArray(); // turns line of text file into array
				Tile[] tileList = new Tile[line.length()]; // temporary array to store row of tiles
				
				for (int x = 0; x < line.length(); x++) { // iterates through array of tiles
					
//					The array for each individual line can be a default array as it is easy to calculate the length
					double width = Main.WIDTH/line.length(); // calculates width of the tile
//					height is also set to the width of the tile so that the maze still looks smart when not a square
					
					if (charList[x] == '2') {
//						Creates a tile with the property goal tile so the AI knows where to go
						tileList[x] = new Tile((charList[x] == '1'), x, y, width, width, true);
					} else {
//						Creates a regular tile that is either solid (1) or not empty (0)
						tileList[x] = new Tile((charList[x] == '1'), x, y, width, width);
					}
					
				}
				
//				Adds the row of tiles we have just created to the master list of rows
				outputList.add(tileList);
				
				y++;
				
			} 
			
			reader.close(); // Closes the file so the program is no longer looking at it
			
			tileHeight = Main.HEIGHT / outputList.size(); // calculates the width of each tile
			tileWidth = Main.WIDTH / outputList.get(0).length; // calculates the height of each tile
			
//			Converts the ArrayList into a normal array as these are easier to deal with
			Tile[][] outputArray = new Tile[outputList.size()][outputList.get(0).length];
			for (int i = 0; i < outputList.size(); i++) {
				outputArray[i] = outputList.get(i);
			}
			
			return outputArray;
			
//		Catch statements will send messages to the user if there are any errors
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Alert a = new Alert(AlertType.ERROR, "File could not be found");
			a.showAndWait();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			Alert a = new Alert(AlertType.ERROR, "I/O exception");
			a.showAndWait();
			return null;
		}
		
	}

	protected void saveMap(String fileName) {
		
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
			Alert a = new Alert(AlertType.ERROR, "Could not create file");
			a.showAndWait();
		}
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void reset() {
		
	}
	
	public double getTileWidth() {
		if (tileWidth < tileHeight) {
			return tileWidth;
		} 
		return tileHeight;
	}

	public double getTileHeight() {
		if (tileWidth < tileHeight) {
			return tileWidth;
		} 
		return tileHeight;
	}
	
	public int getMapTileWidth() {
		return tiles.length;
	}
	
	public int getMapTileHeight() {
		return tiles[0].length;
	}
	
	public Tile getGoalTile() {
		return tiles[endY][endX];
	}
	
	public Tile[][] getMasterArray(){
		return tiles;
	}
	
	public int getStartX() {
		return startX;
	}

	public int getStartY() {
		return startY;
	}
	
}
