package com.boi;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

    @RequestMapping({ "/books", "/authors", "/genres", "/login", "/logout", "/menu", "/publs", "", "/"})
    public String index() {
        return "forward:/index.html";
    }
}