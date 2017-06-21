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
import java.nio.file.Path;
import javax.script.ScriptException;

/**
 *
 * @author erhannis
 */
public class Main {
  public static void main(String[] args) throws IOException, ScriptException, NoSuchMethodException {
    Dictionary dict = new Dictionary(new File("jbo-eng_cleaned.txt"));
    String text = new String(Files.readAllBytes(FileSystems.getDefault().getPath("alice.txt")));
    //String[] a = LojbanTextUtils.lojbanToIpa(text);
    String[] sentences = LojbanTextUtils.lojbanToSentences(LojbanTextUtils.clean(text));
    for (int i = 0; i < sentences.length; i++) {
      System.out.println(i + " - " + sentences[i]);
      String[] words = LojbanTextUtils.sentenceToWords(sentences[i]);
      for (int j = 0; j < words.length; j++) {
        System.out.println(i + ":" + j + " - " + words[j] + " = " + dict.getMeaning(words[j]));
      }
    }
  }
}