package com.ymc.controller;

import com.ymc.service.ESService;
import com.ymc.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/es")
public class ESController {

    @Autowired
    private ESService esService;

    @PostMapping("/beginLoad")
    public R beginLoad(@PathVariable String targetPath){

        return R.success(null);
    }

}
