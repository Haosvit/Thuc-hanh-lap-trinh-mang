<%@page import="java.sql.SQLException"%>
<%@ include file="DatabaseAccess.jsp" %>
<%
String staffId = request.getParameter("staffid");
String queryStr = "DELETE FROM nhanvien WHERE maso='" + staffId.trim() + "'";

try{
	st.executeUpdate(queryStr);
}
catch (SQLException ex) {
	System.out.println(ex.getMessage());
}
request.getRequestDispatcher("xem_nhan_vien.jsp").include(request, response);
%>