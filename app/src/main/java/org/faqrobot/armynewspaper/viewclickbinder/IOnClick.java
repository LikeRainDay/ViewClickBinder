package org.faqrobot.armynewspaper.viewclickbinder;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 候帅
 *         Created by 候帅 on 2017/2/27. 12:00
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@BaseEvent(setListener = "setOnClickListener", listenerType = View.OnClickListener.class, listenerCallback = "onClick")
public @interface IOnClick {
    int[] value();//因为一个方法可能与多个控件绑定
}
