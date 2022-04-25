package com.bitcoindata.BitcoinProcessor.batchconfig;

import com.bitcoindata.BitcoinProcessor.models.BitcoinData;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<BitcoinData> reader() {
        return new FlatFileItemReaderBuilder<BitcoinData>()
                .name("bitcoinItemReader")
                .resource(new ClassPathResource(("bitcoin-data")))
                .delimited()
                .names("timeStamp", "open")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<BitcoinData>() {{
                    setTargetType(BitcoinData.class);
                }})
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<BitcoinData> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<BitcoinData>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO bitcoin (timeStamp, open) VALUES (:timeStamp, :open)")
                .dataSource(dataSource)
                .build();
    }

//    @Bean
//    public Job impo
}
