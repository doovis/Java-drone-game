package DroneSimulation;

import java.util.ArrayList;
import java.util.Random;

import DroneSimulation.Direction.direction;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class DroneArena {	// Drone arena
	private int xSize, ySize;		// Arena size
	ArrayList<Drone> drn;	// Drones array
	Random randomGenerator;	// Random object
//	Image drone;
	
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
//		drone = new Image(getClass().getResourceAsStream("./drone.png"));
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
	
	public void update() {
		moveAllDrones();		
	}

	public void drawObjects(UICanvas canvas, Canvas interfaceCanvas) {
		canvas.clearCanvas();
		interfaceCanvas.getGraphicsContext2D().setFill(Color.color(0.9, 0.9, 0.9, 1));
		interfaceCanvas.getGraphicsContext2D().fillRect(0, 0, xSize, ySize);
		showDrones(canvas);
	}

	/**
	 * add drone to the list with random coordinates
	 */
	public void addDrone() {
		int count = -10;	// Does up to 10 extra random place searches for each drone after which cancels the search
		int x, y;
		
		do {
			count++;
			x = randomGenerator.nextInt(this.xSize - 50);
			y = randomGenerator.nextInt(this.ySize - 50);
			
			// can delete drn.size check, but overflowing drone field will not be handled
		} while (getDroneAt(x, y) != null && count < drn.size());
		
		if (getDroneAt(x, y) == null) {
			this.drn.add(new Drone(x, y, direction.randomDirection()));
		}
		
	}
	
	/**
	 * search list of drones to see if there is a drone at x,y
	 * @param x
	 * @param y
	 * @return null if no Drone there, otherwise return drone
	 */
	public Drone getDroneAt(double x, double y) {
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
	public void showDrones(UICanvas c) {
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
	public boolean canMoveHere(Direction.direction dir, double x, double y, int droneXSize, int droneYSize) {
		boolean canMove = true;
		
		// Collision detection
		switch (dir) {
			case NORTH:
				if (y < 1)		// If outside boundaries
					return canMove = false;
				if (getDroneAt(x, y - 1) != null)	// If drone is in the way
					return canMove = false;
				break;
				
			case EAST:
				if (x + droneXSize >= xSize)	// If outside boundaries
					return canMove = false;
				if (getDroneAt(x + 1, y) != null)	// If drone is in the way
					return canMove = false;	
				break;
				
			case SOUTH:
				if (y + droneYSize >= ySize)	// If outside boundaries
					return canMove = false;
				if (getDroneAt(x, y + 1) != null)	// If drone is in the way
					return canMove = false;			
				break;
				
			case WEST:
				if (x < 1)	// If outside boundaries
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
		DroneArena a = new DroneArena(2, 2);
//		a.addDrone();			
//		a.addDrone();			
//		a.addDrone();			
//		a.canMoveHere(null, xSize, xSize);
//		for (int i = 0; i < 4; i++) {
//			a.addDrone();			
//		}
		System.out.println(a.toString());	//Printing arena/drones info in text format		
	}
}
