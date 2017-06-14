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

/**
 *
 * @author erhannis
 */
public class Main {
  public static void main(String[] args) throws IOException {
    String text = new String(Files.readAllBytes(FileSystems.getDefault().getPath("alice.txt")));
    String[] a = LojbanToIpa.lojbanToSentences(text);
    for (int i = 0; i < a.length; i++) {
      System.out.println(i + " - " + a[i]);
    }
  }
}
