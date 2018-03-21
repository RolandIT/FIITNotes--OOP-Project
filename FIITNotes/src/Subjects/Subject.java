package Subjects;

import java.util.ArrayList;

public class Subject {
	private String subjName;
	private String subj;
	private String folder;
	
	private ArrayList<Document> Documents;
	
	public Subject(String name,String subj) {
		subjName=name;
		this.subj=subj;
	}
	
	public void addDocument(Document document) {
		Documents.add(document);
	}	
}
