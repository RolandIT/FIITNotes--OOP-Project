package Users;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import Subjects.Subject;
/**
 * class that handles all the operations with users 
 * @author Roli
 *
 */
public class UserHandler {
	private ArrayList<User> allUsers  = new ArrayList<User>();
	private User currentUser;
	
	/**
	 * load all the existing users from the Users 
	 * folder and add them to an arrayList 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public void LoadUsers()throws IOException, ClassNotFoundException  {
		File[] userFiles = new File("Users/").listFiles();
		User u = null;
		for (File dir : userFiles) {
			try {
				FileInputStream fileIn;
				fileIn = new FileInputStream("Users/"+dir.getName());
				ObjectInputStream in = new ObjectInputStream(fileIn);
				u=(User) in.readObject();			
				allUsers.add(u);
				in.close();
				fileIn.close();
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
	
	/**
	 * handles login requests
	 * @param name
	 * @param password
	 * @return true/false
	 */
	public boolean LoginHandle(String name, String password) {
		for(User u :allUsers) {
			if(u.getName().equals(name) && u.getPassword().equals(password))
			{
				currentUser=u;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * handles new user requests 
	 * @param name
	 * @param password
	 * @param Type
	 * @return true/false
	 * @throws IOException
	 */
	public boolean NewUserHandle(String name,String password,String Type) throws IOException {
		User newU;
		if(Type.equals("Student"))
			newU=new Student(name,password); //new user is a student 
		else
			newU=new Instructor(name,password); //new user is an instructor
		for(User u:allUsers) {
			if(u.getName().equals(newU.getName()))
				return false;
		}
		allUsers.add(newU);
		newU.saveUser();
		return true;
	}
	
	/**
	 * removes the subject given as an argument 
	 * form each users followed subjects 
	 * @param s
	 */
	public void removeAllFollowedHandler(Subject s)
	{
		for(User u:allUsers)
		{
			u.removeFollowedSubject(s);
			try {
				u.saveUser();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * removes the subject given as an argument 
	 * from the current users followed subjects 
	 * @param s
	 */
	public void removeFollowedHandler(Subject s)
	{
			currentUser.removeFollowedSubject(s);
			try {
				currentUser.saveUser();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * @return returns the current user
	 */
	public User getCurrentUser() {
		return currentUser;
	}
}
