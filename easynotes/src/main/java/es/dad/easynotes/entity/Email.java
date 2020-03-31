package es.dad.easynotes.entity;

public class Email {

    public enum Topic {
        WELCOME,
        DOWNLOAD,
        ADD,
        NEW_ASIGN
    }

    private String username;
    private String userMail;
    private Topic topic;
    private String universidadCarreraAsignatura;

    public Email() {
        // Default constructor
    }

    public Email(String username, String userMail, Topic topic) {
        this.username = username;
        this.userMail = userMail;
        this.topic = topic;
        this.universidadCarreraAsignatura = "";
    }
    
    public Email(String username, String userMail, Topic topic, String UCA) {
        this.username = username;
        this.userMail = userMail;
        this.topic = topic;
        this.universidadCarreraAsignatura = UCA;
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
    
    public String getUniversidadCarreraAsignatura() {
        return universidadCarreraAsignatura;
    }
    
    

    @Override
    public String toString() {
        return "{email: {username:" + this.username + "}, "
                + "{userMail:" + this.userMail + "}"
                + "{topic:" + this.topic + "}}";
    }

}
