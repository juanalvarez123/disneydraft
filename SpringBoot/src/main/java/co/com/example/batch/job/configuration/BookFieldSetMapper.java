package co.com.example.batch.job.configuration;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import co.com.example.model.Author;
import co.com.example.model.Book;
import co.com.example.model.eBook;

public class BookFieldSetMapper implements FieldSetMapper<eBook> {

	@Override
	public eBook mapFieldSet(FieldSet fieldSet) throws BindException {
		eBook book = new eBook();
		
		book.setAuthor(fieldSet.readString("author"));
		book.setBookName(fieldSet.readString("bookName"));
		return book;
	}

}
