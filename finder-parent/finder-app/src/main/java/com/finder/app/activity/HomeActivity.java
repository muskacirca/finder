package com.finder.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.finder.app.R;

public class HomeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_registered);

        Intent callingIntent = this.getIntent();

        String login = callingIntent.getStringExtra(FormVariables.EXTRA_FORM_SIGNUP_LOGIN);

        Toast.makeText(this, "Hey welcome " + login, Toast.LENGTH_LONG).show();


    }


}