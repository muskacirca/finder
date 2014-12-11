package com.finder.ws;

import com.finder.model.User;
import com.finder.model.ws.UserResponse;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 08/12/12
 * Time: 20:06
 */

@WebService(name = "UserManagement", targetNamespace = "http://usermanagement.finder.com")
@SOAPBinding(style = SOAPBinding.Style.RPC, use = SOAPBinding.Use.LITERAL)
public interface UserManagementService {

    @WebMethod(operationName = "subscribe")
    @WebResult(name = "userResponse")
    public UserResponse subscribe(

            @WebParam(name = "user")
            User user);

    @WebMethod(operationName = "authenticate")
    @WebResult(name = "userResponse")
    public UserResponse authenticate(

            @WebParam(name = "login")
            String login,
            @WebParam(name = "password")
            String password);
}
