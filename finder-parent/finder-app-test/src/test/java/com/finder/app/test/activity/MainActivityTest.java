package com.finder.app.test.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.finder.app.activity.MainActivity;
import com.finder.app.controler.UserManagementController;
import com.finder.app.test.R;
import com.finder.app.ws.ApplicationResponse;
import com.finder.app.ws.IUserManagementGateway;
import com.finder.model.ws.EAppMessage;
import com.finder.model.ws.EAppResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;
import roboguice.RoboGuice;

import javax.inject.Inject;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.robolectric.Robolectric.shadowOf;


/**
 * Created with IntelliJ IDEA.
 * User: vincent
 * Date: 26/01/13
 * Time: 12:29
 */
@Ignore
public class MainActivityTest {

/*
    @Inject
    private UserManagementController userController;

    @Inject
    private IUserManagementGateway userGateway;

    private ActivityTestModule testModule = new ActivityTestModule();
    private MainActivity mainActivity;

    @Before
    public void setUp() {

        mainActivity = new MainActivity(userController, userGateway);
        mainActivity.onCreate(null);
    }

    @After
    public void tearDown() {
        reset(userGateway);
        RoboGuice.Util.reset();
    }

    @Test
    public void application_should_load() {
        assertEquals(View.VISIBLE, mainActivity.findViewById(R.id.layout_home_welcome).getVisibility());
        assertEquals(View.GONE, mainActivity.findViewById(R.id.layout_home_signup).getVisibility());
        assertEquals(View.GONE, mainActivity.findViewById(R.id.layout_home_register).getVisibility());
    }

    @Test
    public void register_form_should_show() {

        performClick(R.id.button_home_register);

        assertEquals(View.GONE, mainActivity.findViewById(R.id.layout_home_welcome).getVisibility());
        assertEquals(View.GONE, mainActivity.findViewById(R.id.layout_home_signup).getVisibility());
        assertEquals(View.VISIBLE, mainActivity.findViewById(R.id.layout_home_register).getVisibility());
    }

    @Test
    public void signup_form_should_show() {

        performClick(R.id.button_home_signup);

        assertEquals(View.GONE, mainActivity.findViewById(R.id.layout_home_welcome).getVisibility());
        assertEquals(View.GONE, mainActivity.findViewById(R.id.layout_home_register).getVisibility());
        assertEquals(View.VISIBLE, mainActivity.findViewById(R.id.layout_home_signup).getVisibility());
    }

    @Test
    public void user_should_signup_correctly() {

        ApplicationResponse response = new ApplicationResponse();
        response.setResponse(EAppResponse.SUCCESS);
        response.setErrorMessage("Success");

        when(userGateway.authenticate("login", "password")).thenReturn(response);

        performClick(R.id.button_home_signup);

        ((TextView) mainActivity.findViewById(R.id.signup_field_login)).setText("login");
        ((TextView) mainActivity.findViewById(R.id.signup_field_password)).setText("password");
        Button submitButton = (Button) mainActivity.findViewById(R.id.signup_button_submit);

        assertButtonClickLaunchesActivity(mainActivity, submitButton, "com.finder.app.activity.HomeActivity");
    }

    @Test
    public void user_should_signup_KO() {

        ApplicationResponse response = new ApplicationResponse();
        response.setResponse(EAppResponse.ERROR);
        response.setErrorMessage(EAppMessage.AUTHENTICATION_FAILED.getErrorMessage());

        when(userGateway.authenticate("login", "password")).thenReturn(response);

        performClick(R.id.button_home_signup);

        ((TextView) mainActivity.findViewById(R.id.signup_field_login)).setText("login");
        ((TextView) mainActivity.findViewById(R.id.signup_field_password)).setText("password");
        performClick(R.id.signup_button_submit);

        ShadowActivity shadowActivity = shadowOf(mainActivity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        assertNull(startedIntent);
    }

    public void assertButtonClickLaunchesActivity(Activity activity, Button btn, String targetActivityName) {
        btn.performClick();
        ShadowActivity shadowActivity = shadowOf(activity);
        Intent startedIntent = shadowActivity.getNextStartedActivity();
        ShadowIntent shadowIntent = shadowOf(startedIntent);
        assertThat(shadowIntent.getComponent().getClassName(), equalTo(targetActivityName));
    }
    private void performClick(int buttonId) {
        Button registerButton = (Button) mainActivity.findViewById(buttonId);
        registerButton.performClick();
    }
*/

}
