package com.Demo.hero.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.Demo.hero.repository.StudentRepository;
import com.Demo.hero.student.Student;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {
	
	
	private final StudentRepository repository;
	private final JobRepository jobRepository;
	private final PlatformTransactionManager platformTransactionManager;
	
	@Bean
	public FlatFileItemReader<Student> itemReader(){
		FlatFileItemReader<Student> reader = new FlatFileItemReader<>();
		reader.setResource(new FileSystemResource("./src/main/resources/Students.csv"));
		reader.setName("csvReader");
		reader.setLinesToSkip(1);
		reader.setLineMapper(lineMapper());
		return reader;
		
	}
	
	@Bean
	public StudentProcessor processor() {
		return new StudentProcessor();
	}
	
	@Bean
	public TaskExecutor  taskExecutor() {
		SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
		asyncTaskExecutor.setConcurrencyLimit(10);
		return asyncTaskExecutor;
	}
	
	@Bean
	public RepositoryItemWriter<Student> writer(){
		RepositoryItemWriter<Student> writer = new RepositoryItemWriter<>();
		writer.setRepository(repository);
		writer.setMethodName("save");
		return writer;
		
	}
	
	@Bean
	public Step importStep() {
		return new StepBuilder("csvImport", jobRepository)
				.<Student, Student>chunk(1000 , platformTransactionManager)
				.reader(itemReader())
				.processor(processor())
				.writer(writer())
				.taskExecutor(taskExecutor())
				.build();
	}
	
	@Bean
	public Job runJob() {
		return new JobBuilder("csvImport", jobRepository)
				.start(importStep())
				.build();
	}

	private LineMapper<Student> lineMapper() {
		DefaultLineMapper<Student> linemapper = new DefaultLineMapper<>();
		
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(",");
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id", "firstname", "lastname", "age");
		
		BeanWrapperFieldSetMapper<Student> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Student.class);
		
		linemapper.setLineTokenizer(lineTokenizer);
		linemapper.setFieldSetMapper(fieldSetMapper);

		return linemapper;
	}
	

}
