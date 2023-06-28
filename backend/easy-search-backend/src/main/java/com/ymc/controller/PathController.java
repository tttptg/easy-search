package com.ymc.controller;

import com.ymc.pojo.Path;
import com.ymc.service.PathService;
import com.ymc.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ResponseBody
    @CrossOrigin(origins = "http://localhost:3000")
    public R subdirectory(@RequestParam(value = "targetPath") String targetPath){
        try {
            System.out.println(targetPath);
            if (targetPath.equals("root")) {
                File[] roots = File.listRoots();
                List<Path> paths = Arrays.stream(roots).map(item -> {
                    return new Path(item.getAbsolutePath(), true);
                }).collect(Collectors.toList());
                return R.success(paths);
            }
            File directory = new File(targetPath);
            List<Path> paths = null;
            if (directory.isDirectory()) {
                File[] subDirectories = directory.listFiles(File::isDirectory);
                paths = Arrays.stream(subDirectories).map(item -> {
                    boolean isDir = item.isDirectory();
                    return new Path(item.getName(), isDir);
                }).collect(Collectors.toList());
            }
            return R.success(paths);
        }catch (Exception e){
            return R.badRequest(e.getMessage());
        }
    }

}
