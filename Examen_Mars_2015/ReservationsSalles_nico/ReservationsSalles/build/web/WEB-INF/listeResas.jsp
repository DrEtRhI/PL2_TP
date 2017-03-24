<%-- 
    Document   : listeResas.jsp
    Created on : 26 mars 2015, 11:21:25
    Author     : genoud
--%>

<%@page import="java.util.List"%>
<%@page import="m2pcci.reservationsSalles.model.Reservation"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<table id="tabResas">
    <tr>
        <th>N° Salle</th>
        <th>Type</th>
        <th>Date</th>
        <th>Matin</th>
        <th>Supprimer</th>
    </tr>
    <%
    List<Reservation> list = (List<Reservation>) request.getAttribute("resas");
    for (Reservation resa : list) {
        %>
        <tr>
        <td><%=resa.getNoSalle()%></td>
        <td><%=resa.getTypeSalle()%></td>
        <td><%=resa.getDateAsString()%></td>
        <td><%=resa.getMomentAsString()%></td>
        <td>
            <button onclick="deleteResa(this.value);" value="<%=resa.getNoReservation()%>">
            <img src="images/trash.png" alt="supprimer"/>
            </button>
        </td>
        </tr>
        <%
    }
    %>

</table>

