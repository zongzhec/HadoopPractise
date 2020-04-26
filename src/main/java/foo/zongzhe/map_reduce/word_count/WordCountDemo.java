package foo.zongzhe.map_reduce.word_count;

import java.io.IOException;

/**
 * A demo uses mapreduce to count words in a txt file.
 *
 * @author Zongzhe
 * 此Demo类可以被WordCountDriver完美代替，只是为了遵循本项目下的惯例，固此处仍然用Demo做入口。
 */
public class WordCountDemo {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        WordCountDriver wd = new WordCountDriver();
        wd.wcDriver(args);
    }
}
