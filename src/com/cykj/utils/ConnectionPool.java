package com.cykj.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ConnectionPool {
	
	//静态对象
	private static ConnectionPool pool;
	//定义连接池
	private static List<Connection> conns = new ArrayList<Connection>();
	
	//构造函数私有化
	private ConnectionPool() {
		
	}
	
	//提供获取连接池对象的方法
	public static ConnectionPool getSingleInstance() {
		if(null == pool) {
			synchronized(ConnectionPool.class) {
				if(null == pool) {
					pool = new ConnectionPool();
				}
			}
		}
		return pool;
	}
	
	//预先执行static
	static {
		Properties properties = new Properties();
		try {
			//1.加载配置文件
			properties.load(new FileInputStream("config/config.txt"));
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			//MySQL配置时的用户名
			String user = properties.getProperty("user");
			//MySQL配置时的密码
			String password = properties.getProperty("pwd");
			//加载驱动
			Class.forName(driver);
			//获取连接
			for(int i = 0 ; i < 10; i++) {
				Connection conn = DriverManager.getConnection(url, user, password);
				conns.add(conn);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 从连接池当中，获取一个连接
	 * @return
	 */
	public Connection getConnection() {
		if(!conns.isEmpty()) {
			return conns.remove(0);
		}
		return null;
	}
	
	/**
	 * 归还连接
	 */
	public static void putConnection(Connection conn) {
		if(conns.size() < 10) {
			conns.add(conn);
		}
	}
}
