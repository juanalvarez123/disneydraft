package co.com.example.batch.job.processor;

import org.springframework.batch.item.ItemProcessor;

import co.com.example.model.Book;

public class BookItemProcessor implements ItemProcessor<Book, Book> {

	@Override
	public Book process(final Book person) throws Exception {
		// final String firstName = person.getFirstName().toUpperCase();
		// final String lastName = person.getLastName().toUpperCase();

		final Book transformedBook = new Book();

		return transformedBook;
	}

}