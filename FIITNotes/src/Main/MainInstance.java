package Main;

import java.io.IOException;
import java.util.ArrayList;

import Subjects.Subject;
import Subjects.SubjectHandler;
import Users.UserHandler;

public class MainInstance {
	public UserHandler UHandler;
	public SubjectHandler SHandler;
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
		SHandler=new SubjectHandler();
		try {
			SHandler.findSubjects();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		subjects=SHandler.getSubjects();
	}
}
