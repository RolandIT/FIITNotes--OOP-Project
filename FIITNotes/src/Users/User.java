package Users;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import Subjects.Subject;

public class User {
	private String name;
	private String password;
	private ArrayList<Subject> followedSubj=new ArrayList<Subject>();
	
	public User (String name,String password) {
		this.name=name;
		this.password=password;
		FileReader fr;
		try {
			fr = new FileReader("UserInfo/"+name+".txt");
			Scanner in = new Scanner(fr);
			while(in.hasNext()){
				String subj=in.next();
				if(subj.equals("owned"))
					break;
				followedSubj.add(new Subject(subj,name));
			}
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("User info file doesnt exist!!");
		}
		
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
}

