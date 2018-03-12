package utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.yarn.api.records.ApplicationId;
import org.apache.hadoop.yarn.api.records.ApplicationReport;
import org.apache.hadoop.yarn.client.api.YarnClient;
import org.apache.hadoop.yarn.conf.YarnConfiguration;
import org.apache.hadoop.yarn.exceptions.YarnException;
import org.apache.hadoop.yarn.util.ConverterUtils;

import java.io.IOException;

public class YarnUtils {

	private static YarnClient client = null;

	public static void init(){
        client = YarnClient.createYarnClient();
        Configuration conf = new YarnConfiguration();
        conf.set("yarn.resourcemanager.address", "10.10.7.160:8032");
        client.init(conf);
        client.start();
	}

    public static ApplicationReport getApplicationReport(String appid){
        ApplicationId appId = ConverterUtils.toApplicationId(appid);
	    ApplicationReport appReport = null;
        try {
            appReport = client.getApplicationReport(appId);
        } catch (YarnException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return appReport;
    }

    public static String getApplicationStatus(String appid){
        ApplicationId appId = ConverterUtils.toApplicationId(appid);
        ApplicationReport appReport = null;
        String status = null;
        try {
            appReport = client.getApplicationReport(appId);
            status= appReport.getYarnApplicationState().toString();
        } catch (YarnException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return status;
    }

    public static void close() throws IOException {
        client.stop();
        client.close();
    }

    public static YarnClient getClient() {
        return client;
    }

    public static void setClient(YarnClient client) {
        YarnUtils.client = client;
    }
}




/*
        System.out.println(applicationReport.getApplicationId().toString());
        System.out.println(applicationReport.getCurrentApplicationAttemptId().toString());
        System.out.println(applicationReport.getUser());
        System.out.println(applicationReport.getQueue());
        System.out.println(applicationReport.getName());
        System.out.println(applicationReport.getHost());
        System.out.println(applicationReport.getRpcPort());
//            System.out.println(applicationReport.getClientToAMToken().toString());
        System.out.println(applicationReport.getYarnApplicationState().toString());
        System.out.println(applicationReport.getDiagnostics());
        System.out.println(applicationReport.getTrackingUrl());
        System.out.println(applicationReport.getStartTime());
        System.out.println(applicationReport.getFinishTime());
        System.out.println(applicationReport.getFinalApplicationStatus().toString());
        System.out.println(applicationReport.getApplicationResourceUsageReport().toString());
        System.out.println(applicationReport.getProgress());
        System.out.println(applicationReport.getApplicationType());
        System.out.println(applicationReport.getApplicationTags().toString());
//            System.out.println(applicationReport.getAMRMToken().toString());

    */