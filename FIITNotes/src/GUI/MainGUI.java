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
	private MainInstance main;
	//MainPane
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
	
	//subject window 
	private Button backB=new Button("Back");
	private Button followSubj = new Button("Follow");
	private Button removeSubj = new Button("Delete subject");
	public MainGUI(MainInstance main) {
		this.main=main;
	}
	public void start(Stage FIITNotes) {
	
		BorderPane MainPane = new BorderPane();
		FlowPane MainCenter = new FlowPane();
		GridPane MainRight = new GridPane();
		VBox MainLeft = new VBox();
		FlowPane MainTop = new FlowPane();
		
		MainPane.setTop(MainTop);
		MainPane.setLeft(MainLeft);         
		MainPane.setCenter(MainCenter);
		MainPane.setRight(MainRight);
		
		if((main.UHandler.getCurrentUser().getUserType()).equals("Instructor"))
		{
			MainRight.add(addNewSubject,0,0);
			MainRight.add(newSubNameL,0,1);
			MainRight.add(newSubNameT,0,2);
			MainRight.add(errMess, 0, 3);
		}
		errMess.setTextFill(Color.RED);
		
		MainLeft.getChildren().add(followedLab);
	
		MainTop.getChildren().add(logName);	
		MainTop.getChildren().add(logOut);
		logName.setText("Logged in as : "+(main.UHandler.getCurrentUser()).getName());
		
		MainCenter.getChildren().add(subjectsLab);
		for(Subject subj:((main.UHandler.getCurrentUser()).getFollowedSubjects()))
		{
			Button btn = new Button(subj.getSubjName());
			btn.setMinHeight(150);
			btn.setMinWidth(150);
			MainLeft.getChildren().add(btn);
			followedSubjButtons.add(btn);
		}
		for(Subject subj : main.subjects) {
			Button btn = new Button(subj.getSubjName());
			btn.setMinHeight(150);
			btn.setMinWidth(150);
			MainCenter.getChildren().add(btn);
			subjButtons.add(btn);
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
		//observer pattern 
		main.SHandler.addListener(new NewSubjectListener() {
			@Override
			public void onNewSubject() {
				Button btn = new Button(main.subjects.get((main.subjects.size())-1).getSubjName());
				btn.setMinHeight(150);
				btn.setMinWidth(150);
				MainCenter.getChildren().add(btn);
				subjButtons.add(btn);
				
				btn.setOnAction(e->{
					main.SHandler.setCurrentSubject(btn.getText());
					MainCenter.getChildren().clear();
					MainCenter.getChildren().add(followSubj);
					MainCenter.getChildren().add(backB);
					if((main.SHandler.getCurrentSubject().getOwnerID())==(main.UHandler.getCurrentUser().getID()))
						MainCenter.getChildren().add(removeSubj);	
				});
			}
		});
		main.SHandler.addListener(new NewFollowedSubj() {
			
			@Override
			public void onNewFollowed(Subject subject) {
				Button btn = new Button(subject.getSubjName());
				btn.setMinHeight(150);
				btn.setMinWidth(150);
				MainLeft.getChildren().add(btn);
				followedSubjButtons.add(btn);
				
				btn.setOnAction(e->{
					main.SHandler.setCurrentSubject(btn.getText());
					MainCenter.getChildren().clear();
					MainCenter.getChildren().add(followSubj);
					MainCenter.getChildren().add(backB);
					System.out.println(main.SHandler.getCurrentSubject().getOwnerID()+ " "+ main.UHandler.getCurrentUser().getID());
					if((main.SHandler.getCurrentSubject().getOwnerID())==(main.UHandler.getCurrentUser().getID()))
						MainCenter.getChildren().add(removeSubj);	
				});
			}
		});
		
		Scene MainWindow = new Scene(MainPane,1000,600);
		FIITNotes.setScene(MainWindow);
		FIITNotes.setTitle("FIITNotes-" + (main.UHandler.getCurrentUser()).getName());
		FIITNotes.show();
		
		//button action 
		for(Button btn : subjButtons) {
			btn.setOnAction(e->{
				main.SHandler.setCurrentSubject(btn.getText());
				MainCenter.getChildren().clear();
				MainCenter.getChildren().add(followSubj);
				MainCenter.getChildren().add(backB);
				System.out.println(main.SHandler.getCurrentSubject().getOwnerID()+ " "+ main.UHandler.getCurrentUser().getID());
				if((main.SHandler.getCurrentSubject().getOwnerID())==(main.UHandler.getCurrentUser().getID()))
					MainCenter.getChildren().add(removeSubj);	
			});
		}
		for(Button btn : followedSubjButtons) {
			btn.setOnAction(e->{
				main.SHandler.setCurrentSubject(btn.getText());
				MainCenter.getChildren().clear();
				MainCenter.getChildren().add(followSubj);
				MainCenter.getChildren().add(backB);
				if((main.SHandler.getCurrentSubject().getOwnerID())==(main.UHandler.getCurrentUser().getID()))
					MainCenter.getChildren().add(removeSubj);	
			});
		}
		followSubj.setOnAction(e->{
			try {
				main.SHandler.newFollowHandler(main.UHandler.getCurrentUser(),main.SHandler.getCurrentSubject());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		backB.setOnAction(e->{
			MainCenter.getChildren().clear();
			MainCenter.getChildren().add(subjectsLab);
			for(Button btn : subjButtons) {
				MainCenter.getChildren().add(btn);
			}
		});
		logOut.setOnAction(e->{
			main = null;
			LoginGUI login = new LoginGUI();
			login.start(FIITNotes);
		}
		);
		addNewSubject.setOnAction(e->{
			
			if(main.SHandler.newSubjectHandle(newSubNameT.getText(),main.UHandler.getCurrentUser().getID()))
			{
				newSubNameT.clear();
			}
			else
				errMess.setText("Subject already exists!");	
		});
		removeSubj.setOnAction(e->{
			
		});	
}

}

