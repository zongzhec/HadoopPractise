package foo.zongzhe.map_reduce.phone_data;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

import static foo.zongzhe.utils.Constants.TAB;

public class PDMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

    private Text phoneNum = new Text();
    private FlowBean flow = new FlowBean();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String phoneInfo[] = line.split(TAB);
        phoneNum.set(phoneInfo[1]);
        long upFlow = Long.parseLong(phoneInfo[4]);
        long downFlow = Long.parseLong(phoneInfo[5]);
        flow.setFlows(upFlow, downFlow);
        context.write(phoneNum, flow);
    }
}
