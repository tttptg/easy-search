package com.ymc.reader;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public List<String> docReader(String filePath){
        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument doc = new XWPFDocument(fis)) {

            List<String> lines = new ArrayList<>();

            // 遍历每个段落
            for (XWPFParagraph paragraph : doc.getParagraphs()) {
                String text = paragraph.getText().trim();
                if (!text.isEmpty()) {
                    lines.add(text);
                }
            }

            return lines;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> txtReader(String filePath){
        try {
            // 读取文本文件的所有行
            List<String> lines = Files.readAllLines(Paths.get(filePath), Charset.defaultCharset());
            return lines;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> pdfReader(String filePath){
        try (PDDocument document = PDDocument.load(new File(filePath))) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            // 将文本按行分割为列表
            String[] linesArray = text.split("\\r?\\n");
            List<String> lines = new ArrayList<>();
            for (String line : linesArray) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }

            return lines;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
