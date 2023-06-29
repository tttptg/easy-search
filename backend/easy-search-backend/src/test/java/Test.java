import com.ymc.pojo.Path;
import com.ymc.reader.Reader;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Test {
    @org.junit.jupiter.api.Test
    public void test(){
        String targetPath = "D://coding";
        System.out.println(targetPath);
        File directory = new File(targetPath);
        List<Path> paths = null;
        if (directory.isDirectory()) {
            File[] subDirectories = directory.listFiles(File::isDirectory);
            paths = Arrays.stream(subDirectories).map(item -> {
                boolean isDir = item.isDirectory();
                System.out.println(item.getName());
                return new Path(item.getName(),isDir);
            }).collect(Collectors.toList());
        }
        System.out.println(paths);
    }

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

    @org.junit.jupiter.api.Test
    public void test1(){
        Reader reader = new Reader();
        List<String> lines = reader.txtReader("D:/test/111.txt");
        for(String line : lines){
            System.out.println(line);
        }
    }
}
