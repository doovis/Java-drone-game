package DroneSimulation;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	int canvasWidth = 854;
	int canvasHeight = 480;
	boolean animationOn = true;
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
//	    			
//	    			System.out.println("New arena: X= " + arenaXInput + ", Y= " + arenaYInput);
//	    			break;

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
				for (int i = 0; i < arena.drn.size(); i++) {
					writer.print(Math.floor(arena.drn.get(i).getX()) + " ");
					writer.print(Math.floor(arena.drn.get(i).getY()) + " ");
					writer.print(arena.drn.get(i).getAngle() + " ");
					writer.print(arena.drn.get(i).getHealth() + " ");
					writer.println(arena.drn.get(i).getID() + " ");
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
				arena.drn = new ArrayList<Drone>();		// Initialising new drone array
				
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
					String[] droneInfo = str.get(i).split(" ");
					
					// Constructing new drone and adding to the list
					Drone drone = new Drone(Double.parseDouble(droneInfo[0]), Double.parseDouble(droneInfo[1]), Integer.parseInt(droneInfo[2]), Integer.parseInt(droneInfo[3]), Integer.parseInt(droneInfo[4]));
					arena.drn.add(drone);
				}
				
				bufReader.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Arena loaded from a file: " + file.getName() + "\n");
		} else {
			System.out.println("Arena loading was unsuccessful...\n\n");			
		}
		return str;
	}
	
	/**
	 * About dialog box
	 */
	private void showAbout() {
		Alert alert = new Alert(AlertType.INFORMATION);				// Instantiating dialog box
		alert.setTitle("About");
		alert.setHeaderText(null);
		alert.setContentText("Drone Simulator in GUI format.\nThe simulator is transformed from the console version.");			// give text
		alert.showAndWait();	
	}
	
	/**
	 * Top menu
	 * @return
	 */
	private MenuBar setMenu() {
		MenuBar menuBar = new MenuBar();			// top menu bar
		Menu mHelp = new Menu("Help");				// help section
		
		MenuItem mAbout = new MenuItem("About");	// about sub-menu in the help section
		
		// On click event
		mAbout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent actionEvent) {
				showAbout();						// showing the about message
			}
		});
		
		mHelp.getItems().addAll(mAbout);			// adding sub-menus to Help

		
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

		menuBar.getMenus().addAll(mFile, mHelp);	// menu has File and Help

		return menuBar;								// returning menu bar
	}
	
	private HBox setControlButtons() {		
		// animation on button
		Image play = new Image(getClass().getResourceAsStream("./img/play.png"));
		ImageView playImg = new ImageView(play);
		playImg.setFitHeight(30);
		playImg.setFitWidth(30);
		Button btnAnimOn = new Button("Start Animation", playImg);
		btnAnimOn.getStyleClass().add("buttons");
		// animation on handler
		btnAnimOn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				animationOn = true;
			}
		});

		// animation off button
		Image pause = new Image(getClass().getResourceAsStream("./img/pause.png"));
		ImageView pauseImg = new ImageView(pause);
		pauseImg.setFitHeight(30);
		pauseImg.setFitWidth(30);
		Button btnAnimOff = new Button("Stop Animation", pauseImg);
		btnAnimOff.getStyleClass().add("buttons");
		// animation off handler
		btnAnimOff.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				animationOn = false;
			}
		});
		
		// add drone button
		Image plus = new Image(getClass().getResourceAsStream("./img/plus.png"));
		ImageView addImg = new ImageView(plus);
		addImg.setFitHeight(30);
		addImg.setFitWidth(30);
		Button btn = new Button("Add drone", addImg);
		btn.getStyleClass().add("buttons");
		// add drone handler
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				if (animationOn) {
					arena.addDrone();					
				}
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