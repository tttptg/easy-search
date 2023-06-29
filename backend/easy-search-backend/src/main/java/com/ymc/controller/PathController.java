package com.ymc.controller;

import com.ymc.pojo.Path;
import com.ymc.service.PathService;
import com.ymc.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
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
                File[] subDirectories = directory.listFiles();
                paths = Arrays.stream(subDirectories).map(item -> {
                    boolean isDir = item.isDirectory();
                    return new Path(item.getName(), isDir);
                }).collect(Collectors.toList());
            }
            // 根据两个规则排序
            Collections.sort(paths, new Comparator<Path>() {
                @Override
                public int compare(Path p1, Path p2) {
                    // 按照 type 排序，type 为 true 的在前，false 的在后
                    if (p1.getType() && !p2.getType()) {
                        return -1;
                    } else if (!p1.getType() && p2.getType()) {
                        return 1;
                    }

                    // type 相同，按照 path 的字典序进行升序排序
                    return p1.getPath().compareTo(p2.getPath());
                }
            });
            // 输出排序后的结果
            for (Path path : paths) {
                System.out.println(path.getPath() + " " + path.getType());
            }
            return R.success(paths);
        }catch (Exception e){
            return R.badRequest(e.getMessage());
        }
    }

}
