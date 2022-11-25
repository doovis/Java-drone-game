package DroneSimulation;

import javafx.scene.canvas.GraphicsContext;
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
}
