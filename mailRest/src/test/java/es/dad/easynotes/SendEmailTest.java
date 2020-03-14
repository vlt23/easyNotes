package es.dad.easynotes;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

public class SendEmailTest {

    private RestTemplate rest;

    @Before
    public void setup() {
        MailRestApplication.start();
        rest = new RestTemplate();
    }

    @After
    public void teardown() {
        MailRestApplication.stop();
    }

    @Test
    public void sendEmail() {
        Email email = new Email("dad", "easynotes.dad.2020@gmail.com", Email.Topic.WELCOME);
        rest.postForEntity("http://127.0.0.1:8025/email", email, String.class);
    }

}
