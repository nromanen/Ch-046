package ua.cv.tim.helpNotification.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;
import ua.cv.tim.helpNotification.model.InputObject;
import ua.cv.tim.helpNotification.model.OutputObject;

/**
 */
@RestController
public class HelpWebsocketController {
		
    @MessageMapping("/help/{id}")
    @SendTo("/topic/notification/{id}")
    public OutputObject greeting(@DestinationVariable String id, InputObject input) throws Exception {

        System.out.println("@DestinationVariable String id = " + id);
        final OutputObject output = new OutputObject();
        output.setOutputField("Help notification send!");
        return output;
    }	
}
