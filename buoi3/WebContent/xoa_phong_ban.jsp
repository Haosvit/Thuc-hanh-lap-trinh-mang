<%@page import="java.sql.SQLException"%>
<%@ include file="DatabaseAccess.jsp" %>
<%
String majorId = request.getParameter("majorId");
String queryStr = "DELETE FROM phongban WHERE mapb='" + majorId.trim() + "'";

try{
	st.executeUpdate(queryStr);
}
catch (SQLException ex) {
	System.out.println(ex.getMessage());
}
request.getRequestDispatcher("xem_phong_ban.jsp").include(request, response);
%>