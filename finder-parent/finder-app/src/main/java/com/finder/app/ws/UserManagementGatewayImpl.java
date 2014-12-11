package com.finder.app.ws;

import com.finder.app.controler.ValidationError;
import com.finder.model.User;
import com.finder.model.ws.EAppResponse;
import org.apache.http.HttpStatus;
import org.muskacirca.finder.rs.client.ServiceResponse;
import org.muskacirca.finder.rs.client.UserResourceClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.ws.Service;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 04/01/13
 * Time: 17:22
 */
public class UserManagementGatewayImpl implements IUserManagementGateway {

    private static final Logger LOG = LoggerFactory.getLogger(UserManagementGatewayImpl.class);

    public static final String LOGIN_RESPONSE_FIELD = "login";
    public static final String EMAIL_RESPONSE_FIELD = "email";

    private UserResourceClient userResourceClient = new UserResourceClient();


    @Override
    public ApplicationResponse subscribe(User user) {

        ServiceResponse serviceResponse = userResourceClient.signIn(user);
        return buildApplicationResponse(serviceResponse);
    }

    @Override
    public ApplicationResponse authenticate(String login, String password) {

        ServiceResponse serviceResponse = userResourceClient.authenticate(login, password);
        return buildApplicationResponse(serviceResponse);

    }

    ApplicationResponse buildApplicationResponse(ServiceResponse serviceResponse) {

        ApplicationResponse response = new ApplicationResponse();

        switch (serviceResponse.getHttpCode()) {

            case HttpStatus.SC_OK :

                response.setResponse(EAppResponse.SUCCESS);
                break;

            case HttpStatus.SC_BAD_REQUEST:
            case HttpStatus.SC_FORBIDDEN:

                response.setResponse(EAppResponse.ERROR);
                response.setErrorMessage(serviceResponse.getAppMessage().getErrorMessage());
                break;

            case HttpStatus.SC_INTERNAL_SERVER_ERROR :

                response.setResponse(EAppResponse.TECHNICAL_ERROR);
                response.setErrorMessage(serviceResponse.getAppMessage().getErrorMessage());
                break;

            default:
                LOG.error("");
        }

        User userResponse = (User) serviceResponse.getParameters().get(USER_RESPONSE_FIELD);
        response.getParameters().put(LOGIN_RESPONSE_FIELD, userResponse.getLogin());
        response.getParameters().put(EMAIL_RESPONSE_FIELD, userResponse.getEmail());

        return response;
    }

}
