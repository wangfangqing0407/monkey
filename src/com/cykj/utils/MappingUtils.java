package com.cykj.utils;

import com.cykj.bean.ObjectCopy;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by wangfq on 2020/11/10.
 */
public class MappingUtils {

    public static Element  fetchXml() {
        File file = new File("config/TestInfo.xml");
        SAXReader read = new SAXReader();
        try {
            Document doc = read.read(file);
            Element root = doc.getRootElement();
            return root;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 生成插入语句
     * @param bean
     * @return
     */
    public static String genInsertSql(String bean){
        try {
            Class clz = Class.forName(bean);
            String clzStr = clz.toString();
            //得到类名
            String beanName = clzStr.substring(clzStr.lastIndexOf(".")+1).toLowerCase();
            Field[] strs = clz.getDeclaredFields();
            StringBuffer sb = null;
            String fileNames = "";
            for(Field field : strs) {
                fileNames += "," + field.getName();
                if(null == sb) {
                    sb = new StringBuffer();
                    sb.append("?");
                } else {
                    sb.append(" ,?");
                }
            }
            return "insert into " + beanName + "(" + fileNames.substring(1) +") values (" + sb.toString() + ")";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
