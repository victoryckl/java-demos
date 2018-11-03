<%@page import="java.util.Date"%>
<hr>
<p style="text-align:center">copyright@<%
	String year = request.getParameter("year");
	if (year != null) {
		out.print(year);
	} else {
		out.print("2018");
	}
    out.print(" "+new Date().toLocaleString());
%></p>