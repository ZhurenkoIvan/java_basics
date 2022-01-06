package main;

import org.apache.lucene.morphology.LuceneMorphology;
import org.apache.lucene.morphology.russian.RussianLuceneMorphology;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class Lemmatizator {
    private String text;

    public Lemmatizator(String text) {
        this.text = text;
    }

    public HashMap<String, Integer> morphText () throws IOException {
        String[] wordsList = text.split("[ ,.]{1,2}");
        LuceneMorphology luceneMorphology = new RussianLuceneMorphology();
        HashMap<String, Integer> lemmWords = new HashMap<>();
        for (String s : wordsList) {
            String morph = luceneMorphology.getMorphInfo(s.toLowerCase(Locale.ROOT)).get(0);
            String word = luceneMorphology.getNormalForms(s.toLowerCase(Locale.ROOT)).get(0);
            if (!morph.contains("СОЮЗ") && !morph.contains("ПРЕДЛ")) {
                if (lemmWords.containsKey(word)) {
                    lemmWords.put(word, lemmWords.get(word) + 1);
                } else {
                    lemmWords.put(word, 1);
                }
            }
        }
        return lemmWords;
    }
}
