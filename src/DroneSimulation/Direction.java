package DroneSimulation;

import java.util.Random;

public class Direction {	
	
	enum direction {
		NORTH,
		EAST,
		SOUTH,
		WEST;
		
		public direction randomdirection() {
			Random random = new Random();
			return direction.values()[random.nextInt(direction.values().length)];
		}
	
	//	public direction next() {
	//		return ;
	//	}
	};

	
	public static void main(String[] args) {
//		System.out.println(da);
	}
	
}
