package org.faqrobot.armynewspaper.viewclickbinder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 候帅
 *         Created by 候帅 on 2017/2/27. 11:56
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseEvent {

    String setListener();       //设置监听的方法名

    Class listenerType();      //设置监听的类型

    String listenerCallback();  //设置监听的回调方法
}
