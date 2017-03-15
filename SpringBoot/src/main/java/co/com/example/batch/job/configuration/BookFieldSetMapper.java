package co.com.example.batch.job.configuration;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import co.com.example.model.Book;

public class BookFieldSetMapper implements FieldSetMapper<Book> {

	@Override
	public Book mapFieldSet(FieldSet arg0) throws BindException {
		Book book = new Book();
		
		return book;
	}

}
