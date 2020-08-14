package com.qinzx.timecost;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;

/**
 * 使用agent，打包是需要注意的，使用assembly实现自定义打包，或者采用系统默认的打包模式，
 * 将引入的第三方工具编译为class文件打包到jar中，这样在使用agent的时候无需在引入agent中
 * 依赖的第三方jar，本例中agent依赖的第三方jar包是：javaassist。一开始出错就是因为使用
 * maven打得包有问题，没有包含javaassist，导致执行到ClassPool.getDefault()方法就报
 * 错“ClassNotFoundException“。maven工具得好好掌握一下。
 *
 * 被调用方法
 * @author : qinzx
 * @create 2020-06-29 19:32
 **/
public class TimeCostAgent {

    /**
     * 启动时配置agent调用的方法
     * @param agentArgs
     * @param _inst
     */
    public static void premain(String agentArgs, Instrumentation _inst) {
//        System.out.println("TimeCostAgent.premain() was called.");
        //Initialize the static variables we use to track infomation.
        //set up the class-file transformer
//        System.out.println(agentArgs);
//        System.out.println("Adding a PerfMonXformer instance to the JVM");
        addTrans(_inst);
    }

    private static void addTrans(Instrumentation _inst) {
        _inst.addTransformer(new PerfMonXformer());
    }

    /**
     * 动态 attach 方式启动，运行此方法
     *
     * manifest需要配置属性Agent-Class
     *
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out.println("agentmain");
        addTrans(inst);
    }
}
