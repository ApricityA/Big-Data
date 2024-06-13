package com.lmy;
//alt+/ 手动提示

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
	
	//双11所有买家消费行为比列(0点击：人数  1加购物车：人数  2购买：人数  3关注：人数)
	public static ArrayList<String[]> consumptionBehavior() throws Exception{
		ArrayList<String[]> list = new ArrayList<>();
		startConn();
		//获取statement对象
		statement=connection.createStatement();
		//执行SQL语句
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
	 * 男女买家交易对比
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
	 * 男女买家各个年龄段交易对比
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
	
	// 获取销量前五的商品类别
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
