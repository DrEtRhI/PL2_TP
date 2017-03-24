<%-- 
    Document   : detailResultat.jsp
    Created on : 24 mars 2016, 11:03:44
    Author     : Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer

    Ce code JSP permet de générer le HTML pour afficher le résultat détaillé d'un cavalier
--%>

<%@page import="jem.model.Resultat"%>
<%@page import="jem.model.GestionnaireEpreuve"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    ServletContext context = getServletContext();
    GestionnaireEpreuve gestionnaire = (GestionnaireEpreuve) context.getAttribute("gestionnaire");
    Resultat res = (Resultat) request.getAttribute("resultat");
%>
<h4><%=res.getCavalier()%></h4>
<table class="table table-bordered table-condensed">
    <thead>
    <th>juge</th><th>note</th>
</thead>
<%
    for (int noJuge = 1; noJuge <= res.getNbJuges(); noJuge++) {
%>
<tr>
    <td>Juge <%=noJuge%>: <%=gestionnaire.getJuge(noJuge)%></td>
    <td>
        <%=res.getNote(noJuge)%></td>
</tr>
<%
    }
%>
</table>
<p>note finale: <%=res.getNoteFinale()%></p>
<p>classement: <%=request.getParameter("rang")%></p>
