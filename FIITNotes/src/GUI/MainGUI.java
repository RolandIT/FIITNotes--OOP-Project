package GUI;

import javafx.application.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;

import java.io.IOException;
import java.util.ArrayList;

public class MainGUI extends Application{	
	//controller
	private GUIController controller;
	
	//nodes on the main window
	private Label logName = new Label();
	private Button logOut = new Button("Log Out");
	private Label subjectsLab = new Label("Subjects : ");
	private Label followedLab = new Label("Followed Subjects : ");
	private Button addNewSubject = new Button("Add Subject");
	private Label newSubNameL = new Label("Subject Name");
	private TextField newSubNameT = new TextField();
	private Label errMess = new Label();
	private ArrayList<Button> subjButtons = new ArrayList<Button>();
	private ArrayList<Button> followedSubjButtons = new ArrayList<Button>();
	
	//nodes on the subject window 
	private Button backB = new Button("Back");
	private Button followSubj = new Button("Follow");
	private Button removeSubj = new Button("Delete subject");
	
	//Constructor for the main GUI, MainInstance object main is passed down on
	//creation so current instance information is accessible 
	public MainGUI(GUIController controller) {
		this.controller = controller;
	}
	
	public void start(Stage FIITNotes) {
		
		FIITNotes.setTitle("FIITNotes - Student");
		
		//Panes on the main Window
		BorderPane MainPane = new BorderPane();
		FlowPane MainCenter = new FlowPane();
		GridPane MainRight = new GridPane();
		VBox MainLeft = new VBox();
		FlowPane MainTop = new FlowPane();
		
		MainPane.setTop(MainTop);
		MainPane.setLeft(MainLeft);         
		MainPane.setCenter(MainCenter);
		MainPane.setRight(MainRight);
		
		//Panel Nodes
		
		//Right panel nodes added only if the current user is of type Instructor
		MainRight.add(addNewSubject,0,0);
		MainRight.add(newSubNameL,0,1);
		MainRight.add(newSubNameT,0,2);
		MainRight.add(errMess, 0, 3);
		controller.setUserScene(MainRight,FIITNotes);
		
		
		errMess.setTextFill(Color.RED);
		//Left panel
		MainLeft.getChildren().add(followedLab);
		
		//Top panel
		MainTop.getChildren().add(logName);	
		MainTop.getChildren().add(logOut);
		logName.setText("Logged in as : "+(controller.main.UHandler.getCurrentUser()).getName());
		//Center panel
		MainCenter.getChildren().add(subjectsLab);
		
		//Button added to the left Pane for each subject 
		//that the current user follows 
		followedSubjButtons = controller.setFollowedSubjButtons(150,150);
		for(Button btn : followedSubjButtons)
			MainLeft.getChildren().add(btn);
			
		//button added to the center Pane for each existing subject in the current instance 
		subjButtons=controller.setSubjButtons(150,150);
		for(Button btn : subjButtons) {
			MainCenter.getChildren().add(btn);
		}
		
		//Layout settings
		MainTop.setHgap(800);
		
		GridPane.setMargin(addNewSubject, new Insets(15, 0, 0, 0));
		GridPane.setHalignment(addNewSubject, HPos.CENTER);
		GridPane.setHalignment(newSubNameL, HPos.CENTER);
		GridPane.setHalignment(errMess, HPos.CENTER);
		MainRight.setVgap(20);
		
		MainCenter.setPadding(new Insets(10, 10, 10, 10)); 
		MainCenter.setHgap(50);
		MainCenter.setVgap(50);
		MainCenter.setStyle("-fx-background-color: grey;");
		backB.setAlignment(Pos.TOP_RIGHT);
		
		
		
		//Scene settings
		Scene MainWindow = new Scene(MainPane,1000,600);
		FIITNotes.setScene(MainWindow);
		FIITNotes.show();
		
		ArrayList<Node> studentNodes=new ArrayList<Node>();
		studentNodes.add(followSubj);
		studentNodes.add(backB);
		
		ArrayList<Node> instructorNodes=new ArrayList<Node>();
		instructorNodes.add(removeSubj);
		
		//Adds listeners for new subjects creation 
		controller.addNewSubjectListener(MainCenter,subjButtons,studentNodes,instructorNodes);
		controller.addNewFollowedSubjListener(MainLeft,MainCenter,followedSubjButtons,studentNodes,instructorNodes);
		
		//Button action 
		//change the center pane when a subject button is clicked
		//according to the subject it belongs to 
		for(Button btn : subjButtons) {
			btn.setOnAction(e->{
				controller.setSubjButtonAction(MainCenter,studentNodes,instructorNodes,btn.getText());
				controller.main.SHandler.setCurrentSubject(btn.getText());
			});
		}
		
		//change the center pane when a subject button is clicked
		//according to the subject it belongs to
		for(Button btn : followedSubjButtons) {
			btn.setOnAction(e->{
				controller.setSubjButtonAction(MainCenter,studentNodes,instructorNodes,btn.getText());
				controller.main.SHandler.setCurrentSubject(btn.getText());
			});
		}
		
		//handle a new follow request when clicked 
		followSubj.setOnAction(e->{
			try {
				controller.main.SHandler.newFollowHandler(controller.main.UHandler.getCurrentUser(),controller.main.SHandler.getCurrentSubject());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
		//restore the center pane to the default settings
		backB.setOnAction(e->{
			MainCenter.getChildren().clear();
			MainCenter.getChildren().add(subjectsLab);
			for(Button btn : subjButtons) {
				MainCenter.getChildren().add(btn);
			}
		});
		
		//delete the current instance of main 
		//call the login GUI 
		logOut.setOnAction(e->{
			controller.main = null;
			LoginGUI login = new LoginGUI();
			login.start(FIITNotes);
		}
		);
		
		//clear the new subject panel if the subject handler 
		//returns true , display error message if false 
		addNewSubject.setOnAction(e->{
				errMess.setText(controller.setAddNewSubjectButton(newSubNameT.getText()));	
				newSubNameT.clear();
		});
		
		//TODO
		removeSubj.setOnAction(e->{
			controller.removeSubject(subjButtons,followedSubjButtons);
			
			MainLeft.getChildren().clear();
			MainLeft.getChildren().add(followedLab);
			MainCenter.getChildren().clear();
			MainCenter.getChildren().add(subjectsLab);
			
			for(Button btn : subjButtons) {
				MainCenter.getChildren().add(btn);
			}
			
			for(Button btn : followedSubjButtons) {
				MainLeft.getChildren().add(btn);
			}
		});	
}

}

