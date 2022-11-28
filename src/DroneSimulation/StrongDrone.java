package DroneSimulation;

import javafx.scene.image.Image;

public class StrongDrone extends Entity {
	Image drone4, drone3, drone2, drone1;

	
	/**
	 * Drone constructor initialising coordinates, deltas, 
	 * id and direction
	 * @param X
	 * @param Y
	 * @param d
	 */
	StrongDrone(double X, double Y) {
		super(X, Y, 's');
		drone4 = new Image(getClass().getResourceAsStream("./img/strongDrone4.png"));
		drone3 = new Image(getClass().getResourceAsStream("./img/strongDrone3.png"));
		drone2 = new Image(getClass().getResourceAsStream("./img/strongDrone2.png"));
		drone1 = new Image(getClass().getResourceAsStream("./img/strongDrone1.png"));
	}

	
	/**
	 * Overloaded Drone constructor for file saving method
	 * @param X
	 * @param Y
	 * @param Angle
	 * @param Health
	 * @param ID
	 */
	StrongDrone(double X, double Y, int Angle, int Health, int ID) {
		super(X, Y, Angle, Health, ID, 's');
		drone4 = new Image(getClass().getResourceAsStream("./img/strongDrone4.png"));
		drone3 = new Image(getClass().getResourceAsStream("./img/strongDrone3.png"));
		drone2 = new Image(getClass().getResourceAsStream("./img/strongDrone2.png"));
		drone1 = new Image(getClass().getResourceAsStream("./img/strongDrone1.png"));
	}
	
	/**
	 * abstract method from player rendering drones using UICanvas
	 * @param c
	 */
	public StrongDrone displayPlayer(UICanvas c) {
		if (this.health == 4) {
			c.drawImage(drone4, this.x, this.y, entxSize, entySize);
		} else if (this.health == 3) {
			c.drawImage(drone3, this.x, this.y, entxSize, entySize);
		} else if (this.health == 2) {
			c.drawImage(drone2, this.x, this.y, entxSize, entySize);
		} else if (this.health == 1) {
			c.drawImage(drone1, this.x, this.y, entxSize, entySize);
		} else if (this.health < 1) {
			return this;
		}
		return null;
	}
	
	/**
	 * info about drone
	 */
	public String toString() {
		return "Strong D " + "\t" + id + " is at " + (int)x + ", " + (int)y + " angle " + (int)(angle % 360) + ", health " + health;
	}
}
