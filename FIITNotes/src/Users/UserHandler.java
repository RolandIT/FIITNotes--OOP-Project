package Users;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class UserHandler {
	public ArrayList<User> allUsers  = new ArrayList<User>();
	User currentUser;
	
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
	public boolean NewUserHandle(String name,String password,String Type) throws IOException {
		User newU;
		if(Type.equals("Student"))
			newU=new Student(name,password);
		else
			newU=new Instructor(name,password);
		for(User u:allUsers) {
			if(u.getName().equals(newU.getName()))
				return false;
		}
		allUsers.add(newU);
		newU.saveUser();
		return true;
	}
	public User getCurrentUser() {
		return currentUser;
	}
}
