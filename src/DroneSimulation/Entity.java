package DroneSimulation;

import java.util.Random;

import javafx.scene.paint.Color;

public abstract class Entity {
	protected double x, dx, y, dy;	// Coordinates, delta x and y
	protected int id, angle, health, entxSize, entySize, diameter;
	protected char type;
	protected Color circleColor;
	protected boolean checked;
	private static int count = 0;	// Id increment helper static
	
	/**
	 * Drone constructor initialising coordinates, deltas, sizes, health, type, angle, id etc.
	 * @param X
	 * @param Y
	 * @param d
	 */
	Entity(double X, double Y, char Type) {
		this.x = X;
		this.y = Y;
		this.dx = 2;
		this.dy = 2;
		this.entxSize = 40;
		this.entySize = 40;
		this.health = 4;
		this.type = Type;
		this.angle = new Random().nextInt(360);
		this.id = count++;
		this.checked = false;
		
		if (Type == 'd') {
			this.diameter = 320;
			this.circleColor = Color.rgb(0, 0, 0, 0.25);
		}
	}

	Entity(double X, double Y, int Angle, int Health, int ID, char Type) {
		this.x = X;
		this.y = Y;
		this.dx = 2.0;
		this.dy = 2.0;
		this.entxSize = 40;
		this.entySize = 40;
		this.health = Health;
		this.angle = Angle;
		this.id = ID;
		this.type = Type;
		this.checked = false;
		
		if (Type == 'd') {
			this.diameter = 320;
			this.circleColor = Color.rgb(0, 0, 0, 0.25);
		}
	}
	
	/**
	 * get value of x
	 * @return x
	 */
	public double getX() {
		return x;
	}
	
	/**
	 * get value of y
	 * @return y
	 */
	public double getY() {
		return y;
	}

	/**
	 * get drone type
	 * @return angle
	 */
	public char getType() {
		return type;
	}

	/**
	 * get value of angle
	 * @return angle
	 */
	public int getAngle() {
		return angle;
	}

	/**
	 * get value of health
	 * @return health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * get value of drone x size
	 * @return drnxSize
	 */
	public int getXSize() {
		return entxSize;
	}

	/**
	 * get value of drone y size
	 * @return drnySize
	 */
	public int getYSize() {
		return entySize;
	}

	/**
	 * get value of id
	 * @return id
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Is the drone at this x,y position
	 * @param sx	x position
	 * @param sy	y position
	 * @return		true if drone is at sx,sy, false otherwise
	 */
	public boolean isHere(double sx, double sy, int drnXSize, int drnYSize) {
		if (this.x + this.entxSize > sx && this.x < sx + drnXSize
			&& this.y + this.entySize > sy && this.y < sy + drnYSize) {
			return true;			
		}
		return false;			
	}
	
	// Circle radar collision detection
	public boolean circleDetection(double sx, double sy, double sangle, int drnXSize, int drnYSize) {
		// distance between entities on x axis
		int distanceX = (int)x + (entxSize / 2) - (int)sx + (drnXSize / 2);
		// distance between entities on y axis
		int distanceY = (int)y + (entySize / 2) - (int)sy + (drnYSize / 2);
		// total distance between entities across both axis
		int distanceXY = (int)Math.sqrt(distanceX * distanceX + distanceY * distanceY);

		// If drone is crossing the circle boundary
		if (distanceXY < 160) {
			return true;
		} else {
			return false;
		}

	}
	
	/**
	 * AABB Collision check (Axis-aligned bounding boxes)
	 * @param sx
	 * @param sy
	 * @param drnXSize
	 * @param drnYSize
	 * @return
	 */
	public int collisionCheck(double sx, double sy, double sangle, int drnXSize, int drnYSize) {
		// Checking each side of the drones

		// Middle point of drone
		double thisMiddlePositionX = this.x + (this.entxSize / 2);
		double thisMiddlePositionY = this.y + (this.entySize / 2);

		// Half size of drone
		double thisHalfSizeX = (this.entxSize / 2);
		double thisHalfSizeY = (this.entySize / 2);
		
		// Middle point of other drone
		double otherMiddlePositionX = sx + (drnXSize / 2);
		double otherMiddlePositionY = sy + (drnYSize / 2);
		
		// Half size of other drone
		double otherHalfSizeX = (drnXSize / 2);
		double otherHalfSizeY = (drnYSize / 2);
		
		// Distance between drone middle points in x/y axis
		double deltaX = otherMiddlePositionX - thisMiddlePositionX;
		double deltaY = otherMiddlePositionY - thisMiddlePositionY;
		
		// Distance between drones edges in x/y axis
		double intersectX = Math.abs(deltaX) - (otherHalfSizeX + thisHalfSizeX);
		double intersectY = Math.abs(deltaY) - (otherHalfSizeY + thisHalfSizeY);

		// If drones edges touch
		if (intersectX < 0 && intersectY < 0) {
			// if touched left and right sides
			if (intersectX > intersectY) {
				// mirroring the angle
				sangle = 180 - sangle;
				return 2; // 2 -> || sides collision
			// if touched upper and bottom sides
			} else {
				// mirroring the angle
				sangle = -sangle;
				return 1; // 1 -> = sides collision
			}
		}
		
		return 0; // If no collision detected
	}
	
	/**
	 * attempting to move drone if the area to move to is not obstructed
	 * @param arena
	 */
	public void tryToMove(DroneArena arena) {
		// object to object collision detection
		int condition = 0;				// collision type
		condition = arena.checkPlayerLocation(x, y, angle, entxSize, entySize, id, type);
		
		// regular to regular / strong to strong drones
		if (condition == 1) {
			this.health = this.health - 1;
			this.angle = -angle;
		} else if (condition == 2) {
			this.health = this.health - 1;
			this.angle = 180 - angle;
		}
		// drones to deceptive drones
		if (condition == 7) {
			circleColor = Color.rgb(255, 0, 0, 0.25);
		} else {
			circleColor = Color.rgb(0, 0, 0, 0.25);
		}

		//  strong to regular drones
		if (condition == 3) {
			this.health = this.health - 4;
			this.angle = -angle;
		} else if (condition == 4) {
			this.health = this.health - 4;
			this.angle = 180 - angle;
		}
		
		// drones to obstacles
		if (condition == 5) {
			this.angle = -angle;
		} else if (condition == 6) {
			this.angle = 180 - angle;
		}

		// Arena border collision detection
		if (y < 1) {
			this.y = 1;
			this.angle = -angle;
		} else if (y + entySize >= arena.ySize()) {
			this.y = arena.ySize() - entySize - 1;
			this.angle = -angle;
		} else if (x < 1) {
			this.x = 1;
			this.angle = 180 - angle;
		} else if (x + entxSize >= arena.xSize()) {
			this.x = arena.xSize() - entxSize - 1;
			this.angle = 180 - angle;
		}
		
		// moving entity
		this.x += dx * Math.cos((angle * (Math.PI / 180)));
		this.y += dy * Math.sin((angle * (Math.PI / 180)));
	}
	
	/**
	 * abstract method for entity rendering using UICanvas
	 * @param c
	 */
	public abstract Entity displayEntity(UICanvas c);
	
	/**
	 * info about entity
	 */
	public String toString() {
		return "Entity " + "\t" + id + " is at " + (int)x + ", " + (int)y + " angle " + (int)(angle % 360) + ", health " + health;
	}		
}
