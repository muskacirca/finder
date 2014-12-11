package com.finder.model.ws;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 08/12/12
 * Time: 20:23
 */
public enum EAppResponse {

    SUCCESS,
    ERROR,
    TECHNICAL_ERROR;

    public static EAppResponse value(String wsResponse) {

        for (EAppResponse response : EAppResponse.values()) {

            if (wsResponse.equals(response.name())) {
                return response;
            }
        }
        return null;
    }
}
