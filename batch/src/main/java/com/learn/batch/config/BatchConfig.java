package com.learn.batch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.PostgresPagingQueryProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
    @Bean
    @StepScope
    public JdbcPagingItemReader<Student> studentReader(DataSource dataSource,  @Value("#{jobParameters['hello']}") String hello) {
        JdbcPagingItemReader<Student> reader = new JdbcPagingItemReader<>();
        reader.setDataSource(dataSource);
        reader.setPageSize(10);

        PostgresPagingQueryProvider queryProvider = new PostgresPagingQueryProvider();
        queryProvider.setSelectClause("student_id, first_name, last_name, date_of_birth, email, phone, enrolled_date");
        queryProvider.setFromClause("from Student");
        System.out.println(hello);
        queryProvider.setSortKeys(Map.of("student_id", Order.ASCENDING));

        reader.setQueryProvider(queryProvider);
        reader.setRowMapper((rs, rowNum) -> {
            Student s = new Student();
            s.setStudentId(rs.getLong("student_id"));
            s.setFirstName(rs.getString("first_name"));
            s.setLastName(rs.getString("last_name"));
            s.setDateOfBirth(rs.getDate("date_of_birth") != null ? rs.getDate("date_of_birth").toLocalDate() : null);
            s.setEmail(rs.getString("email"));
            s.setPhone(rs.getString("phone"));
            s.setEnrolledDate(rs.getDate("enrolled_date") != null ? rs.getDate("enrolled_date").toLocalDate() : null);
            return s;
        });

        return reader;
    }
    @Bean
    public ItemWriter<Student> studentWriter() {
        return students -> {
            for (Student s : students) {
                System.out.println("Writing student: " + s.getFirstName() + " " + s.getLastName());
            }
        };
    }
    @Bean
    public Step studentStep(JobRepository jobRepository,
                            PlatformTransactionManager transactionManager,
                            ItemReader<Student> studentReader,
                            ItemWriter<Student> studentWriter) {
        return new StepBuilder("studentStep", jobRepository)
                .<Student, Student>chunk(100, transactionManager)
                .reader(studentReader)
                .writer(studentWriter)
                .build();
    }

    @Bean
    public Job studentJob(@Qualifier("studentStep") Step studentStep, JobRepository jobRepository) {
        return new JobBuilder("studentJob", jobRepository)
                .start(studentStep)
                .build();
    }


}
