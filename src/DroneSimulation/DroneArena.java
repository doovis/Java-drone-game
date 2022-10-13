package DroneSimulation;

import java.util.ArrayList;
import java.util.Random;


public class DroneArena {
	int width, height;
	ArrayList<Drone> drn = new ArrayList<Drone>();
	Random randomGenerator = new Random();
	
	DroneArena(int w, int h) {
		this.width = w;
		this.height = h;
	}

	public void addDrone(/* int x, int y*/) {
//		int X = (int) (x > width || x < 1 ? Math.ceil((double)width / 2) : x);
//		int Y = (int) (y > height || y < 1 ? Math.ceil((double)height / 2) : y);

		int x = this.randomGenerator.nextInt(width) + 1;
		int y = this.randomGenerator.nextInt(height) + 1;
		this.drn.add(new Drone(x, y));
	}
	
	public String toString() {
		String str = "\nArena size:\nwidth: " + width + "\nheight: " + height + "\n\n";

		for (int i = 0; i < drn.size(); i++) {
			str += "Drone " + drn.get(i).id + ":	";			
		}
		str += "\n";
		for (int i = 0; i < drn.size(); i++) {
			str += "x: " + drn.get(i).x + " y: " + drn.get(i).y + "	";
		}
		
		return str;
	}
	
	public void drawArena(String ch) {
		for (int i = 0; i < height + 2; i++) {
			System.out.print(ch);
			for (int j = 0; j < width; j++) {
				for (int k = 0; k < drn.size(); k++) {
					if (j + 1 == drn.get(k).x && i == drn.get(k).y) {					
						System.out.print(" D ");
						j++;
					}
				} 
				if (j >= width) {
					continue;
				} else if (i == 0 || i == height + 1) {					
					System.out.print(" " + ch + " ");
				} else {
					System.out.print("   ");
				}
			}
			System.out.println(ch);
		}
	}
	
	public static void main(String[] args) {
		DroneArena a = new DroneArena(20, 22);
//		a.addDrone(1, 1);	// arguments: 0, 0 return the middle position of the drone
		for (int i = 0; i < 10; i++) {
			a.addDrone();			
		}
		a.drawArena("x");
		System.out.println(a.toString());
		
	}
	
}
