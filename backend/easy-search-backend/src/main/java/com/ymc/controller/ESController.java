package com.ymc.controller;

import com.ymc.reader.Reader;
import com.ymc.service.ESService;
import com.ymc.util.R;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/es")
public class ESController {

    @Autowired
    private ESService esService;

    private RestHighLevelClient client;

    @GetMapping("/beginLoad")
    @CrossOrigin
    public R beginLoad(@RequestParam("targetPath") String targetPath){
        Reader reader = new Reader();
        client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.48.128:9200")
        ));
        List<File> files = getAllFiles(targetPath);
        for (File file : files) {
            List<String> lines = new ArrayList<>();
            if (isDocxFile(file)) {
                // 处理docx文件
                lines = reader.docReader(file.getAbsolutePath());
            } else if (isPdfFile(file)) {
                // 处理pdf文件
                lines = reader.pdfReader(file.getAbsolutePath());
            } else if (isTxtFile(file)) {
                // 处理txt文件
                lines = reader.txtReader(file.getAbsolutePath());
            } else {
                // 处理其他文件类型
                System.out.println("Found file: " + file.getAbsolutePath());
            }
            String path = file.getAbsolutePath();
            int i = 1;
            for(String line : lines){
                insert(path,line,i++);
            }
        }
        return R.success(null);
    }

    public void insert(String path,String content,int line){
        // 存储每行的数据到Elasticsearch
        IndexRequest indexRequest = new IndexRequest("test")
                .source("path", path,
                        "content", content,
                        "line", line);

        try {
            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void printAllLines(List<String> lines){
        int i = 1;
        for(String line : lines){
            System.out.println(i++ + "   " + line);
        }
    }

    public static List<File> getAllFiles(String path) {
        List<File> files = new ArrayList<>();
        File directory = new File(path);
        if (directory.exists() && directory.isDirectory()) {
            getAllFilesRecursive(directory, files);
        }
        return files;
    }

    private static void getAllFilesRecursive(File directory, List<File> files) {
        File[] fileList = directory.listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    files.add(file);
                } else if (file.isDirectory()) {
                    getAllFilesRecursive(file, files);
                }
            }
        }
    }

    private static boolean isDocxFile(File file) {
        String fileName = file.getName();
        return fileName.toLowerCase().endsWith(".docx");
    }

    private static boolean isPdfFile(File file) {
        String fileName = file.getName();
        return fileName.toLowerCase().endsWith(".pdf");
    }

    private static boolean isTxtFile(File file) {
        String fileName = file.getName();
        return fileName.toLowerCase().endsWith(".txt");
    }

}
