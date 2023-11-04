package com.vladislavskiy.configurations;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

@Configuration
public class Config {
    @SneakyThrows
    @Bean
    public DataSource dataSource() {
        try (BufferedReader reader = Files.newBufferedReader(Path.of("D:/IDEA2022/JAVA_PROJECTS/Anime/Anime/src/main/resources/application.properties"))) {
            Properties props = new Properties();
            props.load(reader);

            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setConnectionProperties(props);
            return dataSource;
        }
    }
}
