package com.finder.app.controler;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 03/01/13
 * Time: 23:17
 */
public class UserManagementController {

    private static final String[] unexpectedCharacter = {"..", ".@"};

    public boolean isPasswordExact(String password, String confirmPassword) {

        return password.equals(confirmPassword);
    }

    public boolean isEmailValid(String email) {

        if (!email.contains("@")) return false;
        if (!isDomainPartValid(email)) return false;
        if (email.contains("..")) return false;
        if (email.contains(".@")) return false;

        return true;
    }

    private boolean isDomainPartValid(String email) {

        // Only one @ by email
        if (email.lastIndexOf("@") != email.indexOf("@")) return false;

        String domainPart = email.substring(email.lastIndexOf("@"));

        if (!domainPart.contains(".")) return false;
        if (isLastCharacterValid(domainPart, "@")) return false;
        if (isLastCharacterValid(domainPart, ".")) return false;

        return true;
    }

    private boolean isLastCharacterValid(String email, String character) {
        return email.substring(email.length() - 1).equals(character);
    }
}
