package Subjects;
/**
 * class for a regular comment 
 * @author Roli
 *
 */
public class RegularComment extends Comment {

	public RegularComment(String u, String msg) {
		super(u, msg);
	}
	
	//sets the priviledge ID to 0
	public String applyPriviledges(String priv) {
		
		priv = "0";
		return priv;
	}

}
