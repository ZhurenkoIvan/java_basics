package main;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.lucene.morphology.LuceneMorphology;
import org.apache.lucene.morphology.russian.RussianLuceneMorphology;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        Searcher searcher = new Searcher("http://www.playback.ru/");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(searcher);

       Indexer indexer = new Indexer();
       indexer.addLemmas();
       indexer.addIndexes();

//        String text = "Повторное появление леопарда в Осетии позволяет предположить, что леопард ПОСТоянно обитает в НЕКОТОРЫХ райоНАх Северного Кавказа.";
//        Lemmatizator lemmatizator = new Lemmatizator(text);
//        HashMap<String, Integer> map = lemmatizator.morphText();
//        for (String key : map.keySet()) {
//            System.out.println(key + " - " + map.get(key));
//        }

//        Document doc = Jsoup.connect("http://www.playback.ru/")
//                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
//                .referrer("http://www.google.com")
//                .get();
//        System.out.println(doc.text());




    }
}
