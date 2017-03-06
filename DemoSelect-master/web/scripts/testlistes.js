

$(function () {
    // cette fonction est executée un fois que le navigateur a terminé le chargement
    // de la page et la construction du DOM)
    // c'est la même chose que 
    // $(document).ready( function() { ...
    //     ...
    //    }
    // );
    // 

    // on charge la liste des pays par un appel AJAX
    // le paramètre est l'URL d'une servlet qui retourne du code HTML
    // qui sera inséré dans l'élément d'id listePays
    $("#listePaysAvecSportif").load("listeDesPays?avecSportif=true");
    $("#listePaysSansSportif").load("listeDesPays?avecSportif=false");
    $("#listeTousPays").load("listeDesPays");
    
    // on associe au formulaire d'id choixPaysForm un gestionnaire d'événements
    // qui dès que l'un des inputs de ce formulaire (c'est à dire la liste de
    //  sélection du pays ou le radio bouton définissant le sexe du sportif) 
    //  est modifié  invoque la fonction updateListeSportifs
    $("#choixPaysForm").change(function () {
        updateListeSportifs();
    });
});

function updateListeSportifs() {
    // on récupère dans une chaîne de caractères les paramètres du formulaire
    // d'id choixPaysForm. Ainsi si le pays   sélectionné est la France et le
    // radio bouton Féminé est activé params aura la valeur suivante :
    // "pays=France&sexe=F
    var params = $("#choixPaysForm").serialize();
    // appel AJAX à la servlet d'URL listeDesSportifs avec les paramètres de
    // requête params. Le code HTML retourné par cette servlet est inséré
    // dans l'élément d'id listeSportifs
    $("#listeSportifs").load("listeDesSportifs", params);
    // active la liste de sélection listeSportifs, afin que l'utilisateur puisse
    // sélectionner un sportif
    $("#listeSportifs").prop("disabled", false);
}

