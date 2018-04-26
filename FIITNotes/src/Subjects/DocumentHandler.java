package Subjects;

import java.io.File;
import java.util.ArrayList;

public class DocumentHandler {
	public ArrayList<Document> currentSubjDocuments = new ArrayList<Document>();
	
	//TODO 
	public void discoverDocuments(String folder) {
		File[] docFiles = new File("Documents/"+folder).listFiles();
		for(File doc : docFiles) {
			currentSubjDocuments.add(new Document(doc,folder,doc.getName()));
		}
	}
	
	//TODO
	@SuppressWarnings("unchecked")
	public ArrayList<Document> getDocuments(){
		ArrayList<Document> docs=new ArrayList<Document>();
		docs=(ArrayList<Document>) currentSubjDocuments.clone();
		currentSubjDocuments.clear();
		return docs;
	}
}
