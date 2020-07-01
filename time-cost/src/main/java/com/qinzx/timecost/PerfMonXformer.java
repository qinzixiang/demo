package com.qinzx.timecost;

import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @author : qinzx
 * @create 2020-06-29 19:31
 **/
public class PerfMonXformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] transformed = null;
        CtClass cl = null;
        try {
            cl = ClassPool.getDefault().makeClass(new java.io.ByteArrayInputStream(classfileBuffer));
            if (cl.getName().startsWith("com.qinzx")) {
                System.out.println("生成类：" + cl.getName());
                if (!cl.isInterface()) {
                    CtBehavior[] methods = cl.getDeclaredBehaviors();
                    for (CtBehavior method : methods) {
                        if (!method.isEmpty()) {
                            doMethod(method);
                        }
                    }
                    System.out.println("Transforming " + className);
                    transformed = cl.toBytecode();
                }
            }
        } catch (Exception e) {
            System.err.println("Could not instrument " + className + ", exception: " + e.getMessage());
        } finally {
            if (cl != null) {
                cl.detach();
            }
        }
        return transformed;
    }

    private void doMethod(CtBehavior method) throws CannotCompileException {
//        method.insertBefore("long stime = System.nanoTime();");
//        method.insertAfter("System.out.println(\"leave " + method.getName() + " and time :\" + (System.nanoTime()-stime));");

        method.instrument(new ExprEditor() {
            @Override
            public void edit(MethodCall m) throws CannotCompileException {
                m.replace("{ long stime = System.nanoTime(); $_ = $proceed($$); System.out.println(\""
                        + m.getClassName() + "." + m.getMethodName()
                        + ":\"+(System.nanoTime()-stime));}");
            }
        });
    }
}
