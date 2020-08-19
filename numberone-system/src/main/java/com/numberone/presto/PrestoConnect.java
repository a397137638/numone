package com.numberone.presto;

import java.sql.*;

public class PrestoConnect {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //使用facebook驱动
        String url = "jdbc:presto://47.100.20.182:8082";
        try {
            Connection connection = DriverManager.getConnection(url, "root", null);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from mysql.test.user as a left join hive.dept.depts as b on a.deptId=b.deptId");
            while (rs.next()) {
                System.out.println(rs.getString("id")+","+rs.getString("userName")+","+rs.getString("deptName"));
            }
            rs.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
