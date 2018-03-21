package GUI;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.File;
import java.util.ArrayList;

import Main.MainInstance;
import Subjects.Subject;
import Users.User;



public class MainGUI extends Application{
	MainInstance main;
	
	
	//MainPane
	private Label logName=new Label();
	private Button logOut=new Button("Log Out");
	public MainGUI(User user) {
		main=new MainInstance(user);
	}
	
	public void start(Stage FIITNotes) {
		
		BorderPane MainPane = new BorderPane();
		FlowPane MainCenter=new FlowPane();
		FlowPane MainRight=new FlowPane();
		VBox MainLeft =new VBox();
		FlowPane MainTop = new FlowPane();
		
		MainPane.setTop(MainTop);
		MainPane.setLeft(MainLeft);         
		MainPane.setCenter(MainCenter);
		MainPane.setRight(MainRight);
		
	
		MainTop.getChildren().add(logName);	
		MainTop.getChildren().add(logOut);
		MainTop.setHgap(800);
		
		MainCenter.setAlignment(Pos.CENTER);
		MainCenter.setPadding(new Insets(10, 10, 10, 10)); 
		MainCenter.setHgap(50);
		MainCenter.setVgap(50);
		
		logName.setText("Logged in as : "+(main.getCurrentUser()).getName());
		
		for(Subject subj : main.subjects) {
			Button btn=new Button(subj.getSubjName());
			btn.setMinHeight(150);
			btn.setMinWidth(150);
			MainCenter.getChildren().add(btn);
		}
		Scene MainWindow =new Scene(MainPane,1000,800);
		FIITNotes.setScene(MainWindow);
		FIITNotes.setTitle("FIITNotes-"+(main.getCurrentUser()).getName());
		FIITNotes.show();
		
		logOut.setOnAction(e->{
			main=null;
			LoginGUI login=new LoginGUI();
			login.start(FIITNotes);
		}
		);
				
}

}

