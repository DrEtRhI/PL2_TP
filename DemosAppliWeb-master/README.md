# DemosAppliWeb

Ce site contient différents projet NetBeans qui illustrent l’utilisation conjointe de HTML/CSS/JavaScript et Java pour la réalisation d’applications web.

Les applications proposées sont :

- **SelectDemo**: ce projet NetBeans contient une application web montrant la création dynamique de listes de sélection HTML à partir d'informations contenues dans des tables d'une base de données.

- **DemoWebMail**: ce projet NetBeans contient une application web de démonstration de génération d'un fichier pdf et envoi de celui-ci par email. Cette application utilise l'API standard JavaMail et son [implémentation de référence](https://java.net/projects/javamail/pages/Home) pour l'envoi des courriels. Pour la génération de fichier pdf, la librairie [Apache PDFBox](https://pdfbox.apache.org/index.html) version 1.8.11 est utilisée. La configuration du serveur de mail sortant (SMTP) utilisé par cette application est effectuée dans le fichier `context.xml`. 
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Context antiJARLocking="true" path="/DemoWebMail">
    <Resource 
        name="mail/DEMO"
        type="javax.mail.Session"
        auth="Container" 
        mail.smtp.auth="true"
        mail.smtp.starttls.enable="true"
        mail.smtp.host="smtps.ujf-grenoble.fr"
        mail.smtp.port="587"
        mail.smtp.user="VotreLoginUGA"
        mail.smtp.password="VotreMotDePasseUGA"
        password="VotreMotDePasseUGA"
    />
</Context>
```

