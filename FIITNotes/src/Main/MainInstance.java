package Main;

import java.util.ArrayList;

import Subjects.Subject;
import Users.User;

public class MainInstance {
	User currentUser;
	public ArrayList<Subject> subjects=new ArrayList<Subject>();
	public MainInstance(User user)
	{
		this.currentUser=user;
		SubjectInit initiator=new SubjectInit();
		initiator.findSubjects();
		subjects=initiator.getSubjects();
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
}
