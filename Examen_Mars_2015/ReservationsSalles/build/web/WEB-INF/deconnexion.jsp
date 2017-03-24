<%-- 
    Document   : deconnexion
    Created on : 26 mars 2015, 17:06:20
    Author     : genoud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Réservations UFR XXXX</title>
        <link rel="stylesheet" type="text/css" href="scripts/jquery-ui/jquery-ui.min.css">
        <link rel="stylesheet" type="text/css" href="css/reservations.css">
        <style>
            fieldset > p {
                margin-top:65px;
            }
        </style>
    </head>
    <body>
        <div id="wrapper">
            <div id="logoutPage">
                <fieldset>
                    <p>Au revoir ${param.prenom} ${param.nom}</p>
                    <h3>L'UFR XXXX vous remercie de votre visite</h3>
                </fieldset>
            </div>
                    
        </div>
                    <%
                        session.invalidate();
                    %>
    </body>
</html>
