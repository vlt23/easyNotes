package es.dad.easynotes;

public class Email {

    private String username;
    private String userMail;

    public Email(String username, String userMail) {
        this.username = username;
        this.userMail = userMail;
    }

    public String getUserMail() {
        return userMail;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "{email: {username:" + this.username + "}, "
                + "{userMail:" + this.userMail + "}}";
    }

}
