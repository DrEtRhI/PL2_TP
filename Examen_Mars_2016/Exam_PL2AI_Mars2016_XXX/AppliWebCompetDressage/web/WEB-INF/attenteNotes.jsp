<%-- 
    Document   : attenteNotes
    Created on : 31 août 2014, 20:03:26
    Author     : Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer

    Cette page JSP affiche un résultat incomplet. du code JavaScript permet 
    de vérifier toutes les 5 secondes si le résultat a été complété ou non en
    evoyant une requête au serveur.
--%>

<%@page import="jem.model.Resultat"%>
<%@page import="jem.model.GestionnaireEpreuve"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Dressage</title>

        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <link href="css/jeuxequestres.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script>
            window.setInterval(waiting, 5000);
            function waiting() {
                document.forms["myform"].submit();
            }
        </script>
    </head>
    <body>
        <div id="container">
            <h1 class='bg-primary'>Compétition dressage</h1>
            <div>
                <%@include file="/WEB-INF/jspf/jugeHeader.jspf" %>
                <%@include file="/WEB-INF/jspf/cavalierHeader.jspf" %>
            </div>

            <p><em>Attente des notes des autres juges</em></p>
            <p><img src="images/waiting.gif" width=80 height=40 align="middle"></p>
            <%-- formulaire qui invoque la servlet ProgressionServlet 
                ce formulaire est soumis automatiquemen toutes les 5 secondes
                grâce au code JavaScript défini en début de page
            --%>
            <form id="myform" action="afficherProgression" method="POST">
            </form>
        </div>
    </body>
</html>
