package com.fh.test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@CrossOrigin
@RequestMapping("test/")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("testRibbon")
    public String testRibbon(){


       String forEntity = restTemplate.getForObject("http://USERSERVER/api/test/getTest", String.class);

        return forEntity;
    }


}
