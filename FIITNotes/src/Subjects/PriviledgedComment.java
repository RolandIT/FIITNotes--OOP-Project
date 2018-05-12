package Subjects;
/**
 * class for a special comment 
 * @author Roli
 *
 */
public class PriviledgedComment extends Comment {

	public PriviledgedComment(String u, String msg) {
		super(u, msg);
	}
	
	//sets the priviledge ID to 1 
	public String applyPriviledges(String priv) {
		priv = "1";
		return priv;
	}

}
