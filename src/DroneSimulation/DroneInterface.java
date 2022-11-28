package DroneSimulation;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

public class DroneInterface extends Application {
	int canvasWidth = 1024;
	int canvasHeight = 576;
	boolean animationOn = true;
	boolean obstacleButton = false;
	GraphicsContext gc;
	VBox rtPane;
	UICanvas cnv;
	DroneArena arena;

//	    		/**
//	    		 * Creating a new arena
//	    		 */
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

	/**
	 * .txt file extension filter for file loading
	 */
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
	public void writingToFile() {
		JFileChooser Chooser = new JFileChooser();
		int value = Chooser.showSaveDialog(null);
		
		// If file valid
		if (value == JFileChooser.APPROVE_OPTION) {
			File file = Chooser.getSelectedFile();
			
			// Writing in plain text
			try {
				FileWriter outFileWriter = new FileWriter(file);
				PrintWriter writer = new PrintWriter(outFileWriter);
				
				// Writing drone information in a line
				for (int i = 0; i < arena.entities.size(); i++) {
					if (arena.entities.get(i).type != 'o') {
						writer.print(Math.floor(arena.entities.get(i).getX()) + " ");
						writer.print(Math.floor(arena.entities.get(i).getY()) + " ");
						writer.print(arena.entities.get(i).getAngle() + " ");
						writer.print(arena.entities.get(i).getHealth() + " ");
						writer.print(arena.entities.get(i).getID() + " ");
						writer.println(arena.entities.get(i).getType() + " ");						
					} else {
						writer.print(Math.floor(arena.entities.get(i).getX()) + " ");
						writer.print(Math.floor(arena.entities.get(i).getY()) + " ");
						writer.print(arena.entities.get(i).getID() + " ");
						writer.println(arena.entities.get(i).getType() + " ");						
					}
				}
				
				writer.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Arena saved to a file: " + file.getName() + "\n");
		} else {
			System.out.println("Arena saving was unsuccessful...\n");			
		}
	}

	/**
	 * reading from selected file
	 */
	public ArrayList<String> readingFromFile() {
		JFileChooser Chooser = new JFileChooser();
		Chooser.setFileFilter(filter);
		int value = Chooser.showOpenDialog(null);		// Opening file picker with .txt file extension filter
		ArrayList<String> str = new ArrayList<String>();
		String input = "";

		// If chosen file is valid
		if (value == JFileChooser.APPROVE_OPTION) {
			File file = Chooser.getSelectedFile();		// Obtaining selected file
			
			try {
				FileReader fileReader = new FileReader(file);
				BufferedReader bufReader = new BufferedReader(fileReader);
				StringBuilder sb = new StringBuilder();
				String line = bufReader.readLine();
				arena.entities = new ArrayList<Entity>();		// Initialising new entities array
				
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
				
				// Splitting each number in line into separate elements
				for (int i = 0; i < str.size(); i++) {
					String[] playerInfo = str.get(i).split(" ");
					
					// Constructing new arena object and adding to the list
					
					if ((int)playerInfo[3].charAt(0) == 111) {	// 111 == 'o'
						Obstacle obstacle = new Obstacle(Double.parseDouble(playerInfo[0]), Double.parseDouble(playerInfo[1]), Integer.parseInt(playerInfo[2]));
						arena.entities.add(obstacle);
					} else if ((int)playerInfo[5].charAt(0) == 114) {		// 114 == 'o'
						Drone player = new Drone(Double.parseDouble(playerInfo[0]), Double.parseDouble(playerInfo[1]), Integer.parseInt(playerInfo[2]), Integer.parseInt(playerInfo[3]), Integer.parseInt(playerInfo[4]));
						arena.entities.add(player);
					} else if ((int)playerInfo[5].charAt(0) == 115) {	// 115 == 's'
						StrongDrone player = new StrongDrone(Double.parseDouble(playerInfo[0]), Double.parseDouble(playerInfo[1]), Integer.parseInt(playerInfo[2]), Integer.parseInt(playerInfo[3]), Integer.parseInt(playerInfo[4]));
						arena.entities.add(player);
					}
				}
				bufReader.close();

				System.out.println("Arena loaded from a file: " + file.getName() + "\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Arena loading was unsuccessful...\n\n");			
		}
		return str;
	}
	
	/**
	 * Top menu
	 * @return
	 */
	private MenuBar setMenu() {
		MenuBar menuBar = new MenuBar();			// top menu bar
		Menu mInfo = new Menu("Info");				// help section
		
		MenuItem mAbout = new MenuItem("About");	// sub-menus in the help section
		MenuItem mHelp = new MenuItem("Help");
		MenuItem mCredits = new MenuItem("Credits");
		
		// On click event
		mAbout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				String message = "Drone Simulator GUI version.\n"
						+ "The simulator is transformed from the console version.";
				cnv.dialogBox("About", message);
			}
		});

