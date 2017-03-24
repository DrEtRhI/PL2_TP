<%-- Copyright 2005 Sun Microsystems, Inc. All rights reserved. You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: http://developer.sun.com/berkeley_license.html
$Id: success.jsp,v 1.1 2005/03/26 01:59:10 gmurray71 Exp $ --%>
<html>
    <head>
        <title></title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <h1>Account Created</h1>
        <hr/>
        <p>
            Your account has been created with the user id: <strong>${requestScope.targetId}</strong> .
        </p>
        <p>
            <a href="index.jsp">Go back to the application home</a>.
        </p>
    </body>
</html>

