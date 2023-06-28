package com.ymc.controller;

import com.ymc.pojo.Path;
import com.ymc.service.PathService;
import com.ymc.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/path")
public class PathController {

    @Autowired
    private PathService pathService;

    @GetMapping("/subdirectory")
    public R subdirectory(String targetPath){
        System.out.println(targetPath);
        File directory = new File(targetPath);
        List<Path> paths = null;
        if (directory.isDirectory()) {
            File[] subDirectories = directory.listFiles(File::isDirectory);
            paths = Arrays.stream(subDirectories).map(item -> {
                boolean isDir = item.isDirectory();
                return new Path(item.getName(),isDir);
            }).collect(Collectors.toList());
        }
        return R.success(paths);
    }

}
