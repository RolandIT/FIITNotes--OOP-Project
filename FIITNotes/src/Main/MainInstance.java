package Main;

import Users.User;

public class MainInstance {
	User currentUser;
	
	public MainInstance(User user)
	{
		this.currentUser=user;
	}
	
	public User getCurrentUser() {
		return currentUser;
	}
}
