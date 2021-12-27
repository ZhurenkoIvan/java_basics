package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ForkJoinPool;


public class Main {
    public static void main(String[] args) throws IOException {
        Searcher searcher = new Searcher("http://www.playback.ru/");
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(searcher);
        searcher.showAllURLS();

    }
}
