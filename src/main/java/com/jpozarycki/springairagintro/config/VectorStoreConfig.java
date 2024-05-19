package com.jpozarycki.springairagintro.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingClient;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Configuration
@Slf4j
public class VectorStoreConfig {

    @Bean
    SimpleVectorStore simpleVectorStore(EmbeddingClient embeddingClient, VectorStoreProperties vectorStoreProperties) {
        var store = new SimpleVectorStore(embeddingClient);
        File vectorStoreFile = new File(vectorStoreProperties.getVectorStorePath());

        if (vectorStoreFile.exists()) {
            store.load(vectorStoreFile);
        } else {
            log.info("Loading documents into vector store");
            vectorStoreProperties.getDocumentsToLoad().forEach(doc -> {
                log.info("Loading document: {}", doc);
                TikaDocumentReader reader = new TikaDocumentReader(doc);
                List<Document> docs = reader.get();
                TextSplitter splitter = new TokenTextSplitter();
                List<Document> splitDocs = splitter.apply(docs);
                store.add(splitDocs);
            });
            store.save(vectorStoreFile);
        }
        return store;
    }
}
