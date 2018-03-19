package GUI;

import Main.Main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainGUI extends Application{
	public void start(Stage FIITNotes) {
		FIITNotes.setTitle("FIITNotes");
		
		FlowPane pane = new FlowPane();
		
		Main mainWindow = new Main();
		

		//pane.getChildren().add(skrolVypis);
		FIITNotes.setScene(new Scene(pane, 1000,800));
		FIITNotes.show();
}
	 public static void main(String[] args) {
	        launch(args);
	    }
}

