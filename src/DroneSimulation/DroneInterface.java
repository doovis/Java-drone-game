package DroneSimulation;

import java.util.Scanner;

public class DroneInterface {
	private Scanner s;						// scanner used for input from user
	private DroneArena myArena;				// arena in which drones are shown

	/**
	 * constructor for DroneInterface
	 * sets up scanner used for input and the arena
	 * then has main loop allowing user to enter commands
	 */
	public DroneInterface() {
		s = new Scanner(System.in);			// set up scanner for user input
		myArena = new DroneArena(20, 6);		// create arena of size 20*6

		char ch = ' ';
	    do {
	    	System.out.print("Enter (A)dd drone, get (I)nformation, (D)isplay arena, (M)ove drones, (S)imulation or e(X)it > ");
	    	ch = s.next().charAt(0);
	    	s.nextLine();
	    	switch (ch) {
				case 'A':
				case 'a':
					myArena.addDrone();			// add a new drone to arena
					break;
	    		case 'I':
	    		case 'i':
					System.out.print(myArena.toString());
					break;
	    		case 'x': 	ch = 'X';			// when X detected program ends
					break;
	    		case 'D':
	    		case 'd':
	    			doDisplay();				// Display arena with drones inside
	    			break;
	    		case 'M':
	    		case 'm':
	    			myArena.moveAllDrones();
	    			doDisplay();				// Display arena with drones inside
	    			System.out.print(myArena.toString());
	    			break;
	    		case 'S':
	    		case 's':
	    			int count = 0;
	    			while (count < 10) {
	    				
	    				myArena.moveAllDrones();
	    				System.out.print(myArena.toString());
	    				doDisplay();				// Display arena with drones inside
	    				count++;
	    				try {
	    					Thread.sleep(150);		
	    				} catch (InterruptedException e) {
	    					e.printStackTrace();
	    				}
	    			}
	    			break;
	    	}
		} while (ch != 'X');					// test if end

	   s.close();								// close scanner
	}
	
	/**
	 * Displaying drone arena
	 */
	void doDisplay() {
		int xSizeArena = myArena.xSize();
		int ySizeArena = myArena.ySize();
		ConsoleCanvas cnv = new ConsoleCanvas(xSizeArena, ySizeArena);	// Creating appropriate size canvas object 
		
		myArena.showDrones(cnv);
		System.out.print(cnv.toString());		// printing 2D array in console
	}
	
	public static void main(String[] args) {
		new DroneInterface();	// call to the interface
	}
}