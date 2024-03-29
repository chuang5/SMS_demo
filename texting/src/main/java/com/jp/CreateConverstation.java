package com.jp;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class CreateConverstation {
    public static String ACCOUNT_SID;
    public static String AUTH_TOKEN;

    public CreateConverstation(){
        try {
            BufferedReader reader = Files.newBufferedReader(Paths.get("./texting/src/main/java/com/jp/user.json"));
            JsonObject parser = (JsonObject) Jsoner.deserialize(reader);
            ACCOUNT_SID = (String) parser.get("TWILIO_ACCOUNT_SID");
            AUTH_TOKEN = (String) parser.get("TWILIO_AUTH_TOKEN");
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                new PhoneNumber((String) parser.get("TO")), 
                new PhoneNumber((String) parser.get("FROM")),
                "Welcome to try this demo. Please response 1 or 2 to this SMS."
            ).create();
            System.out.println(message.getSid());
        } catch (IOException | JsonException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
