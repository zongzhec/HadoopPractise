package foo.zongzhe.map_reduce.word_count;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * Driver 的套路化非常突出，因此也是最好写的一个类
 */
public class WordCountDriver {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        WordCountDriver wd = new WordCountDriver();
        wd.wcDriver(args);
    }

    public void wcDriver(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        // 获取一个MR job实例
        Job job = Job.getInstance(new Configuration());

        // 设置类路径
        job.setJarByClass(WordCountDriver.class);

        // 设置Mapper和Reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        // 设置Mapper和Reducer的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 设置输入输出数据
        FileInputFormat.setInputPaths(job, new Path(args[0])); //FileInputFormat导包不要导错了
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        // 提交job
        boolean success = job.waitForCompletion(true);
        System.exit(success ? 0 : 1);
    }
}
