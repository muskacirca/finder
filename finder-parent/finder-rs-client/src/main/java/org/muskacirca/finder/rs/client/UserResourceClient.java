package org.muskacirca.finder.rs.client;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.finder.model.User;
import com.finder.model.ws.EAppMessage;
import org.glassfish.jersey.client.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by muskacirca on 14/11/2014.
 */
public class UserResourceClient {

    private static final Logger LOG = LoggerFactory.getLogger(UserResourceClient.class);
    public static final String URL = "http://finder-rs.heroku.com/rest/user/";

    public ServiceResponse getUser(String id) {

        WebTarget webTarget = connectToService();

        Invocation.Builder invocationBuilder = webTarget
                .path("{id}")
                .resolveTemplate("{id}", id)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        return buildServiceResponse(invocationBuilder.get());
    }

    public ServiceResponse signIn(User user) {

        WebTarget webTarget = connectToService();

        Invocation.Builder invocationBuilder = webTarget
                .path("signin")
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);

        return buildServiceResponse(invocationBuilder.post(Entity.entity(user, MediaType.APPLICATION_JSON)));
    }

    public ServiceResponse authenticate(String name, String password) {

        WebTarget webTarget = connectToService();

        Map<String, Object> arguments = new HashMap<String, Object>();
        arguments.put("name", name);
        arguments.put("password", password);

        Invocation.Builder invocationBuilder = webTarget
                .path("{name}/{password}")
                .resolveTemplates(arguments)
                .request(MediaType.APPLICATION_XML)
                .accept(MediaType.APPLICATION_JSON);

        return buildServiceResponse(invocationBuilder.get());
    }

    private WebTarget connectToService() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.register(JacksonJsonProvider.class);

        Client client = ClientBuilder.newClient(clientConfig);

        return client.target(URL);
    }


    private ServiceResponse buildServiceResponse(Response response) {

        response.getEntity();

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setHttpCode(response.getStatus());
        serviceResponse.setAppMessage(response.readEntity(EAppMessage.class));

        return serviceResponse;
    }
}
