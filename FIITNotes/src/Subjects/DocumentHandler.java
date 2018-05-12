package Subjects;

import java.io.File;
import java.util.ArrayList;
/**
 * class that handles all the operations with documents 
 * @author Roli
 *
 */
public class DocumentHandler {
	private ArrayList<Document> currentSubjDocuments = new ArrayList<Document>();
	
	//loads all the doccuments for the current subject
	public void discoverDocuments(String folder) {
		File[] docFiles = new File("Documents/"+folder).listFiles();
		for(File doc : docFiles) {
			currentSubjDocuments.add(new Document(doc,folder,doc.getName()));
		}
	}
	
	//returns a list of documents for the current subject 
	@SuppressWarnings("unchecked")
	public ArrayList<Document> getDocuments(){
		ArrayList<Document> docs=new ArrayList<Document>();
		docs=(ArrayList<Document>) currentSubjDocuments.clone();
		currentSubjDocuments.clear();
		return docs;
	}
}
