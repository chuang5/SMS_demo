package com.jp;





import com.google.gson.Gson;
import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import static spark.Spark.*;

public class App {
    
    public static void main(String[] args) {
        

        get("/", (req, res) -> "Hello Web");

        post("/sms", (req, res) -> {
            System.out.println(req.queryParams("Body"));
            res.type("application/xml");
            Body body = new Body
                    .Builder("The Robots are coming! Head for the hills!")
                    .build();
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


