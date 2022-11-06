package DroneSimulation;

public class Drone {				// Drone class
	private int x, dx, y, dy, id;	// Coordinates, delta x and y, identifier
	Direction.direction dir;		// Direction
	private static int count = 0;	// Id increment helper
	
	
	/**
	 * Drone constructor initialising coordinates, deltas, 
	 * id and direction
	 * @param X
	 * @param Y
	 * @param d
	 */
	Drone(int X, int Y, Direction.direction d) {
		this.x = X;
		this.y = Y;
		this.dx = 1;
		this.dy = 1;
		this.id = count++;
		this.dir = d;
	}
	
	/**
	 * get value of x
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * get value of y
	 * @return y
	 */
	public int getY() {
		return y;
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
	public void setXY(int X, int Y) {
		x = X;
		y = Y;
	}
	
	/**
	 * Is the drone at this x,y position
	 * @param sx	x position
	 * @param sy	y position
	 * @return		true if drone is at sx,sy, false otherwise
	 */
	public boolean isHere(int sx, int sy) {
		if (this.x == sx && this.y == sy) {
			return true;			
		}
		return false;			
	}
	
	/**
	 * attempting to move drone if the area to move to is not obstructed
	 * @param arena
	 */
	public void tryToMove(DroneArena arena) {

		if (arena.canMoveHere(dir, x, y)) {	// if can move update x, y
			switch (this.dir) {				// calculate next x, y position
				case NORTH:
						y -= dy;
					break;
				case EAST:
						x += dx;
					break;
				case SOUTH:
						y += dy;
					break;
				case WEST:
						x -= dx;
					break;
			}
		} else {
			this.dir = dir.next(this.dir);	// Otherwise change direction
		}
	}
	
	/**
	 * displays drone as 'D' using ConsoleCanvas method 'showIt'
	 * @param c
	 */
	public void displayDrone(ConsoleCanvas c) {
		c.showIt(this.x, this.y, 'D');
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
