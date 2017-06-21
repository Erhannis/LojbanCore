/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erhannis.lojban.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author erhannis
 */
public class Dictionary {
  protected static final String UNKNOWN = "[MEANING UNKNOWN]";
  
  protected final HashMap<String, String> mMap = new HashMap<>();
  
  public Dictionary(File dictionary) throws IOException {
    this(Files.readAllLines(dictionary.toPath()));
  }
  
  public Dictionary(List<String> dictionaryLines) {
    String mode = "words";
    HashSet<String> words = new HashSet<>();
    StringBuilder meanings = new StringBuilder();
    for (String line : dictionaryLines) {
      switch (mode) {
        case "words":
          if (line.startsWith("\t")) {
            mode = "meanings";
          }
          break;
        case "meanings":
          if (!line.startsWith("\t")) {
            String meaning = meanings.toString();
            for (String word : words) {
              if (mMap.containsKey(word)) {
                System.err.println("Word already mapped! " + word);
                System.err.println("  to " + mMap.get(word));
                System.err.println("  now to " + meaning);
              }
              mMap.put(word, meaning);
            }
            words.clear();
            meanings.setLength(0);
            mode = "words";
          }
          break;
      }
      switch (mode) {
        case "words":
          // Decided we only want the first word; the others are rafsi
          if (words.isEmpty()) {
            words.add(line);
          }
          break;
        case "meanings":
          meanings.append(" " + line.trim());
          break;
      }
    }
  }
  
  public String getMeaning(String word) {
    if (mMap.containsKey(word)) {
      return mMap.get(word);
    } else {
      return UNKNOWN;
    }    
  }
}
