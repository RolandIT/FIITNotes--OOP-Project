package CustomExceptions;

import javafx.scene.control.Label;

public class IllegalArgException extends Throwable{

	private static final long serialVersionUID = 1L;
	public IllegalArgException(Label lb,String msg) {
		lb.setText(msg);
	}

}
