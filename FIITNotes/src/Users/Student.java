package Users;

import GUI.GUIController;
/**
 * student class
 * @author Roli
 *
 */
public class Student extends User {
	private static final long serialVersionUID = 1L;

	public Student(String name, String password) {
		super(name, password);
		
	}

	/**
	 * applies the privilidges of the student 
	 * the add new subject panel is removed 
	 */
	@Override
	public void applyPrivileges() {
		GUIController.clearPane();
	}
	
	/**
	 * returns the type of user 
	 */
	public String getType() {
		return "Student";
	}
	
}
