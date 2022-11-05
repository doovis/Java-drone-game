package DroneSimulation;

public class Drone {	// Drone class
	int x, y;			// Coordinates
	int id = 0;			// Identifier
	Direction.direction dir;		// Direction
	public static int count = 0;	// Id increment helper
	
	Drone(int X, int Y, Direction.direction d) {
		this.x = X;
		this.y = Y;
		this.id = count++;
		this.dir = d;
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
		return "Drone " + id + " is at " + x + ", " + y + ", and points to " + dir;
	}
	
	public static void main(String[] args) {
//		Drone d = new Drone(5, 2, Direction.direction.SOUTH);	// Drone instance
//		System.out.println(d.toString());	// Print where it is		
	}
}
