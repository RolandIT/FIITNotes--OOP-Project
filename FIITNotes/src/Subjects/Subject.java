package Subjects;

import java.util.ArrayList;

public class Subject {
	public static int numberOfSubjects=0;
	private String subjName;
	
	private ArrayList<Document> Documents;
	
	public Subject(String name) {
		subjName=name;
		numberOfSubjects++;
	}
	
	public void addDocument(Document document) {
		Documents.add(document);
	}

	public String getSubjName() {
		return subjName;
	}	
}
