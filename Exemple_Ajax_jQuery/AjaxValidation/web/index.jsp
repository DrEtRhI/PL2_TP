<%-- Copyright 2005 Sun Microsystems, Inc. All rights reserved. You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: http://developer.sun.com/berkeley_license.html
$Id: index.jsp,v 1.4 2005/06/15 05:39:43 gmurray71 Exp $ --%>
<html>
    <head>

        <!-- JavaScript code starts from here -->
        <script type="text/javascript">
            var req;
            var target;
            var isIE;
            var userIdIsValid = false;

            // (3) JavaScript function in which XMLHttpRequest JavaScript object is created.
            // Please note that depending on a browser type, you create
            // XMLHttpRequest JavaScript object differently.  Also the "url" parameter is not
            // used in this code (just in case you are wondering why it is
            // passed as a parameter).
            //
            function initRequest(url) {
                if (window.XMLHttpRequest) {
                    req = new XMLHttpRequest();
                } else if (window.ActiveXObject) {
                    isIE = true;
                    req = new ActiveXObject("Microsoft.XMLHTTP");
                }
            }

            // (2) Event handler that gets invoked whenever a user types a character
            // in the input form field whose id is set as "userid".  This event
            // handler invokes "initRequest(url)" function above to create XMLHttpRequest
            // JavaScript object.
            //
            function validateUserId() {
                if (!target) {
                    target =document.getElementById("userid");
                }
                var url = "validate?id=" + encodeURI(target.value);
                
                // Invoke initRequest(url) to create XMLHttpRequest object
                initRequest(url);

                // The "processRequest" function is set as a callback function.
                // (Please note that, in JavaScript, functions are first-class objects: they
                // can be passed around as objects.  This is different from the way
                // methods are treated in Java programming language.)
                req.onreadystatechange = processRequest;
                req.open("GET", url, true);
                req.send(null);
            }

            // (4) Callback function that gets invoked asynchronously by the browser
            // when the data has been successfully returned from the server.
            // (Actually this callback function gets called every time the value
            // of "readyState" field of the XMLHttpRequest object gets changed.)
            // This callback function needs to be set to the "onreadystatechange"
            // field of the XMLHttpRequest.
            //
            function processRequest() {
                if (req.readyState == 4) {
                    if (req.status == 200) {

                        // Extract "true" or "false" from the returned data from the server.
                        // The req.responseXML should contain either <valid>true</valid> or <valid>false</valid>
                        var message = req.responseXML.getElementsByTagName("valid")[0].childNodes[0].nodeValue;

                        // Call "setMessageUsingDOM(message)" function to display
                        // "Valid User Id" or "Invalid User Id" message.
                        setMessageUsingDOM(message);
            
                        if (message == "false") {
                            userIdIsValid = false;
                        } else {
                            userIdIsValid = true;
                        }
                    }
                }
            }

            // This function is not used for now. You will use this later.
            //
            function setMessageUsingInline(message) {
                var mdiv = document.getElementById("userIdMessage");
                if (message == "false") {
                    mdiv.innerHTML = "<div style=\"color:red\">Invalid User Id</div>";
                } else {
                    mdiv.innerHTML = "<div style=\"color:green\">Valid User Id</div>";
                }
            }

            // (5) Function in which message indicating the validity of the data gets displayed
            // through the "userIdMessage" <div> element.
            //
            function setMessageUsingDOM(message) {
                var userMessageElement = document.getElementById("userIdMessage");
                var messageText;
                if (message == "false") {
                    userMessageElement.style.color = "red";
                    messageText = "Invalid User Id";
                } else {
                    userMessageElement.style.color = "green";
                    messageText = "Valid User Id";
                }
                var messageBody = document.createTextNode(messageText);
                // if the messageBody element has been created simple replace it otherwise
                // append the new element
                if (userMessageElement.childNodes[0]) {
                    userMessageElement.replaceChild(messageBody, userMessageElement.childNodes[0]);
                } else {
                    userMessageElement.appendChild(messageBody);
                }
            }

            function validatePasswd() {
                var passwd = document.getElementById("passwd").value;
                var passwdConfirmation = document.getElementById("passwdConfirm").value;
                var passwdMessDiv = document.getElementById("passwdMessage");
                if (passwd == passwdConfirmation) {
                    passwdMessDiv.innerHTML = "";
                    disableSubmitBtn(false);
                }   
                else {
                    passwdMessDiv.innerHTML = "<div style=\"color:red\">Passwords must match</div>";
                    disableSubmitBtn(true);
                }
            }

            function disableSubmitBtn(val) {
                var submitBtn = document.getElementById("submit_btn");
                submitBtn.disabled = val;
            }
        </script>
        <title>Form Data Validation using AJAX</title>
    </head>
    <body onload="disableSubmitBtn(true)">
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
                                  name="id"
                                  onkeyup="validateUserId()">
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
                               size="20"
                               onkeyup="validatePasswd()"/>
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
                            <input id="submit_btn" type="Submit" value="Create Account" disabled="true">
                        </div></td>
                    <td></td>
                </tr>
            </table>
        </form>
    </body>
</html>
