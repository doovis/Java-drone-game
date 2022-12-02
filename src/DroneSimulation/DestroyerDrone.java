package DroneSimulation;

import javafx.scene.image.Image;

public class DestroyerDrone extends Entity {
	private Image drone4, drone3, drone2, drone1;

	
	/**
	 * Initialising images, x/y coords and entity type
	 * @param X
	 * @param Y
	 */
	DestroyerDrone(double X, double Y) {
		super(X, Y, 'd');
		drone4 = new Image(getClass().getResourceAsStream("./img/protectedDrone4.png"));
		drone3 = new Image(getClass().getResourceAsStream("./img/protectedDrone3.png"));
		drone2 = new Image(getClass().getResourceAsStream("./img/protectedDrone2.png"));
		drone1 = new Image(getClass().getResourceAsStream("./img/protectedDrone1.png"));
	}

	
	/**
	 * Overloaded entity constructor used for file saving
	 * @param X
	 * @param Y
	 * @param Angle
	 * @param Health
	 * @param ID
	 */
	DestroyerDrone(double X, double Y, int Angle, int Health, int ID) {
		super(X, Y, Angle, Health, ID, 'd');
		drone4 = new Image(getClass().getResourceAsStream("./img/protectedDrone4.png"));
		drone3 = new Image(getClass().getResourceAsStream("./img/protectedDrone3.png"));
		drone2 = new Image(getClass().getResourceAsStream("./img/protectedDrone2.png"));
		drone1 = new Image(getClass().getResourceAsStream("./img/protectedDrone1.png"));
	}
	
	/**
	 * abstract method from entity for rendering using UICanvas
	 * @param c
	 */
	public DestroyerDrone displayEntity(UICanvas c) {
		// Drawing drone picture with oval around it
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
		return "Destroyer D " + "\t" + id + " is at " + (int)x + ", " + (int)y + " angle " + (int)(angle % 360) + ", health " + health;
	}
}
