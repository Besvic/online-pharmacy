<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>

<form action="controller" method="get">
    <%--<input type="text" name="num" value="1">--%>
    <input type="submit" name="command" value="go_sign_in" > to sign in </form>
</form>
<%--<hr/>
result = ${res}
<br/>--%>
<%--<jsp:forward page="pages/enter/sign_in.jsp"></jsp:forward>--%>
<a href="pages/test_page.jsp">
    <button type="button" value="to_sign" > to page</button>
</a>
</body>
</html>