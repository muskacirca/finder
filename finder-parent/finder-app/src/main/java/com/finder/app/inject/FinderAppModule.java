package com.finder.app.inject;

import android.app.Application;
import com.finder.app.ws.IUserManagementGateway;
import com.finder.app.ws.UserManagementGatewayImpl;
import com.google.inject.AbstractModule;

/**
 * Created by muskacirca on 24/11/2014.
 */
public class FinderAppModule extends AbstractModule {

    private Application application;

    public FinderAppModule() {
    }

    public FinderAppModule(Application application) {
        this.application = application;
    }

    protected void bind() {

        bind(IUserManagementGateway.class).to(UserManagementGatewayImpl.class);
    }

    @Override
    protected void configure() {

        bind(IUserManagementGateway.class).to(UserManagementGatewayImpl.class);
    }
}