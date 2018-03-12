package utils;

import java.util.Random;

public class IDUtils {

    public static String getRandomId(String table){
        String randomId = RandomID();
        String sql = "select "+table+"id from "+table+" where "+table+"id="+randomId;
        if(DBUtils.isDuplicate(sql)){
            return getRandomId(table);
        }else
            return randomId;
    }
    private static String RandomID(){
        Random rand=new Random();//生成随机数
        String Nnumer="";
        for(int a=0;a<6;a++){
            Nnumer+=rand.nextInt(10);//生成6位数字
        }
        return Nnumer;
    }
}
