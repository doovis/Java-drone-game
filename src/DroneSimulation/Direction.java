package DroneSimulation;

import java.util.Random;


public class Direction {
	dir d;
	
	enum dir {
		NORTH,
		EAST,
		SOUTH,
		WEST;
	};
	
	Direction(dir D) {
		this.d = D;
	}

	public dir randomdir() {
		Random random = new Random();
		return dir.values()[random.nextInt(dir.values().length)];
	}

//	public static dir next() {
//		return ;
//	}
	
	public static void main(String[] args) {
//		d.SOUTH;
//		System.out.println(da);
	}
	
}
