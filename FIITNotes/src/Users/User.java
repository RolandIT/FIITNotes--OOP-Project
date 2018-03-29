package Users;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


import Subjects.Subject;
import javafx.scene.control.Button;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String password;
	protected int ID;
	private ArrayList<Subject> followedSubj=new ArrayList<Subject>();
	
	public User (String name,String password) {
		this.name = name;
		this.password = password;		
	}
	
	//add subject to the followed subjects list if 
	//requested subject isnt already there 
	public boolean followSubject(Subject Subject) {
		for(Subject s : followedSubj)
		{	
			if(s.getSubjName().equals(Subject.getSubjName()))
				return false;	
		}
		followedSubj.add(Subject);
		return true;
	}
	
	//returns an arraylist of all followed subjects
	public ArrayList<Subject> getFollowedSubjects(){
		return followedSubj;
	}
	
	//returns the password 
	public String getPassword() {
		return password;
	}
	
	//sets the password
	public void setPassword(String password) {
		this.password = password;
	}
	
	//returns the username
	public String getName() {
		return name;
	}
	
	//sets the username
	public void setName(String name) {
		this.name = name;
	}
	
	//saves the user to the Users folder 
	public void saveUser() throws IOException {
		try {
			FileOutputStream Fileout = new FileOutputStream("Users/" + this.name+".ser");
			ObjectOutputStream Objectout = new ObjectOutputStream(Fileout);
			Objectout.writeObject(this);
			Objectout.close();
			Fileout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//returns the type of user 
	public String getUserType() {
		return "User";
	}
	
	//returns the users ID
	public int getID() {	
		return ID;
	}
	
	//
	public void removeFollowedSubject(Subject s)
	{
		ArrayList<Subject> toRemove = new ArrayList<Subject>();
		for(Subject subj : followedSubj)
		{
			if(subj.getSubjName().equals(s.getSubjName()))
				toRemove.add(subj);
				
		}
		followedSubj.removeAll(toRemove);
	}
}

