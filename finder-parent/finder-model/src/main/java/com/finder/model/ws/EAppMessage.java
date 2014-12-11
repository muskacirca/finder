package com.finder.model.ws;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 09/12/12
 * Time: 23:45
 */
public enum EAppMessage {


    SUCCESS("1000", "Success"),
    LOGIN_ALREADY_EXIST("1001", "Le nom d'utilisateur existe déjà"),
    EMAIL_ALREADY_EXIST("1002", "L'email existe déjà"),
    PASSWORD_VERIFICATION_FAILED("1003", "Les mots de passe sont diférents"),
    EMAIL_NOT_VALID("1004", "L'email n'est pas valide"),
    AUTHENTICATION_FAILED("2001", "Mauvais couple login/paswword"),
    EXCEPTION("9999", "Sorry a technical error occurred");

    private String errorCode;
    private String errorMessage;


    private EAppMessage(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @JsonCreator
    public static EAppMessage create(String code) {
        EAppMessage[] units = EAppMessage.values();
        for (EAppMessage unit : units) {
            if (unit.name().equals(code)) {
                return unit;
            }
        }
        return SUCCESS;
    }

    @JsonValue
    private String value() {
        return this.name();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
