package com.bitcoindata.BitcoinProcessor.springbatchconfig;

import com.bitcoindata.BitcoinProcessor.models.BitcoinData;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Value("${file.input}")
    private String fileInput;

    @Bean
    public FlatFileItemReader<BitcoinData> reader() {

        FlatFileItemReader<BitcoinData> flatFileItemReader = new FlatFileItemReader<>();
        flatFileItemReader.setLinesToSkip(1);

        return new FlatFileItemReaderBuilder<BitcoinData>()
                .name("bitcoinItemReader")
                .resource(new ClassPathResource(fileInput))
                .delimited()
                .names("timeStamp", "date_time", "open", "high", "low", "close", "volume_btc", "volume_currency", "weighted_price")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<BitcoinData>() {{
                    setTargetType(BitcoinData.class);
                }})
                .build();
    }

    private LineMapper<BitcoinData> lineMapper() {
        DefaultLineMapper<BitcoinData> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("unix_timestamp", "date_time", "open", "high", "low", "close", "volume_btc", "volume_currency", "weighted_price");

        BeanWrapperFieldSetMapper<BitcoinData> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(BitcoinData.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;
    }

    @Bean
    public JdbcBatchItemWriter<BitcoinData> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<BitcoinData>()
                .itemSqlParameterSourceProvider(new
                        BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO bitcoin (timestamp, date_time, open, high, low, close, volume_btc, volume_currency, weighted_price) VALUES (:timestamp, :date_tame, :open, :high, :low, :close, :volume_btc, :volume_currency, :weighted_price)")
                .dataSource(dataSource)
                .build();
    }

//    public Step step1() {
//        return stepBuilderFactory.get("csv-step").<BitcoinData, BitcoinData> chunk(10)
//                .reader(reader())
//                .writer(writer())
//                .build();
//    }

//    @Bean
//    public Job runJob(){
//        return jobBuilderFactory.get("importBitcoinData")
//                .flow(step1()).end().build();
//    }
}
