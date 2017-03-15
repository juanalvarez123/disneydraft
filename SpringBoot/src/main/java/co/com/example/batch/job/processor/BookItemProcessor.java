package co.com.example.batch.job.processor;

import org.springframework.batch.item.ItemProcessor;

import co.com.example.model.eBook;

public class BookItemProcessor implements ItemProcessor<eBook, eBook> {

	@Override
	public eBook process(final eBook book) throws Exception {
		// final String firstName = person.getFirstName().toUpperCase();
		// final String lastName = person.getLastName().toUpperCase();

		final eBook transformedBook = book;

		return transformedBook;
	}

}