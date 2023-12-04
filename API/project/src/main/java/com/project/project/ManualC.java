package com.project.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller 
@RequestMapping
public class ManualC {
    @GetMapping
    public String index(){
        return "index";
    }
    
}
