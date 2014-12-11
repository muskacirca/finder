package com.finder.da.model;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: muskacirca
 * Date: 12/08/12
 * Time: 23:57
 */
@Entity
@Table(name = "User")
public class UserDAO {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "mail")
    private String mail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String login) {
        this.name = login;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String email) {
        this.mail = email;
    }

    @Override
    public String toString() {
        return "UserDAO{" +
                "id=" + id +
                ", login='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + mail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDAO user = (UserDAO) o;

        if (id != user.id) return false;
        if (!mail.equals(user.mail)) return false;
        if (!name.equals(user.name)) return false;
        if (!password.equals(user.password)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + mail.hashCode();
        return result;
    }
}
