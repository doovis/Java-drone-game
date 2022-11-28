package DroneSimulation;

import java.util.ArrayList;
import java.util.Random;

import DroneSimulation.Direction.direction;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class DroneArena {	// Drone arena
	private int xSize, ySize;		// Arena size
	ArrayList<Player> players;	// Drones array
	ArrayList<Obstacle> obstacles;	// obstacles array
	Random randomGenerator;	// Random object
	Image bg;
	
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
		players = new ArrayList<Player>();
		obstacles = new ArrayList<Obstacle>();
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
		moveAllPlayers();		
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
		
		do {
			x = randomGenerator.nextInt(this.xSize - 52) + 1;
			y = randomGenerator.nextInt(this.ySize - 52) + 1;
		} while (getDroneAt(x, y, 50, 50) != null);
		
		if (getDroneAt(x, y, 50, 50) == null) {
			if (type == 'r') this.players.add(new Drone(x, y));
			if (type == 's') this.players.add(new StrongDrone(x, y));
			if (type == 'o') this.obstacles.add(new Obstacle(x, y));
		}
	}
	
	/**
	 * display drones present in the list
	 * @param c
	 */
	public void drawEntities(UICanvas c) {
		Player playerToRemove = null;

		// Displaying players
		for (Player p : players) {
			Player player = p.displayPlayer(c);
			if (player != null) playerToRemove = player;
		}

		// Displaying obstacles
		for (Obstacle o : obstacles) {
			o.displayObstacle(c);
		}
		
		if (playerToRemove != null) {
			players.remove(playerToRemove);
		}
	}
	
	/**
	 * moving all drones
	 */
	public void moveAllPlayers() {
		for (Player p : players) {
			p.tryToMove(this);
		}
	}
	
	/**
	 * search list of drones to see if there is a drone at x,y
	 * @param x
	 * @param y
	 * @return null if no Drone there, otherwise return drone
	 */
	public Player getDroneAt(double x, double y, int playersXSize, int playersYSize) {
		Player drone = null;
		
		for(Player p : players) {
			// If drone is at the location - return it
			if (p.isHere(x, y, playersXSize, playersYSize)) {
				return p;
			}
		}
		return drone;
	}
	
	public int checkPlayerLocation(double x, double y, double angle, int playersXSize, int playersYSize, int id, char type) {
//		Drone drone = null;
		
		for(Player p : players) {
			// If vertical collision - return 1
			if (p.collisionCheck(x, y, angle, playersXSize, playersYSize) == 1 && p.getID() != id) {
				if (p.type == 's' && type == 's') {
					return 1;					
				} else if (p.type == 's') {
					return 3;
				} else {
					return 1;					
				}
			}
			// If horizontal collision - return 2
			else if (p.collisionCheck(x, y, angle, playersXSize, playersYSize) == 2 && p.getID() != id) {
				if (p.type == 's' && type == 's') {
					return 2;					
				} else if (p.type == 's') {
					return 4;
				} else {
					return 2;										
				}
			}
		}
		
		for (Obstacle o : obstacles) {
			if (o.collisionCheck(x, y, angle, playersXSize, playersYSize) == 1) {
				return 5;					
			}else if (o.collisionCheck(x, y, angle, playersXSize, playersYSize) == 2) {
				return 6;					
			}
		}
		
		return 0;
	}
	
	/**
	 * info about arena
	 */
	public String toString() {
		String str = "Arena size: x = " + xSize + " y = " + ySize + "\n\n";

		for (Player i : players) {
			str += i.toString()  + "\n";
		}
		return str;
	}
}
