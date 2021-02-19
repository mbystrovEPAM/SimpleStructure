package com.epam.enums;

public enum Users {

    VALID_USER("kloid.gherkin", "citizen100");

    private String login;
    private String passwd;

    Users(String login, String passwd) {
        this.login = login;
        this.passwd = passwd;
    }
}
