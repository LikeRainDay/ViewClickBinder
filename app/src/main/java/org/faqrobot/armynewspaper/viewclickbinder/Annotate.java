package org.faqrobot.armynewspaper.viewclickbinder;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 候帅
 *         Created by 候帅 on 2017/2/27. 13:47
 */

public class Annotate {

    public void OnClick(Activity activity) {
        //获取MainActivity
        Class<? extends Activity> aClass = activity.getClass();
        //获取MainActivity中所有的方法
        Method[] declaredMethods = aClass.getDeclaredMethods();
        for (Method m :
                declaredMethods) {
            //获取方法上对应的@IOnclick的注解
            Annotation[] annotations = m.getAnnotations();
            for (Annotation a :
                    annotations) {
                Class<? extends Annotation> annotationType = a.annotationType();
                if (null != annotationType) {
                    BaseEvent baseEvent = annotationType.getAnnotation(BaseEvent.class);
                    if (null != baseEvent) {
                        String callback = baseEvent.listenerCallback();
                        Class type = baseEvent.listenerType();
                        String setListener = baseEvent.setListener();

                        try {
                            //通过反射获取方法，@IonClick里面的int[]  value()不需要传参。所以参数省略
                            Method declaredMethod = annotationType.getDeclaredMethod("value");
                            //调用反射方法，获取@IonClick的value,即两个button的id,参数省略
                            int[] valuesIds = (int[]) declaredMethod.invoke(a);
                            InjectInvocationHandler injectInvocationHandler = new InjectInvocationHandler(activity);
                            //增加拦截列表
                            injectInvocationHandler.add(callback, m);
                            //得到监听的代理对象
                            Proxy o = (Proxy) Proxy.newProxyInstance(type.getClassLoader(), new Class[]{type}, injectInvocationHandler);
                            //遍历所有监听的代理对象
                            for (int valuesId :
                                    valuesIds) {
                                //遍历所有button的id
                                View viewById =
                                        activity.findViewById(valuesId);
                                //通过反射获取方法
                                Method method = viewById.getClass().getMethod(setListener, type);
                                //执行方法
                                method.invoke(viewById, o);

                            }

                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }


                    }

                }

            }
        }


    }


    private class InjectInvocationHandler implements InvocationHandler {
        //拦截的方法名列表
        private Map<String, Method> map = new HashMap<>();
        //这里实际上是MainActivity
        private Object target;

        public InjectInvocationHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            if (target != null) {
                //获取名字
                String name = method.getName();
                Method m = map.get(name);
                if (null != m) {
                    //如果存在  就执行
                    Class<?> aClass = o.getClass();
                    Log.e("AA", "类的名字是：" + aClass.getName());
                    Log.e("AA", "方法的名字是：" + method.getName());
                    return m.invoke(target, objects);
                }

            }
            return null;

        }

        /**
         * 向列表里增加拦截的方法
         */
        public void add(String name, Method method) {
            map.put(name, method);
        }
    }

}

