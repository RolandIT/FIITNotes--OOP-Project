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

import Main.MainInstance;
import Subjects.NewFollowedSubj;
import Subjects.NewSubjectListener;
import Subjects.Subject;

public class MainGUI extends Application{	
	//Main class
	private MainInstance main;
	
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
	public MainGUI(MainInstance main) {
		this.main = main;
	}
	
	public void start(Stage FIITNotes) {
		
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
		if((main.UHandler.getCurrentUser().getUserType()).equals("Instructor"))
		{
			MainRight.add(addNewSubject,0,0);
			MainRight.add(newSubNameL,0,1);
			MainRight.add(newSubNameT,0,2);
			MainRight.add(errMess, 0, 3);
		}
		errMess.setTextFill(Color.RED);
		//Left panel
		MainLeft.getChildren().add(followedLab);
		
		//Top panel
		MainTop.getChildren().add(logName);	
		MainTop.getChildren().add(logOut);
		logName.setText("Logged in as : "+(main.UHandler.getCurrentUser()).getName());
		//Center panel
		MainCenter.getChildren().add(subjectsLab);
		//Button added to the left Pane for each subject 
		//that the current user follows 
		for(Subject subj:((main.UHandler.getCurrentUser()).getFollowedSubjects()))
		{
			Button btn = new Button(subj.getSubjName());
			btn.setMinHeight(150);
			btn.setMinWidth(150);
			MainLeft.getChildren().add(btn);
			followedSubjButtons.add(btn);//button added to the array of subject buttons
		}
		
		//button added to the center Pane for each existing subject in the current instance  
		for(Subject subj : main.subjects) {
			Button btn = new Button(subj.getSubjName());
			btn.setMinHeight(150);
			btn.setMinWidth(150);
			MainCenter.getChildren().add(btn);
			subjButtons.add(btn);//button added to the array of subject buttons
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
		
		//observer pattern - listener added to new subject listeners 
		main.SHandler.addListener(new NewSubjectListener() {
			//Anonymous class with onNewSubject method implemented
			//onNewSubject adds a new button to the center Pane each 
			//time its called by the Handler
			@Override
			public void onNewSubject() {
				Button btn = new Button(main.subjects.get((main.subjects.size())-1).getSubjName());
				btn.setMinHeight(150);
				btn.setMinWidth(150);
				MainCenter.getChildren().add(btn);
				subjButtons.add(btn);
				
				//button action 
				btn.setOnAction(e->{
					main.SHandler.setCurrentSubject(btn.getText());
					MainCenter.getChildren().clear();
					MainCenter.getChildren().add(followSubj);
					MainCenter.getChildren().add(backB);
					//remove subject button only added if the use is the owner of the subject
					if((main.SHandler.getCurrentSubject().getOwnerID())==(main.UHandler.getCurrentUser().getID()))
						MainCenter.getChildren().add(removeSubj);	
				});
			}
		});
		//observer pattern - listener added to newFollowedSubject listeners
		main.SHandler.addListener(new NewFollowedSubj() {
			//Anonymous class with onNewFollowed method implemented
			//onNewFollowed adds a new button to the left Pane each 
			//time its called by the Handler
			@Override
			public void onNewFollowed(Subject subject) {
				Button btn = new Button(subject.getSubjName());
				btn.setMinHeight(150);
				btn.setMinWidth(150);
				MainLeft.getChildren().add(btn);
				followedSubjButtons.add(btn);
				
				//button action
				btn.setOnAction(e->{
					main.SHandler.setCurrentSubject(btn.getText());
					MainCenter.getChildren().clear();
					MainCenter.getChildren().add(followSubj);
					MainCenter.getChildren().add(backB);
					//remove subject button only added if the use is the owner of the subject
					if((main.SHandler.getCurrentSubject().getOwnerID())==(main.UHandler.getCurrentUser().getID()))
						MainCenter.getChildren().add(removeSubj);	
				});
			}
		});
		
		//Scene settings
		Scene MainWindow = new Scene(MainPane,1000,600);
		FIITNotes.setScene(MainWindow);
		FIITNotes.setTitle("FIITNotes-" + (main.UHandler.getCurrentUser()).getName());
		FIITNotes.show();
		
		//Button action 
		
		//change the center pane when a subject button is clicked
		//according to the subject it belongs to 
		for(Button btn : subjButtons) {
			btn.setOnAction(e->{
				main.SHandler.setCurrentSubject(btn.getText());
				MainCenter.getChildren().clear();
				MainCenter.getChildren().add(followSubj);
				MainCenter.getChildren().add(backB);
				//remove subject button only added if the use is the owner of the subject
				if((main.SHandler.getCurrentSubject().getOwnerID())==(main.UHandler.getCurrentUser().getID()))
					MainCenter.getChildren().add(removeSubj);	
			});
		}
		
		//change the center pane when a subject button is clicked
		//according to the subject it belongs to
		for(Button btn : followedSubjButtons) {
			btn.setOnAction(e->{
				main.SHandler.setCurrentSubject(btn.getText());
				MainCenter.getChildren().clear();
				MainCenter.getChildren().add(followSubj);
				MainCenter.getChildren().add(backB);
				//remove subject button only added if the use is the owner of the subject
				if((main.SHandler.getCurrentSubject().getOwnerID())==(main.UHandler.getCurrentUser().getID()))
					MainCenter.getChildren().add(removeSubj);	
			});
		}
		
		//handle a new follow request when clicked 
		followSubj.setOnAction(e->{
			try {
				main.SHandler.newFollowHandler(main.UHandler.getCurrentUser(),main.SHandler.getCurrentSubject());
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
			main = null;
			LoginGUI login = new LoginGUI();
			login.start(FIITNotes);
		}
		);
		
		//clear the new subject panel if the subject handler 
		//returns true , display error message if false 
		addNewSubject.setOnAction(e->{
			
			if(main.SHandler.newSubjectHandle(newSubNameT.getText(),main.UHandler.getCurrentUser().getID()))
			{
				newSubNameT.clear();
			}
			else
				errMess.setText("Subject already exists!");	
		});
		
		
		removeSubj.setOnAction(e->{
			ArrayList<Button> toRemove = new ArrayList<Button>();
			for(Button btn : subjButtons) {
				if(btn.getText().equals(main.SHandler.getCurrentSubject().getSubjName()))
					toRemove.add(btn);
			}
			subjButtons.removeAll(toRemove);
			toRemove.clear();
			for(Button btn:followedSubjButtons) {
				if(btn.getText().equals(main.SHandler.getCurrentSubject().getSubjName()))
					toRemove.add(btn);
			}
			followedSubjButtons.removeAll(toRemove);
			toRemove.clear();
			
			main.UHandler.removeFollowedHandler(main.SHandler.getCurrentSubject());
			main.SHandler.removeCurrentSubject();
			
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

