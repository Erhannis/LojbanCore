/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erhannis.lojban.core;

import java.util.HashMap;

/**
 *
 * @author erhannis
 */
public class LojbanToIpa {
  /**
   * Turns a Lojban text into an array of IPA sentences
   * @param lojban
   * @return 
   */
  public static String[] lojbanToIpa(String lojban) {
    String[] sentences = lojbanToSentences(clean(lojban));
    String[] results = new String[sentences.length];
    for (int i = 0; i < sentences.length; i++) {
      results[i] = sentenceToIpa(sentences[i]);
    }
    return results;
  }

  protected static String clean(String lojban) {
    lojban = lojban.toLowerCase();
    lojban = lojban.replace("’", "'");
    lojban = lojban.replaceAll("\\s+", " ");
    return lojban;
  }
  
  protected static final HashMap<Character, String> CHAR_TO_IPA = new HashMap<Character, String>();
  static {
    CHAR_TO_IPA.put('b', "b");
    CHAR_TO_IPA.put('c', "ʃ");
    CHAR_TO_IPA.put('d', "d");
    CHAR_TO_IPA.put('f', "f");
    CHAR_TO_IPA.put('g', "g");
    CHAR_TO_IPA.put('j', "ʒ");
    CHAR_TO_IPA.put('k', "k");
    CHAR_TO_IPA.put('l', "l");
    CHAR_TO_IPA.put('m', "m");
    CHAR_TO_IPA.put('n', "n");
    CHAR_TO_IPA.put('p', "p");
    CHAR_TO_IPA.put('r', "r");
    CHAR_TO_IPA.put('s', "s");
    CHAR_TO_IPA.put('t', "t");
    CHAR_TO_IPA.put('v', "v");
    CHAR_TO_IPA.put('x', "x");
    CHAR_TO_IPA.put('z', "z");
    CHAR_TO_IPA.put('\'', "h");
    CHAR_TO_IPA.put('.', "ʔ"); //TODO ?
    CHAR_TO_IPA.put('a', "a");
    CHAR_TO_IPA.put('e', "ɛ");
    CHAR_TO_IPA.put('i', "i");
    CHAR_TO_IPA.put('o', "o");
    CHAR_TO_IPA.put('u', "u");
    CHAR_TO_IPA.put('y', "ə");
  }
  
  protected static String sentenceToIpa(String sentence) {
    StringBuilder sb = new StringBuilder();
    for (char c : sentence.toCharArray()) {
      String c2 = CHAR_TO_IPA.get(c);
      if (c2 == null) {
        //TODO Just skip?
        c2 = "" + c;
      }
      sb.append(c2);
    }
    return sb.toString();
  }

  /**
   * Turns a Lojban text into an array of Lojban sentences.
   * Splits on periods and before "i".
   * @param lojban
   * @return 
   */
  protected static String[] lojbanToSentences(String lojban) {
    String[] results = lojban.toLowerCase().split("(?<![a-z'])i(?![a-z'])");
    //TODO Seems kinda wasteful
    for (int i = 1; i < results.length; i++) {
      results[i] = ("i " + results[i]).replaceAll("\\s+", " ");
    }
    //TODO Some people might use periods; accommodate them.
    return results;
  }
}
