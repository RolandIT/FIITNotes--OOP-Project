package Users;

public class Instructor extends User {
	private static int InstructorCount=0;
	private static final long serialVersionUID = 1L;
	
	
	public Instructor(String name, String password) {
		super(name, password);
		InstructorCount++;
		ID=InstructorCount+10000;
	}
	
	@Override
	public String getUserType() {
		return "Instructor";
	}
}
