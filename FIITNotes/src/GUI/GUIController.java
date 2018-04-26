package GUI;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import CustomExceptions.IllegalArgException;
import Main.MainInstance;
import Subjects.Comment;
import Subjects.Document;
import Subjects.NewFollowedSubj;
import Subjects.PriviledgedComment;
import Subjects.RegularComment;
import Subjects.Subject;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUIController {
	public MainInstance main;
	static BorderPane currentPane;
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
		currentPane.setRight(null);
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
		currentPane = (BorderPane)pn;
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
	public void addNewSubjectListener(Pane pn,ArrayList<Button> btns,HBox controls,ArrayList<Node> controlnd,Button removeSubj,ArrayList<Pane>subjpns) {
		//observer pattern - listener added to new subject listeners 
		main.SHandler.addListener(()-> {
		//lambda expression which inserts code to the onNewSubject method 
		//adds a new button to the center Pane each 
		//time its called by the Handler
			Button btn = new Button(main.subjects.get((main.subjects.size())-1).getSubjName());
			btn.setMinHeight(150);
			btn.setMinWidth(150);
			pn.getChildren().add(btn);
			btns.add(btn);
			//button action 
			btn.setOnAction(e->{
				GUIController.clearPane();
				main.SHandler.setCurrentSubject(btn.getText());
				pn.getChildren().clear();
				pn.getChildren().add(controls);
			
				controls.getChildren().clear();
				for(Node nd:controlnd)
					controls.getChildren().add(nd);
				
				//remove subject button only added if the use is the owner of the subject
				if((main.SHandler.getCurrentSubject().getOwnerID())==(main.UHandler.getCurrentUser().getID()))
					controls.getChildren().add(removeSubj);
				
				for(Pane pns:subjpns)
					pn.getChildren().add(pns);
				
				GUIController c1 = new GUIController();
				c1.main.SHandler.setCurrentSubject(btn.getText()); //adds the current subject to the temporary controller
				c1.addAllDocuments(subjpns.get(0));
			});
		});
	}
	
	//TODO
	public void setSubjButtonAction(Pane pn,HBox controls,ArrayList<Node> controlnd,Button removeSubj,String subj) {
		main.SHandler.setCurrentSubject(subj);
		pn.getChildren().clear();
		pn.getChildren().add(controls);
		
		controls.getChildren().clear();
		for(Node nd:controlnd)
			controls.getChildren().add(nd);

		//remove subject button only added if the use is the owner of the subject
		if((main.SHandler.getCurrentSubject().getOwnerID())==(main.UHandler.getCurrentUser().getID()))
			controls.getChildren().add(removeSubj);
	}
	
	//TODO
	public void addNewFollowedSubjListener(Pane pn,Pane actionPane,ArrayList<Button> btns,HBox controls,ArrayList<Node> controlnd,Button removeSubj,ArrayList<Pane> subjpns,Label documentsL){
		//observer pattern - listener added to newFollowedSubject listeners
		main.SHandler.addListener(new NewFollowedSubj() {
			//Anonymous class with onNewFollowed method implemented
			//onNewFollowed adds a new button to the left Pane each 
			//time its called by the Handler
			@Override
			public void onNewFollowed(Subject subject) {
				Button btn = new Button(subject.getSubjName());
				btn.setMinHeight(10);
				btn.setMinWidth(100);
				pn.getChildren().add(btn);
				btns.add(btn);
				
				//button action
				btn.setOnAction(e->{
					GUIController.clearPane();
					main.SHandler.setCurrentSubject(subject.getSubjName());
					actionPane.getChildren().clear();
					actionPane.getChildren().add(controls);
					
					controls.getChildren().clear();
					for(Node nd:controlnd)
						controls.getChildren().add(nd);
					
					//remove subject button only added if the use is the owner of the subject
					if((main.SHandler.getCurrentSubject().getOwnerID())==(main.UHandler.getCurrentUser().getID()))
							controls.getChildren().add(removeSubj);
					
					
					for(Pane pns:subjpns)
						actionPane.getChildren().add(pns);
					
					GUIController c1 = new GUIController();
					c1.main.SHandler.setCurrentSubject(subject.getSubjName()); //adds the current subject to the temporary controller
					
					subjpns.get(0).getChildren().clear();
					subjpns.get(0).getChildren().add(documentsL);
					c1.addAllDocuments(subjpns.get(0));
				});
			}
		});
	}
	
	//TODO
	public String setAddNewSubjectButton(String subjName,Label lb) throws IllegalArgException {
		if(subjName.equals(""))
			throw new IllegalArgException(lb,"Subject name not allowed");
		else
		{
			if(main.SHandler.newSubjectHandle(subjName,main.UHandler.getCurrentUser().getID()))
			{
				return " ";
			}
			return "Subject already exists!";
		}
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
		
		main.UHandler.removeAllFollowedHandler(main.SHandler.getCurrentSubject());
		main.SHandler.removeCurrentSubject();
	}
	
	//TODO 
	public void removeFollow(ArrayList<Button> followedSubj) {
		ArrayList<Button> toRemove = new ArrayList<Button>();
		for(Button btn : followedSubj) {
			if(btn.getText().equals(main.SHandler.getCurrentSubject().getSubjName()))
				toRemove.add(btn);
		}
		followedSubj.removeAll(toRemove);
		
		main.UHandler.removeFollowedHandler(main.SHandler.getCurrentSubject());
		main.SHandler.setCurrentSubject(null);
	}
	
	//TODO
	static class fileOperations{
		private static String getFileExtension(File file) {
	    String fileName = file.getName();
	    if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".")!= 0)
	        return fileName.substring(fileName.lastIndexOf(".")+1);
	        else return "";
		}
		
		public static void copyFile(FileInputStream src,Path dest) throws IOException{
		Files.copy(src,dest); 
		}
	}
	
	//TODO
	public void addNewDocument(String docName,Stage stg,Label lb) throws IOException,IllegalArgException{
		if(docName.equals(""))
			throw new IllegalArgException(lb,"Document name not allowed");
		else
		{
			String folder = main.SHandler.getCurrentSubject().getSubjName();
			FileChooser fileChooser = new FileChooser();
			File source = fileChooser.showOpenDialog(stg);
			if(source!=null)
			{
				fileOperations.copyFile(new FileInputStream(source),Paths.get("Documents/" + folder + "/" + docName + "." + fileOperations.getFileExtension(source)));
			}
		}
	}
	
	//TODO
	public void addAllDocuments(Pane pn) {
			ArrayList<Document> docs=new ArrayList<Document>();
			main.DHandler.discoverDocuments(main.SHandler.getCurrentSubject().getSubjName());
			docs = main.DHandler.getDocuments();
			
			for(Document doc:docs)
			{
				Hyperlink link=new Hyperlink(doc.getName());
				pn.getChildren().add(link);
				
				link.setOnAction(e->{
					try {
						Desktop.getDesktop().open(doc.getFile());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				});
			}
		}
	
	//TODO
	public void setRight(BorderPane pn,Pane vb) {
		if(main.UHandler.getCurrentUser().getType().equals("Instructor"))
			pn.setRight(vb);
	}
	
	//TODO
	public void addComments(VBox vb) throws IOException {
		BufferedReader reader;
		reader = new BufferedReader(new FileReader("Comments/"+main.SHandler.getCurrentSubject().getSubjName()+".txt"));
		vb.getChildren().clear();
		String comment=reader.readLine();
		while(comment!=null)
		{
			Label lb = new Label(comment);
			comment=reader.readLine();
			if(comment.equals("1"))
				lb.setId("owner");
			vb.getChildren().add(lb);
			comment=reader.readLine();
		}
		reader.close();	
	}

	//TODO
	public void addNewComment(String text) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("Comments/"+main.SHandler.getCurrentSubject().getSubjName()+".txt",true));
		Comment c1;
		if((main.SHandler.getCurrentSubject().getOwnerID())==(main.UHandler.getCurrentUser().getID()))
			c1 = new PriviledgedComment(main.UHandler.getCurrentUser().getName(),text);
		else
			c1 = new RegularComment(main.UHandler.getCurrentUser().getName(),text);
		writer.write(main.UHandler.getCurrentUser().getName()+" : "+text);
		writer.newLine();
		String priviledge="";
		priviledge=c1.applyPriviledges(priviledge);
		writer.write(priviledge);
		writer.newLine();
		
		writer.close();
	}
}
