package Main;

import java.io.IOException;
import java.util.ArrayList;

import Subjects.Subject;
import Users.UserHandler;

public class MainInstance {
	public UserHandler UHandler;
	public ArrayList<Subject> subjects=new ArrayList<Subject>();
	public MainInstance()
	{
		//load all the users 
		UHandler= new UserHandler();
		try {
			UHandler.LoadUsers();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SubjectInit initiator=new SubjectInit();
		initiator.findSubjects();
		subjects=initiator.getSubjects();
	}
}
