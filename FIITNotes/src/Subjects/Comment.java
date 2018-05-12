package Subjects;
/**
 * comments class 
 * @author Roli
 *
 */
public class Comment {
	private String commenter;
	private String message;
	
	public Comment(String u,String msg) {
		commenter = u;
		message = msg;
	}
	
	 public String getCommenter() {
		 return commenter;
	 }
	 
	 public String getMessage() {
		 return message;
	 }
	 
	 /**
	  * applies the priviledges of the current user 
	  * @param priv
	  * @return
	  */
	 public String applyPriviledges(String priv) {
			
		 priv = " ";
		 return priv;
	 }
	
}
