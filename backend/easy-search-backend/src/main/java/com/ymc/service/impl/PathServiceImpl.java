package com.ymc.service.impl;

import com.ymc.pojo.Path;
import com.ymc.service.PathService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.awt.*;
import java.util.stream.Collectors;

@Service
public class PathServiceImpl implements PathService {
    @Override
    public List<Path> subdirectory(String targetPath) {
        File directory = new File(targetPath);
        List<Path> paths = null;
        if (directory.isDirectory()) {
            File[] subDirectories = directory.listFiles(File::isDirectory);
            paths = Arrays.stream(subDirectories).map(item -> {
                boolean isDir = item.isDirectory();
                return new Path(item.getName(),isDir);
            }).collect(Collectors.toList());
        }
        return paths;
    }
}
