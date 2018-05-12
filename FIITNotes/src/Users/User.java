package Users;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


import Subjects.Subject;
/**
 * user class
 * @author Roli
 *
 */
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String password;
	protected int ID;
	private ArrayList<Subject> followedSubj = new ArrayList<Subject>();
	
	public User (String name,String password) {
		this.name = name;
		this.password = password;		
	}
	
	/**
	 * add subject to the followed subjects list if 
	 * requested subject isnt already there 
	 * @param Subject
	 * @return true/false
	 */
	public boolean followSubject(Subject Subject) {
		for(Subject s : followedSubj)
		{	
			if(s.getSubjName().equals(Subject.getSubjName()))
				return false;	
		}
		followedSubj.add(Subject);
		return true;
	}
	
	/**
	 * @return  returns an arraylist of all followed subjects
	 */
	public ArrayList<Subject> getFollowedSubjects(){
		return followedSubj;
	}
	
	/**
	 * @return returns the password 
	 */
	public String getPassword() {
		return password;
	}
	
	/**
	 * sets the password
	 * @param password 
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * @return returns the username
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * sets the username
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * saves the user to the Users folder 
	 * @throws IOException
	 */
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
	
	/**
	 * applies the users priviledges 
	 */
	public void applyPrivileges() {
	}
	
	/**
	 * @return returns the type of user 
	 */
	public String getType() {
		return "User";
	}
	
	/**
	 * @return returns the users ID
	 */
	public int getID() {	
		return ID;
	}
	
	/**
	 * removes the subject given as an argument from 
	 * the followed subjects of the user 
	 * @param s
	 */
	public void removeFollowedSubject(Subject s)
	{
		ArrayList<Subject> toRemove = new ArrayList<Subject>();
		for(Subject subj : followedSubj)
		{
			if(subj.getSubjName().equals(s.getSubjName()))
				toRemove.add(subj);	
		}
		followedSubj.removeAll(toRemove);
	}
}

