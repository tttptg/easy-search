import com.ymc.pojo.Path;

import java.io.File;
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
}
