package com.jpozarycki.springairagintro.bootstrap;

import com.jpozarycki.springairagintro.config.VectorStoreProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class LoadVectorStore implements CommandLineRunner {

    @Autowired
    VectorStore vectorStore;

    @Autowired
    VectorStoreProperties vectorStoreProperties;

    @Override
    public void run(String... args) throws Exception {
        log.info("Loading Vector Store");
        if (vectorStore.similaritySearch("Sportsman").isEmpty()) {
            log.info("Loading documents into vector store");
            vectorStoreProperties.getDocumentsToLoad().forEach(doc -> {
                log.info("Loading document: {}", doc.getFilename());
                TikaDocumentReader docReader = new TikaDocumentReader(doc);
                List<Document> documentList = docReader.get();

                TextSplitter textSplitter = new TokenTextSplitter();

                List<Document> splitDocuments = textSplitter.apply(documentList);

                vectorStore.add(splitDocuments);
            });
        }
    }
}
