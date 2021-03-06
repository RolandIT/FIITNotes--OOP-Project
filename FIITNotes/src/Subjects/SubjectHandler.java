package Subjects;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import Users.User;
/**
 * class that handles all the operations with subjects 
 * @author Roli
 *
 */
public class SubjectHandler {
	private ArrayList<Subject> subjects = new ArrayList<Subject>();
	private NewSubjectListener listener;
	private NewSubjectListener.NewFollowedListener followListener;
	private Subject currentSubject;
	
	/**
	 * finds all the subjects in /Subjects/ folder and adds them
	 * to the list of subjects 
	 * @throws ClassNotFoundException
	 */
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
	
	/**
	 * handles new subject creation request 
	 * @param subjName
	 * @param ownerID
	 * @return true/false
	 */
	public boolean newSubjectHandle(String subjName,int ownerID){
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
			
		listener.onNewSubject(); //call on new subject method for the listener
		
		File dir = new File("Documents/"+subjName);
		dir.mkdir();
		try {
			@SuppressWarnings({ "unused", "resource" })
			PrintWriter writer = new PrintWriter("Comments/" + subjName + ".txt", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * deletes the current subject 
	 */
	public void removeCurrentSubject()
	{
		subjects.remove(currentSubject);
		File subjFile = new File("Subjects/" + currentSubject.getSubjName() + ".ser");
		subjFile.delete();
		currentSubject = null;
	}
	
	/**
	 * handles new follow requests 
	 * @param currentUser
	 * @param subject
	 * @throws IOException
	 */
	public void newFollowHandler(User currentUser,Subject subject) throws IOException {
		
		if(currentUser.followSubject(subject))
				followListener.onNewFollowed(subject);//call the onNewFollowed for the listener 
			
		currentUser.saveUser();
	}
	
	/**
	 * returns an Arraylist of all subjects 
	 * @return
	 */
	public ArrayList<Subject> getSubjects(){
		return subjects;
	}
		
	/**
	 * adds a new listener 
	 * @param e
	 */
	public void addListener(NewSubjectListener e)
	{
		listener = e;
	}
	
	/**
	 * adds a new listener 
	 * @param e
	 */
	public void addListener(NewSubjectListener.NewFollowedListener e)
	{
		followListener = e;
	}
	
	/**
	 * set the current subject; accepts (string) subject name 
	 * @param s
	 */
	public void setCurrentSubject(String s) {
		for(Subject sub:subjects)
		{
			if(sub.getSubjName().equals(s))
				currentSubject = sub;
		}
	}
	
	/**
	 * @return returns the current subject
	 */
	public Subject getCurrentSubject() {
		return currentSubject;
	}
}