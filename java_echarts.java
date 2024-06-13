package com.lmy;
//alt+/ 手动提示

import java.sql.*;
import java.util.ArrayList;

public class java_echarts {

	public static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql://192.168.2.200:3306/lmy";
	public static final String USER="root";
	public static final String PASSWORD="root";
	public static Connection connection=null;
	public static Statement statement=null;
	public static ResultSet resultSet=null;

	
//	连接数据库
	public static void startConn(){
		try {
			//加载驱动
			Class.forName(JDBC_DRIVER);
			//连接数据库
			connection=DriverManager.getConnection(URL,USER,PASSWORD);
			System.out.println("连接数据库成功！");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("连接失败！");
			e.printStackTrace();
		}
	}
	
	//关闭数据库资源
	public static void closeConn(){
		try {
			if(resultSet!=null){
				resultSet.close();
				resultSet=null;
			}
			if(statement!=null){
				statement.close();
				statement=null;
			}
			if(connection!=null){
				connection.close();
				connection=null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
		



}
