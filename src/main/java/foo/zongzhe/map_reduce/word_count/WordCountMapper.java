package foo.zongzhe.map_reduce.word_count;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

import static foo.zongzhe.utils.Constants.SPACE;

/**
 * Mapper阶段编程规范：
 * 1. 用户自定义的Mapper要继承自己的父类
 * 2. MApper的输入数据是KV对的形式（KV的类型可以自己定义）
 * 3. Mapper中的业务逻辑写在map()方法里
 * 4. Mapper的输出数据是KV对的形式（KV的类型可以自己定义）
 * 5. map()方法（MapTask进程）对每一个<K, V>调用一次
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    /*
     * LongWritable 是input数据在文件中的偏移量
     * Text（Input）是读进来的字符串
     * Text（Output）是WordCount输出的Key
     * IntWritable 是统计的个数
     * */

    private Text word = new Text();
    private IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        // 把一行数据转换成String
        String line = value.toString();
        String[] words = line.split(SPACE);
        // 遍历数组，把单次编程(word, 1)的形式，再返还给框架
        for (String word : words) {
            this.word.set(word);
            context.write(this.word, one);
            /*
            * 注意：上一行可以写成：context.write(new Text(word), new IntWritable(1));
            * 但是没读进来一行就会new两个对象，最终会导致程序变慢甚至崩溃
            * */
//            context.write(new Text(word), new IntWritable(1));
        }
    }
}
