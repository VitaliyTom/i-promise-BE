package com.exadel.ipromise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
public class IpromiseApplication {

    public static void main(String[] args) {
        SpringApplication.run(IpromiseApplication.class, args);
    }

    @RequestMapping(value = "/")
    public ModelAndView root(ModelMap model) {
        return new ModelAndView("redirect:/hello", model);
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }

}
