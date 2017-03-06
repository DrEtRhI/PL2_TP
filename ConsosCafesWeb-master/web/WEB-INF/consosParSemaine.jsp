<%-- 
    Document   : consosParSemaine - page jSP se chargeant de l'affichage
                 de la liste des consommations sous la forme d'un tableau HTML.

    Created on : 13 févr. 2017, 11:25:44
    Author     : Philippe GENOUD - Université Grenoble Alpes - Lab LIG-Steamer
--%>
<%@page import="im2ag.m2pcci.webcafes.model.ListeConsos"%>
<%@page import="im2ag.m2pcci.webcafes.model.Programmeur"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="styles/cafe.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id='wrapper'>
            <img class="icon" src="images/coffee.png" width=100 height=100 alt="logo avec une tasse de café"/>
            <h1>Consommations de café pour<br>la semaine : ${listeConsos.noSem}</h1>
            <table id='tabConsos'>
                <thead>
                <th>PROGRAMMEUR</th><th>NBRE TASSES</th>
                </thead>
                <tbody>
                    <%
                        int nbTotalTasses = 0;
                        ListeConsos listeConsos = (ListeConsos) request.getAttribute("listeConsos");
                        for (Programmeur p : listeConsos) {
                            out.println("<tr>");
                            out.println("<td>" + p.getPrenom() + " " + p.getNom() + "</td>");
                            out.println("<td> " + p.getNbTasses() + "</td> ");
                            nbTotalTasses += p.getNbTasses();
                            out.println("</tr>");
                        }
                    %>  
                </tbody>
                <tfoot>
                    <tr><td>nbre total de tasses </td><td><%=nbTotalTasses%></td></tr>
                </tfoot>
            </table>
            <a href="index.html">chosir une autre semaine</a>
    </body>
</html>
