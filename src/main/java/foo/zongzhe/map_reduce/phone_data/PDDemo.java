package foo.zongzhe.map_reduce.phone_data;

import java.io.IOException;

/**
 * 根据phone_data.txt统计每个电话号码的上行流量和下行流量的总和。
 * 此Demo仅仅是为了遵循以往的入口风格，实际上跟Driver是一样的。
 */
public class PDDemo {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        PDDriver driver = new PDDriver();
        driver.PdDriver(args);

    }
}
