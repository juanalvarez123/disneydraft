package co.com.example.dao;

import java.util.Collection;
import co.com.example.model.Book;
import co.com.example.model.Category;

public interface CategoryDAO {

	public void addCategory(Category category);

	public void addBook(Category category);

	public Collection<Book> getBooks(String categoryId);

	public Category getCategory(String id);

	public void deleteCategory(String id);

	public void updateCategory(Category category);
	
	public Book getBook(String bookId);
}
