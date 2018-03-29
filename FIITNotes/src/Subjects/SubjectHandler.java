package Subjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Users.User;


public class SubjectHandler {
	private ArrayList<Subject> subjects = new ArrayList<Subject>();
	private ArrayList<NewSubjectListener> listeners = new ArrayList<NewSubjectListener>();
	private ArrayList<NewFollowedSubj> followListeners = new ArrayList<NewFollowedSubj>();
	private Subject currentSubject;
	
	//finds all the subjects in /Subjects/ folder and adds them
	//to the list of subjects 
	public void findSubjects() throws ClassNotFoundException {
		File[] subjectFiles = new File("Subjects/").listFiles();
		Subject s = null;
		for (File dir : subjectFiles) {
			try {
				FileInputStream fileIn;
				fileIn = new FileInputStream("Subjects/"+dir.getName());
				ObjectInputStream in = new ObjectInputStream(fileIn);
				s = (Subject) in.readObject();			
				subjects.add(s);
				in.close();
				fileIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
	
	//handles new subject creation request 
	public boolean newSubjectHandle(String subjName,int ownerID) {
		Subject newSubj=new Subject(subjName,ownerID);
		for(Subject s:subjects)
		{
			if(s.getSubjName().equals(subjName))
				return false;
		}
		try {
			newSubj.saveSubject();
		} catch (IOException e) {
			e.printStackTrace();
		}
		subjects.add(newSubj);
		for(NewSubjectListener listener: listeners) {
			listener.onNewSubject(); //call on new subject method for each listener
		}
		File dir = new File("Documents/"+subjName);
		dir.mkdir();
		return true;
	}
	public void removeCurrentSubject()
	{
		subjects.remove(currentSubject);
		File subjFile = new File("Subjects/"+currentSubject.getSubjName()+".ser");
		subjFile.delete();
		currentSubject=null;
	}
	
	//handles new follow requests 
	public void newFollowHandler(User currentUser,Subject subject) throws IOException {
		
		if(currentUser.followSubject(subject))
		{
			for(NewFollowedSubj listener: followListeners) {
				listener.onNewFollowed(subject);//call the onNewFollowed for each listener 
			}
		}
		currentUser.saveUser();
	}
	
	//returns an Arraylist of all subjects 
	public ArrayList<Subject> getSubjects(){
		return subjects;
	}
	
	//adds a new listener to new subject listeners
	public void addListener(NewSubjectListener listener)
	{
		listeners.add(listener);
	}
	
	//adds a new listener to followListeners 
	public void addListener(NewFollowedSubj listener)
	{
		followListeners.add(listener);
	}
	
	//set the current subject; accepts (string) subject name 
	public void setCurrentSubject(String s) {
		for(Subject sub:subjects)
		{
			if(sub.getSubjName().equals(s))
				currentSubject=sub;
		}
	}
	
	//return the current subject
	public Subject getCurrentSubject() {
		return currentSubject;
	}
}
