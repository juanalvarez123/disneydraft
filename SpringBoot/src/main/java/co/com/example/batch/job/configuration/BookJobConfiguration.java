package co.com.example.batch.job.configuration;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import co.com.example.batch.job.listener.JobCompletionNotificationListener;
import co.com.example.batch.job.processor.BookItemProcessor;
import co.com.example.model.eBook;


@Configuration
@EnableBatchProcessing
public class BookJobConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public DataSource dataSource;

	// tag::readerwriterprocessor[]
	@Bean
	public FlatFileItemReader<eBook> reader() {
		FlatFileItemReader<eBook> reader = new FlatFileItemReader<eBook>();
		reader.setLinesToSkip(1);// first line is title definition
		reader.setResource(new ClassPathResource("books.csv"));
		reader.setLineMapper(lineMapper());
		reader.setStrict(false);
		return reader;
	}

	@Bean
	public BookItemProcessor processor() {
		return new BookItemProcessor();
	}

	@Bean
	public JdbcBatchItemWriter<eBook> writer() {
		JdbcBatchItemWriter<eBook> writer = new JdbcBatchItemWriter<eBook>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<eBook>());
		writer.setSql("INSERT INTO book (name, author) VALUES (:bookName, :author)");
		writer.setDataSource(dataSource);
		return writer;
	}

	// Defines the job
	@Bean
	public Job importBookJob(JobCompletionNotificationListener listener) {
		return jobBuilderFactory.get("importBookJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1())
				.end()
				.build();
	}

	// a single step
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1")
				.<eBook, eBook>chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}

	@Bean
	public LineMapper<eBook> lineMapper() {
		DefaultLineMapper<eBook> lineMapper = new DefaultLineMapper<eBook>();

		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(";");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames(new String[] { "bookName", "author" });

		BeanWrapperFieldSetMapper<eBook> fieldSetMapper = new BeanWrapperFieldSetMapper<eBook>();
		fieldSetMapper.setTargetType(eBook.class);

		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(bookFieldSetMapper());

		return lineMapper;
	}

	@Bean
	public BookFieldSetMapper bookFieldSetMapper() {
		return new BookFieldSetMapper();
	}
}
