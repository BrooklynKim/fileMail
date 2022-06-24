package com.spring.fileMail;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLConnectionTest {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/fileMail";
	private static final String USER = "root";
	private static final String PW = "root";
	
	
	public void testConnection() throws Exception{
		
		Class.forName(DRIVER);
		
		try(Connection con = DriverManager.getConnection(URL, USER, PW)){
			System.out.println(con);
		}catch(Exception e) {
			e.printStackTrace();
		}//try_
	}//testConnection_
}//MySQLConnectionTest_
