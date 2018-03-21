package utils;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.scheduler.*;

class JobListener implements StreamingListener {

    @Override
    public void onBatchCompleted(StreamingListenerBatchCompleted batchCompleted) {

        System.out.println("Batch completed, Total delay :" + batchCompleted.batchInfo().totalDelay().get().toString() +  " ms");

    }

    @Override
    public void onReceiverStarted(StreamingListenerReceiverStarted receiverStarted) {

    }

    @Override
    public void onReceiverError(StreamingListenerReceiverError receiverError) {

    }

    @Override
    public void onReceiverStopped(StreamingListenerReceiverStopped receiverStopped) {

    }

    @Override
    public void onBatchSubmitted(StreamingListenerBatchSubmitted batchSubmitted) {

    }

    @Override
    public void onBatchStarted(StreamingListenerBatchStarted batchStarted) {

    }


}