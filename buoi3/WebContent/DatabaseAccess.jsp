<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String driverName = "com.mysql.jdbc.Driver";
String connectionURL = "jdbc:mysql://localhost:3306/data?useUnicode=true&characterEncoding=UTF-8";
Connection conn;
Statement st;

//register drive sql jdbc
Class.forName(driverName);
// create connection;
conn = DriverManager.getConnection(connectionURL, "root", "");
// create statement for execute queries
st = conn.createStatement();
%>