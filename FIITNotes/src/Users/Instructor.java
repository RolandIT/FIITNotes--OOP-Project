package Users;

import GUI.GUIController;

public class Instructor extends User {
	private static int InstructorCount=0;
	private static final long serialVersionUID = 1L;
	
	
	public Instructor(String name, String password) {
		super(name, password);
		InstructorCount++;
		ID=InstructorCount+10000;
	}
	
	@Override
	public void applyPrivileges() {
		GUIController.changeTitle();
	}
	
	public String getType() {
		return "Instructor";
	}
}
