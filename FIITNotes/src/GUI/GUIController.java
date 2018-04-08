package GUI;

import java.io.IOException;

import Main.MainInstance;
import javafx.stage.Stage;

public class GUIController {
	MainInstance mainInstance;
	
	public GUIController() {
		//start a new main Instance to handle user data 
		mainInstance=new MainInstance();
	}
	
	//Login function receives the name and password of the user trying to log in
	//calls the hander and if login is successful starts the main GUI 
	//else it returns an error message 
	public String Login(String name,String password,Stage stg) {
		if(mainInstance.UHandler.LoginHandle(name,password))
		{
			MainGUI main = new MainGUI(mainInstance);
			main.start(stg);
		}
		else
			return "Invalid username or password!";	
		return " ";
	}
	
	public String CreateNewUser(String name,String password,String type,Stage stg) throws IOException {
		System.out.println(name+" "+password+" "+type);
		if(mainInstance.UHandler.NewUserHandle(name,password,type))
		{
			System.out.println(name+" "+password+" "+type);
			return this.Login(name, password, stg); //always returns " " 
		}
		else
			return "User already exists!";
	}
}
