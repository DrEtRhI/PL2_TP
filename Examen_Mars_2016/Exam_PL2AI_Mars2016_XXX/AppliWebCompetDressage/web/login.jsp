<%-- 
    Document   : login
    Created on : 27 août 2015, 10:02:22
    Author     : genoud
--%>

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
    </head>
    <body>
        <div class="container">
            <h1 class='bg-primary'>Compétition dressage</h1>
            <form class="form-signin" action="login" method="POST">
                <h2 class="form-signin-heading">Connectez vous SVP</h2>
                <label for="noJugeId" class="control-label">Numéro de juge :</label>
                <select id="noJugeId" class="form-control" placeholder="no juge" name="noJuge">
                    <option>No de Juge</option>
                    <%
                        ServletContext sc = request.getServletContext();
                        GestionnaireEpreuve ge = (GestionnaireEpreuve) sc.getAttribute("gestionnaire");
                        for (int i =1 ; i <= ge.getNbJuges(); i++) {
                            if ( ! ge.getJuge(i).isConnecte()) {
                                // on affiche que les numéro de juges qui ne sont pas encore connectés.
                    %>
                    <option value="<%=i%>"><%=i%></option>
                    <%
                            } // end if
                        } // end for
                    %>
                </select>
                <br>
                <label for="inputId" class="control-label">Mot de passe:</label>
                <input id="inputPassword" type="password" class="form-control" placeholder="Mot de passe" name="password" value="${param.password}" size="24" /><br>
                <div id="error-message">${messageErreur}</div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Envoyer</button>
            </form>
        </div>
    </body>
</html>
