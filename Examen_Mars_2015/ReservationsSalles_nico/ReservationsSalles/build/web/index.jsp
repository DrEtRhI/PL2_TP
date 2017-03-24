<%-- 
    Document   : index.jsp : page d'accueil de l'application de réservation des salles
    Created on : 25 mars 2015, 15:06:06
    Author     : genoud
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Réservations UFR IM2AG</title>
        <link rel="stylesheet" type="text/css" href="scripts/jquery-ui/jquery-ui.min.css">
        <link rel="stylesheet" type="text/css" href="css/reservations.css">
    </head>
    <body>
        <div id="wrapper">
            <div id='loginPage'>
                <fieldset>
                    <legend>UFR IM2AG : Réservation des Salles</legend>
                    <form id="identifForm" action="authentification" method='POST'>
                        <p>
                            <label>Votre identifiant:</label>
                            <input type="text" name="login" value="${param.login}" size="16" required/> </p>
                        <p><label>Votre code d'accès:</label>
                            <input type="password" name="password" value="" size="16" required/> </p>
                        <p><button type="submit">Connexion</button>
                        </p>
                    </form>
                    <div id="messageErreur">${messageErreur}</div>
                    <div id="exceptionBD">${exceptionBD}</div>
                </fieldset>
            </div>
        </div>
    </body>

</body>
</html>
