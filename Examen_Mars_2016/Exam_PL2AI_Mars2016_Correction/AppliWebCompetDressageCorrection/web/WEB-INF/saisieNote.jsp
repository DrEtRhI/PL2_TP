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
            <div>
                <%@include file="/WEB-INF/jspf/jugeHeader.jspf" %>
                <%@include file="/WEB-INF/jspf/cavalierHeader.jspf" %>
            </div>
            <form  class="form-signin" method="POST" action="enregistrerNote">
                <h2 class="form-signin-heading">Entrez votre note</h2>
                <label for="inputNote" class="sr-only">note:</label>
                <input class="form-control" placeholder="Note" type="text" name="note" value="" width="3"/><br>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Envoyer</button>
            </form>
        </div>
    </body>
</html>
