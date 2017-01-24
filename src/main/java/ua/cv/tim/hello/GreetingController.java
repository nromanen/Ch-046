package ua.cv.tim.hello;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class GreetingController {

    private List<HelloMessage> mess = new ArrayList<>();


    @MessageMapping({"/hello","/askhelp"})
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
            System.out.println("Testinggggg");
            Thread.sleep(1000); // simulated delay
            return new Greeting("Hello, " + message.getName() + "!");
    }

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public HelloMessage hello(HelloMessage message) throws Exception {
        System.out.println("Test");
        return new HelloMessage("Hello, " + message.getName() + "!");
    }

}
