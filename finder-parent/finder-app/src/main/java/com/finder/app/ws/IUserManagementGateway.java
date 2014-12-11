package com.finder.app.ws;

import com.finder.model.User;
import org.muskacirca.finder.rs.client.ServiceResponse;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 04/01/13
 * Time: 17:20
 */
public interface IUserManagementGateway {


    String NAMESPACE = "http://usermanagement.finder.com";
    String SOAP_ACTION = "UserManagementServiceImpl";
    String SUBSCRIBE_METHOD = "subscribe";
    String AUTHENTICATE_METHOD = "authenticate";

    String WS_RESPONSE_FIELD = "wsResponse";
    String ERROR_MESSAGE_FIELD = "errorMessage";
    String USER_RESPONSE_FIELD = "user";

    String LOGIN_PARAM_FIELD = "login";
    String PASSWORD_PARAM_FIELD = "password";

    ApplicationResponse subscribe(User user);

    ApplicationResponse authenticate(String login, String password);
}
