#ConsosCafesWeb

Application Web à base de servlets/JSP montrant de manière progressive la mise en place d'une architecture MVC (Model/View/Controller).
Il s'agit de lister les consommations de cafés d'une équipe de développeurs pour une semaine donnée. Les consommations sont stockées dans une base de données relationnelle.

Différentes versions de l'applications correspondant aux différentes évolutions sont définies dans différentes branches. La branche principale (master) correspond à la version la plus complète (actuellement la version 2.2.1).

Pour chaque version, une étiquette (tag) a été définie. 

##Les versions

###ConsosCafesWeb-2.2.2:

- Ajout d'une fonctionnalité de test unitaire JUNIT pour la DAO.

###ConsosCafesWeb-2.2.1:

- Remplacement de la servlet se chargeant de la génération de la page HTML par une
page JSP.

###ConsosCafesWeb-2.2:

Mise en place d'une architecture MVC:
- un modèle pour représenter une liste des consommations
- un DAO pour construire la liste à partir de la BD (JDBC)
- deux vues:
      une servlet pour l'affichage PDF
      une servlet pour l'affichage HTML
- le contrôleur crée le modèle et le transmet ensuite comme attribut de la requête aux vues.


###ConsosCafesWeb-2.1:

Meilleure séparation des responsabilités:
- une servlet controleur qui fait l'aiguillage
- une servlet pour l'affichage HTML
- une servlet pour l'affichage pdf

###ConsosCafesWeb-2.0:

Possibilité d'afficher la liste des consommations soit en HTML, soit en pdf (en 
utilisant la bibliothèque iText)

###ConsosCafesWeb-1.0:

- utilisation d'une DataSource gérée par le conteneur (Tomcat) pour les connexions JDBC.

###ConsosCafesWeb-0.0:

- version initiale de l'application web de consultation des consommations de café.
