package utils;

import java.sql.*;

public class DBUtils {
    // 向数据库插入数据
    public static int operatorMysql(String sql, Object... args) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i + 1, args[i]);
            }
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement("SELECT LAST_INSERT_ID();");
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();

        }
        DBUtils.release(null, preparedStatement, null, connection);
        return -1;
    }

    public static boolean isDuplicate(String sql){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection();
            statement= connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet == null){
                return false;
            }
            while (resultSet.next()){
                DBUtils.release(resultSet, null, statement, connection);
                return true;
            }
            DBUtils.release(resultSet, null, statement, connection);
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }


    // 连接数据库
    public static Connection getConnection() throws SQLException,
            ClassNotFoundException {
        // 得到配置信息
        String driverClass = "com.mysql.jdbc.Driver";
        String user = "root";
        String password = "jingpengkun";
        String url = "jdbc:mysql://39.106.96.46:3306/TaskMonitor";
        Class.forName(driverClass);
        // 返回一个connection连接
        return DriverManager.getConnection(url, user, password);
    }

    // 关闭资源
    public static void release(ResultSet resultSet,
            PreparedStatement preparedStatement, Statement statement,
            Connection connection) throws SQLException {

        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (connection != null) {
            connection.close();
        }

    }
}