package foo.zongzhe.hdfs_client;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;

import java.io.IOException;
import java.net.URI;

public class HDFSClientDemo {

    public static void main(String[] args) {
        HDFSClientDemo demo = new HDFSClientDemo();
        try {
            demo.listFiles();
            demo.ls();
//            demo.put();
            demo.rename();
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
        fileSystem.close();
    }

    public void rename() throws IOException {
        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://hadoop102:9000"), new Configuration());
        fileSystem.rename(new Path("/wcoutput"), new Path("/wcoutput_rename"));
        fileSystem.close();
    }

    /**
     * 查看文件状态
     * 注意，此方法只查文件，不查文件夹
     */
    public void listFiles() throws IOException {
        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://hadoop102:9000"), new Configuration());
        System.out.println("Listing files");
        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(new Path("/"), true);
        while (files.hasNext()) {
            LocatedFileStatus file = files.next();
            System.out.println(file.getPath());
            BlockLocation[] blockLocations = file.getBlockLocations();
            System.out.println("块信息：");
            for (BlockLocation blockLocation : blockLocations) {
                String[] hosts = blockLocation.getHosts();
                System.out.print("块在 ");
                for (String host : hosts) {
                    System.out.print(host + " ");
                }
                System.out.println("里");
            }
        }
        fileSystem.close();
    }

    /**
     * 查看文件状态
     * 注意，此方法不只查文件，也查文件夹
     */
    public void ls() throws IOException {
        FileSystem fileSystem = FileSystem.get(URI.create("hdfs://hadoop102:9000"), new Configuration());
        System.out.println("Listing files and dirs");
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/"));
        for (FileStatus fileStatus : fileStatuses) {
            if (fileStatus.isFile()) {
                System.out.print("检测到一个文件: " + fileStatus.getPath());
            } else {
                System.out.println("检测到一个文件夹: " + fileStatus.getPath());
            }
        }
        fileSystem.close();
    }
}
