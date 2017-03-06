<%-- 
    Document   : listeDates
    Created on : 15 févr. 2017, 22:24:32
    Author     : Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
--%>

<%@page import="im2ag.m2pcci.demoweb.model.Spectacle"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Les spectacles</title>
    </head>
    <body>
        <h1>Les spectacles</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Nom</th>
                    <th>Date</th>
                    <th>Heure</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Spectacle> allDates = (List<Spectacle>) request.getAttribute("lesSpectacles");
                    for (Spectacle s : allDates) {
                          String dateJJMMAA = String.format("%02d-%02d-%4d",s.getJour(),s.getMois(),s.getAnnee());
                          String heureHHMM = String.format("%02d:%02d",s.getHeure(),s.getMinute());
                %>
                <tr>
                    <td><%=s.getNom()%></td>
                    <td><%=dateJJMMAA%></td>
                    <td><%=heureHHMM%></td>
                </tr>
                <% }%>
            </tbody>
        </table>
            <p><a href="index.html">Nouveau spectacle</a></p>
    </body>
</html>
