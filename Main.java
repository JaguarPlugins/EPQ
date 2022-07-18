package edu.agray.maze;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {

//	GLOBAL VARIABLES
	public static final int WIDTH = 800, HEIGHT = 800;
	
	private Thread t;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
	
//		Adding canvas to the stage to allow for rendering
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		GraphicsContext g = canvas.getGraphicsContext2D();
		Group placeholder = new Group(canvas);
		
//		Adding the runnable class to tick and render
		Run run = new Run(g);
		t = new Thread(run);
		t.start();
		
//		Setup for stage
		primaryStage.setScene(new Scene(placeholder));
		primaryStage.setTitle("Alex Gray EPQ Maze AI Program");
		primaryStage.setWidth(WIDTH + 16);
		primaryStage.setHeight(HEIGHT + 39);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(e -> {
			t.interrupt(); // terminates the thread when the window is closed
			run.interrupt(); // stops main loop from running
		});
		primaryStage.show();
		
	}

}
