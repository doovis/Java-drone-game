package DroneSimulation;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.awt.Paint;
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
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;

public class DroneInterface extends Application {
//	int canvasWidth = 854;
//	int canvasHeight = 480;
	int canvasWidth = 154;
	int canvasHeight = 110;
	boolean animationOn = true;
	GraphicsContext gc;
	VBox rtPane;
	UICanvas cnv;
	DroneArena arena;
//	Image drone = new Image(getClass().getResourceAsStream("./drone.png"));
//	private Scanner s;						// scanner used for input from user
//	private DroneArena myArena;				// arena in which drones are shown


	/**
	 * constructor for DroneInterface
	 * sets up scanner used for input and the arena
	 * then has main loop allowing user to enter commands
	 */
//	public DroneInterface() {
//		s = new Scanner(System.in);			// set up scanner for user input
//		myArena = new DroneArena(20, 6);		// create arena of size 20*6
//
//		char ch = ' ';
//		int arenaXInput;
//		int arenaYInput;
//	    do {
//	    	System.out.print("Enter (A)dd drone, get (I)nformation, (D)isplay arena, (M)ove drones,\n"
//	    			+ "(N)ew arena, Save arena to (F)ile, (L)oad arena file, (S)imulation or e(X)it > ");
//	    	ch = s.next().charAt(0);
//	    	s.nextLine();
//	    	switch (ch) {
//	    		/**
//	    		 * Adding drone to random position
//	    		 */
//				case 'A':
//				case 'a':
//					myArena.addDrone();			// add a new drone to arena
//					break;
//					
//				/**
//				 * Printing information about arena
//				 */
//	    		case 'I':
//	    		case 'i':
//					System.out.print(myArena.toString());
//					break;
//					
//				/**
//				 * Displaying arena with drones
//				 */
//	    		case 'D':
//	    		case 'd':
//	    			doDisplay();				// Display arena with drones inside
//	    			break;
//	    			
//	    		/**
//	    		 * Moving drones by 1 frame and displaying arena
//	    		 */
//	    		case 'M':
//	    		case 'm':
//	    			if (myArena.drn.size() != 0) {
//	    				myArena.moveAllDrones();	    				
//	    				doDisplay();				// Display arena with drones inside
//	    				System.out.print(myArena.toString());
//	    			} else {
//	    				System.out.print("Arena does not contain any drones...\n\n");
//	    			}
//	    			break;
//	    			
//	    		/**
//	    		 * Simulating drone movement by 10 frames
//	    		 */
//	    		case 'S':
//	    		case 's':
//	    			int count = 0;
//	    			if (myArena.drn.size() != 0) {
//		    			while (count < 10) {
//		    				
//		    				myArena.moveAllDrones();
//		    				System.out.print(myArena.toString());
//		    				doDisplay();				// Display arena with drones inside
//		    				count++;
//		    				try {
//		    					Thread.sleep(150);		
//		    				} catch (InterruptedException e) {
//		    					e.printStackTrace();
//		    				}
//		    			}
//	    			} else {
//	    				System.out.println("Arena does not contain any drones...\n\n");
//	    			}
//	    			break;
//	    			
//	    		/**
//	    		 * Creating a new arena
//	    		 */
//	    		case 'N':
//	    		case 'n':
//	    			try {
//		    			// Taking x, y input
//		    			System.out.println("Enter x size of the new arena: ");
//		    			arenaXInput = s.nextInt();
//		    			s.nextLine();
//		    			System.out.println("Enter y size of the new arena: ");
//		    			arenaYInput = s.nextInt();
//		    			s.nextLine();
//	    			} catch (InputMismatchException i) {
//	    				System.out.println("Something went wrong...\n\n");
//	    				break;
//	    			}
//	    			
//	    			myArena = new DroneArena(arenaXInput, arenaYInput);
//	    			
//	    			System.out.println("New arena: X= " + arenaXInput + ", Y= " + arenaYInput);
//	    			break;
//	    			
//	    		/**
//	    		 * Saving arena information to a file
//	    		 */
//	    		case 'F':
//	    		case 'f':
//	    			// !!!!!!!!!!!!!!!!!TODO!!!!!!!!!!!!!!!!!!!!!!!!!
//	    			/**
//	    			 * WEEK 5, TASK 5.8 READ/WRITE OBJECTS IN SEPARATE CLASSES AND SERIALIZABLE
//	    			 */
//	    			// !!!!!!!!!!!!!!!!!TODO!!!!!!!!!!!!!!!!!!!!!!!!!
//	    				    			
//	    			// outputting arena info to the file
//	    			writingToFile(myArena);
//	    			break;
//	    			
//	    		/**
//	    		 * Loading arena from a file
//	    		 */
//	    		case 'L':
//	    		case 'l':
//	    			ArrayList<String> data = readingFromFile();
//	    			
//	    			try {
//	    				String[] arenaSize = data.get(0).split(" ");	// Splitting first line string when whitespace detected
//
//	    				// reinitialising new arena
//	    				myArena = new DroneArena(Integer.parseInt(arenaSize[0].trim()), Integer.parseInt(arenaSize[1].trim()));
//
//	    				// Populating arena with drones
//	    				for (int i = 1; i < data.size(); i++) {
//	    					String[] droneInfo = data.get(i).split(" ");
//	    					
//	    					Drone drone = new Drone(Integer.parseInt(droneInfo[0]), Integer.parseInt(droneInfo[1]), Direction.direction.valueOf(droneInfo[2].trim()));
//	    					
//	    					myArena.drn.add(drone);
//	    				}
//	    			} catch (IndexOutOfBoundsException i) {
//	    				i.getMessage();
//	    			}
//	    			break;
//    			/**
//    			 * exiting program
//    			 */
//	    		case 'x': 	ch = 'X';			// when X detected program ends
//	    			break;
//	    	}
//		} while (ch != 'X');					// test if end
//
//	   s.close();								// close scanner
//	}
	
//	FileFilter filter = new FileFilter() {
//		@Override
//		public boolean accept(File f) {
//			if (f.getAbsolutePath().endsWith(".txt")) return true;
//			if (f.isDirectory()) return true;
//			return false;
//		}
//		
//		@Override
//		public String getDescription() {
//			return "txt";
//		}
//	};
	
