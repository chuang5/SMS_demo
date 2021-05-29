package com.jp;

import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import static spark.Spark.*;

public class App {
    
    public static void main(String[] args) {
        // create new conversation
        new CreateConverstation();
        

        get("/", (req, res) -> "Hello Web");

        post("/sms", (req, res) -> {
            System.out.println("Incoming message: " + req.queryParams("Body"));
            res.type("application/xml");
            Body body;
            switch(req.queryParams("Body")){
                case "1":
                    body = new Body
                            .Builder("Thank you to reply. Try 2.")
                            .build();
                    break;
                case "2":
                    body = new Body
                            .Builder("You are the best to test out my demo.")
                            .build();
                    break;
                default:
                    body = new Body
                            .Builder("Are you sure you read my message?")
                            .build();
            }
            
            Message sms = new Message
                    .Builder()
                    .body(body)
                    .build();
            MessagingResponse twiml = new MessagingResponse
                    .Builder()
                    .message(sms)
                    .build();
            return twiml.toXml();
        });
    }
}


