package com.ly.demo.ocr.drivingLicense.utils;

import java.sql.*;
/**
 * @author ：LY
 * @date ：2020/10/20 15:07
 * @description ：JdbcUtil
 */


public class JdbcUtil {


    //通过上面的工具就可以获取到properties文件中的键值从而可以加载驱动 获取链接 从而 可以增删改查
    private static Connection conn = null;




    public static Connection getConn(){
        PropertiesUtil.loadFile("jdbc.properties");
        String driver = PropertiesUtil.getPropertyValue("driver");
        String url = PropertiesUtil.getPropertyValue("url");
        String username  = PropertiesUtil.getPropertyValue("username");
        String password = PropertiesUtil.getPropertyValue("password");


        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url,username,password);


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            close();
        }
        return conn;
    }


    public static void close(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    /**
     * 查询车牌号
     * @return
     */
    public static long getEnterTimeByNo(String no) {
        String sql = "select entertime from dl where no = " +"'" + no+"'";
        Connection conn = JdbcUtil.getConn();
        Statement stmt=null;
        ResultSet ret = null;
        long entertime = 0;
        try {
            stmt = conn.createStatement();
            ret = stmt.executeQuery(sql);
            while (ret.next()) {
                //这里只查询的密码
                entertime = ret.getLong(1);
            }
            ret.close();
            conn.close();//关闭连接
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return entertime;
    }

//    private static Date getExitTimeByNo(Date no) {
//        String sql = "select exittime from dl where no = " +"'" + no+"'";
//        Connection conn = JdbcUtil.getConn();
//        Statement stmt=null;
//        ResultSet ret = null;
//        Date exittime=null;
//        try {
//            stmt = conn.createStatement();
//            ret = stmt.executeQuery(sql);
//            while (ret.next()) {
//                //这里只查询的密码
//                exittime = ret.getDate(1);
//            }
//            ret.close();
//            conn.close();//关闭连接
//        } catch (SQLException e1) {
//            e1.printStackTrace();
//        }
//        return exittime;
//    }
    public static int insertCar(String no, long entertime) {
        String sql = "insert into dl value (?,?)";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps=null;
        int n = 0;
        try {
            // 创建会话
            ps = conn.prepareStatement(sql);
            ps.setString(1, no);
            ps.setLong(2, entertime);
            n = ps.executeUpdate();
            conn.close();//关闭连接
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return n;
    }

    public static int deleteCar(String no) {
        String sql = "delete from dl where no = ?";
        Connection conn = JdbcUtil.getConn();
        PreparedStatement ps=null;
        int n = 0;
        try {
            // 创建会话
            ps = conn.prepareStatement(sql);
            ps.setString(1, no);
            n = ps.executeUpdate();
            conn.close();//关闭连接
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return n;
    }
}
