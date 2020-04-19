package foo.zongzhe.hdfs_client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;

public class HDFSClientDemo {

    public static void main(String[] args) {

    }

    public void put() throws IOException {
        FileSystem fileSystem = FileSystem.get(new Configuration());
        fileSystem.copyFromLocalFile(new Path("Z:\\personmalWorkspace\\gitWorkSpace\\HadoopPractise\\src\\main\\resources\\input\\input.txt"));
    }
}
