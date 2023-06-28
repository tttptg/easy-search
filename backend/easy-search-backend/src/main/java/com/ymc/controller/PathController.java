package com.ymc.controller;

import com.ymc.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/path")
public class PathController {

    @Autowired
    private PathService pathService;

    

}
