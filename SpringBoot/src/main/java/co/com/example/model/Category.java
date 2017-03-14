package co.com.example.model;

import java.util.Collection;

import co.com.example.utils.ResourceLink;

public class Category {

	private String categoryId;
	private String categoryName;
	private Collection<Book> books;
	
	ResourceLink links;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Collection<Book> getBooks() {
		return books;
	}

	public void setBooks(Collection<Book> books) {
		this.books = books;
	}

	public ResourceLink getLinks() {
		return links;
	}

	public void setLinks(ResourceLink links) {
		this.links = links;
	}

}
