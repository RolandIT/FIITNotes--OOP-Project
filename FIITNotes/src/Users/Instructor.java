package Users;

import GUI.GUIController;
/**
 * instructor class 
 * @author Roli
 *
 */
public class Instructor extends User {
	private static int InstructorCount = 0;
	private static final long serialVersionUID = 1L;
	
	
	public Instructor(String name, String password) {
		super(name, password);
		InstructorCount++;
		ID=InstructorCount+10000;
	}
	
	/**
	 * applies the privilidges of the instructor 
	 * the title of the window is changed 
	 */
	@Override
	public void applyPrivileges() {
		GUIController.changeTitle();
	}
	
	/**
	 * returns the type of user 
	 */
	public String getType() {
		return "Instructor";
	}
}
