package com.finder.app.action;

import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 18/08/12
 * Time: 02:12
 */
public interface Action {

    int getDrawable();

    void performAction(View view);
}
