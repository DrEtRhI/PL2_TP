# DateTimeDemo

Petite application WEB montrant l'usage du type date Oracle avec Java.

Cette application utilise une table

    CREATE TABLE SPECTACLE (
      NOM VARCHAR2(32) NOT NULL,
      DATE_SPECTACLE DATE NOT NULL
    );
    
Elle permet de saisir un nouveau spectacle en utilisant un input de type `date-time`.
Firefox ne supporte pas encore ce type d'input, mais d'autres navigateurs (Chrome, Opera...)
le font. Cela permet d'avoir un calendrier pour saisir la date (jour, mois, année) et aussi
de fixer l'heure.

Une fois un nouveau spectacle créé, la liste de tous les spectacles triés par date est affichée.


