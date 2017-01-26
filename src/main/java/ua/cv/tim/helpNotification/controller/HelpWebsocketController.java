package ua.cv.tim.helpNotification.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import ua.cv.tim.helpNotification.model.InputObject;
import ua.cv.tim.helpNotification.model.OutputObject;

/**
 */
@RestController
public class HelpWebsocketController {
		
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public OutputObject greeting(InputObject input) throws Exception {
        Thread.sleep(1000); // simulated delay
        final OutputObject output = new OutputObject();
        output.setOutputField("[Pushed by Spring Framework] Nice, I received "+input.getInputField());
        return output;
    }	
}
