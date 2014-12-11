package com.finder.model.ws;

import com.finder.model.User;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 08/12/12
 * Time: 20:12
 */
@XmlRootElement
public class UserResponse {

    private EAppResponse wsResponse;
    private String errorCode;
    private String errorMessage;
    private User user;

    public EAppResponse getWsResponse() {
        return wsResponse;
    }

    public void setWsResponse(EAppResponse wsResponse) {
        this.wsResponse = wsResponse;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserResponse that = (UserResponse) o;

        if (errorCode != null ? !errorCode.equals(that.errorCode) : that.errorCode != null) return false;
        if (errorMessage != null ? !errorMessage.equals(that.errorMessage) : that.errorMessage != null) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (wsResponse != that.wsResponse) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = wsResponse.hashCode();
        result = 31 * result + (errorCode != null ? errorCode.hashCode() : 0);
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
