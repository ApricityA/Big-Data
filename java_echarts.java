package com.lmy;
//alt+/ �ֶ���ʾ

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

	
//	�������ݿ�
	public static void startConn(){
		try {
			//��������
			Class.forName(JDBC_DRIVER);
			//�������ݿ�
			connection=DriverManager.getConnection(URL,USER,PASSWORD);
			System.out.println("�������ݿ�ɹ���");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("����ʧ�ܣ�");
			e.printStackTrace();
		}
	}
	
	//�ر����ݿ���Դ
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
