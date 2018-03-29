package GUI;

import java.io.IOException;

import Main.MainInstance;
import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class LoginGUI extends Application{
		MainInstance mainInstance;
		
		//login nodes 
		private TextField name = new TextField();
		private Button login = new Button("Login");
		private Label NLab = new Label("Name:");
		private Label PLab = new Label("Password:");
		private Button newAcc =new Button("Create a new account");
		private Label loginFail = new Label();
		private PasswordField password = new PasswordField();
		
		//new account nodes 
		private Label newName = new Label("Enter new username:");
		private TextField newNameT = new TextField();
		private Label newPassword = new Label("Enter your password:");
		private PasswordField newPasswordT = new PasswordField();
		private Button confirm = new Button("Confirm");
		private Label errMess = new Label();
		private Button existingUser = new Button("Existing user");
		private RadioButton rbS = new RadioButton("Student");
		private RadioButton rbI = new RadioButton("Instructor");
		final ToggleGroup rbgroup = new ToggleGroup();
		
	public void start(Stage FIITNotes){
		//start a new main Instance to handle user data 
		mainInstance=new MainInstance();
		
		
		FlowPane LogPane = new FlowPane(10,10);
		FlowPane NewAccPane = new FlowPane(10,10);
		
		//login nodes added 
		LogPane.getChildren().add(NLab);
		LogPane.getChildren().add(name);
		LogPane.getChildren().add(PLab);
		LogPane.getChildren().add(password);
		LogPane.getChildren().add(login);
		LogPane.getChildren().add(newAcc);
		LogPane.getChildren().add(loginFail);
		loginFail.setTextFill(Color.RED);
		
		//new account nodes added 
		NewAccPane.getChildren().add(newName);
		NewAccPane.getChildren().add(newNameT);
		NewAccPane.getChildren().add(newPassword);
		NewAccPane.getChildren().add(newPasswordT);
		NewAccPane.getChildren().add(confirm);
		NewAccPane.getChildren().add(existingUser);
		NewAccPane.getChildren().add(errMess);
		NewAccPane.getChildren().add(rbS);
		NewAccPane.getChildren().add(rbI);
		errMess.setTextFill(Color.RED);
		rbS.setToggleGroup(rbgroup);
		rbS.setUserData(new String("Student"));
		rbI.setUserData(new String("Instructor"));
		rbS.setSelected(true);
		rbI.setToggleGroup(rbgroup);
		
		//Layout settings
		LogPane.setAlignment(Pos.CENTER);
		LogPane.setOrientation(Orientation.VERTICAL);
		LogPane.setVgap(10);
		LogPane.setHgap(10);
		LogPane.setPrefWrapLength(5);
		
		NewAccPane.setAlignment(Pos.CENTER);
		NewAccPane.setOrientation(Orientation.VERTICAL);
		NewAccPane.setVgap(10);
		NewAccPane.setHgap(10);
		NewAccPane.setPrefWrapLength(5);
		
		//scene settings 
		Scene LogScene = new Scene (LogPane,300,400);
		Scene NewAccScene = new Scene(NewAccPane,300,400);
		
		FIITNotes.setScene(LogScene);
		FIITNotes.setTitle("FIITNotes Login");
		FIITNotes.show();
		
		//Button action 
		
		//call the login handler to check for user 
		//if the user exists open a new main GUI
		//display error message if login fails
		login.setOnAction(e -> {
			if(mainInstance.UHandler.LoginHandle(name.getText(),password.getText()))
			{
				MainGUI main = new MainGUI(mainInstance);
				main.start(FIITNotes);
			}
			else
				loginFail.setText("Invalid username or password!");}			
		);
		
		//change scene to new account creation if clicked 
		newAcc.setOnAction(e->{
			FIITNotes.setScene(NewAccScene);
			FIITNotes.setTitle("FIITNotes -create a new account");
			FIITNotes.show();
		});
		
		//call handler to handle new user request 
		//if new user is created change scene to login 
		//else display an error message 
		confirm.setOnAction(e->{
			try {
				if(mainInstance.UHandler.NewUserHandle(newNameT.getText(), newPasswordT.getText(),(String)(rbgroup.getSelectedToggle().getUserData())))
				{
					FIITNotes.setScene(LogScene);
					FIITNotes.setTitle("FIITNotes Login");
					FIITNotes.show();
				}
				else
					errMess.setText("Username already in use!");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		//change scene back to login 
		existingUser.setOnAction(e->{
			FIITNotes.setScene(LogScene);
			FIITNotes.setTitle("FIITNotes Login");
			FIITNotes.show();
		});

	}
	 public static void main(String[] args) {
	        launch(args);
	    }

}
