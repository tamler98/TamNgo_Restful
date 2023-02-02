package com.spring.bookstore.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GreetingController {
    @RequestMapping("/hello")
    public String greeting(@RequestParam(name="name") String name,
                            @RequestParam(name="country", required = false, defaultValue = "VietNam") String country) {
        return "Hello " + name + " from " +country;
    }

    @RequestMapping("/hello2")
    public Object greeting2(@RequestParam(name="name") String name,
                                  @RequestParam(name="country") String country) {
        Map<String, String> result = new HashMap<>();
        result.put("name", name);
        result.put("country", country);
        return result;
    }
    @RequestMapping("/hello3/{name}/{country}")
    public Object greeting3(@PathVariable(name="name") String name,
                            @PathVariable(name="country") String country) {
        Map<String, String> result = new HashMap<>();
        result.put("name", name);
        result.put("country", country);
        return result;
    }
    @RequestMapping("/hello4")
    public List<String> greeting4(@RequestParam(name="name") String name,
                                  @RequestParam(name="country") String country) {
        List<String> list = new ArrayList<>();
        list.add(name);
        list.add(country);
        return list;
    }
}
