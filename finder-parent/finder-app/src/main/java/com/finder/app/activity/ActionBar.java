package com.finder.app.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.*;
import com.finder.app.R;
import com.finder.app.action.AbstractAction;

import java.util.LinkedList;

public class ActionBar extends RelativeLayout implements View.OnClickListener {

    private LayoutInflater layoutInflater;
    private ImageView logoView;

    private TextView titleView;
    private ProgressBar progressBar;
    private LinearLayout actionIconContainer;
    private LinkedList<AbstractAction> actionList;

    public ActionBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        RelativeLayout barView = (RelativeLayout) layoutInflater.inflate(R.layout.actionbar, null);
        addView(barView);

        logoView = (ImageView) barView.findViewById(R.id.actionbar_home_logo);
        titleView = (TextView) barView.findViewById(R.id.actionbar_title);
        actionIconContainer = (LinearLayout) barView.findViewById(R.id.actionbar_actionIcons);
    }

    public void setHomeLogo(int resId) {
        setHomeLogo(resId, null);
    }

    public void setHomeLogo(int resId, OnClickListener onClickListener) {
        logoView.setImageResource(resId);
        logoView.setVisibility(View.VISIBLE);
        logoView.setOnClickListener(onClickListener);
    }

    public void setTitle(CharSequence title) {
        titleView.setText(title);
    }

    public void setTitle(int resid) {
        titleView.setText(resid);
    }

    /**
     * Remove the action icon from the given index (0 based)
     *
     * @param index
     * @return <code>true</code> if the item was removed
     */
    public boolean removeActionIconAt(int index) {
        int count = actionIconContainer.getChildCount();
        if (count > 0 && index >= 0 && index < count) {
            actionIconContainer.removeViewAt(index);
            return true;
        }
        return false;
    }

    /**
     * @return <code>true</code> if the progressbar is visible
     * @see View#VISIBLE
     */
    public boolean isProgressBarVisible() {
        return progressBar.getVisibility() == View.VISIBLE;
    }

    private void setProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void onClick(View view) {
        final Object tag = view.getTag();
        if (tag instanceof AbstractAction) {
            final AbstractAction action = (AbstractAction) tag;
            action.performAction(view);
        }
    }

    /**
     * Adds a list of {@link AbstractAction}s.
     *
     * @param actionList the actions to add
     */
    public void addActions(LinkedList<AbstractAction> actionList) {
        int size = actionList.size();
        for (int i = 0; i < size; i++) {
            addAction(actionList.get(i));
        }
    }

    /**
     * Adds a new {@link AbstractAction}.
     *
     * @param action the action to add
     */
    public void addAction(AbstractAction action) {
        final int index = actionIconContainer.getChildCount();
        addAction(action, index);
    }

    /**
     * Adds a new {@link AbstractAction} at the specified index.
     *
     * @param action the action to add
     * @param index  the position at which to add the action
     */
    public void addAction(AbstractAction action, int index) {
        actionIconContainer.addView(inflateAction(action), index);
    }

    /**
     * Removes all action views from this action bar
     */
    public void removeAllActions() {
        actionIconContainer.removeAllViews();
    }

    /**
     * Remove a action from the action bar.
     *
     * @param index position of action to remove
     */
    public void removeActionAt(int index) {
        actionIconContainer.removeViewAt(index);
    }

    /**
     * Remove a action from the action bar.
     *
     * @param action The action to remove
     */
    public void removeAction(AbstractAction action) {
        int childCount = actionIconContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = actionIconContainer.getChildAt(i);
            if (view != null) {
                final Object tag = view.getTag();
                if (tag instanceof AbstractAction && tag.equals(action)) {
                    actionIconContainer.removeView(view);
                }
            }
        }
    }

    /**
     * Returns the number of actions currently registered with the action bar.
     *
     * @return action count
     */
    public int getActionCount() {
        return actionIconContainer.getChildCount();
    }

    /**
     * Inflates a {@link View} with the given {@link AbstractAction}.
     *
     * @param action the action to inflate
     * @return a view
     */
    private View inflateAction(AbstractAction action) {
        View view = layoutInflater.inflate(R.layout.actionbar_icon, actionIconContainer, false);


        ImageButton labelView =
                (ImageButton) view.findViewById(R.id.actionbar_item);
        labelView.setImageResource(action.getDrawable());

        view.setTag(action);
        view.setOnClickListener(this);
        return view;
    }


}