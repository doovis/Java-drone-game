package DroneSimulation;

import javafx.scene.image.Image;

public class Obstacle {
	private double x, y;	// Coordinates
	private int stnxSize, stnySize, id;	// size, id
	Image stone;

	
	/**
	 * Drone constructor initialising coordinates, deltas, 
	 * id and direction
	 * @param X
	 * @param Y
	 * @param d
	 */
	Obstacle(double X, double Y) {
		this.x = X;
		this.y = Y;
		this.stnxSize = 40;
		this.stnySize = 40;
		this.id = 5;
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
	Obstacle(double X, double Y, int Angle, int Health, int ID) {
		this.x = X;
		this.y = Y;
		this.stnxSize = 40;
		this.stnySize = 40;
		this.id = 5;
		stone = new Image(getClass().getResourceAsStream("./img/stone.png"));
	}
	
	/**
	 * abstract method from player rendering drones using UICanvas
	 * @param c
	 */
	public Obstacle displayObstacle(UICanvas c) {
		c.drawImage(stone, this.x, this.y, stnxSize, stnySize);
		return null;
	}
	
	// Arena border collision detection
	public int collisionCheck(double sx, double sy, double sangle, int drnXSize, int drnYSize) {
		// Checking each side of the drones

		// Middle point of drone
		double thisMiddlePositionX = this.x + (this.stnxSize / 2);
		double thisMiddlePositionY = this.y + (this.stnySize / 2);

		// Half size of drone
		double thisHalfSizeX = (this.stnxSize / 2);
		double thisHalfSizeY = (this.stnySize / 2);
		
		// Middle point of other drone
		double otherMiddlePositionX = sx + (drnXSize / 2);
		double otherMiddlePositionY = sy + (drnYSize / 2);
		
		// Half size of other drone
		double otherHalfSizeX = (drnXSize / 2);
		double otherHalfSizeY = (drnYSize / 2);
		
		// Distance between drone middle points in x/y axis
		double deltaX = otherMiddlePositionX - thisMiddlePositionX;
		double deltaY = otherMiddlePositionY - thisMiddlePositionY;
		
		// Distance between drones edges in x/y axis
		double intersectX = Math.abs(deltaX) - (otherHalfSizeX + thisHalfSizeX);
		double intersectY = Math.abs(deltaY) - (otherHalfSizeY + thisHalfSizeY);

		// If drones edges touch
		if (intersectX < 0 && intersectY < 0) {
			// if touched left and right sides
			if (intersectX > intersectY) {
				sangle = 180 - sangle;
				return 2; // 2 -> || sides collision
			// if touched upper and bottom sides
			} else {
				sangle = -sangle;
				return 1; // 1 -> = sides collision
			}
		}
		
		return 0; // If no collision detected
	}
	
	/**
	 * info about drone
	 */
	public String toString() {
		return "Obstacle " + "\t" + id + " is at " + (int)x + ", " + (int)y;
	}
}
