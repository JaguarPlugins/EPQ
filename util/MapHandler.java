package edu.agray.maze.util;

import edu.agray.maze.map.Map;

public class MapHandler {

	private Map map;

	public MapHandler(Map map) {
		this.map = map;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
}