		mHelp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				String message = "The simulator is laid out into 3 separate parts.\n"
						+ "Left side contains an arena which contains all the simulations occurring.\n"
						+ "Right side contains a list of every entity in the simulation and reports its location or angle and health for drone entitities.\n"
						+ "Bottom part contains interactive control buttons which let user interact with the simulation.\n"
						+ "Start/Pause animation - these buttons allow user to pause or resume the animation.\n"
						+ "Add drone/strong drone/obstacle - buttons allow user to add mentioned entities to the arena.";
				cnv.dialogBox("Help", message);
			}
		});

		mCredits.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				cnv.dialogBox("Credits", "The simulator was developed by Dovydas Mockevicius 2022.");
			}
		});
		
		mInfo.getItems().addAll(mAbout, mHelp, mCredits);		// adding sub-menus to Help

		
		Menu mFile = new Menu("File");				// File section
		
		// Sub-menus
		MenuItem mExit = new MenuItem("Exit");
		MenuItem mSave = new MenuItem("Save As...");
		MenuItem mOpen = new MenuItem("Open File...");
		
		// On click methods
		mExit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				System.exit(0);						// quit program
			}
		});
		mSave.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				writingToFile();					// Save arena
			}
		});
		mOpen.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent t) {
				readingFromFile();					// Open saved arena
			}
		});
		
		// adding sub-menus to File
		mFile.getItems().addAll(mSave, mOpen, mExit);

		menuBar.getMenus().addAll(mFile, mInfo);	// menu has File and Help

		return menuBar;								// returning menu bar
	}
	
	private HBox setControlButtons(Canvas canvas) {	
		Image play = new Image(getClass().getResourceAsStream("./img/play.png"));
		Image pause = new Image(getClass().getResourceAsStream("./img/pause.png"));
		Image plus = new Image(getClass().getResourceAsStream("./img/plus.png"));
				
		
		// animation on button
		ImageView playImg = new ImageView(play);
		playImg.setFitHeight(30);
		playImg.setFitWidth(30);
		Button btnAnimOn = new Button("START ANIMATION", playImg);
		btnAnimOn.getStyleClass().add("buttons");
		// animation on handler
		btnAnimOn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				animationOn = true;
				obstacleButton = false;
			}
		});

		// animation off button
		ImageView pauseImg = new ImageView(pause);
		pauseImg.setFitHeight(30);
		pauseImg.setFitWidth(30);
		Button btnAnimOff = new Button("STOP ANIMATION", pauseImg);
		btnAnimOff.getStyleClass().add("buttons");
		// animation off handler
		btnAnimOff.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				animationOn = false;
			}
		});
		
		// add drone button
		ImageView addImg = new ImageView(plus);
		addImg.setFitHeight(30);
		addImg.setFitWidth(30);
		Button addentitiesbtn = new Button("ADD DRONE", addImg);
		addentitiesbtn.getStyleClass().add("buttons");
		// add drone handler
		addentitiesbtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (animationOn) {
					arena.addObject('r');					
				} else {
					cnv.dialogBox("Start animation", "Please start animation in order to add a drone");
				}
			}
		});

		// add Strong drone button
		ImageView addImg2 = new ImageView(plus);
		addImg2.setFitHeight(30);
		addImg2.setFitWidth(30);
		Button addStrentitiesbtn = new Button("ADD STRONG DRONE", addImg2);
		addStrentitiesbtn.getStyleClass().add("buttons");
		// add drone handler
		addStrentitiesbtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (animationOn) {
					arena.addObject('s');
				} else {
					cnv.dialogBox("Start animation", "Please start animation in order to add a super drone");					
				}
			}
		});

		// add Stone obstacle button
		ImageView addImg3 = new ImageView(plus);
		addImg3.setFitHeight(30);
		addImg3.setFitWidth(30);
		Button addObstaclebtn = new Button("ADD OBSTACLE", addImg3);
		addObstaclebtn.getStyleClass().add("buttons");
		// add drone handler
		addObstaclebtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (!animationOn) {
					obstacleButton = true;
				} else {
					cnv.dialogBox("Stop animation", "In order to add an obstacle please stop animation.");
				}
			}
		});

		HBox btnBox = new HBox(btnAnimOn, btnAnimOff, addentitiesbtn, addStrentitiesbtn, addObstaclebtn);
		btnBox.getStyleClass().add("button-box");
		btnBox.setSpacing(10);
		
		return btnBox;
	}

	private VBox setDroneInfoList(ScrollPane scroll) {

		// creating VBox pane
		rtPane = new VBox();
		rtPane.getStyleClass().add("drone-info-box");
		rtPane.setAlignment(Pos.TOP_LEFT);
		rtPane.setMinWidth(304);
		rtPane.setMinHeight(canvasHeight - 1);
		
		// entities list
		for (int i = 0; i < arena.entities.size(); i++) {
			Label l = new Label(arena.entities.get(i).toString());
			l.setTextFill(Color.WHITE);
			rtPane.getChildren().addAll(l);		
		}

		// Adding VBox into the scroll pane
		scroll.setContent(rtPane);
		scroll.setPrefViewportWidth(290);
		scroll.setMinViewportWidth(290);
		scroll.setMinViewportHeight(canvasHeight - 15);
		scroll.setMaxHeight(canvasHeight);
		scroll.getStyleClass().add("list-inside");

		VBox list = new VBox(listLabel(), scroll);
		
		list.getStyleClass().add("list");

		
		return list;
	}
	
	
	// Mouse event handler for obstacle addition
	public void mouseEvent(Canvas canvas) {
		// Mouse movement handler
		canvas.addEventHandler(MouseEvent.MOUSE_MOVED, 
			new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (!animationOn && obstacleButton) {
					cnv.clearCanvas();
					arena.drawObjects(cnv, gc);
					Obstacle o = new Obstacle(e.getX() - 20, e.getY() - 20);
					o.displayPlayer(cnv);
				}
			}
		});
		
		// Mouse click handler
		canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
			new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (!animationOn && obstacleButton) {
					boolean isHere = false;
					for (Entity p : arena.entities) {
						if (p.isHere(e.getX() - (p.getXSize() / 2), e.getY() - (p.getYSize() / 2), p.getXSize(), p.getYSize())) {
							isHere = true;
						}
					}
					if (!isHere) {
						arena.addObject(e.getX() - 20, e.getY() - 20, 'o');
						obstacleButton = false;
					} else {
						cnv.dialogBox("Occupied space", "Please place the obstacle in an unoccupied area.");
					}
				}
			}
		});
	}

	/**
	 * Placing canvas into VBox element with label
	 * @param root
	 * @return
	 */
	private VBox setCanvas(Group root) {
		// Creating title
		Text title = new Text("ARENA");
		title.setFont(Font.font("agency FB", FontWeight.NORMAL, 36));
		title.setFill(Color.rgb(255, 255, 255));
		title.setTranslateX(canvasWidth / 2 - 50);
		
		// Creating VBox with label and canvas
		VBox cnv = new VBox(title, root);
		cnv.getStyleClass().add("canvas");
		
		return cnv;
	}
	
	/**
	 * Label for the entity list
	 * @return
	 */
	private Text listLabel() {
		Text title = new Text("PLAYER INFO");
		title.setFont(Font.font("agency FB", FontWeight.NORMAL, 36));
		title.setFill(Color.rgb(255, 255, 255));
		title.setTranslateX(80);
		
		return title;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// primary stage properties
		primaryStage.setTitle("Drone Simulator");
		primaryStage.setResizable(false);
		
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
		bp.setBottom(setControlButtons(canvas));
		
		// Adding canvas into the group
		root.getChildren().add(canvas);
		mouseEvent(canvas);

		// Starting animation loop
//		final long startNanoTime = System.nanoTime();
		new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				if (animationOn) {
//					double time = (currentNanoTime - startNanoTime) / 1000000000.0;
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