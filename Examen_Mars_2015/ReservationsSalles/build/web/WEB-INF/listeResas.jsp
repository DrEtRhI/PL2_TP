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
        <th>N°Salle</th>
        <th>Type</th>
        <th>Date</th>
        <th>Matin</th>
        <th>Supprimer</th>
    </tr>
    <%
        List<Reservation> lesResas = (List<Reservation>) request.getAttribute("lesReservations");
        for (Reservation resas : lesResas) {
    %>
    <tr>
        <td><%=resas.getNoSalle()%></td>
        <td><%=resas.getTypeSalle()%></td>
        <td><%=resas.getDateAsString()%></td>
        <td><%=resas.getMomentAsString()%></td>
        <td>
            <button onclick="deleteResa(this.value);" value="<%=resas.getNoReservation()%>">
                <img src="./images/trash.png" alt="non disponible"/>
            </button> 
        </td>
    </tr>
    <%
        }
    %>
</table>

