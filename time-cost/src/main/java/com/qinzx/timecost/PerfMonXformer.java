package com.qinzx.timecost;

import javassist.*;

import java.io.ByteArrayInputStream;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.Objects;

/**
 * @author : qinzx
 * @create 2020-06-29 19:31
 **/
public class PerfMonXformer implements ClassFileTransformer {
    private static final String START_TIME = "\nlong startTime = System.currentTimeMillis();\n";
    private static final String END_TIME = "\nlong endTime = System.currentTimeMillis();\n";
    private static final String METHOD_RUTURN_VALUE_VAR = "__time_monitor_result";
    private static final String EMPTY = "";

    private String classNameKeyword;

    public PerfMonXformer(String classNameKeyword){
        this.classNameKeyword = classNameKeyword;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        byte[] transformed = null;
        System.out.println("Transforming "+className);
        ClassPool pool = ClassPool.getDefault();
//        CtClass cl = null;
        className = className.replace("/", ".");
        CtClass ctClass = null;

        try {
//            cl = pool.makeClass(new ByteArrayInputStream(classfileBuffer));
//            if (!cl.isInterface()) {
//                CtBehavior[] methods = cl.getDeclaredBehaviors();
//                for (int i = 0; i < methods.length; i++) {
//                    if (!methods[i].isEmpty()) {
//                        doMethod(methods[i]);
//                    }
//                }
//                transformed = cl.toBytecode();
//            }
            ctClass = pool.get(className);
            if(Objects.equals(classNameKeyword, EMPTY)||(!Objects.equals(classNameKeyword, EMPTY)&&className.indexOf(classNameKeyword)!=-1)){
                //所有方法
                CtMethod[] ctMethods = ctClass.getDeclaredMethods();
                //遍历每一个方法
                for(CtMethod ctMethod:ctMethods){
                    //修改方法的字节码
                    transformMethod(ctMethod, ctClass);
                }
            }
        } catch (Exception e) {
            System.err.println("Could not instrument " + className + ", exception: " + e.getMessage());
            e.printStackTrace();
        }
//        finally {
//            if (cl != null) {
//                cl.detach();
//            }
//        }
//        return transformed;
        return null;
    }
    /**
     * 为每一个拦截到的方法 执行一个方法的耗时操作
     * @param ctMethod
     * @param ctClass
     * @throws Exception
     */
    private void transformMethod(CtMethod ctMethod,CtClass ctClass) throws Exception{
        //抽象的方法是不能修改的 或者方法前面加了final关键字
        if((ctMethod.getModifiers()&Modifier.ABSTRACT)>0){
            return;
        }
        //获取原始方法名称
        String methodName = ctMethod.getName();
        String monitorStr = "\nSystem.out.println(\"method " + ctMethod.getLongName() + " cost:\" +(endTime - startTime) +\"ms.\");";
        //实例化新的方法名称
        String newMethodName = methodName + "$impl";
        //设置新的方法名称
        ctMethod.setName(newMethodName);
        //创建新的方法，复制原来的方法 ，名字为原来的名字
        CtMethod newMethod = CtNewMethod.copy(ctMethod,methodName, ctClass, null);

        StringBuilder bodyStr = new StringBuilder();
        //拼接新的方法内容
        bodyStr.append("{");

        //返回类型
        CtClass returnType = ctMethod.getReturnType();

        //是否需要返回
        boolean hasReturnValue = (CtClass.voidType != returnType);

        if (hasReturnValue) {
            String returnClass = returnType.getName();
            bodyStr.append("\n").append(returnClass + " " + METHOD_RUTURN_VALUE_VAR + ";");
        }


        bodyStr.append(START_TIME);

        if (hasReturnValue) {
            bodyStr.append("\n").append(METHOD_RUTURN_VALUE_VAR + " = ($r)" + newMethodName + "($$);");
        } else {
            bodyStr.append("\n").append(newMethodName + "($$);");
        }

        bodyStr.append(END_TIME);
        bodyStr.append(monitorStr);

        if (hasReturnValue) {
            bodyStr.append("\n").append("return " + METHOD_RUTURN_VALUE_VAR+" ;");
        }

        bodyStr.append("}");
        //替换新方法
        newMethod.setBody(bodyStr.toString());
        //增加新方法
        ctClass.addMethod(newMethod);
    }

    private void doMethod(CtBehavior method) throws CannotCompileException {
        method.insertBefore("long stime = System.nanoTime();");
        method.insertAfter("System.out.println(\"leave "+method.getName()+
                " and time :\" + (System.nanoTime()-stime));");
    }
}
