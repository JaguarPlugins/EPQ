package edu.agray.maze.ai;

import java.util.ArrayList;
import java.util.Comparator;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;
import javafx.scene.canvas.GraphicsContext;

public abstract class AI {
	
	protected Tile[] generateOptions(Map map, Tile currentPosition) {
//		Takes all the tiles adjacent to the tile and returns a list of all the possible options
		
		int xPos = currentPosition.getX();
		int yPos = currentPosition.getY();

//		Generates options of where the turtle can move
		Tile[] tiles = {
				map.getTile(xPos, yPos - 1), 
				map.getTile(xPos + 1, yPos),
				map.getTile(xPos, yPos + 1),
				map.getTile(xPos - 1, yPos)};
		
		
		ArrayList<Tile> tileList = new ArrayList<Tile>();
		
		for (Tile t : tiles) {
			if (t != null) {
				if (!t.isSolid() && !t.isDeadEnd()) {
					tileList.add(t);
				}
			}
		}
		

//		Comparator sorts the list of objects based on 1 property (score) so that AI can choose the best tile to go to
		tileList.sort(new Comparator<Tile>() {

			@Override
			public int compare(Tile t1, Tile t2) {
				return Double.toString(t1.getScore()).compareTo(Double.toString(t2.getScore()));
			}
			
		});
		
//		Converts ArrayList to a basic Array
		Tile[] output = new Tile[tileList.size()];
		for (int i = 0; i < tileList.size(); i++) {
			output[i] = tileList.get(i);
		}
		
		return output;
			
	}
	
	public abstract Tile nextMove(Map map, Tile currentPosition);
	
	public abstract void tick(Map map, Tile currentPosition);
	
	public abstract void render(GraphicsContext g);
	
}
