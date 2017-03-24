<%-- 
    Document   : bienvenue
    Created on : 25 mars 2015, 16:03:24
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
            <div id="header">${utilisateur.prenom} ${utilisateur.nom} <a href="deconnexion">déconnexion</a></div>
            <div id="tabs">
                <ul>
                    <li><a href="mesResas">Mes Réservations</a></li>
                    <li><a href="#tabs-2">Nouvelles Réservations</a></li>
                    <li><a href="#tabs-3">Aide</a></li>
                </ul>
                <div id="tabs-2">
                    <p>Nouvelle Réservation</p>
                    <p>A faire plus tard</p>
                </div>
                <div id="tabs-3">
                    <p>Application de reservation en ligne des salle d'enseignement</p>
                    <ul>
                        <li>L'onglet <strong>Mes Réservations</strong> vous permet de consulter la liste de vos réservation et 
                            d'éventuellement supprimer celles dont la dates n'est pas encore passée.</li>
                        <li>L'onglet <strong>Nouvelles Réservations</strong> vous permet d'effectuer de nouvelles réservations.</li>
                    </ul>
                </div>
            </div>
        </div>
        <script src="scripts/jquery-2.1.3.min.js"></script>
        <script src="scripts/jquery-ui/jquery-ui.min.js"></script>
        <script src="scripts/reservations.js"></script>
    </body>
</html>
