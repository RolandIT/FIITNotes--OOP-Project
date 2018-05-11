package Subjects;

public interface NewSubjectListener {
	void onNewSubject();
	
	interface NewFollowedListener{
		void onNewFollowed(Subject subject);
	}
}