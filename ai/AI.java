package edu.agray.maze.ai;

import java.util.ArrayList;
import java.util.Comparator;

import edu.agray.maze.map.Map;
import edu.agray.maze.map.Tile;

public abstract class AI {
	
	protected Tile[] generateOptions(Map map, Tile up, Tile right, Tile down, Tile left) {
//		Takes all the tiles adjacent to the tile and returns a list of all the possible options
		
//		System.out.println("Options:");
		
		Tile[] tiles = {up, right, down, left};
		
		ArrayList<Tile> tileList = new ArrayList<Tile>();
		
		for (Tile t : tiles) {
			if (t != null) {
				if (!t.isSolid() && t.getScore() > 0) {
					tileList.add(t);
				}
			}
		}
		

//		Comparator sorts the list of objects based on 1 property (score) so that AI can choose the best tile to go to
		tileList.sort(new Comparator<Tile>() {

			@Override
			public int compare(Tile t1, Tile t2) {
				return -Double.toString(t1.getScore()).compareTo(Double.toString(t2.getScore()));
			}
			
		});
		
//		Converts ArrayList to a basic Array
		Tile[] output = new Tile[tileList.size()];
		for (int i = 0; i < tileList.size(); i++) {
			output[i] = tileList.get(i);
		}
		
//		Debug printing
//		for (Tile t : output) {
////			System.out.println("(" + t.getX() + ", " + t.getY() + ")");
//		}
		
		return output;
			
	}
	
	public abstract Tile nextMove(Map map, Tile currentPosition);
	
}
