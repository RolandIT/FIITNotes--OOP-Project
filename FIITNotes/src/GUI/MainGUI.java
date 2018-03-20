package GUI;

import javafx.application.*;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

import Main.MainInstance;
import Users.LoginHandler;


public class MainGUI extends Application{
	//LogPane
	private TextField name = new TextField();
	private Button login = new Button("Login");
	private Label NLab = new Label("Name:");
	private Label PLab = new Label("Password:");
	private Button newAcc =new Button("Create a new account");
	private Label loginFail = new Label();
	PasswordField password = new PasswordField();
	
	//MainPane
	private Label logName=new Label();
	
	public void start(Stage FIITNotes) {
		
		FlowPane LogPane = new FlowPane(10,10);
		FlowPane MainPane = new FlowPane(10,10);
		
		
		
		LogPane.getChildren().add(NLab);
		LogPane.getChildren().add(name);
		LogPane.getChildren().add(PLab);
		LogPane.getChildren().add(password);
		LogPane.getChildren().add(login);
		LogPane.getChildren().add(newAcc);
		LogPane.getChildren().add(loginFail);
		loginFail.setTextFill(Color.RED);
		
		//Layout settings
		LogPane.setAlignment(Pos.CENTER);
		LogPane.setOrientation(Orientation.VERTICAL);
		LogPane.setVgap(10);
		LogPane.setHgap(10);
		LogPane.setPrefWrapLength(5);

		MainPane.getChildren().add(logName);	
		
		
		Scene LogScene = new Scene (LogPane,300,400);
		Scene MainWindow =new Scene(MainPane,1000,800);
		
		FIITNotes.setScene(LogScene);
		FIITNotes.setTitle("FIITNotes Login");
		FIITNotes.show();
		
		//Button action 
		login.setOnAction(e -> {
			LoginHandler LH=new LoginHandler(name.getText(),password.getText());
			if(LH.Handle())
			{
				MainInstance main = new MainInstance(LH.getUser());
				FIITNotes.setScene(MainWindow);
				FIITNotes.setTitle("FIITNotes");
				logName.setText("logged in as: "+(LH.getUser()).getName());}
			else
				loginFail.setText("Invalid username or password");}
			
		);
				
}
	 public static void main(String[] args) {
	        launch(args);
	    }
}

