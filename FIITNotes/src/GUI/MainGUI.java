package GUI;

import javafx.application.*;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.io.*;

import Main.MainInstance;


public class MainGUI extends Application{
	private TextField name = new TextField();
	private TextField password = new TextField();
	private Button login = new Button("Login");
	private Label menoLab = new Label("Name:");
	private Label passLab = new Label("Password:");
	private Button newAcc =new Button("Create a new account");
	public void start(Stage FIITNotes) {
		
		FlowPane LogPane = new FlowPane(10,10);
		FlowPane MainPane = new FlowPane(10,10);
		
		LogPane.getChildren().add(menoLab);
		LogPane.getChildren().add(name);
		LogPane.getChildren().add(passLab);
		LogPane.getChildren().add(password);
		LogPane.getChildren().add(login);
		LogPane.getChildren().add(newAcc);
		
		//Layout settings
		LogPane.setAlignment(Pos.CENTER);
		LogPane.setOrientation(Orientation.VERTICAL);
		LogPane.setVgap(10);
		LogPane.setHgap(10);
		LogPane.setPrefWrapLength(5);

		
		
		Scene LogScene = new Scene (LogPane,300,400);
		Scene MainWindow =new Scene(MainPane,1000,800);
		
		FIITNotes.setScene(LogScene);
		FIITNotes.setTitle("FIITNotes Login");
		FIITNotes.show();
		
		//Button action 
		login.setOnAction(e -> {
			MainInstance main = new MainInstance();
			FIITNotes.setScene(MainWindow);
			FIITNotes.setTitle("FIITNotes");}
		);
		
}
	 public static void main(String[] args) {
	        launch(args);
	    }
}

