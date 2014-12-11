package com.finder.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import com.finder.app.R;
import com.finder.app.action.AbstractAction;
import com.finder.app.action.IntentAction;

import java.util.LinkedList;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 15/08/12
 * Time: 19:42
 */
public abstract class AbstractActivity extends Activity {

    private MenuInflater menuInflater;
    private Intent intent;
    private Button menuButton;

    private ActionBar actionBar;

    public void initActionBar() {

        actionBar = new ActionBar(this, null);
        actionBar.setBackgroundColor(R.color.background);
        actionBar.setTitle(R.string.app_name);
        actionBar.setHomeLogo(R.drawable.icon);

        final AbstractAction homeAction = new IntentAction(this, new Intent(this, HomeActivity.class), R.drawable.home_icon);
        final AbstractAction mapAction = new IntentAction(this, new Intent(this, FinderMapActivity.class), R.drawable.map_icon);
        final AbstractAction friendsAction = new IntentAction(this, new Intent(this, FriendsActivity.class), R.drawable.friends_icon);
        LinkedList<AbstractAction> menu = new LinkedList<AbstractAction>();
        menu.add(homeAction);
        menu.add(friendsAction);
        menu.add(mapAction);
        actionBar.addActions(menu);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        initActionBar();


    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {


        menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {


        switch (item.getItemId()) {

            case R.id.profile_menu:
                intent = new Intent(this, HomeActivity.class);
                break;
            case R.id.message_menu:
                finish();
                break;
            case R.id.friends_menu:
                intent = new Intent(this, FriendsActivity.class);
                break;
            case R.id.map_menu:
                intent = new Intent(this, FinderMapActivity.class);
                break;
        }

        startActivity(intent);
        return true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public MenuInflater getMenuInflater() {
        return menuInflater;
    }

    public void setMenuInflater(MenuInflater menuInflater) {
        this.menuInflater = menuInflater;
    }

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public Button getMenuButton() {
        return menuButton;
    }

    public void setMenuButton(Button menuButton) {
        this.menuButton = menuButton;
    }

    public void setActionBar(ActionBar actionBar) {
        this.actionBar = actionBar;
    }
}
