package edu.agray.maze.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.agray.maze.Main;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Map {

	private Tile[][] tiles; // NOTE: DUE TO READING OF FILES, X AND Y ARE THE WRONG WAY AROUND. WILL CORRECT IN GETTERS
	
	public Map(String fileName) {
		
		tiles = loadMap(fileName);
		
	}
	
	public Tile getTile(int x, int y) {
		
		return tiles[y][x];
		
	}
	
	private Tile[][] loadMap(String fileName) {
		
		try {
			
//			Opens the file and starts reading it
			File file = new File(fileName);
			BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			
			ArrayList<Tile[]> outputList = new ArrayList<Tile[]>();
			
//			Converts text file into array of tiles
			String line;
			int y = 0;
			while ((line = reader.readLine()) != null) {
				
				char[] charList = line.toCharArray(); // turns line of txt file into array
				Tile[] tileList = new Tile[line.length()]; // temporary array to store row of tiles
				
				for (int x = 0; x < line.length(); x++) { // iterates through array of tiles
					
					double width = Main.WIDTH/line.length();
//					TODO generate height separately for when it isn't a square.
					double xPos = x*width;
					double yPos = y*width;
					
					tileList[x] = new Tile((charList[x] == '1'), xPos, yPos, width, width, 0);
					
				}
				
				outputList.add(tileList);
				
				y++;
				
			} 
			
			reader.close();
			
			Tile[][] outputArray = new Tile[outputList.size()][outputList.get(0).length];
			for (int i = 0; i < outputList.size(); i++) {
				outputArray[i] = outputList.get(i);
			}
			
			return outputArray;
			
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
	
}
