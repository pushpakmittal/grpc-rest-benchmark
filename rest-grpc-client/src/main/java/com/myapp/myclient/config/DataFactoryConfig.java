package com.myapp.myclient.config;

import org.fluttercode.datafactory.impl.DataFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataFactoryConfig {
    @Bean
    public DataFactory dataFactory() {
        return new DataFactory();
    }
}
