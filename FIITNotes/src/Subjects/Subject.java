package Subjects;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Subject implements Serializable {
	private static final long serialVersionUID = 1L;
	public static int numberOfSubjects=0;
	private String subjName;
	private int ownerID;
	private ArrayList<Document> Documents;
	
	public Subject(String name,int ownerID) {
		subjName = name;
		numberOfSubjects++;
		this.ownerID = ownerID;
	}
	
	public void addDocument(Document document) {
		Documents.add(document);
	}

	public String getSubjName() {
		return subjName;
	}
	
	public int getOwnerID() {
		return ownerID;
	}
	
	//save object to the subjects folder
	public void saveSubject() throws IOException {
		try {
			FileOutputStream Fileout = new FileOutputStream("Subjects/" + this.subjName+".ser");
			ObjectOutputStream Objectout = new ObjectOutputStream(Fileout);
			Objectout.writeObject(this);
			Objectout.close();
			Fileout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
