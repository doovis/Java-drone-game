package DroneSimulation;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class UICanvas extends Application {
	int canvasWidth = 512;
	int canvasHeight = 512;
	GraphicsContext gc;
	
	UICanvas(GraphicsContext GC, int w, int h) {
		gc = GC;
		canvasWidth = w;
		canvasHeight = h;
	}
	
	/**
	 * Clearing graphics canvas
	 */
	public void clearCanvas() {
		gc.clearRect(0, 0, canvasWidth, canvasHeight);
	}
	
	/**
	 * Drawing an image in x,y location w,h size
	 * @param img
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public void drawImage(Image img, double angle, double x, double y, double width, double height) {
//		gc.save();
//		Rotate r = new Rotate(22, x, y);
//		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
		gc.drawImage(img, x, y, width, height);
//		gc.restore();
	}

	/**
	 * Drawing rectangle
	 * @param x
	 * @param y
	 * @param xSize
	 * @param ySize
	 * @param color
	 */
	public void drawRectangle(int x, int y, int xSize, int ySize, Color color) {
		gc.setFill(color);
		gc.fillRect(x, y, xSize, ySize);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Group root = new Group();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		
//		Button btn = new Button();
//		btn.setText("button");
//		btn.getTranslateX();
		Canvas canvas = new Canvas(300, 300);
		gc = canvas.getGraphicsContext2D();
		this.drawRectangle(20, 20, 50, 50, Color.BLUE);
//		gc.beginPath();
//		gc.moveTo(10, 10);
//		gc.lineTo(10, 100);
//		gc.lineTo(100, 100);
//		gc.lineTo(10, 100);
//		gc.stroke();
		
		
		root.getChildren().add(canvas);
//		root.getChildren().add(btn);
		
		
		stage.setTitle("Title");
		stage.show();
		
//		gc.fillRect(0, 60, canvasWidth, canvasHeight);
	}
	
}
