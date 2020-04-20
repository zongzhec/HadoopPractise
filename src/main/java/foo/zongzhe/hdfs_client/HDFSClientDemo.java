package foo.zongzhe.hdfs_client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class HDFSClientDemo {

    public static void main(String[] args) {
        HDFSClientDemo demo = new HDFSClientDemo();
        try {
            demo.put();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void put() throws IOException {
        // 获取HDFS的抽象封装对象
        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://hadoop102:9000"), new Configuration());

        // 用此对象操作文件系统
        fileSystem.copyFromLocalFile(new Path("E:\\input.txt"), new Path("/"));

        fileSystem.close(); // HDFS不支持并发写入，因此如果不关，别的进程写不进去
    }

    public void get() throws IOException {
        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://hadoop102:9000"), new Configuration());
        fileSystem.copyToLocalFile(new Path("/"), new Path("E:\\"));
        fileSystem.close(); // HDFS不支持并发写入，因此如果不关，别的进程写不进去
    }
}
