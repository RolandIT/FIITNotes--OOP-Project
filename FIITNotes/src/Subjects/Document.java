package Subjects;

import java.io.File;
import java.util.ArrayList;
/**
 * Documents class
 * @author Roli
 *
 */
public class Document {
	
	private String docName;
	private File docFile;
	
	public ArrayList<Comment> comments;
	
	public Document(File docFile,String folder,String docName) {
		this.docFile = docFile;
		this.docName = docName;
	}
	
	public String getName() {
		return docName;
	}
	
	public File getFile() {
		return docFile;
	}
}


