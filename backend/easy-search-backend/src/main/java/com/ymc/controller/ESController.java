package com.ymc.controller;

import com.ymc.reader.Reader;
import com.ymc.service.ESService;
import com.ymc.util.R;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

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

    @PostMapping("search")
    @CrossOrigin
    public String search(@RequestParam("keyword") String keyword){
        System.out.println(keyword);
        StringBuilder result = new StringBuilder();
        client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://192.168.48.128:9200")
        ));
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery("content", keyword));

        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("content");
        highlightBuilder.preTags("<strong>");
        highlightBuilder.postTags("</strong>");
        searchSourceBuilder.highlighter(highlightBuilder);

        SearchRequest searchRequest = new SearchRequest("test");
        searchRequest.source(searchSourceBuilder);


        try {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            Map<String, List<String>> resultMap = new HashMap<>();

            for (SearchHit hit : searchResponse.getHits().getHits()) {
                String filePath = hit.getSourceAsMap().get("path").toString();
                String lineContent = hit.getSourceAsMap().get("content").toString();
                int lineNumber = Integer.parseInt(hit.getSourceAsMap().get("line").toString());
                // 获取高亮结果
                Map<String, HighlightField> highlightFields = hit.getHighlightFields();
                if (!CollectionUtils.isEmpty(highlightFields)) {
                    // 根据字段名获取高亮结果
                    HighlightField highlightField = highlightFields.get("content");
                    if (highlightField != null) {
                        // 获取高亮值
                        // 覆盖非高亮结果
                        lineContent = highlightField.getFragments()[0].string();
                    }
                }
                if (!resultMap.containsKey(filePath)) {
                    resultMap.put(filePath, new ArrayList<>());
                }

                List<String> lines = resultMap.get(filePath);
                lines.add("Line " + lineNumber + "\t" + lineContent);
            }

            for (Map.Entry<String, List<String>> entry : resultMap.entrySet()) {
                String filePath = entry.getKey();
                List<String> lines = entry.getValue();

                result.append(filePath).append("<br>");
                result.append("-----------------------------------").append("<br>");
                for (String line : lines) {
                    result.append(line).append("<br>");
                }
                result.append("<br>");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
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
