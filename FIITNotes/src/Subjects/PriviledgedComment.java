package Subjects;

public class PriviledgedComment extends Comment {

	public PriviledgedComment(String u, String msg) {
		super(u, msg);
	}
	
	public String applyPriviledges(String priv) {
		
		priv="1";
		return priv;
	}

}
