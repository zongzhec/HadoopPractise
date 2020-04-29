package foo.zongzhe.map_reduce.phone_data;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PDReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
    private int sum = 0;
    private IntWritable total = new IntWritable();
    private FlowBean sumFlow = new FlowBean();

    @Override
    protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
        long sumUpFlow = 0;
        long sumDownFlow = 0;
        for (FlowBean value : values) {
            sumUpFlow += value.getUpFlow();
            sumDownFlow += value.getDownFlow();
        }
        sumFlow.setFlows(sumUpFlow, sumDownFlow);
        context.write(key, sumFlow);
    }
}
