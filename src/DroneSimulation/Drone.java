package DroneSimulation;

public class Drone {	// Drone class
	int x, y;			// Coordinates
	int id = 0;			// Identifier
	static Direction.dir dir;		// Direction
	public static int count = 0;	// Id increment helper
	
	
	Drone(int X, int Y, Direction d) {
		this.x = X;
		this.y = Y;
		this.id = count++;
		Drone.dir = DroneSimulation.Direction.dir.SOUTH;
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
	
	public void displayDrone(ConsoleCanvas c) {
		c.showIt(this.x, this.y, 'D');
	}
	
	public String toString() {
		return "Drone " + id + " is at " + x + ", " + y;
	}
	
	public static void main(String[] args) {
		Direction dire = new Direction(dir);
		Drone d = new Drone(5, 2, dire);	// Drone instance
		System.out.println(d.toString());	// Print where it is		
	}
}
