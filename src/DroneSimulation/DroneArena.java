package DroneSimulation;

import java.util.ArrayList;
import java.util.Random;

import DroneSimulation.Direction.direction;

public class DroneArena {	// Drone arena
	private int xSize, ySize;		// Arena size
	ArrayList<Drone> drn;	// Drones array
	Random randomGenerator;	// Random object
	
	/**
	 * drone arena constructor initialising arena's size, 
	 * random generator and drone list
	 * @param w
	 * @param h
	 */
	DroneArena(int w, int h) {
		this.xSize = w;
		this.ySize = h;
		randomGenerator = new Random();
		drn = new ArrayList<Drone>();
	}
	
	/**
	 * size of arena's x length
	 * @return xSize
	 */
	public int xSize() {
		return xSize;
	}

	/**
	 * size of arena's y length
	 * @return ySize
	 */
	public int ySize() {
		return ySize;
	}

	/**
	 * add drone to the list with random coordinates
	 */
	public void addDrone() {
		int count = -10;	// Does up to 10 extra random place searches for each drone after which cancels the search
		int x, y;
		
		do {
			count++;
			x = randomGenerator.nextInt(this.xSize) + 1;
			y = randomGenerator.nextInt(this.ySize) + 1;
			
			// can delete drn.size check, but overflowing drone field will not be handled
		} while (getDroneAt(x, y) != null && count < drn.size());
		
		if (getDroneAt(x, y) == null) {
			this.drn.add(new Drone(x, y, direction.values()[randomGenerator.nextInt(direction.values().length)]));
		}
		
	}
	
	/**
	 * search list of drones to see if there is a drone at x,y
	 * @param x
	 * @param y
	 * @return null if no Drone there, otherwise return drone
	 */
	public Drone getDroneAt(int x, int y) {
		Drone drone = null;
		
		for(Drone d : drn) {
			// If drone is at the location - return it
			if (d.isHere(x, y)) {
				return d;
			}
		}
		return drone;
	}
	
	/**
	 * display drones present in the list
	 * @param c
	 */
	public void showDrones(ConsoleCanvas c) {
		for (Drone d : drn) {
			d.displayDrone(c);
		}
	}
	
	/**
	 * moving all drones
	 */
	public void moveAllDrones() {
		for (Drone d : drn) {
			d.tryToMove(this);
		}
	}
	
	/**
	 * checking if the direction drone is moving towards is not obstructed
	 * @param dir
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean canMoveHere(Direction.direction dir, int x, int y) {
		boolean canMove = true;
		
		// Collision detection
		switch (dir) {
			case NORTH:
				if (y < 2)		// If outside boundaries
					return canMove = false;
				if (getDroneAt(x, y - 1) != null)	// If drone is in the way
					return canMove = false;
				break;
				
			case EAST:
				if (x >= xSize)	// If outside boundaries
					return canMove = false;
				if (getDroneAt(x + 1, y) != null)	// If drone is in the way
					return canMove = false;	
				break;
				
			case SOUTH:
				if (y >= ySize)	// If outside boundaries
					return canMove = false;
				if (getDroneAt(x, y + 1) != null)	// If drone is in the way
					return canMove = false;			
				break;
				
			case WEST:
				if (x < 2)	// If outside boundaries
					return canMove = false;
				if (getDroneAt(x - 1, y) != null)	// If drone is in the way
					return canMove = false;	
				break;
		}
		
		return canMove;
	}
	
	/**
	 * info about arena
	 */
	public String toString() {
		String str = "Arena size: x = " + xSize + " y = " + ySize + "\n\n";

		for (Drone i : drn) {
			str += "Drone " + i.getID() + ": x = " + i.getX() + "; y = "
					+ i.getY() + "; direction = " + i.dir + "\n";
		}
		return str;
	}
	
	public static void main(String[] args) {
//		DroneArena a = new DroneArena(2, 2);
//		a.addDrone();			
//		a.canMoveHere(null, xSize, xSize);
//		for (int i = 0; i < 4; i++) {
//			a.addDrone();			
//		}
//		System.out.println(a.toString());	//Printing arena/drones info in text format		
	}
}
