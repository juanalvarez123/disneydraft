package co.com.example.batch.job.listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import co.com.example.model.Book;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
//		 if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
//		 List<Book> results = jdbcTemplate.query("SELECT first_name, last_name
//		 FROM people",
//		 new RowMapper<Book>() {
//		 @Override
//		 public Book mapRow(ResultSet rs, int row) throws SQLException {
//		 return new Book();
//		 }
//		 });
//		
//		 for (Book book : results) {
//		
//		 }
//		
//		 }
	}

}
