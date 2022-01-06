package main;

import org.apache.lucene.morphology.LuceneMorphology;
import org.apache.lucene.morphology.russian.RussianLuceneMorphology;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
//        String text = "Повторное появление леопарда в Осетии позволяет предположить, что леопард ПОСТоянно обитает в НЕКОТОРЫХ райоНАх Северного Кавказа.";
//        Lemmatizator lemmatizator = new Lemmatizator(text);
//        HashMap<String, Integer> map = lemmatizator.morphText();
//        for (String key : map.keySet()) {
//            System.out.println(key + " - " + map.get(key));
//        }


        Searcher searcher = new Searcher("http://www.playback.ru/");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(searcher);
        searcher.showAllURLS();

    }
}
