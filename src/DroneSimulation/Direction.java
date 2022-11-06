package DroneSimulation;

import java.util.Random;

public class Direction {
	/**
	 * direction enum for drones
	 *
	 */
	enum direction {
		NORTH,
		EAST,
		SOUTH,
		WEST;
		
		/**
		 * picking random direction from enum
		 * @return
		 */
		public direction randomDirection() {
			Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
	
		/**
		 * picking next direction in NESW sequence
		 * @param currentDir
		 * @return
		 */
		public direction next(direction currentDir) {
			return values()[(currentDir.ordinal() + 1) % values().length];
		}
	};

	
	public static void main(String[] args) {
//		direction a = direction.SOUTH;
//		System.out.println(a.next(a));
	}
	
}
