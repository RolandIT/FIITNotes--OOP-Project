package Users;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


import Subjects.Subject;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String password;
	private ArrayList<Subject> followedSubj=new ArrayList<Subject>();
	
	public User (String name,String password) {
		this.name=name;
		this.password=password;		
	}
	
	public ArrayList<Subject> getFollowedSubjects(){
		return followedSubj;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
}

