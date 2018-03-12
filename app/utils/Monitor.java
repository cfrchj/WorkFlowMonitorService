package utils;

import java.sql.SQLException;

public class Monitor extends Thread{

    private String appid ;
    private String taskid;
    public Monitor(String taskid,String appid) {
        this.taskid= taskid;
        this.appid = appid;
    }
    @Override
    public void run() {
        super.run();
        YarnUtils.init();
        while(true){
            try {
                sleep(1000);
                String status = YarnUtils.getApplicationStatus(appid);

                if(status.equals("FAILED") || status.equals("KILLED")){
                    String errorid = IDUtils.getRandomId("error");
                    addError(errorid,taskid,status);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private void addError(String errorid,String taskid,String errortpye) throws SQLException {
        String sql = "INSERT INTO task (errorid,taskid,errortype) VALUES(?,?,?)";
        DBUtils.operatorMysql(sql, errorid,taskid,errortpye);
    }
}
