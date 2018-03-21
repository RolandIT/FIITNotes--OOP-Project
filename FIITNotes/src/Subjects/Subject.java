package Subjects;

import java.util.ArrayList;

public class Subject {
	public static int numberOfSubjects=0;
	private String subjName;
	private String owner;
	private ArrayList<Document> Documents;
	
	public Subject(String name,String owner) {
		subjName=name;
		numberOfSubjects++;
		this.owner=owner;
	}
	
	public void addDocument(Document document) {
		Documents.add(document);
	}

	public String getSubjName() {
		return subjName;
	}	
	public String getOwner() {
		return owner;
	}
}
