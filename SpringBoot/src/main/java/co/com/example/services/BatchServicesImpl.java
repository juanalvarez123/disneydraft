package co.com.example.services;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service("batchServicesImpl")
public class BatchServicesImpl implements BatchServices {

	@Autowired
	private ApplicationContext appContext;

	@Override
	public void executeBatchTest() throws JobExecutionAlreadyRunningException, JobRestartException,
		JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		JobLauncher jobLauncher = appContext.getBean(JobLauncher.class);
		Job addNewPodcastJob = appContext.getBean("New Book Job", Job.class);
		JobParameters jobParameters = new JobParametersBuilder().addDate("date", new Date()).toJobParameters();

		JobExecution jobExecution = jobLauncher.run(addNewPodcastJob, jobParameters);
	}

}
