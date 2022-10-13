package DroneSimulation;


public class Drone {
	int x, y;
	int id = 0;
	public static int count = 0;
	
	Drone(int X, int Y) {
		this.x = X;
		this.y = Y;
		this.id = count++;
	}
	
	
	public String toString() {
		return "Drone " + id + " is at " + x + ", " + y;
	}
	
	public static void main(String[] args) {
//		Drone d = new Drone(5, 3);	// Drone instance
//		Drone m = new Drone(5, 8);	// Drone instance
//		System.out.println(d.toString());
//		System.out.println(m.toString());
	}
}