	/**
	 * Writing to selected file
	 */
//	public void writingToFile(DroneArena arena) {
//		JFileChooser Chooser = new JFileChooser();
//		Chooser.setFileFilter(filter);
//		int value = Chooser.showSaveDialog(null);
//		
//		
//		if (value == JFileChooser.APPROVE_OPTION) {
//			File file = Chooser.getSelectedFile();
//			// Writing in plain text
//			try {
//				FileWriter outFileWriter = new FileWriter(file);
//				PrintWriter writer = new PrintWriter(outFileWriter);
//				writer.print(arena.xSize() + " ");
//				writer.print(arena.ySize());
//				writer.print("\n");
//				for (int i = 0; i < myArena.drn.size(); i++) {
//					writer.print(myArena.drn.get(i).getX() + " ");
//					writer.print(myArena.drn.get(i).getY() + " ");
//					writer.println(myArena.drn.get(i).dir);
//				}
//				
//				writer.close();
//			}
//			
//			// Writing in binary
///*			try {
//				FileOutputStream outStream = new FileOutputStream(file);
//				DataOutputStream outDataStream = new DataOutputStream(outStream);
////				for (int i : input)
//					outDataStream.writeInt(arena.xSize());
//					outDataStream.writeInt(arena.ySize());
//				outDataStream.close();
//			} */catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			System.out.println("Arena saved to a file: " + file.getName() + "\n");
//		} else {
//			System.out.println("Arena saving was unsuccessful...\n");			
//		}
//	}
//
//	/**
//	 * reading from selected file
//	 */
//	public ArrayList<String> readingFromFile() {
//		JFileChooser Chooser = new JFileChooser();
//		Chooser.setFileFilter(filter);
//		int value = Chooser.showOpenDialog(null);
//		ArrayList<String> str = new ArrayList<String>();
//		String input = "";
//
//		if (value == JFileChooser.APPROVE_OPTION) {
//			File file = Chooser.getSelectedFile();
//			
//			try {
//				FileReader fileReader = new FileReader(file);
//				BufferedReader bufReader = new BufferedReader(fileReader);
//				StringBuilder sb = new StringBuilder();
//				String line = bufReader.readLine();
//				
//				// Reading each line
//				while (line != null) {
//					sb.append(line);
//					sb.append(System.lineSeparator());
//					line = bufReader.readLine();
//				}
//				input = sb.toString();
//				
//				// Splitting each line to separate list elements
//				String[] s = input.split("\n");
//				
//				// Adding each element to list array
//				for (int i = 0; i < s.length; i++) {
//					str.add(s[i]);
//				}
//				bufReader.close();
//			}
//			
//			// Reading in binary
///*			try {
//				FileInputStream inStream = new FileInputStream(file);
//				DataInputStream inDataStream = new DataInputStream(inStream);
////				for (int i : input)
//				i = inDataStream.readInt();
//				System.out.println(i);
//				inDataStream.close();
//			}*/ catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			System.out.println("Arena loaded from a file: " + file.getName() + "\n");
//		} else {
//			System.out.println("Arena loading was unsuccessful...\n\n");			
//		}
//		return str;
//	}
	
