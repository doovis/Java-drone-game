package DroneSimulation;

import javafx.scene.image.Image;

public class Obstacle extends Entity {
	private Image stone;

	/**
	 * Initialising image, x/y coords, speed, and entity type
	 * @param X
	 * @param Y
	 * @param d
	 */
	Obstacle(double X, double Y) {
		super(X, Y, 'o');
		this.dx = 0;
		this.dy = 0;
		stone = new Image(getClass().getResourceAsStream("./img/stone.png"));
	}

	
	/**
	 * Overloaded entity constructor for file saving method
	 * @param X
	 * @param Y
	 * @param Angle
	 * @param Health
	 * @param ID
	 */
	Obstacle(double X, double Y, int ID) {
		super(X, Y, 0, 0, ID, 'o');
		this.dx = 0;
		this.dy = 0;
		stone = new Image(getClass().getResourceAsStream("./img/stone.png"));
	}

	/**
	 * abstract method from entity for rendering using UICanvas
	 * @param c
	 */
	public Obstacle displayEntity(UICanvas c) {
		c.drawImage(stone, this.x, this.y, entxSize, entySize);
		return null;
	}
	
	/**
	 * info about obstacle
	 */
	public String toString() {
		return "Obstacle " + "\t" + id + " is at " + (int)x + ", " + (int)y;
	}
}
