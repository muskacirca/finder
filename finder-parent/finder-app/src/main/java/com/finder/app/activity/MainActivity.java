package com.finder.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.finder.app.R;
import com.finder.app.action.IntentAction;
import com.finder.app.controler.UserManagementController;
import com.finder.app.ws.ApplicationResponse;
import com.finder.app.ws.IUserManagementGateway;
import com.finder.app.ws.UserManagementGatewayImpl;
import com.finder.model.User;
import com.finder.model.ws.EAppMessage;
import com.finder.model.ws.EAppResponse;
import com.google.inject.Inject;
import roboguice.activity.RoboActivity;
import roboguice.inject.InjectView;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 15/12/12
 * Time: 22:52
 */
public class MainActivity extends RoboActivity {


    // home unregistered widget
    @InjectView(R.id.layout_home_welcome)
    RelativeLayout welcomeLayout;
    @InjectView(R.id.button_home_register)
    Button registerChoiceButton;
    @InjectView(R.id.button_home_connect)
    Button signUpChoiceButton;

    // Signup widget
    @InjectView(R.id.layout_home_signup)
    RelativeLayout signUpLayout;
    @InjectView(R.id.signup_field_login)
    EditText loginText;
    @InjectView(R.id.signup_field_password)
    EditText passwordText;
    @InjectView(R.id.signup_button_submit)
    Button signUpButton;

    // Register widget
    @InjectView(R.id.layout_home_register)
    RelativeLayout registerLayout;
    @InjectView(R.id.register_field_login)
    EditText inputTextLogin;
    @InjectView(R.id.register_field_email)
    EditText inputTextEmail;
    @InjectView(R.id.register_field_password)
    EditText inputTextPassword;
    @InjectView(R.id.register_field_confirmPassword)
    EditText inputTextConfirmPassword;
    @InjectView(R.id.register_button_submit)
    Button registerButton;

    @Inject
    private UserManagementController controller;

    @Inject
    private IUserManagementGateway userGateway;

    public MainActivity() {

    }

    public MainActivity(UserManagementController userManagementController, IUserManagementGateway userGateway) {
        this.controller = userManagementController;
        this.userGateway = userGateway;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_unregistered);

        this.registerChoiceButton.setOnClickListener(registerChoiceButtonListener);
        this.signUpChoiceButton.setOnClickListener(signUpChoiceButtonListener);

        this.signUpButton.setOnClickListener(signUpButtonListener);
        this.registerButton.setOnClickListener(registerButtonListener);

    }

    private View.OnClickListener signUpButtonListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            handleSignUp(view);
        }
    };

    private View.OnClickListener registerButtonListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            final Intent registeredHomeActivity = new Intent(view.getContext(), HomeActivity.class);

            String login = inputTextLogin.getText().toString();
            String email = inputTextEmail.getText().toString();
            String password = inputTextPassword.getText().toString();
            String confirmPassword = inputTextConfirmPassword.getText().toString();

            ApplicationResponse response = checkFieldsValidity(email, password, confirmPassword);
            if(response.getResponse() == EAppResponse.SUCCESS) {

                User user = new User();
                user.setEmail(email);
                user.setLogin(login);
                user.setPassword(password);

                IUserManagementGateway userGateway = new UserManagementGatewayImpl();
                response = userGateway.subscribe(user);

            }

            if (response.getResponse() == EAppResponse.SUCCESS) {

                registeredHomeActivity.putExtra(FormVariables.EXTRA_FORM_SIGNUP_EMAIL, email);
                registeredHomeActivity.putExtra(FormVariables.EXTRA_FORM_SIGNUP_LOGIN, login);

                startActivity(registeredHomeActivity);

            } else {

                Toast.makeText(view.getContext(), response.getErrorMessage(), Toast.LENGTH_LONG).show();
            }
        }
    };

    private ApplicationResponse checkFieldsValidity(String email, String password, String confirmPassword) {

        ApplicationResponse applicationResponse = new ApplicationResponse();
        applicationResponse.setResponse(EAppResponse.SUCCESS);

        if (!controller.isEmailValid(email)) {
            applicationResponse.setResponse(EAppResponse.ERROR);
            applicationResponse.setErrorMessage(EAppMessage.EMAIL_NOT_VALID.getErrorMessage());
        }

        if (!controller.isPasswordExact(password, confirmPassword)) {
            applicationResponse.setResponse(EAppResponse.ERROR);
            applicationResponse.setErrorMessage(EAppMessage.PASSWORD_VERIFICATION_FAILED.getErrorMessage());
        }

        return applicationResponse;
    }

    private View.OnClickListener registerChoiceButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            welcomeLayout.setVisibility(View.GONE);
            signUpLayout.setVisibility(View.GONE);
            registerLayout.setVisibility(View.VISIBLE);
        }
    };

    private View.OnClickListener signUpChoiceButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            welcomeLayout.setVisibility(View.GONE);
            registerLayout.setVisibility(View.GONE);
            signUpLayout.setVisibility(View.VISIBLE);
        }
    };

    private void handleSignUp(View view) {

        final Intent registeredHomeActivity = new Intent(view.getContext(), HomeActivity.class);
        final IntentAction homeAction = new IntentAction(view.getContext(), registeredHomeActivity);

        String login = loginText.getText().toString();
        String password = passwordText.getText().toString();

        ApplicationResponse response = userGateway.authenticate(login, password);

        if (response.getResponse() == EAppResponse.SUCCESS) {

            homeAction.putExtraString(FormVariables.EXTRA_FORM_SIGNUP_LOGIN, login);
            homeAction.performAction(view);

        } else {

            Toast.makeText(view.getContext(), response.getErrorMessage(), Toast.LENGTH_LONG);
        }

    }
}
