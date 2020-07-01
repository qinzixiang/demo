package com.qinzx.timecost;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * @author : qinzx
 * @create 2020-06-29 19:32
 **/
public class TimeCostAgent {

    public static void premain(String agentArgs, Instrumentation _inst) {
        System.out.println("TimeCostAgent.premain() was called.");
        //Initialize the static variables we use to track infomation.
        //set up the class-file transformer
        System.out.println(agentArgs);
        ClassFileTransformer trans = new PerfMonXformer();
        System.out.println("Adding a PerfMonXformer instance to the JVM");
        _inst.addTransformer(trans);
    }
}
