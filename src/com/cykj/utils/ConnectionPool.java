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
	
	//��̬����
	private static ConnectionPool pool;
	//�������ӳ�
	private static List<Connection> conns = new ArrayList<Connection>();
	
	//���캯��˽�л�
	private ConnectionPool() {
		
	}
	
	//�ṩ��ȡ���ӳض���ķ���
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
	
	//Ԥ��ִ��static
	static {
		Properties properties = new Properties();
		try {
			//1.���������ļ�
			properties.load(new FileInputStream("config/config.txt"));
			String driver = properties.getProperty("driver");
			String url = properties.getProperty("url");
			//MySQL����ʱ���û���
			String user = properties.getProperty("user");
			//MySQL����ʱ������
			String password = properties.getProperty("pwd");
			//��������
			Class.forName(driver);
			//��ȡ����
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
	 * �����ӳص��У���ȡһ������
	 * @return
	 */
	public Connection getConnection() {
		if(!conns.isEmpty()) {
			return conns.remove(0);
		}
		return null;
	}
	
	/**
	 * �黹����
	 */
	public static void putConnection(Connection conn) {
		if(conns.size() < 10) {
			conns.add(conn);
		}
	}
}
