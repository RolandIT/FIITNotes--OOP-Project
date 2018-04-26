package GUI;

import javafx.application.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import CustomExceptions.IllegalArgException;

public class MainGUI extends Application{	
	//controller
	private GUIController controller;
	
	//nodes on the main window
	private Label logName = new Label();
	private Button logOut = new Button("Log Out");
	private Label followedLab = new Label("Followed Subjects");
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
	private Button addDocument = new Button("Add a new document");
	private TextField newDocumentName = new TextField();
	private Label newDocumentL = new Label("Document name");
	private Label documentsL = new Label("List of all documents");
	private Button unfollowSubj = new Button ("Unfollow");
	private Label errMessDoc = new Label();
	private Button comments = new Button("Comments");
	
	//nodes on the comment pane 
	private Button backBN = new Button("Back");
	private TextField comment = new TextField();
	private Button send = new Button("Send");
	private Label commentsL = new Label("Comments :");
	
	//Constructor for the main GUI, MainInstance object main is passed down on
	//creation so current instance information is accessible 
	public MainGUI(GUIController controller) {
		this.controller = controller;
	}
	
	public void start(Stage FIITNotes) {
		
		FIITNotes.setTitle("FIITNotes - Student");
		FIITNotes.setResizable(false);
		//Panes on the main Window
		BorderPane MainPane = new BorderPane();
		FlowPane MainCenter = new FlowPane();
		GridPane MainRight = new GridPane();
		VBox MainLeft = new VBox();
		FlowPane MainTop = new FlowPane();
		VBox CenterLeft=new VBox();
		GridPane CenterRight = new GridPane();
		ScrollPane MainCenterScroll = new ScrollPane();
		HBox CenterControls = new HBox();
		HBox CommentsControls = new HBox();
		HBox NewComment = new HBox();
		VBox AllComments = new VBox();
		
		MainPane.setTop(MainTop);
		MainPane.setLeft(MainLeft);         
		MainPane.setCenter(MainCenterScroll);
		MainPane.setRight(MainRight);
		
		//Panel Nodes
		MainCenterScroll.setContent(MainCenter);
		MainCenterScroll.setFitToHeight(true);
		MainCenterScroll.setFitToWidth(true);
		
		//Right panel nodes added only if the current user is of type Instructor
		MainRight.add(addNewSubject,0,0);
		MainRight.add(newSubNameL,0,1);
		MainRight.add(newSubNameT,0,2);
		MainRight.add(errMess, 0, 3);
		controller.setUserScene(MainPane,FIITNotes);
		errMess.setId("red");
		errMessDoc.setId("red");
		
		//Left panel
		MainLeft.getChildren().add(followedLab);
		//Button added to the left Pane for each subject 
		//that the current user follows 
		followedSubjButtons = controller.setFollowedSubjButtons(100,10);
		for(Button btn : followedSubjButtons)
		MainLeft.getChildren().add(btn);
				
		//Top panel
		MainTop.getChildren().add(logName);	
		MainTop.getChildren().add(logOut);
		logName.setText("Logged in as : "+(controller.main.UHandler.getCurrentUser()).getName());
		
		//Center panel
		CenterRight.add(newDocumentL,0,0);
		CenterRight.add(newDocumentName,0,1);
		CenterRight.add(addDocument,0,2);
		CenterRight.add(errMessDoc,0,3);
		
		GridPane.setHalignment(newDocumentL, HPos.CENTER);
		GridPane.setHalignment(addDocument, HPos.CENTER);
		GridPane.setHalignment(newDocumentName, HPos.CENTER);
		CenterRight.setVgap(20);
		
		//button added to the center Pane for each existing subject in the current instance 
		subjButtons=controller.setSubjButtons(150,150);
		for(Button btn : subjButtons) {
			MainCenter.getChildren().add(btn);
		}
		
		//Layout settings
		MainTop.setHgap(660);
		
		GridPane.setMargin(addNewSubject, new Insets(15, 0, 0, 0));
		GridPane.setHalignment(addNewSubject, HPos.CENTER);
		GridPane.setHalignment(newSubNameL, HPos.CENTER);
		GridPane.setHalignment(errMess, HPos.CENTER);
		MainRight.setVgap(20);
		MainLeft.setSpacing(10);
		MainLeft.setAlignment(Pos.TOP_CENTER);
		
		CenterLeft.setAlignment(Pos.TOP_CENTER);
		CenterLeft.setSpacing(10);
		
		
		MainCenter.setPadding(new Insets(35, 35, 35, 35)); 
		MainCenter.setHgap(35);
		MainCenter.setVgap(35);
		backB.setAlignment(Pos.TOP_RIGHT);
		MainCenter.setAlignment(Pos.TOP_CENTER);
		MainTop.setPadding(new Insets(10, 10, 10, 10)); 
		MainLeft.setPadding(new Insets(10, 10, 10, 10)); 
		
		CenterControls.setSpacing(45);
		CenterControls.setMinWidth(700);
		CenterControls.setAlignment(Pos.TOP_CENTER);
		CommentsControls.setAlignment(Pos.TOP_RIGHT);
		CommentsControls.setSpacing(260);
		CommentsControls.setMinWidth(700);
		NewComment.setAlignment(Pos.BOTTOM_CENTER);
		NewComment.setSpacing(45);
		NewComment.setMinWidth(700);
		
		AllComments.setSpacing(20);
		AllComments.setMinWidth(700);
		AllComments.setAlignment(Pos.CENTER_LEFT);
		//Style settings 
		newDocumentL.setId("documentL");
		documentsL.setId("documentL");
		MainCenter.setId("MainCenter");
		CenterLeft.setId("CenterLeft");
		MainLeft.setId("MainLeft");
		MainRight.setId("MainRight");
		MainTop.setId("MainTop");
		commentsL.setId("documentL");
		
		//Scene settings
		Scene MainWindow = new Scene(MainPane,890,500);
		MainWindow.getStylesheets().add("GUIStyle.css");
		FIITNotes.setScene(MainWindow);
		FIITNotes.show();
		
		ArrayList<Node> controlNodes=new ArrayList<Node>();
		controlNodes.add(followSubj);
		controlNodes.add(backB);
		controlNodes.add(unfollowSubj);
		controlNodes.add(comments);
		
		CommentsControls.getChildren().add(commentsL);
		CommentsControls.getChildren().add(backBN);
		NewComment.getChildren().add(comment);
		NewComment.getChildren().add(send);
		
		ArrayList<Pane> subjectPanes=new ArrayList<Pane>();
		subjectPanes.add(CenterLeft);
		subjectPanes.add(CenterRight);
		
		
		backBN.setMinHeight(10);
		backBN.setMinWidth(100);
		send.setMinHeight(10);
		send.setMinWidth(100);
		comment.setMinWidth(300);
		
		//Adds listeners for new subjects creation 
		CenterLeft.getChildren().clear();
		CenterLeft.getChildren().add(documentsL);
		controller.addNewSubjectListener(MainCenter,subjButtons,CenterControls,controlNodes,removeSubj,subjectPanes);
		controller.addNewFollowedSubjListener(MainLeft,MainCenter,followedSubjButtons,CenterControls,controlNodes,removeSubj,subjectPanes,documentsL);
		
		//Button action 
		//change the center pane when a subject button is clicked
		//according to the subject it belongs to 
		for(Button btn : subjButtons) {
			btn.setOnAction(e->{
				controller.setSubjButtonAction(MainCenter,CenterControls,controlNodes,removeSubj,btn.getText());
				controller.main.SHandler.setCurrentSubject(btn.getText());
				CenterLeft.getChildren().clear();
				CenterLeft.getChildren().add(documentsL);
				controller.addAllDocuments(CenterLeft);
				MainCenter.getChildren().add(CenterLeft);
				MainCenter.getChildren().add(CenterRight);
				GUIController.clearPane();
			});
		}
		
		//change the center pane when a subject button is clicked
		//according to the subject it belongs to
		for(Button btn : followedSubjButtons) {
			btn.setOnAction(e->{
				controller.setSubjButtonAction(MainCenter,CenterControls,controlNodes,removeSubj,btn.getText());
				controller.main.SHandler.setCurrentSubject(btn.getText());
				CenterLeft.getChildren().clear();
				CenterLeft.getChildren().add(documentsL);
				controller.addAllDocuments(CenterLeft);
				MainCenter.getChildren().add(CenterLeft);
				MainCenter.getChildren().add(CenterRight);
				GUIController.clearPane();
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
			for(Button btn : subjButtons) {
				MainCenter.getChildren().add(btn);
			}
			CenterLeft.getChildren().clear();
			CenterLeft.getChildren().add(documentsL);
			controller.setRight(MainPane,MainRight);
			errMess.setText("");
			errMessDoc.setText("");
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
			try {
				errMess.setText(controller.setAddNewSubjectButton(newSubNameT.getText(),errMess));
			} catch (IllegalArgException e1) {
				
			}	
			newSubNameT.clear();
		});
		
		//TODO
		removeSubj.setOnAction(e->{
			controller.removeSubject(subjButtons,followedSubjButtons);
			
			MainLeft.getChildren().clear();
			MainLeft.getChildren().add(followedLab);
			MainCenter.getChildren().clear();
			controller.setRight(MainPane,MainRight);
			for(Button btn : subjButtons) {
				MainCenter.getChildren().add(btn);
			}
			
			for(Button btn : followedSubjButtons) {
				MainLeft.getChildren().add(btn);
			}
		});	
		
		//TODO
		addDocument.setOnAction(e->{
			try {
				controller.addNewDocument(newDocumentName.getText(),FIITNotes,errMessDoc);
				newDocumentName.clear();
				CenterLeft.getChildren().clear();
				CenterLeft.getChildren().add(documentsL);
				controller.addAllDocuments(CenterLeft);
				errMessDoc.setText("");
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				errMessDoc.setText("File name already used!");
			} catch (IllegalArgException e1) {	
			}
		});
		
		unfollowSubj.setOnAction(x->{
			controller.removeFollow(followedSubjButtons);
			MainLeft.getChildren().clear();
			MainLeft.getChildren().add(followedLab);
			for(Button btn : followedSubjButtons) {
				MainLeft.getChildren().add(btn);
			}
		});
		
		//TODO
		backBN.setOnAction(e->{
			MainCenter.getChildren().clear();
			CenterLeft.getChildren().clear();
			CenterLeft.getChildren().add(documentsL);
			MainCenter.getChildren().add(CenterControls);
			MainCenter.getChildren().add(CenterLeft);
			MainCenter.getChildren().add(CenterRight);
			controller.addAllDocuments(CenterLeft);
		});
		
		//TODO
		comments.setOnAction(e->{
			MainCenter.getChildren().clear();
			try {
				controller.addComments(AllComments);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			MainCenter.getChildren().add(CommentsControls);
			MainCenter.getChildren().add(NewComment);
			MainCenter.getChildren().add(AllComments);
		});
		
		//TODO
		send.setOnAction(e->{
			try {
				controller.addNewComment(comment.getText());
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
				controller.addComments(AllComments);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			comment.setText("");
		});
}

}

