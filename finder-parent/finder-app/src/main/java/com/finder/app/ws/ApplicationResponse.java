package com.finder.app.ws;

import com.finder.model.ws.EAppResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 04/01/13
 * Time: 17:22
 */
public class ApplicationResponse {

    private EAppResponse wsResponse;
    private String errorMessage;
    private Map<String, String> parameters = new HashMap<String, String>();

    public EAppResponse getResponse() {
        return wsResponse;
    }

    public void setResponse(EAppResponse wsResponse) {
        this.wsResponse = wsResponse;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Map<String, String> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }
}
