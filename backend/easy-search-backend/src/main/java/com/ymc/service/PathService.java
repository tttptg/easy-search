package com.ymc.service;

import com.ymc.pojo.Path;
import org.springframework.stereotype.Service;
import java.util.List;
import java.awt.*;

public interface PathService {
    public List<Path> subdirectory(String targetPath);
}
