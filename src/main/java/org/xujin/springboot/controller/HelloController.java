package org.xujin.springboot.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujin
 * @package-name org.xujin.springboot.controller
 * @createtime 2020-02-18 14:08
 */
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello world";
    }
}
