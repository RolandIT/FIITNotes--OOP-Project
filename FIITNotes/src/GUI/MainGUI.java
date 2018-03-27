package GUI;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import Main.MainInstance;
import Subjects.Subject;
import Users.User;

public class MainGUI extends Application{	
	private MainInstance main;
	//MainPane
	private Label logName = new Label();
	private Button logOut = new Button("Log Out");
	private Label subjectsLab = new Label("Subjects : ");
	private Label followedLab = new Label("Followed Subjects : ");
	
	public MainGUI(MainInstance main) {
		this.main=main;
	}
	public void start(Stage FIITNotes) {
		
		
		BorderPane MainPane = new BorderPane();
		FlowPane MainCenter = new FlowPane();
		VBox MainRight = new VBox();
		VBox MainLeft = new VBox();
		FlowPane MainTop = new FlowPane();
		
		MainPane.setTop(MainTop);
		MainPane.setLeft(MainLeft);         
		MainPane.setCenter(MainCenter);
		MainPane.setRight(MainRight);
		
	
		MainTop.getChildren().add(logName);	
		MainTop.getChildren().add(logOut);
		MainTop.setHgap(800);
		
		MainCenter.getChildren().add(subjectsLab);
		MainCenter.setAlignment(Pos.CENTER);
		MainCenter.setPadding(new Insets(10, 10, 10, 10)); 
		MainCenter.setHgap(50);
		MainCenter.setVgap(50);
		MainCenter.setStyle("-fx-background-color: grey;");
		logName.setText("Logged in as : "+(main.UHandler.getCurrentUser()).getName());
		
		MainLeft.getChildren().add(followedLab);
		for(Subject subj:(main.UHandler.getCurrentUser()).getFollowedSubjects())
		{
			Button btn = new Button(subj.getSubjName());
			btn.setMinHeight(150);
			btn.setMinWidth(150);
			MainLeft.getChildren().add(btn);
		}
		for(Subject subj : main.subjects) {
			Button btn = new Button(subj.getSubjName());
			btn.setMinHeight(150);
			btn.setMinWidth(150);
			MainCenter.getChildren().add(btn);
		}
		Scene MainWindow = new Scene(MainPane,1000,600);
		FIITNotes.setScene(MainWindow);
		FIITNotes.setTitle("FIITNotes-" + (main.UHandler.getCurrentUser()).getName());
		FIITNotes.show();
		
		logOut.setOnAction(e->{
			main = null;
			LoginGUI login = new LoginGUI();
			login.start(FIITNotes);
		}
		);
				
}

}

