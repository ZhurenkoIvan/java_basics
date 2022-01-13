package main;

import org.apache.lucene.morphology.LuceneMorphology;
import org.apache.lucene.morphology.english.EnglishLuceneMorphology;
import org.apache.lucene.morphology.russian.RussianLuceneMorphology;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Разбивает полученный текст на леммы как русские, так и английские. При это не записывает союзы, частицы и предлоги.
public class Lemmatizator {
    private final String text;

    public Lemmatizator(String text) {
        this.text = text;
    }

    public HashMap<String, Integer> getLemmas () throws IOException {
        HashSet<String> engWords = new HashSet<>();
        HashSet<String> rusWords = new HashSet<>();

        Pattern pEng = Pattern.compile("[A-z]{2,}");
        Pattern pRus = Pattern.compile("[А-яёЁ]{2,}");
        Matcher engMatcher = pEng.matcher(text);
        Matcher rusMatcher = pRus.matcher(text);
        while (engMatcher.find()) {
            engWords.add(engMatcher.group());
        }
        while (rusMatcher.find()) {
            rusWords.add(rusMatcher.group());
        }
        LuceneMorphology russianLuceneMorphology = new RussianLuceneMorphology();
        LuceneMorphology englishLuceneMorphology = new EnglishLuceneMorphology();
        HashMap<String, Integer> lemmWords = new HashMap<>();
        for (String s : rusWords) {
            String morph = russianLuceneMorphology.getMorphInfo(s.toLowerCase(Locale.ROOT)).get(0);
            String word = russianLuceneMorphology.getNormalForms(s.toLowerCase(Locale.ROOT)).get(0).replace('ё','е');
            if (!morph.contains("СОЮЗ") && !morph.contains("ПРЕДЛ")) {
                if (lemmWords.containsKey(word)) {
                    lemmWords.put(word, lemmWords.get(word) + 1);
                } else {
                    lemmWords.put(word, 1);
                }
            }
        }
        for (String s : engWords) {
            String morph = englishLuceneMorphology.getMorphInfo(s.toLowerCase(Locale.ROOT)).get(0);
            String word = englishLuceneMorphology.getNormalForms(s.toLowerCase(Locale.ROOT)).get(0).replace('ё','е');
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
