package no.java.moosehead.saga;

import java.util.HashMap;
import java.util.Map;

public interface EmailSender {
    public void send(EmailType type,String to,Map<String,String> values);

    public default void sendEmailConfirmation(String to,String token) {
        Map<String, String> values = new HashMap<>();
        values.put("token",token);
        send(EmailType.CONFIRM_EMAIL,to,values);
    }
}