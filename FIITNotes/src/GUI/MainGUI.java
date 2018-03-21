package GUI;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import Main.MainInstance;
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
		
		//main=new MainInstance();
		
		BorderPane MainPane = new BorderPane();
		GridPane MainCenter=new GridPane();
		FlowPane MainRight=new FlowPane();
		VBox MainLeft =new VBox();
		HBox MainTop = new HBox();
		
		MainPane.setTop(MainTop);
		MainPane.setLeft(MainLeft);         
		MainPane.setCenter(MainCenter);
		MainPane.setRight(MainRight);
		
	
		
		MainTop.getChildren().add(logName);	
		MainTop.getChildren().add(logOut);
		logName.setText("Logged in as : "+(main.getCurrentUser()).getName());
		
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

