package co.com.example.batch.job.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BatchController {
	
		
	private JobLauncher jobLauncher;
	private Map<String, JobExecution> jobExecutionMap;
	private Map<String, BatchStatus> batchStatusMap;
	
	@Autowired
	public BatchController(ApplicationContext appContext){
		jobLauncher = appContext.getBean(JobLauncher.class);
		jobExecutionMap = new HashMap<>();
		batchStatusMap = new HashMap<>();
	}


	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}


	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}


	public Map<String, JobExecution> getJobExecutionMap() {
		return jobExecutionMap;
	}


	public void setJobExecutionMap(Map<String, JobExecution> obExecutionMap) {
		this.jobExecutionMap = obExecutionMap;
	}


	public Map<String, BatchStatus> getBatchStatusMap() {
		return batchStatusMap;
	}


	public void setBatchStatusMap(Map<String, BatchStatus> batchStatusMap) {
		this.batchStatusMap = batchStatusMap;
	}


}
