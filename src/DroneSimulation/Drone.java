package DroneSimulation;

import javafx.scene.image.Image;

public class Drone extends Player {				// Drone class
	Image drone4, drone3, drone2, drone1;

	/**
	 * Drone constructor initialising coordinates, deltas, 
	 * id and direction
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
	public Drone displayPlayer(UICanvas c) {
		if (this.health == 4) {
			c.drawImage(drone4, this.x, this.y, drnxSize, drnySize);
		} else if (this.health == 3) {
			c.drawImage(drone3, this.x, this.y, drnxSize, drnySize);
		} else if (this.health == 2) {
			c.drawImage(drone2, this.x, this.y, drnxSize, drnySize);
		} else if (this.health == 1) {
			c.drawImage(drone1, this.x, this.y, drnxSize, drnySize);
		} else if (this.health < 1) {
			return this;
		}
		return null;
	}
	
	/**
	 * info about drone
	 */
	public String toString() {
		return "Drone " + id + " is at " + (int)x + ", " + (int)y + ", angle " + (int)(angle % 360) + ", health " + health;
	}
}
