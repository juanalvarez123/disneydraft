package co.com.example.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import co.com.example.model.Author;
import co.com.example.model.Book;
import co.com.example.model.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static Map<String, Category> categoryMap = new HashMap<String, Category>();
	private static Map<String, Collection<Book>> bookMap = new HashMap<String, Collection<Book>>();
	private static Map<String, Book> books = new HashMap<String, Book>();

	static {
		Category category1 = new Category();
		category1.setCategoryId("001");
		category1.setCategoryName("Java");
		categoryMap.put(category1.getCategoryId(), category1);

		Author author1 = new Author("author01@gmail.com", "author01", "author02");

		Book book1 = new Book();
		book1.setAuthor(author1);
		book1.setBookName("Spring Series");
		book1.setBookId("001");

		Book book2 = new Book();
		book2.setAuthor(author1);
		book2.setBookName("CXF Series");
		book2.setBookId("002");

		Collection<Book> booksList = new ArrayList<Book>();
		booksList.add(book1);
		books.put(book1.getBookId(), book1);
		booksList.add(book2);
		books.put(book2.getBookId(), book2);
		
		category1.setBooks(booksList);
		bookMap.put(category1.getCategoryId(), booksList);

	}

	public void addCategory(Category category) {
		categoryMap.put(category.getCategoryId(), category);
	}

	// Add Books associated with the Category
	public void addBook(Category category) {
		bookMap.put(category.getCategoryId(), category.getBooks());
	}

	public Collection<Book> getBooks(String categoryId) {
		return bookMap.get(categoryId);
	}
	
	public Book getBook(String bookId){
		return books.get(bookId);
	}

	public Category getCategory(String id) {
		Category cat = null;
		// Dummy implementation to return a new copy of category to
		// avoid getting overridden by service
		if (categoryMap.get(id) != null) {
			cat = new Category();
			cat.setCategoryId(categoryMap.get(id).getCategoryId());
			cat.setCategoryName(categoryMap.get(id).getCategoryName());
			cat.setBooks(categoryMap.get(id).getBooks());
		}
		return cat;
	}

	public void deleteCategory(String id) {
		categoryMap.remove(id);
		// Remove association of books
		bookMap.remove(id);
	}

	public void updateCategory(Category category) {
		categoryMap.put(category.getCategoryId(), category);
	}
}
