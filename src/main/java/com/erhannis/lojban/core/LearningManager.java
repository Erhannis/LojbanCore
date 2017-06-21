/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erhannis.lojban.core;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author erhannis
 */
public class LearningManager implements Serializable {
  protected static class WordTracking implements Serializable {
    public final HashSet<Long> timesRead = new HashSet<>();
    public int targetCount = 1;
  }
  
  protected HashMap<String, WordTracking> mTrackings = new HashMap<String, WordTracking>();
  
  protected void ensureContainsTracking(String word) {
    if (!mTrackings.containsKey(word)) {
      mTrackings.put(word, new WordTracking());
    }
  }
  
  public synchronized boolean shouldGiveMeaning(String word) {
    ensureContainsTracking(word);
    WordTracking tracking = mTrackings.get(word);
    return (tracking.timesRead.size() < tracking.targetCount);
  }
  
  public synchronized void meaningGiven(String word) {
    ensureContainsTracking(word);
    mTrackings.get(word).timesRead.add(System.currentTimeMillis());
  }
  
  public synchronized void increaseScheduledRepeats(String word) {
    ensureContainsTracking(word);
    WordTracking tracking = mTrackings.get(word);
    if (tracking.targetCount > tracking.timesRead.size()) {
      tracking.targetCount = tracking.timesRead.size() + 1;
    } else {
      tracking.targetCount++;
    }     
  }
}
