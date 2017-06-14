/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erhannis.lojban.core;

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
    throw new UnsupportedOperationException("Not implemented");
  }
  
  /**
   * Turns a Lojban text into an array of Lojban sentences.
   * Splits on periods and before "i".
   * @param lojban
   * @return 
   */
  protected static String[] lojbanToSentences(String lojban) {
    String[] results = lojban.toLowerCase().split("(?<![a-z'’])i(?![a-z'’])");
    //TODO Seems kinda wasteful
    for (int i = 1; i < results.length; i++) {
      results[i] = ("i " + results[i]).replaceAll("\\s+", " ");
    }
    //TODO Some people might use periods; accommodate them.
    return results;
  }
}
