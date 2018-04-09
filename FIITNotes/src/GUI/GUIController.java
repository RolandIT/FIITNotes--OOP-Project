package GUI;

import java.io.IOException;
import java.util.ArrayList;

import Main.MainInstance;
import Subjects.NewFollowedSubj;
import Subjects.NewSubjectListener;
import Subjects.Subject;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GUIController {
	public MainInstance main;
	static Pane currentPane;
	static Stage currentStage;
	
	public GUIController() {
		//start a new main Instance to handle user data 
		main = new MainInstance();
	}
	
	//Login method receives the name and password of the user trying to log in
	//calls the hander and if login is successful starts the main GUI 
	//else it returns an error message 
	public String Login(String name,String password,Stage stg) {
		if(main.UHandler.LoginHandle(name,password))
		{
			MainGUI main = new MainGUI(this);
			main.start(stg);
		}
		else
			return "Invalid username or password!";	
		return " ";
	}
	
	//Calls the new account handler to create a new account , logs in the account if successful 
	//returns error message if not 
	public String CreateNewUser(String name,String password,String type,Stage stg) throws IOException {
		if(main.UHandler.NewUserHandle(name,password,type))
			return this.Login(name, password, stg); //always returns " " 
		else
			return "User already exists!";
	}
	
	//clears all children of the Current pane in the controller
	//is called only for a student 
	public static void clearPane()
	{
		currentPane.getChildren().clear();
	}
	
	//changes the title of the window to show the user is an instructor
	public static void changeTitle()
	{
		currentStage.setTitle("FIITNotes - Instructor");
	}
	
	//calls the applyPriviledges method to change the right pane 
	//based on the current logged in user
	public void setUserScene(Pane pn,Stage stg) {
		currentStage = stg;
		currentPane = pn;
		main.UHandler.getCurrentUser().applyPrivileges();
	}
	
	//TODO
	public ArrayList<Button> setFollowedSubjButtons(int width,int height)
	{
		ArrayList<Button> btns=new ArrayList<Button>();
		for(Subject subj: main.UHandler.getCurrentUser().getFollowedSubjects())
		{
			Button newBtn= new Button(subj.getSubjName());
			newBtn.setMinHeight(height);
			newBtn.setMinWidth(width);
			btns.add(newBtn);	
		}
		return btns;
	}
	
	//TODO
	public ArrayList<Button> setSubjButtons(int width, int height)
	{
		ArrayList<Button> btns=new ArrayList<Button>();
		for(Subject subj: main.SHandler.getSubjects())
		{
			Button newBtn= new Button(subj.getSubjName());
			newBtn.setMinHeight(height);
			newBtn.setMinWidth(width);
			btns.add(newBtn);	
		}		
		return btns;
	}
	
	//TODO
	public ArrayList<Button> addNewSubjectListener(Pane pn,ArrayList<Button> btns,ArrayList<Node> stdNodes,ArrayList<Node>instNodes) {
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
			pn.getChildren().add(btn);
			btns.add(btn);
			//button action 
			btn.setOnAction(e->{
				main.SHandler.setCurrentSubject(btn.getText());
				pn.getChildren().clear();
				for(Node nd:stdNodes)
					pn.getChildren().add(nd);
					
				//remove subject button only added if the use is the owner of the subject
				if((main.SHandler.getCurrentSubject().getOwnerID())==(main.UHandler.getCurrentUser().getID()))
				{
					for(Node nd:instNodes)
					pn.getChildren().add(nd);
				}
			});
										
			}
		});
		return btns;
	}
	
	//TODO
	public void setSubjButtonAction(Pane pn,ArrayList<Node> stdNodes,ArrayList<Node> instNodes,String subj) {
		main.SHandler.setCurrentSubject(subj);
		pn.getChildren().clear();
		for(Node nd:stdNodes)
			pn.getChildren().add(nd);
			
		//remove subject button only added if the use is the owner of the subject
		if((main.SHandler.getCurrentSubject().getOwnerID())==(main.UHandler.getCurrentUser().getID()))
		{
			for(Node nd:instNodes)
			pn.getChildren().add(nd);
		}
	}
	
	//TODO
	public ArrayList<Button> addNewFollowedSubjListener(Pane pn,Pane actionPane,ArrayList<Button> btns,ArrayList<Node> stdNodes,ArrayList<Node>instNodes){
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
				pn.getChildren().add(btn);
				btns.add(btn);
				
				//button action
				btn.setOnAction(e->{
					main.SHandler.setCurrentSubject(btn.getText());
					actionPane.getChildren().clear();
					for(Node nd:stdNodes)
						actionPane.getChildren().add(nd);
						
					//remove subject button only added if the use is the owner of the subject
					if((main.SHandler.getCurrentSubject().getOwnerID())==(main.UHandler.getCurrentUser().getID()))
					{
						for(Node nd:instNodes)
							actionPane.getChildren().add(nd);
					}
				});
			}
		});
		return btns;
	}
	
	//TODO
	public String setAddNewSubjectButton(String subjName) {
		if(main.SHandler.newSubjectHandle(subjName,main.UHandler.getCurrentUser().getID()))
		{
			return " ";
		}
		return "Subject already exists!";
	}
	
	//TODO
	public void removeSubject(ArrayList<Button> subjButtons,ArrayList<Button> followedSubjButtons) {
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
	}
}
