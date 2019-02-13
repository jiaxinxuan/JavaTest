package com.lucence.test;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.nio.file.Paths;

/**
 * @Author jiaxinxuan
 * @Description //TODO
 * @Date 下午1:56 19-1-24
 **/
public class LuceneTest1 {

    public static void main(String[] args) {
        String content="feofjiodjfoishtioehglijcsoijffffoiwejhhhfdoshfiossssssssssssjdffwoiejwposjdflpmcl;dcm";
        Analyzer analyzer = new StandardAnalyzer();
        analyzer.setVersion(Version.LUCENE_7_0_0);
        IndexWriterConfig config  = new IndexWriterConfig(analyzer);
        String INDEX_DIR = "/home/jiaxinxuan/data/luceneIndex";


        File indexFile = new File(INDEX_DIR);
        if (!indexFile.exists()) {
            indexFile.mkdirs();
        }
        try{
            FSDirectory directory = FSDirectory.open(Paths.get(INDEX_DIR));
            IndexWriter writer = new IndexWriter(directory, config);
            Document document = new Document();
            document.add(new TextField("content", content, Field.Store.YES));
            writer.addDocument(document);
            writer.commit();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
