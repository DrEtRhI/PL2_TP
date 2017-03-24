<%-- 
    Document   : AchatReussi
    Created on : 25 févr. 2016, 09:03:36
    Author     : Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer

    Cette page JSP correspond à la page HTML retournée à l'utilisateur lorsque
    l'achat a été effectué avec succès.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Demo mail</title>
        <link href="css/demowebmail.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h2 class="messageOK">Merci de votre achat.</h2>
        <p>Votre ticket avec ${param.nbTickets} places<br>
            pour l'épreuve  <strong>${epreuve.nom}</strong><br>
            du ${epreuve.formatedDate}<br>
            vous a été envoyé par email à l'adresse <code>${param.email}</code>
    </p>
    <p>
        <a href="index.html">Acheter un autre ticket</a>.
    </p>
</body>
</html>
