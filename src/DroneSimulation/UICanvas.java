package DroneSimulation;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class UICanvas {
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
	public void drawImage(Image img, double x, double y, double width, double height) {
		gc.drawImage(img, x, y, width, height);
	}
	
	/**
	 * Dialog box
	 */
	public void dialogBox(String title, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);				// Instantiating dialog box
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();	
	}

	/**
	 * Drawing rectangle
	 * @param x
	 * @param y
	 * @param xSize
	 * @param ySize
	 * @param color
	 */
	public void drawOval(int x, int y, int xSize, int ySize, Color color) {
		gc.setFill(color);
		gc.fillOval(x, y, xSize, ySize);
	}
}
