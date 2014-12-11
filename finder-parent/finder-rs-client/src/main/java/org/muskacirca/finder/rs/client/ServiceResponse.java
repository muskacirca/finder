package org.muskacirca.finder.rs.client;

import com.finder.model.ws.EAppMessage;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by muskacirca on 15/11/2014.
 */
public class ServiceResponse {

    private int httpCode;
    private EAppMessage appMessage;

    public EAppMessage getAppMessage() {
        return appMessage;
    }

    public void setAppMessage(EAppMessage appMessage) {
        this.appMessage = appMessage;
    }

    private Map<String, Object> parameters;

    public int getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(int httpCode) {
        this.httpCode = httpCode;
    }



    public Map<String, Object> getParameters() {
        if(parameters == null) {
            parameters = new HashMap<String, Object>();
        }
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceResponse that = (ServiceResponse) o;

        if (httpCode != that.httpCode) return false;
        if (appMessage != that.appMessage) return false;
        if (parameters != null ? !parameters.equals(that.parameters) : that.parameters != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = httpCode;
        result = 31 * result + (appMessage != null ? appMessage.hashCode() : 0);
        result = 31 * result + (parameters != null ? parameters.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "httpCode=" + httpCode +
                ", appMessage=" + appMessage +
                ", parameters=" + parameters +
                '}';
    }
}
