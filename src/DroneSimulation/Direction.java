package DroneSimulation;

import java.util.Random;

public class Direction {	
	
	enum direction {
		NORTH,
		EAST,
		SOUTH,
		WEST;
		
		public direction randomDirection() {
			Random random = new Random();
			return direction.values()[random.nextInt(direction.values().length)];
		}
	
		public direction next(direction currentDir) {
			int cur = currentDir.ordinal();
			return direction.values()[(cur + 1) % 4];
		}
	};

	
	public static void main(String[] args) {
//		direction a = direction.SOUTH;
//		System.out.println(a.next(a));
	}
	
}
