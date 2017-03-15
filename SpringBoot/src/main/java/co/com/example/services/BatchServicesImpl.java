package co.com.example.services;

import java.util.Date;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import co.com.example.batch.job.configuration.BatchController;

@Service("batchServices")
public class BatchServicesImpl implements BatchServices {

	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	public BatchController batchController;
	

	@Override
	public void executeBatchTest() throws JobExecutionAlreadyRunningException, JobRestartException,
		JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Job bookLoadBatch = appContext.getBean("importBookJob", Job.class);
		JobParameters jobParameters = new JobParametersBuilder().addDate("date", new Date()).toJobParameters();

		JobExecution jobExecution = batchController.getJobLauncher().run(bookLoadBatch, jobParameters);
		BatchStatus batchStatus = jobExecution.getStatus();
		
		batchController.getJobExecutionMap().put("bookLoadBatch", jobExecution);
		batchController.getBatchStatusMap().put("bookLoadBatch", batchStatus);
	}

}
