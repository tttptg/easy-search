package com.ymc.controller;

import com.ymc.service.ESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/es")
public class ESController {

    @Autowired
    private ESService esService;

    

}
