package Subjects;
/**
 * Interface used for the observer pattern
 * @author Roli
 *
 */
public interface NewSubjectListener {
	void onNewSubject();
	
	//nested interface for a new followed subject 
	interface NewFollowedListener{
		void onNewFollowed(Subject subject);
	}
}