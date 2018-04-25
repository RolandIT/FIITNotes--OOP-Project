package Users;

import GUI.GUIController;

public class Student extends User {
	private static final long serialVersionUID = 1L;

	public Student(String name, String password) {
		super(name, password);
		
	}
	
	@Override
	public void applyPrivileges() {
		GUIController.clearPane();
	}
	
	public String getType() {
		return "Student";
	}
	
}
