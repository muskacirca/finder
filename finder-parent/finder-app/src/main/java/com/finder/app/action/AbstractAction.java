package com.finder.app.action;

import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 18/08/12
 * Time: 02:00
 */
public abstract class AbstractAction implements Action {

    private int drawable;

    public AbstractAction() {

    }

    public AbstractAction(int drawable) {
        this.drawable = drawable;
    }

    public abstract void performAction(View view);

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
