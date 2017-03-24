<%-- Copyright 2005 Sun Microsystems, Inc. All rights reserved. You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: http://developer.sun.com/berkeley_license.html
$Id: index.jsp,v 1.4 2005/06/15 05:39:43 gmurray71 Exp $ --%>
<html>
    <head>
        <script src="//code.jquery.com/jquery-2.1.3.min.js"></script>
        <script src="scripts/validation.js"></script>
        <title>Form Data Validation using AJAX</title>
        <style>
            .valid {
                color: green;
            }
            .invalid {
                color: red;
            }
        </style>
    </head>
    <body>
        <table width="100%" border="0">
            <tr>
                <td width="10%"><img src="images/ajax001.png" width="123" height="74" align="middle"></td>
                <td width="90%"><h1>Validation d'un formulaire en utilisant AJAX</h1>
                    <p>Cet exemple montre comment utiliser AJAX pour effectuer une
                        validation de donn&eacute;es c&ocirc;t&eacute; serveur sans rechargement
                        de la page.<br>
                        D'apr&egrave;s le cours de Sang Shin, <font color="#0000CC"><strong><font color="#990000">18-week &quot;Free&quot; AJAX
                            and Web 2.0 Programming (with Passion!) Online Course</font>
                        </strong></font> <a href="http://www.javapassion.com/ajaxcodecamp/">www.javapassion.com/ajaxcodecamp/</a> </p>
                </td>
            </tr>
        </table>
        <hr/>
        <p>
            Entrez un identifiant utilisateur et un mot de passe dans le formulaire ci-dessous.
            Par defaut les identifiants utilisateur &quot;toto38&quot; and &quot;titi&quot;
            sont déjà définis. Si vous entrez un identifiant déjà utilisé un message d'erreur
            sera affiché à côté du champ de saisie. De même l'application vérifie que les deux mots
            de passe fournis sont identiques. Dans le cas contraire un message le signale à
            l'utilisateur. Le bouton &quot;Create Account&quot; qui permet de créer un compte
            pour l'utilisateur n'est activé que lorsque ces deux conditions sont remplies. Dans ce cas
            le nom de l'utilisateur et son mot de passe sont ajoutés à la liste des utilisateurs
            connus.</p>
        <form name="updateAccount" action="validate" method="post">
            <input type="hidden" name="action" value="create"/>
            <table border="0" cellpadding="5" cellspacing="0">
                <tr>
                    <td><b>User Id:</b></td>
                    <td>
                        <!-- (1) Input form field whose id is set as "userid" and "validateUserId()" function is
                        associated with the onkeyup event -->
                        <input    type="text"
                                  size="20"
                                  id="userid"
                                  name="id">
                    </td>
                    <!-- The "userIdMessage" div element specifies the location where input validation
                    message gets displayed. -->
                    <td>
                        <div id="userIdMessage"></div>
                    </td>
                </tr>
                <tr>
                    <td>password:</td>
                    <td>
                        <input type="password"
                               name="passwd"
                               id="passwd"
                               value=""
                               size="20" />
                    </td>
                </tr>
                <tr>
                    <td>confirm password:</td>
                    <td>
                        <input type="password"
                               name="passwdConfirm" id="passwdConfirm"
                               value="" size="20"
                               onkeyup="validatePasswd()"/>
                    </td>
                    <td>
                        <div id="passwdMessage"></div>
                    </td>
                </tr>
                <tr>
                    <td align="right" colspan="2">
                        <div align="center">
                            <input id="submit_btn" type="Submit" value="Create Account">
                        </div></td>
                    <td></td>
                </tr>
            </table>
        </form>
    </body>
</html>
