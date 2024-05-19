package com.jpozarycki.springairagintro.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "jfp.aiapp")
public class VectorStoreProperties {
    private String vectorStorePath;
    private List<Resource> documentsToLoad;

}
