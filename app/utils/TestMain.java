package utils;
/*
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.scheduler.*;



public class TestMain {

    public static void main(String[] args) throws Exception{

        SparkConf sconf = new SparkConf();
        sconf.setAppName("TestApp");
        sconf.setMaster("local[4]");

        JavaStreamingContext jssc = new JavaStreamingContext(sconf, Durations.milliseconds(1000));

        JavaDStream<String> jd = jssc.textFileStream("/home/akhld/sigmoid");
        jd.print();
        jssc.addStreamingListener(new JobListener());
        jssc.start();
        jssc.awaitTermination();

    }

}

*/