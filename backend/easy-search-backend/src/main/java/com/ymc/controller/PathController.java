package com.ymc.controller;

import com.ymc.pojo.Path;
import com.ymc.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/path")
public class PathController {

    @Autowired
    private PathService pathService;

    @GetMapping("/subdirectory")
    public List<Path> subdirectory(){
        
        return null;
    }

}
