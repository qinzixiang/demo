package com.qinzx.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * JDK动态代理：有接口的对象使用JDK动态代理，无接口的对象使用CGLIB动态代理
 * @author qinzx
 * @date 2020/06/22 16:27
 */
public class ProxyDemo {
    public static void main(String[] args){
        Class[] ins = {CarService.class};
        CarServiceImpl carService = new CarServiceImpl();
        CarService proxy = (CarService) Proxy.newProxyInstance(ProxyDemo.class.getClassLoader(),ins,new JDKProxy(carService));
        proxy.didi();
        proxy.stop();
    }
}
class JDKProxy implements InvocationHandler {
    private Object obj;

    public JDKProxy(Object obj) {
        this.obj = obj;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法执行之前执行:" + method.getName() + ",传递的参数：" + Arrays.toString(args));

        Object res = method.invoke(obj, args);

        System.out.println("方法执行之后执行:" + method.getName() + ",传递的参数：" + Arrays.toString(args));
        return res;
    }
}