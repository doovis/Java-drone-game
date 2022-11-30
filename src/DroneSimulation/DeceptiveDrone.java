package DroneSimulation;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class DeceptiveDrone extends Entity {
	private Image drone4, drone3, drone2, drone1;

	
	/**
	 * Drone constructor initialising coordinates, deltas, 
	 * id and direction
	 * @param X
	 * @param Y
	 * @param d
	 */
	DeceptiveDrone(double X, double Y) {
		super(X, Y, 'd');
		drone4 = new Image(getClass().getResourceAsStream("./img/protectedDrone4.png"));
		drone3 = new Image(getClass().getResourceAsStream("./img/protectedDrone3.png"));
		drone2 = new Image(getClass().getResourceAsStream("./img/protectedDrone2.png"));
		drone1 = new Image(getClass().getResourceAsStream("./img/protectedDrone1.png"));
	}

	
	/**
	 * Overloaded Drone constructor for file saving method
	 * @param X
	 * @param Y
	 * @param Angle
	 * @param Health
	 * @param ID
	 */
	DeceptiveDrone(double X, double Y, int Angle, int Health, int ID) {
		super(X, Y, Angle, Health, ID, 'd');
		drone4 = new Image(getClass().getResourceAsStream("./img/protectedDrone4.png"));
		drone3 = new Image(getClass().getResourceAsStream("./img/protectedDrone3.png"));
		drone2 = new Image(getClass().getResourceAsStream("./img/protectedDrone2.png"));
		drone1 = new Image(getClass().getResourceAsStream("./img/protectedDrone1.png"));
	}
	
	/**
	 * abstract method from player rendering drones using UICanvas
	 * @param c
	 */
	public DeceptiveDrone displayPlayer(UICanvas c) {
		if (this.health == 4) {
			c.drawOval((int)x + (entxSize / 2) - (diameter / 2), (int)y + (entySize / 2) - (diameter / 2), diameter, diameter, circleColor);
			c.drawImage(drone4, this.x, this.y, entxSize, entySize);
		} else if (this.health == 3) {
			c.drawOval((int)x + (entxSize / 2) - (diameter / 2), (int)y + (entySize / 2) - (diameter / 2), diameter, diameter, circleColor);
			c.drawImage(drone3, this.x, this.y, entxSize, entySize);
		} else if (this.health == 2) {
			c.drawOval((int)x + (entxSize / 2) - (diameter / 2), (int)y + (entySize / 2) - (diameter / 2), diameter, diameter, circleColor);
			c.drawImage(drone2, this.x, this.y, entxSize, entySize);
		} else if (this.health == 1) {
			c.drawOval((int)x + (entxSize / 2) - (diameter / 2), (int)y + (entySize / 2) - (diameter / 2), diameter, diameter, circleColor);
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
		return "Deceptive D " + "\t" + id + " is at " + (int)x + ", " + (int)y + " angle " + (int)(angle % 360) + ", health " + health;
	}
}
