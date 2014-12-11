package com.finder.app.test.activity;

import com.google.inject.util.Modules;
import org.junit.runners.model.InitializationError;
import org.robolectric.Robolectric;
import roboguice.RoboGuice;
import roboguice.inject.RoboInjector;

/**
 * Created with IntelliJ IDEA.
 * User: vincent
 * Date: 26/01/13
 * Time: 14:24
 */
public class RobolectricActivityTestRunner/* extends RobolectricRoboTestRunner*/ {

    private RoboInjector injector;

    private ActivityTestModule activityTestModule = new ActivityTestModule();

    public RobolectricActivityTestRunner(Class<?> testClass) {/* throws InitializationError {
        super(testClass);*/
    }

/*    @Override*/
    public void prepareTest(Object test) {
/*        super.prepareTest(test);

        RoboGuice.injectMembers(Robolectric.application, RoboGuice.DEFAULT_STAGE,
                Modules.override(RoboGuice.newDefaultRoboModule(Robolectric.application)).with(activityTestModule));

        injector = RoboGuice.getInjector(Robolectric.application.getApplicationContext());
        injector.injectMembers(test);*/
    }
}
