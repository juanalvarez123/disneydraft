package co.com.example.model;

import co.com.example.utils.ResourceLink;

public class Book {

	private String bookId;
	private String bookName;
	private Author author;
	
	ResourceLink links;
	
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public ResourceLink getSelfLink() {
		return links;
	}
	public void setSelfLink(ResourceLink selfLink) {
		this.links = selfLink;
	}

}
