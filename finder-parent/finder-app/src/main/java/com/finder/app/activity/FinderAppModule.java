package com.finder.app.activity;

import com.finder.app.ws.IUserManagementGateway;
import com.finder.app.ws.UserManagementGatewayImpl;
import com.google.inject.AbstractModule;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 10/01/13
 * Time: 00:42
 */
public class FinderAppModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(IUserManagementGateway.class).to(UserManagementGatewayImpl.class);
    }
}
