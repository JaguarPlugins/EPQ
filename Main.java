package edu.agray.maze;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {

//	GLOBAL VARIABLES
	public static final double WIDTH = 800, HEIGHT = 800;
	
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
		run.start();
		
//		Create scene and add listeners
		Scene scene = new Scene(placeholder);
//		scene.setOnKeyReleased(run.getActive()); // player now listens for key presses to move
//		NOTE: IF NEEDED, CO-ORDINATES CAN EASILY BE DISPLAYED
//		scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent e) {
//				
//				System.out.println("(" + e.getX() + "," + e.getY() + ")");
//				
//			}
//			
//		});
		
//		Setup for stage
		primaryStage.setScene(scene);
		primaryStage.setTitle("Alex Gray EPQ Maze AI Program");
		primaryStage.setWidth(WIDTH + 16);
		primaryStage.setHeight(HEIGHT + 39);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(e -> {
			run.stop();
		});
		primaryStage.show();
		
	}

}
