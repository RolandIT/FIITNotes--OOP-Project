package Subjects;



public class Comment {
	public String commenter;
	public String message;
	
	public Comment(String u,String msg) {
		commenter=u;
		message=msg;
	}
	 public String getCommenter() {
		 return commenter;
	 }
	 
	 public String getMessage() {
		 return message;
	 }
	 
	 public String applyPriviledges(String priv) {
			
		 priv = " ";
		 return priv;
	 }
	
}
