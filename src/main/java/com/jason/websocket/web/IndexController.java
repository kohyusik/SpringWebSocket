package com.jason.websocket.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
 
/*    @RequestMapping(value = "/")
    public String index() {
        return "index.html";
    }*/
    @RequestMapping(value = "/")
    public String index() {
        return "index_ws.html";
    }
}