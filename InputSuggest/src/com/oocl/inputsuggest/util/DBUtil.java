package com.oocl.inputsuggest.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Aquariuslt
 * @version 15-08-05
 */
public class DBUtil {

    public static final String DB_DRIVER="com.mysql.jdbc.Driver";
    public static final String DB_URL="jdbc:mysql://localhost:3306/input_suggest";
    public static final String DB_USER="root";
    public static final String DB_PWD="3363";

    public static Connection getConnection(){
        try{
            Class.forName(DB_DRIVER);
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


    public static void freeConnection(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
