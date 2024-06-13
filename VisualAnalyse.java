package com.lmy;
//alt+/ �ֶ���ʾ

import java.sql.*;
import java.util.ArrayList;

public class VisualAnalyse {

	public static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql://192.168.2.200:3306/dbecommerce";
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
	
	//˫11�������������Ϊ����(0���������  1�ӹ��ﳵ������  2��������  3��ע������)
	public static ArrayList<String[]> consumptionBehavior() throws Exception{
		ArrayList<String[]> list = new ArrayList<>();
		startConn();
		//��ȡstatement����
		statement=connection.createStatement();
		//ִ��SQL���
		resultSet=statement.executeQuery
				("select action,count(*) num from user_log group by action desc");
		//
		while(resultSet.next()){
			String[] temp={resultSet.getString("action"),resultSet.getString("num")};
			list.add(temp);
		}
		closeConn();
		return list;
	}
	
	/**
	 * ��Ů��ҽ��׶Ա�
	 * 
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<String[]> manAndWoman() throws Exception {
		ArrayList<String[]> list = new ArrayList<String[]>();
		startConn();
		statement = connection.createStatement();
		resultSet = statement.executeQuery("select gender,count(*) num from user_log group by gender desc");
		while (resultSet.next()) {
			String[] temp = { resultSet.getString("gender"), resultSet.getString("num") };
			list.add(temp);
		}
		closeConn();
		return list;
	}
	
	/**
	 * ��Ů��Ҹ�������ν��׶Ա�
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<String[]> ageComparison() throws SQLException {
		ArrayList<String[]> list = new ArrayList<String[]>();
		startConn();
		statement = connection.createStatement();
		resultSet = statement
				.executeQuery("select gender,age_range,count(*) num from user_log group by gender,age_range desc");
		while (resultSet.next()) {
			String[] temp = { resultSet.getString("gender"), resultSet.getString("age_range"),
					resultSet.getString("num") };
			list.add(temp);
		}
		closeConn();
		return list;
	}
	
	// ��ȡ����ǰ�����Ʒ���
		public static ArrayList<String[]> saleRank() throws SQLException {
			ArrayList<String[]> list = new ArrayList<String[]>();
			startConn();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(
					"select cat_id,count(*) num from user_log group by cat_id order by count(*) desc limit 5");
			while (resultSet.next()) {
				String[] temp = { resultSet.getString("cat_id"), resultSet.getString("num") };
				list.add(temp);
			}
			closeConn();
			return list;
		}



}
