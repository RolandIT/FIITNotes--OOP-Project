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
	public void findSubjects() throws ClassNotFoundException {
		File[] subjectFiles = new File("Subjects/").listFiles();
		Subject s = null;
		for (File dir : subjectFiles) {
			try {
				FileInputStream fileIn;
				fileIn = new FileInputStream("Subjects/"+dir.getName());
				ObjectInputStream in = new ObjectInputStream(fileIn);
				s=(Subject) in.readObject();			
				subjects.add(s);
				in.close();
				fileIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
	public boolean newSubjectHandle(String subjName,String owner) {
		Subject newSubj=new Subject(subjName,owner);
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
			listener.onNewSubject();
		}
		File dir = new File("Documents/"+subjName);
		dir.mkdir();
		return true;
	}
	public void newFollowHandler(User currentUser,Subject subject) throws IOException {
		
		if(currentUser.followSubject(subject))
		{
			for(NewFollowedSubj listener: followListeners) {
				listener.onNewFollowed(subject);
			}
		}
		currentUser.saveUser();
	}
	public ArrayList<Subject> getSubjects(){
		return subjects;
	}
	public void addListener(NewSubjectListener listener)
	{
		listeners.add(listener);
	}
	public void addListener(NewFollowedSubj listener)
	{
		followListeners.add(listener);
	}
	public void setCurrentSubject(String s) {
		for(Subject sub:subjects)
		{
			if(sub.getSubjName().equals(s))
				currentSubject=sub;
		}
	}
	public Subject getCurrentSubject() {
		return currentSubject;
	}
}
