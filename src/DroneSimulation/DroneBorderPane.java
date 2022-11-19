package DroneSimulation;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DroneBorderPane {
	DroneArena arena;
	
	DroneBorderPane(DroneArena Arena) {
		arena = Arena;
	}
	
	public Button addDroneBtn() {
		Button btn = new Button("Add drone");
		
		btn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				arena.addDrone();
				System.out.println(arena.drn.size());
				System.out.println(arena.drn.get(0).getXSize());
				System.out.println(arena.drn.get(0).getYSize());
			}
		});
		
		return btn;
	}
}
