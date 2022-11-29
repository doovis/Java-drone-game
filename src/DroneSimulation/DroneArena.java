package DroneSimulation;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class DroneArena {	// Drone arena
	private int xSize, ySize;		// Arena size
	private Image bg;
	protected ArrayList<Entity> entities;	// Drones array
	protected Random randomGenerator;	// Random object
	
	/**
	 * drone arena constructor initialising arena's size, 
	 * random generator and drone list
	 * @param w
	 * @param h
	 */
	DroneArena(int w, int h) {
		this.xSize = w;
		this.ySize = h;
		this.bg = new Image(getClass().getResourceAsStream("./img/background.png"));
		randomGenerator = new Random();
		entities = new ArrayList<Entity>();
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
	 * method for updating drone x/y positions in the simulation loop
	 */
	public void update() {
		moveAllentities();		
	}

	/**
	 * redrawing objects in arena
	 * @param canvas
	 * @param gc
	 */
	public void drawObjects(UICanvas canvas, GraphicsContext gc) {
		canvas.clearCanvas();
		canvas.drawImage(bg, 0, 0, xSize, ySize);
		drawEntities(canvas);
	}

	/**
	 * add drone to the list with random coordinates
	 */
	public void addObject(char type) {
		int x, y;
		
		// Randomly generate x and y
		do {
			x = randomGenerator.nextInt(this.xSize - 52) + 1;
			y = randomGenerator.nextInt(this.ySize - 52) + 1;
		} while (getEntityAt(x, y, 50, 50) != null);	// generate x and y until empty space found
		
		// Double check if there's no object in that spot
		if (getEntityAt(x, y, 50, 50) == null) {
			if (type == 'r') this.entities.add(new Drone(x, y));
			if (type == 's') this.entities.add(new StrongDrone(x, y));
			if (type == 'o') this.entities.add(new Obstacle(x, y));
		}
	}

	/**
	 * add drone to the list with x and y coordinates
	 */
	public void addObject(double x, double y, char type) {

		if (getEntityAt(x, y, 50, 50) == null) {
			if (type == 'r') this.entities.add(new Drone(x, y));
			if (type == 's') this.entities.add(new StrongDrone(x, y));
			if (type == 'o') this.entities.add(new Obstacle(x, y));
		}
	}
	
	/**
	 * display drones present in the list
	 * @param c
	 */
	public void drawEntities(UICanvas c) {
		Entity playerToRemove = null;

		// Displaying entities
		for (Entity p : entities) {
			Entity player = p.displayPlayer(c);
			if (player != null) playerToRemove = player;
		}
		
		if (playerToRemove != null) {
			entities.remove(playerToRemove);
		}
	}
	
	/**
	 * moving all drones
	 */
	public void moveAllentities() {
		for (Entity p : entities) {
			p.tryToMove(this);
		}
	}
	
	/**
	 * search list of drones to see if there is a drone at x,y
	 * @param x
	 * @param y
	 * @return null if no Drone there, otherwise return drone
	 */
	public Entity getEntityAt(double x, double y, int entitiesXSize, int entitiesYSize) {
		Entity drone = null;
		
		for(Entity p : entities) {
			// If drone is at the location - return it
			if (p.isHere(x, y, entitiesXSize, entitiesYSize)) {
				return p;
			}
		}
		
		return drone;
	}
	
	/**
	 * Checking player's location for collision detection
	 * @param x
	 * @param y
	 * @param angle
	 * @param entitiesXSize
	 * @param entitiesYSize
	 * @param id
	 * @param type
	 * @return
	 */
	public int checkPlayerLocation(double x, double y, double angle, int entitiesXSize, int entitiesYSize, int id, char type) {		
		for(Entity p : entities) {
			// If vertical collision - return 1
			if (p.collisionCheck(x, y, angle, entitiesXSize, entitiesYSize) == 1 && p.getID() != id) {
				if (p.type == 's' && type == 's') {
					return 1;					
				} else if (p.type == 's') {
					return 3;
				} else if (p.type == 'o') {
					return 5;
				} else {
					return 1;					
				}
			}
			// If horizontal collision - return 2
			else if (p.collisionCheck(x, y, angle, entitiesXSize, entitiesYSize) == 2 && p.getID() != id) {
				if (p.type == 's' && type == 's') {
					return 2;					
				} else if (p.type == 's') {
					return 4;
				} else if (p.type == 'o') {
					return 6;
				} else {
					return 2;										
				}
			}
		}
		
		return 0;
	}
	
	/**
	 * info about arena
	 */
	public String toString() {
		String str = "Arena size: x = " + xSize + " y = " + ySize + "\n\n";

		for (Entity i : entities) {
			str += i.toString()  + "\n";
		}
		return str;
	}
}
