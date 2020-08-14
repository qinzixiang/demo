package com.qinzx.timecost;

import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * 修改类字节码方法，使用javaassist字节码修改包修改类字节码。
 *
 * @author : qinzx
 * @create 2020-06-29 19:31
 **/
public class PerfMonXformer implements ClassFileTransformer {

    /*@Override
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
    }*/

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (!className.startsWith("com/global")) {
            return classfileBuffer;
        }
        CtClass cl = null;
        try {
            ClassPool classPool = ClassPool.getDefault();
            cl = classPool.makeClass(new java.io.ByteArrayInputStream(classfileBuffer));

            for (CtMethod method : cl.getDeclaredMethods()) {
                method.addLocalVariable("start", CtClass.longType);
                method.insertBefore("start = System.currentTimeMillis();");
                String methodName = method.getLongName();
                // 请注意第二个参数，设置为true，则表示即便抛出异常了，下面的代码也会执行；相当于将它封装在finally里面了
                method.insertAfter("System.out.println(\"" + methodName + " cost: \" + (System" +
                        ".currentTimeMillis() - start));", true);
            }
            return cl.toBytecode();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cl != null) {
                cl.detach();
            }
        }
        return classfileBuffer;
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
