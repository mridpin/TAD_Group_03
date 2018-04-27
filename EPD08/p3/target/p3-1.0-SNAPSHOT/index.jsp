<%-- 
    Document   : index
    Created on : Apr 27, 2018, 10:09:58 AM
    Author     : ridao
--%>

<%@page import="com.mongodb.BasicDBObject"%>
<%@page import="com.mongodb.DBCursor"%>
<%@page import="upo.tad.p3.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
    DAO dao = new DAO();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EPD08 - GRUPO03</title>
    </head>
    <body>
        <h1>EPD08 - GRUPO03</h1>

        <section>
            <h2>Equipos</h2>
            <%
                for (String s : dao.findTeams()) {
                    out.write(s);
                    out.write("<br />");
                }
            %>
        </section>

        <section>
            <h2>Jugadores</h2>
            <%
                for (String s : dao.findPlayers()) {
                    out.write(s);
                    out.write("<br />");
                }
            %>
        </section>
    </body>
</html>