	/**
	 * Displaying drone arena
	 */
//	void doDisplay() {
//		int xSizeArena = myArena.xSize();
//		int ySizeArena = myArena.ySize();
//		UICanvas cnv = new UICanvas(xSizeArena, ySizeArena);	// Creating appropriate size canvas object 
//		
//		myArena.showDrones(cnv);
//		System.out.print(cnv.toString());		// printing 2D array in console
//	}
	
	private MenuBar setMenu() {
		MenuBar menuBar = new MenuBar();		// create menu

		Menu mHelp = new Menu("Help");			// have entry for help
		// then add sub menus for About and Help
		// add the item and then the action to perform
		MenuItem mAbout = new MenuItem("About");
		mAbout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
//				showAbout();				// show the about message
			}
		});
		mHelp.getItems().addAll(mAbout); 	// add submenus to Help

		// now add File menu, which here only has Exit
		Menu mFile = new Menu("File");
		MenuItem mExit = new MenuItem("Exit");
		mExit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				System.exit(0);						// quit program
			}
		});
		mFile.getItems().addAll(mExit);

		menuBar.getMenus().addAll(mFile, mHelp);	// menu has File and Help

		return menuBar;					// return the menu, so can be added
	}
	
	private HBox setControlButtons() {		
		// animation on button
		Button btnAnimOn = new Button("Start Animation");
		btnAnimOn.getStyleClass().add("buttons");
		// animation on handler
		btnAnimOn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				animationOn = true;
			}
		});

		// animation off button
		Button btnAnimOff = new Button("Stop Animation");
		btnAnimOff.getStyleClass().add("buttons");
		// animation off handler
		btnAnimOff.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				animationOn = false;
			}
		});
		
		// add drone button
		Button btn = new Button("Add drone");
		btn.getStyleClass().add("buttons");
		// add drone handler
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (animationOn) {
					arena.addDrone();					
				}
//				System.out.println(arena.drn.size());
//				System.out.println(arena.drn.get(0).getXSize());
//				System.out.println(arena.drn.get(0).getYSize());
			}
		});

		HBox btnBox = new HBox(btnAnimOn, btnAnimOff, btn);
		btnBox.getStyleClass().add("button-box");
		
		return btnBox;
	}
	
	private VBox setDroneInfoList(ScrollPane scroll) {

		// creating VBox pane
		rtPane = new VBox();
		rtPane.getStyleClass().add("drone-info-box");
		rtPane.setAlignment(Pos.TOP_LEFT);
		
		for (int i = 0; i < arena.drn.size(); i++) {
			Label l = new Label(arena.drn.get(i).toString());
			rtPane.getChildren().addAll(l);		
		}
		
		// Adding VBox into the scroll pane
		scroll.setContent(rtPane);
		scroll.setMinViewportWidth(230);
		scroll.setMinViewportHeight(canvasHeight - 15);
		scroll.setMaxHeight(canvasHeight);
		
		return new VBox(listLabel(), scroll);
	}

	private VBox setCanvas(Group root) {
		return new VBox(canvasLabel(), root);
	}
	
	private Text canvasLabel() {
		Text title = new Text("Drone arena");
		
		title.setFont(Font.font("arial", FontWeight.NORMAL, 27));
		title.setFill(Color.rgb(50, 50, 50));
		title.setTranslateX(canvasWidth / 2 - 50);
		
		return title;
	}

	private Text listLabel() {
		Text title = new Text("Drone info");

		title.setFont(Font.font("arial", FontWeight.NORMAL, 27));
		title.setFill(Color.rgb(50, 50, 50));
		title.setTranslateX(60);
		
		return title;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// primary stage properties
		primaryStage.setTitle("Drone Simulator");
//		primaryStage.setResizable(false);

		// initialising group, border/scroll panes, scene, canvas and arena
		Group root = new Group();
		BorderPane bp = new BorderPane();
		ScrollPane scroll = new ScrollPane();
		Canvas canvas = new Canvas(canvasWidth, canvasHeight);
		Scene scene = new Scene(bp, canvasWidth * 1.3, canvasHeight * 1.3);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		gc = canvas.getGraphicsContext2D();
		cnv = new UICanvas(gc, canvasWidth, canvasHeight);
		arena = new DroneArena(canvasWidth, canvasHeight);
		
		// Placing elements into right places in border pane
		bp.setTop(setMenu());
		bp.setCenter(setCanvas(root));
		bp.setRight(setDroneInfoList(scroll));
		bp.setBottom(setControlButtons());
		
		// Adding canvas into the group
		root.getChildren().add(canvas);

		// Starting animation loop
		final long startNanoTime = System.nanoTime();
		new AnimationTimer() {

			@Override
			public void handle(long currentNanoTime) {
				if (animationOn) {
					double time = (currentNanoTime - startNanoTime) / 1000000000.0;
					arena.drawObjects(cnv, gc);
					arena.update();
					
					// Updating drone list
					bp.setRight(setDroneInfoList(scroll));
				}
			}
		}.start();

		// rendering window
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}