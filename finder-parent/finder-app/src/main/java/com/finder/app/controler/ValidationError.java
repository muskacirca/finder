package com.finder.app.controler;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 04/01/13
 * Time: 16:59
 */
public enum ValidationError {

    SUCCESS("Success"),
    PASSWORD_IS_NOT_EXACT("Le mot de passe n'est pas exact"),
    EMAIL_NOT_VALID("L'email n'est pas valide"),
    CONNECTION_ERROR("une erreur est survenue lors de la connection au serveur");

    private String errorMessage;

    ValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
