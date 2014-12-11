package com.finder.app.test.activity;

import com.finder.app.ws.IUserManagementGateway;
import com.google.inject.AbstractModule;

import static org.mockito.Mockito.mock;

/**
 * Created with IntelliJ IDEA.
 * User: vincent
 * Date: 26/01/13
 * Time: 14:22
 */
public class ActivityTestModule extends AbstractModule {

    IUserManagementGateway userGateway = mock(IUserManagementGateway.class);

    @Override
    protected void configure() {

        bind(IUserManagementGateway.class).toInstance(userGateway);
    }
}
