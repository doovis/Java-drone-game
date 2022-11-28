package DroneSimulation;

import javafx.scene.image.Image;

public class Obstacle extends Entity {
	Image stone;

	
	/**
	 * Drone constructor initialising coordinates, deltas, 
	 * id and direction
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
	 * Overloaded Drone constructor for file saving method
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
	 * abstract method from player rendering drones using UICanvas
	 * @param c
	 */
	public Obstacle displayPlayer(UICanvas c) {
		c.drawImage(stone, this.x, this.y, entxSize, entySize);
		return null;
	}
	
	/**
	 * info about drone
	 */
	public String toString() {
		return "Obstacle " + "\t" + id + " is at " + (int)x + ", " + (int)y;
	}
}
