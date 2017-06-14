/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erhannis.lojban.core;

import java.io.FileNotFoundException;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author erhannis
 */
public class IpaReader {
  public static void test() throws ScriptException, NoSuchMethodException, FileNotFoundException {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("JavaScript");

    engine.eval(new java.io.FileReader("mespeak.full.js"));
    engine.eval("meSpeak.loadConfig(\"mespeak_config.json\");");
    engine.eval("meSpeak.loadVoice(\"en.json\");");
    
    Object meSpeak = engine.get("meSpeak");

    Invocable inv = (Invocable) engine;
    //inv.invokeFunction("hello", "Scripting!!" );
    engine.eval("meSpeak.play(meSpeak.speak('[[DIs Iz @n Ig'zA:mpl 'sEnt@ns]]', { 'rawdata': 'mime' }));");
  }
}
