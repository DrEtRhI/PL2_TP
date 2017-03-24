<%-- 
    Document   : finEpreuve
    Created on : 27 août 2015, 10:15:47
    Philippe Genoud - UGA Université Grenoble Alpes - Lab. LIG Steamer
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

        <!-- jquery -->
        <script src="//code.jquery.com/jquery-2.2.0.min.js"></script>
        <!-- Bootstrap -->
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <script src="bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- BootstrapDialog -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/css/bootstrap-dialog.min.css" rel="stylesheet" type="text/css" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.34.9/js/bootstrap-dialog.min.js"></script>
        <!-- detailResultat -->
        <script src="./scripts/detailResultat.js" type="text/javascript"></script>
        <link href="css/jeuxequestres.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <style>
            tr:hover {
                background-color: #bce8f1;
            }
        </style>
    </head>
    <body>
        <div id="container">
            <h1 class='bg-primary'>Compétition dressage</h1>
            <div>
                <%@include file="/WEB-INF/jspf/jugeHeader.jspf" %>
            </div>
            <h5>L'épreuve est terminée<br>Classement final</h5>
            <table class="table table-bordered table-condensed">
                <thead>
                <th>rang</th><th>cavalier</th><th>note</th>
                </thead>
                <%
                    ServletContext context = getServletContext();
                    GestionnaireEpreuve gestionnaire = (GestionnaireEpreuve) context.getAttribute("gestionnaire");
                    Resultat[] classement = gestionnaire.getClassement();
                    int rang = 1;
                    for (Resultat res : classement) {
                %>
                <tr>
                    <td><%=rang%></td>
                    <td><%=res.getCavalier().getPrenom()%> <%=res.getCavalier().getNom()%> (<%=res.getCavalier().getPays()%>)<br>
                        sur <%=res.getCavalier().getMonture()%></td>
                    <td><%=String.format("%5.2f", res.getNoteFinale())%></td>
                </tr>
                <%
                        rang = rang + 1;
                    }
                %>
            </table>

        </div>
    </body>
</html>
