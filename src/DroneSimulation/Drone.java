package DroneSimulation;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.transform.Rotate;

public class Drone {				// Drone class
	private double x, dx, y, dy, angle;	// Coordinates, delta x and y
	private int id;	// identifier
	private static int drnxSize;
	private static int drnySize;
	Direction.direction dir;		// Direction
	private static int count = 0;	// Id increment helper
	Image drone;

	
	/**
	 * Drone constructor initialising coordinates, deltas, 
	 * id and direction
	 * @param X
	 * @param Y
	 * @param d
	 */
	Drone(double X, double Y, Direction.direction d) {
		this.x = X;
		this.y = Y;
		this.dx = 2;
		this.dy = 2;
		this.angle = new Random().nextInt(360);
		this.id = count++;
		this.dir = d;
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
	public static int getXSize() {
		return drnxSize;
	}

	/**
	 * get value of y
	 * @return y
	 */
	public static int getYSize() {
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
		// Checking each side of the drones
//		boolean collisionX = this.x < sx + drnXSize && this.x + drnxSize > sx;
//		boolean collisionY = this.y < sy + drnYSize && this.y + drnySize > sy;

//		if (this.x + 1 < sx + drnXSize && this.x + drnxSize - 1 > sx) {
//			if ()
//		}
		
		
		return collisionX && collisionY;
	}
	
	/**
	 * attempting to move drone if the area to move to is not obstructed
	 * @param arena
	 */
	public void tryToMove(DroneArena arena) {
		if (y < 1 || y + drnySize >= arena.ySize()) {
			this.angle = -angle;
		} else if (x < 1 || x + drnxSize >= arena.xSize()) {
			this.angle = 180 - angle;
		}

//		if (arena.getDroneAt(x, y, drnxSize, drnySize, id) != null) {
//			this.angle = -angle;			
//		}
		
		this.x += dx * Math.cos((angle * (Math.PI / 180)));
		this.y += dy * Math.sin((angle * (Math.PI / 180)));

//		if (arena.getDroneAt(x, y, drnxSize, drnySize, id) != null) {
//			this.angle = 180*Math.atan2(y-b.getY(), x-b.getX())/Math.PI;			
//		}

		if (arena.getDroneAt(x, y, drnxSize, drnySize, id) != null) {
			this.angle = 180 - angle;			
		}
		
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
		return "Drone " + id + " is at " + x + ", " + y + ", and points to " + dir.toString();
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
