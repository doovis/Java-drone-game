package DroneSimulation;

import javafx.scene.image.Image;

public class Drone extends Entity {				// Drone class
	private Image drone4, drone3, drone2, drone1;

	/**
	 * Initialising images, x/y coords, and entity type
	 * @param X
	 * @param Y
	 * @param d
	 */
	Drone(double X, double Y) {
		super(X, Y, 'r');
		drone4 = new Image(getClass().getResourceAsStream("./img/drone4.png"));
		drone3 = new Image(getClass().getResourceAsStream("./img/drone3.png"));
		drone2 = new Image(getClass().getResourceAsStream("./img/drone2.png"));
		drone1 = new Image(getClass().getResourceAsStream("./img/drone1.png"));
	}
	
	/**
	 * Overloaded Drone constructor for file saving method
	 * @param X
	 * @param Y
	 * @param Angle
	 * @param Health
	 * @param ID
	 */
	Drone(double X, double Y, int Angle, int Health, int ID) {
		super(X, Y, Angle, Health, ID, 'r');
		drone4 = new Image(getClass().getResourceAsStream("./img/drone4.png"));
		drone3 = new Image(getClass().getResourceAsStream("./img/drone3.png"));
		drone2 = new Image(getClass().getResourceAsStream("./img/drone2.png"));
		drone1 = new Image(getClass().getResourceAsStream("./img/drone1.png"));
	}
	
	/**
	 * abstract method from player rendering drones using UICanvas
	 * @param c
	 */
	public Drone displayEntity(UICanvas c) {
		if (health == 4) {
			c.drawImage(drone4, x, y, entxSize, entySize);
		} else if (health == 3) {
			c.drawImage(drone3, x, y, entxSize, entySize);
		} else if (health == 2) {
			c.drawImage(drone2, x, y, entxSize, entySize);
		} else if (health == 1) {
			c.drawImage(drone1, x, y, entxSize, entySize);
		} else if (health < 1) {
			return this;
		}
		return null;
	}
	
	/**
	 * info about drone
	 */
	public String toString() {
		return "Drone " + "\t" + id + " is at " + (int)x + ", " + (int)y + " angle " + (int)(angle % 360) + ", health " + health;
	}
}
