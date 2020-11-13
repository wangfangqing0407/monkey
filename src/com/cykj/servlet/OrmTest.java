package com.cykj.servlet;

import com.cykj.bean.ObjectCopy;
import com.cykj.bean.OrmTestBean;
import com.cykj.bean.TargetBean;
import com.cykj.bean.TestTable;
import com.cykj.utils.ConnectionPool;
import com.cykj.utils.MappingUtils;
import org.dom4j.Element;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

/**
 * Created by wangfq on 2020/11/10.
 */
public class OrmTest {

    public static void main(String[] args) {
        /*ConnectionPool pool = ConnectionPool.getSingleInstance();
        Connection conn = pool.getConnection();
        try {
            if(!conn.isClosed()) {
                System.out.printf("-=====");
            }
            Statement statement = conn.createStatement();
            //要执行的SQL语句
            String sql = "select * from nideshop_ad_position";
            //3.ResultSet类，用来存放获取的结果集！！
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()) {
                System.out.println(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        TestTable tt = new TestTable();
        tt.setName("peter");
        tt.setPwd("123456789");


        Element root = MappingUtils.fetchXml();
        Element foo ;
        for (Iterator i = root.elementIterator("table"); i.hasNext();) {
            foo = (org.dom4j.Element) i.next();
            String sql = MappingUtils.genInsertSql(foo.attributeValue("name"));



            for(Iterator j = foo.elementIterator("property"); j.hasNext();) {
                Element table = (org.dom4j.Element) j.next();
                String property = table.attributeValue("name");
                String column = table.attributeValue("column");
                System.err.println("tableName: "+ property);
                System.err.println("column: "+ column);
            }
        }
    }
}
