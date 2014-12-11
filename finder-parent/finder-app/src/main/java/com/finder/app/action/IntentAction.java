package com.finder.app.action;

import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 18/08/12
 * Time: 02:13
 */
public class IntentAction extends AbstractAction {

    private Context context;
    private Intent intent;

    public IntentAction(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    public IntentAction(Context context, Intent intent, int drawable) {
        super(drawable);
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void performAction(View view) {
        context.startActivity(intent);
    }

    public void putExtraString(String name, String value) {

        if (intent != null) {
            this.intent.putExtra(name, value);
        }
    }
}
