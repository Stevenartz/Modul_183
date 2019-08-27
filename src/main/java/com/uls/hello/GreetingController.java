package com.uls.hello;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping("/greeting")
    public List<Greeting> greeting() {
        return Arrays.asList(new Greeting("Test"), new Greeting("ahja"));
    }
}