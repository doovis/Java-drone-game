package DroneSimulation;

import java.util.ArrayList;
import java.util.Random;

public class DroneArena {	// Drone arena
	private int xSize, ySize;		// Arena size
	ArrayList<Drone> drn;	// Drones array
	Random randomGenerator;	// Random object
	
	DroneArena(int w, int h) {
		this.xSize = w;
		this.ySize = h;
		randomGenerator = new Random();
		drn = new ArrayList<Drone>();
	}
	
	public int xSize() {
		return this.xSize;
	}

	public int ySize() {
		return this.ySize;
	}

	public void addDrone() {
		int count = -10;	// Does up to 10 extra random place searches for each drone after which cancels the search
		int x, y;
		
		do {
			count++;
			x = randomGenerator.nextInt(xSize) + 1;
			y = randomGenerator.nextInt(ySize) + 1;
			
			// can delete drn.size check, but overflowing drone field will not be handled
		} while (getDroneAt(x, y) != null && count < drn.size());
		
		if (getDroneAt(x, y) == null) {
			this.drn.add(new Drone(x, y));						
		}
		
	}
	
	/**
	 * search arraylist of drones to see if there is a drone at x,y
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
	
	
	public void showDrones(ConsoleCanvas c) {
		// Display drones present in the list
		for (Drone d : drn) {
			d.displayDrone(c);
		}
	}
	
	
//	public void drawArena(String ch) {
//		for (int i = 0; i < ySize + 2; i++) {
//			System.out.print(ch);
//			for (int j = 0; j < xSize; j++) {
//				for (int k = 0; k < drn.size(); k++) {
//					if (j + 1 == drn.get(k).x && i == drn.get(k).y) {					
//						System.out.print(" D ");
//						j++;
//					}
//				} 
//				if (j >= xSize) {
//					continue;
//				} else if (i == 0 || i == ySize + 1) {					
//					System.out.print(" " + ch + " ");
//				} else {
//					System.out.print("   ");
//				}
//			}
//			System.out.println(ch);
//		}
//	}
	
	public String toString() {
		String str = "Arena size: x = " + xSize + " y = " + ySize + "\n\n";

		for (Drone i : drn) {
			str += "Drone " + i.id + ": ";
			str += "x = " + i.x + " y = " + i.y + "\n";
		}
		return str;
	}
	
	public static void main(String[] args) {
//		DroneArena a = new DroneArena(2, 2);
//
//		for (int i = 0; i < 4; i++) {
//			a.addDrone();			
//		}
//		System.out.println(a.toString());	//Printing arena/drones info in text format		
	}
}
