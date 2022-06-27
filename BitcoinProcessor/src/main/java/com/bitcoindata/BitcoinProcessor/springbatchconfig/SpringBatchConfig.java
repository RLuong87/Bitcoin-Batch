package com.bitcoindata.BitcoinProcessor.springbatchconfig;

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
                .names("timeStamp", "open", "high", "low", "close", "volume_BTC", "volume_Currency", "weighted_Price")
                .fieldSetMapper(new BeanWrapperFieldSetMapper<BitcoinData>() {{
                    setTargetType(BitcoinData.class);
                }})
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<BitcoinData> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<BitcoinData>()
                .itemSqlParameterSourceProvider(new
                        BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO bitcoin (timeStamp, open, high, low, close, volume_BTC, volume_Currency, weighted_Price) VALUES (:timeStamp, :open, :high, :low, :close, :volume_BTC, :volume_Currency, :weighted_Price)")
                .dataSource(dataSource)
                .build();
    }
}
