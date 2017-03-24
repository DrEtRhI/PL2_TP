$(document).ready(function () {
    $("#tabs").tabs({
        beforeLoad: function (event, ui) {
            ui.jqXHR.fail(function () {
                ui.panel.html("Problème chargement des données");
            });
        }
    });
});

/**
 * fonction invoquée lorsque l'utilisateur clique sur un bouton de suppression 
 * d'une réservation.
 * @param {int} noResa le numéro de la réservation à supprimer.
 */
function deleteResa(noResa) {
    
    // trace à enlever quand le comportement du bouton sera programmé.
    //alert("noResa " + noResa);
    
    // faire un appel ajax (get ou post) à une servlet DeleteReservation 
    // avec comme paramètre le numéro de la réservation à supprimer.
    // associer à cet appel une fonction call back qui demande de recharger 
    // l'onglet (voir la méthode load du composant Tabs de JQueryUI)
    var url = "delResa?noResa="+noResa;
    var self = $(this);
    $.get( url, function (data) {
        if (data.ans===1) {
            self.parent().html("<p>OK</p>");
            //self.css({"color":"green","font-weight":"bold"});
        } else {
            self.parent().html("<p>Erreur lors de la suppression ...</p>");
            self.css({"color":"red","font-weight":"bold"});
        }
    });
    //$("#tabs").tabs("load",0);
}
