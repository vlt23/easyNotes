package es.dad.easynotes.entity;

public class Email {

    public enum Topic {
        WELCOME,
        DOWNLOAD,
        ADD
    }

    private String username;
    private String userMail;
    private Topic topic;

    public Email() {
        // Default constructor
    }

    public Email(String username, String userMail, Topic topic) {
        this.username = username;
        this.userMail = userMail;
        this.topic = topic;
    }

    public String getUserMail() {
        return userMail;
    }

    public String getUsername() {
        return username;
    }

    public Topic getTopic() {
        return topic;
    }

    @Override
    public String toString() {
        return "{email: {username:" + this.username + "}, "
                + "{userMail:" + this.userMail + "}"
                + "{topic:" + this.topic + "}}";
    }

}
