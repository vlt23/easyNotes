package es.dad.easynotes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MailRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailRestApplication.class, args);
    }

    /* -------------------------------------------------------------
     * Begin methods for junit test
     */
    private static ConfigurableApplicationContext app;

    public static void start() {
        if (app == null) {
            app = SpringApplication.run(MailRestApplication.class);
        }
    }

    public static void stop() {
        if (app != null) {
            app.close();
        }
    }
    // End methods for junit test

}
