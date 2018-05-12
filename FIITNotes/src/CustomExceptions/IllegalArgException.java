package CustomExceptions;

import javafx.scene.control.Label;
/**
 * an exception for illegal arguments 
 * @author Roli
 *
 */
public class IllegalArgException extends Throwable{

	private static final long serialVersionUID = 1L;
	/**
	 * sets the text of the label given as an argument 
	 */
	public IllegalArgException(Label lb,String msg) {
		lb.setText(msg);
	}
	
	/**
	 * prints the error message to the console 
	 */
	public  void printErrMess() {
		System.out.println("Illegal arg entered!");
	}

}
