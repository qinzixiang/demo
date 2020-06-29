package com.qinzx.timecost;

import java.lang.instrument.Instrumentation;

/**
 * @author : qinzx
 * @create 2020-06-29 19:32
 **/
public class TimeCostAgent {
    static private Instrumentation inst = null;

    public static void premain(String agentArgs, Instrumentation _inst) {
        System.out.println("TimeCostAgent.premain() was called.");
        //Initialize the static variables we use to track infomation.
//        inst = _inst;
        //set up the class-file transformer
        System.out.println(agentArgs);
        PerfMonXformer trans = new PerfMonXformer(agentArgs);
        System.out.println("Adding a PerfMonXformer instance to the JVM");
        _inst.addTransformer(trans);
    }
}
