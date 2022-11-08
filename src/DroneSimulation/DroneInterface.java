package DroneSimulation;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

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
		int arenaXInput;
		int arenaYInput;
	    do {
	    	System.out.print("Enter (A)dd drone, get (I)nformation, (D)isplay arena, (M)ove drones,\n"
	    			+ "(N)ew arena, Save arena to (F)ile, (L)oad arena file, (S)imulation or e(X)it > ");
	    	ch = s.next().charAt(0);
	    	s.nextLine();
	    	switch (ch) {
	    		/**
	    		 * Adding drone to random position
	    		 */
				case 'A':
				case 'a':
					myArena.addDrone();			// add a new drone to arena
					break;
					
				/**
				 * Printing information about arena
				 */
	    		case 'I':
	    		case 'i':
					System.out.print(myArena.toString());
					break;
					
				/**
				 * Displaying arena with drones
				 */
	    		case 'D':
	    		case 'd':
	    			doDisplay();				// Display arena with drones inside
	    			break;
	    			
	    		/**
	    		 * Moving drones by 1 frame and displaying arena
	    		 */
	    		case 'M':
	    		case 'm':
	    			myArena.moveAllDrones();
	    			doDisplay();				// Display arena with drones inside
	    			System.out.print(myArena.toString());
	    			break;
	    			
	    		/**
	    		 * Simulating drone movement by 10 frames
	    		 */
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
	    			
	    		/**
	    		 * Creating a new arena
	    		 */
	    		case 'N':
	    		case 'n':
	    			// Taking x, y input
	    			System.out.println("Enter x size of the new arena: ");
	    			arenaXInput = s.nextInt();
	    			s.nextLine();
	    			System.out.println("Enter y size of the new arena: ");
	    			arenaYInput = s.nextInt();
	    			s.nextLine();
	    			
	    			myArena = new DroneArena(arenaXInput, arenaYInput);
	    			
	    			System.out.println("New arena: X= " + arenaXInput + ", Y= " + arenaYInput);
	    			break;
	    			
	    		/**
	    		 * Saving arena information to a file
	    		 */
	    		case 'F':
	    		case 'f':
	    			// !!!!!!!!!!!!!!!!!TODO!!!!!!!!!!!!!!!!!!!!!!!!!
	    			/**
	    			 * WEEK 5, TASK 5.8 READ/WRITE OBJECTS IN SEPARATE CLASSES AND SERIALIZABLE
	    			 */
	    			// !!!!!!!!!!!!!!!!!TODO!!!!!!!!!!!!!!!!!!!!!!!!!
	    				    			
	    			// outputting arena info to the file
	    			writingToFile(myArena);
	    			break;
	    			
	    		/**
	    		 * Loading arena from a file
	    		 */
	    		case 'L':
	    		case 'l':
	    			ArrayList<String> data = readingFromFile();
	    			
	    			String[] arenaSize = data.get(0).split(" ");	// Splitting first line string when whitespace detected
	    			
	    			// reinitialising new arena
	    			myArena = new DroneArena(Integer.parseInt(arenaSize[0].trim()), Integer.parseInt(arenaSize[1].trim()));

	    			// Populating arena with drones
	    			for (int i = 1; i < data.size(); i++) {
	    				String[] droneInfo = data.get(i).split(" ");
	    					    				
	    				Drone drone = new Drone(Integer.parseInt(droneInfo[0]), Integer.parseInt(droneInfo[1]), Direction.direction.valueOf(droneInfo[2].trim()));
	    				
	    				myArena.drn.add(drone);
	    			}
	    			
	    			break;
    			/**
    			 * exiting program
    			 */
	    		case 'x': 	ch = 'X';			// when X detected program ends
	    			break;
	    	}
		} while (ch != 'X');					// test if end

	   s.close();								// close scanner
	}
	
	FileFilter filter = new FileFilter() {
		@Override
		public boolean accept(File f) {
			if (f.getAbsolutePath().endsWith(".txt")) return true;
			if (f.isDirectory()) return true;
			return false;
		}
		
		@Override
		public String getDescription() {
			return "txt";
		}
	};
	
	/**
	 * Writing to selected file
	 */
	public void writingToFile(DroneArena arena) {
		JFileChooser Chooser = new JFileChooser();
		Chooser.setFileFilter(filter);
		int value = Chooser.showSaveDialog(null);
		
		
		if (value == JFileChooser.APPROVE_OPTION) {
			File file = Chooser.getSelectedFile();
			// Writing in plain text
			try {
				FileWriter outFileWriter = new FileWriter(file);
				PrintWriter writer = new PrintWriter(outFileWriter);
				writer.print(arena.xSize() + " ");
				writer.print(arena.ySize());
				writer.print("\n");
				for (int i = 0; i < myArena.drn.size(); i++) {
					writer.print(myArena.drn.get(i).getX() + " ");
					writer.print(myArena.drn.get(i).getY() + " ");
					writer.println(myArena.drn.get(i).dir);
				}
				
				writer.close();
			}
			
			// Writing in binary
/*			try {
				FileOutputStream outStream = new FileOutputStream(file);
				DataOutputStream outDataStream = new DataOutputStream(outStream);
//				for (int i : input)
					outDataStream.writeInt(arena.xSize());
					outDataStream.writeInt(arena.ySize());
				outDataStream.close();
			} */catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Arena saved to a file: " + file.getName());
		} else {
			System.out.println("Arena saving was unsuccessful");			
		}
	}

	/**
	 * reading from selected file
	 */
	public ArrayList<String> readingFromFile() {
		JFileChooser Chooser = new JFileChooser();
		int value = Chooser.showOpenDialog(null);
		ArrayList<String> str = new ArrayList<String>();
		String input = "";

		if (value == JFileChooser.APPROVE_OPTION) {
			File file = Chooser.getSelectedFile();
			
			try {
				FileReader fileReader = new FileReader(file);
				BufferedReader bufReader = new BufferedReader(fileReader);
				StringBuilder sb = new StringBuilder();
				String line = bufReader.readLine();
				
				// Reading each line
				while (line != null) {
					sb.append(line);
					sb.append(System.lineSeparator());
					line = bufReader.readLine();
				}
				input = sb.toString();
				
				// Splitting each line to separate list elements
				String[] s = input.split("\n");
				
				// Adding each element to list array
				for (int i = 0; i < s.length; i++) {
					str.add(s[i]);
				}
				bufReader.close();
			}
			
			// Reading in binary
/*			try {
				FileInputStream inStream = new FileInputStream(file);
				DataInputStream inDataStream = new DataInputStream(inStream);
//				for (int i : input)
				i = inDataStream.readInt();
				System.out.println(i);
				inDataStream.close();
			}*/ catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Arena saved to a file: " + file.getName());
		} else {
			System.out.println("Arena saving was unsuccessful");			
		}
		return str;
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