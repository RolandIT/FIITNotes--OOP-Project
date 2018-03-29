package Subjects;

import java.util.ArrayList;

import Users.User;

public class Document {
	private User author;
	private String docName;
	private int rating;
	private int instRating;
	
	public ArrayList<Comment> comments;
	
	public Document(User author,String docName) {
		this.author=author;
		this.docName=docName;
	}

	public User getAuthor() {
		return author;
	}

	public String getDocName() {
		return docName;
	}

	public int getRating() {
		return rating;
	}

	public int getInstRating() {
		return instRating;
	}		
}


