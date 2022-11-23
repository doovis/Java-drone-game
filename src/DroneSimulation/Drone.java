package DroneSimulation;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class Drone {				// Drone class
	private double x, dx, y, dy, angle;	// Coordinates, delta x and y
	private int id;	// identifier
	private int drnxSize;
	private int drnySize;
	private static int count = 0;	// Id increment helper
	Image drone;

	
	/**
	 * Drone constructor initialising coordinates, deltas, 
	 * id and direction
	 * @param X
	 * @param Y
	 * @param d
	 */
	Drone(double X, double Y) {
		this.x = X;
		this.y = Y;
		this.dx = 2;
		this.dy = 2;
		this.angle = /*new Random().nextInt(360)*/ 135;
		this.id = count++;
		this.drnxSize = 50;
		this.drnySize = 50;
		drone = new Image(getClass().getResourceAsStream("./drone.png"));
	}
	
	/**
	 * get value of x
	 * @return x
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * get value of y
	 * @return y
	 */
	public double getY() {
		return y;
	}

	/**
	 * get value of y
	 * @return y
	 */
	public int getXSize() {
		return drnxSize;
	}

	/**
	 * get value of y
	 * @return y
	 */
	public int getYSize() {
		return drnySize;
	}

	/**
	 * get value of id
	 * @return id
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * set values of x and y
	 * @param X
	 * @param Y
	 */
	public void setXY(double X, double Y) {
		x = X;
		y = Y;
	}
	
	/**
	 * Is the drone at this x,y position
	 * @param sx	x position
	 * @param sy	y position
	 * @return		true if drone is at sx,sy, false otherwise
	 */
	public boolean isHere(double sx, double sy, int drnXSize, int drnYSize) {
		if (this.x + this.drnxSize > sx && this.x < sx + drnXSize
			&& this.y + this.drnySize > sy && this.y < sy + drnYSize) {
			return true;			
		}
		return false;			
	}
	
	/**
	 * AABB Collision check (Axis-aligned bounding boxes)
	 * @param sx
	 * @param sy
	 * @param drnXSize
	 * @param drnYSize
	 * @return
	 */
	public int collisionCheck(double sx, double sy, int drnXSize, int drnYSize) {
		// Checking each side of the drones

		// Middle point of drone
		double thisMiddlePositionX = this.x + (this.drnxSize / 2);
		double thisMiddlePositionY = this.y + (this.drnySize / 2);

		// Half size of drone
		double thisHalfSizeX = (this.drnxSize / 2);
		double thisHalfSizeY = (this.drnySize / 2);
		
		// Middle point of other drone
		double otherMiddlePositionX = sx + (drnXSize / 2);
		double otherMiddlePositionY = sy + (drnYSize / 2);
		
		// Half size of drone
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
//			System.out.println("intersection point: " + intersectX + " " + intersectY);
			// if touched left and right sides
			if (intersectX > intersectY) {
				return 2; // 2 -> || sides collision
			// if touched upper and bottom sides
			} else {
				return 1; // 1 -> = sides collision
			}
		}
		
		return 0; // If no collision detected
	}
	
	/**
	 * attempting to move drone if the area to move to is not obstructed
	 * @param arena
	 */
	public void tryToMove(DroneArena arena) {
		// drone to drone collision detection
		if (arena.checkDroneLocation(x, y, drnxSize, drnySize, id) == 1) {
			this.angle = -angle;
		} else if (arena.checkDroneLocation(x, y, drnxSize, drnySize, id) == 2) {
			this.angle = 180 - angle;			

		}
		
		// Arena border collision detection
		if (y < 1 || y + drnySize >= arena.ySize()) {
			this.angle = -angle;
		} else if (x < 1 || x + drnxSize >= arena.xSize()) {
			this.angle = 180 - angle;
		}
		
		this.x += dx * Math.cos((angle * (Math.PI / 180)));
		this.y += dy * Math.sin((angle * (Math.PI / 180)));
		
//		if (arena.canMoveHere(dir, x, y, drnxSize, drnySize, id)) {	// if can move update x, y
//			switch (this.dir) {				// calculate next x, y position
//				case NORTH:
//						y -= dy;
//					break;
//				case EAST:
//						x += dx;
//					break;
//				case SOUTH:
//						y += dy;
//					break;
//				case WEST:
//						x -= dx;
//					break;
//			}
//			System.out.println(angle);
//		} else {

//			this.dir = dir.next(this.dir);	// Otherwise change direction
//		}
	}
	
	/**
	 * displays drone as 'D' using ConsoleCanvas method 'showIt'
	 * @param c
	 */
	public void displayDrone(UICanvas c) {
		c.drawImage(drone, this.angle, this.x, this.y, drnxSize, drnySize);
	}
	
	/**
	 * info about drone
	 */
	public String toString() {
		return "Drone " + id + " is at " + (int)x + ", " + (int)y + ", and points to " + (int)(angle % 360);
	}
	
	public static void main(String[] args) {
//		Drone d = new Drone(1, 1, Direction.direction.NORTH);	// Drone instance
//		DroneArena a = new DroneArena(2, 2);
//		a.addDrone();
//		System.out.println(d.toString());	// Print where it is		
//		d.tryToMove(a);
//		d.tryToMove(a);
//		d.tryToMove(a);
//		System.out.println(d.toString());	// Print where it is		
	}
}
