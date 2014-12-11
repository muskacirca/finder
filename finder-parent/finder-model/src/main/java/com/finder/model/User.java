package com.finder.model;


/*
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;
*/

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 12/08/12
 * Time: 23:57
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class User /* for soap only implements KvmSerializable */ {

    @XmlTransient
    private int id;
    private String login;
    private String password;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!email.equals(user.email)) return false;
        if (!login.equals(user.login)) return false;
        if (!password.equals(user.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
/* For soap only
    @Override
    public Object getProperty(int argPos) {
        switch (argPos) {
            case 0:
                return id;
            case 1:
                return login;
            case 2:
                return password;
            case 3:
                return email;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 4;
    }

    @Override
    public void setProperty(int argPos, Object o) {

        switch (argPos) {
            case 0:
                this.id = Integer.parseInt(o.toString());
                break;
            case 1:
                this.login = o.toString();
                break;
            case 2:
                this.password = o.toString();
                break;
            case 3:
                this.email = o.toString();
            default:
                break;
        }
    }

    @Override
    public void getPropertyInfo(int argPos, Hashtable hashtable, PropertyInfo propertyInfo) {

        switch (argPos) {
            case 0:
                propertyInfo.type = PropertyInfo.INTEGER_CLASS;
                propertyInfo.name = "id";
                break;
            case 1:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "login";
                break;
            case 2:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "password";
                break;
            case 3:
                propertyInfo.type = PropertyInfo.STRING_CLASS;
                propertyInfo.name = "email";
                break;
            default:
                break;
        }
    }
    */
}
