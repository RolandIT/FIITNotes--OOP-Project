package Users;

public class Instructor extends User {

	private static final long serialVersionUID = 1L;

	public Instructor(String name, String password) {
		super(name, password);
	
	}
	@Override
	public String getUserType() {
		return "Instructor";
	}
}
