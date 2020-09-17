<%@ page import="java.sql.*"%>

<%
	Connection conn = null;
	String url ="jdbc:mysql://localhost:3306/evening";
	String user="root";
	String dbPassword="9616";
	
	Class.forName("org.mariadb.jdbc.Driver");
	conn = DriverManager.getConnection(url,user,dbPassword);
%>