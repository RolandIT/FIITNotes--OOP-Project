package Main;

import java.io.IOException;
import java.util.ArrayList;

import Subjects.DocumentHandler;
import Subjects.Subject;
import Subjects.SubjectHandler;
import Users.UserHandler;
/**
 * main instance class there all the handlers are created 
 * @author Roli
 *
 */
public class MainInstance {
	public UserHandler UHandler;
	public SubjectHandler SHandler;
	public DocumentHandler DHandler;
	public ArrayList<Subject> subjects = new ArrayList<Subject>();
	public MainInstance()
	{
		//load all the users once instance is created 
		UHandler = new UserHandler();
		try {
			UHandler.LoadUsers();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//load all the existing subjects 
		SHandler = new SubjectHandler();
		try {
			SHandler.findSubjects();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		subjects = SHandler.getSubjects();
		
		DHandler = new DocumentHandler();
		
	}
}
