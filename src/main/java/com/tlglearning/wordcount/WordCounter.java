package com.tlglearning.wordcount;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class WordCounter {

  private final Map<String, Integer> counts = new HashMap<>();

  private int totalWords;


  public Set<String> words(){
    return counts.keySet();
  }

  public int get(String word){
    return counts.getOrDefault(word, 0);
  }

  public Map<String, Integer> getCounts() {
    return Collections.unmodifiableMap(counts);
  }

  public void add(String text){
    String trimmedLine = text.trim();
    if (!trimmedLine.isEmpty()) {
      String[] words = splitWords(trimmedLine);
      countWords(words);
    }
  }

  public int size(){
    return counts.size();
  }

  public int total(){
    return totalWords;
  }

  @Override
  public String toString() {
    return counts.toString();
  }

  String[] splitWords(String text) {
    return text
        .toLowerCase()
        .split("[\\W_]+");
  }

  void countWords(String[] words) {
    Arrays
        .stream(words)
        .map(String::trim)
        .filter((s) -> !s.isEmpty())
        .filter(s-> s.length() > 5)
//        .filter(Predicate.not(String::isEmpty))
        .forEach((word) -> counts.put(word, 1 + counts.getOrDefault(word, 0)));
  }
}
