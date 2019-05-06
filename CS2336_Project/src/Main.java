/* 		CS 2336 Group Project
 * 		This class was created by (Multiple Contributers)
 */

import java.util.ArrayList;
import java.lang.Math;
import javafx.application.Application;
import javafx.stage.Stage; 
import javafx.scene.shape.Circle; 
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color; 
import javafx.scene.Scene; 
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

public class Main extends Application{
	ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
	
	public static void main(String[] args) {
	  launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		VBox root = new VBox();
		
		  Pane pane = new Pane();
		  BorderPane uiPane = new BorderPane();
		  Pane instructions = new Pane();
		  
		  Rectangle instructionRect = new Rectangle(200, 100);
		  //instructionRect.setX(50);
		  //instructionRect.setY(50);
		  instructionRect.setStroke(Color.BLACK);
		  instructionRect.setFill(Color.WHITE);
		  
		  Label iLabel = new Label("INSTRUCTIONS\nAdd:\t\t\tLeft Click\n"
		  		+ "Move:\t\tCtrl Drag\nConnect:\t\tDrag\nRemove:\t\tRight Click");
		  
		  instructions.getChildren().addAll(instructionRect, iLabel);
		  
		  StackPane findSP = new StackPane();
		  
		  Rectangle fspRect = new Rectangle(300, 75);
		  fspRect.setStroke(Color.BLACK);
		  fspRect.setFill(Color.WHITE);
		  
		  TextField sVertex = new TextField();
		  TextField eVertex = new TextField();
		  
		  Button showSP = new Button("Show Shortest Path");
		  
		  findSP.getChildren().addAll(new Label("Find a shortest path\n"), new Label("Starting vertex: "),
				  sVertex, new Label("Ending vertex: "), eVertex, showSP);
	  
		  uiPane.getChildren().addAll(instructions, findSP);
		  
		  root.getChildren().addAll(uiPane, pane);
		  
		  
		  Scene scene = new Scene(root, 750, 500);

	  primaryStage.setScene(scene);
	  scene.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	      @Override
	      public void handle(MouseEvent event) {
	          if (event.getButton() != MouseButton.PRIMARY)
	         	   return;
	          Circle addVertex = createVertex(event);
	          if (addVertex != null) {
	              pane.getChildren().add(addVertex);
	              addVertex.addEventHandler(MouseEvent.MOUSE_CLICKED,  new EventHandler<MouseEvent>() {
	                  @Override
	         	      public void handle(MouseEvent event2) {
	         	          if (event2.getButton() != MouseButton.SECONDARY)
	         	              return;
	         	          int place = inVertex(event2);
	         	          if (place >= 0) {
	         	              vertexList.remove(place);
	         	              pane.getChildren().remove(addVertex);
	         	          }
	         	      }
	              });
	          }
	      }
	  });
	  primaryStage.show();
	}

	public Circle createVertex(MouseEvent event) {
		if (inVertex(event) == -1) {
			vertexList.add(new Vertex((int)event.getSceneX(), (int)event.getSceneY(), vertexList.size())); // Please take a look at this line, it fixed it temporarily by casting it to an int, however might be a better solution
		 	Circle circle = new Circle(event.getSceneX(), event.getSceneY(), 20);
		 	circle.setStroke(Color.BLACK); 
		 	circle.setFill(Color.WHITE);
		 	return circle;
		}
		return null;
	}

	public int inVertex(MouseEvent event) {
		if (vertexList.size() == 0)
			return -1;
		else {
			int check = 0;
		      	int hold = -1;
		      	while (check < vertexList.size()) {
		        	double distance = Math.hypot(event.getSceneX()-vertexList.get(check).xLocation, event.getSceneY()-vertexList.get(check).yLocation);
		          	if (distance <= 20)
		             		return check;
		          	else if (distance < 40)
		             		hold = check;
		          	check++;
		   	}
		   	if (hold > -1)
		      		return -2;
		   	return -1;
		}
	}
	
	public double getDistance(Vertex a, Vertex b) {
		double xDiff = Math.abs(a.xLocation - b.xLocation);
		double yDiff = Math.abs(a.yLocation - b.yLocation);
		double xDiffSQ = xDiff*xDiff;
		double yDiffSQ = yDiff*yDiff;
		
		double ans = Math.sqrt(xDiffSQ+yDiffSQ);
		return ans;
	}
}