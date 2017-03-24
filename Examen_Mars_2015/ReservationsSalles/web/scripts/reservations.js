$(document).ready(
        function () {
            $("#tabs").tabs({
                beforeLoad: function (event, ui) {
                    ui.jqXHR.fail(function () {
                        ui.panel.html(
                                "Problème chargement des données");
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

    // faire un appel ajax (get ou post) à une servlet DeleteReservation 
    // avec comme paramètre le numéro de la réservation à supprimer.
    // associer à cet appel une fonction call back qui demande de recharger 
    // l'onglet (voir la méthode load du composant Tabs de JQueryUI)

    $.ajax({
        type: 'get',
        url: 'ReservationSuppression',
        data: 'idResa=' + noResa,
        success: function callback(){
            $( "#tabs" ).tabs( "load", 0 );
        }
    });
}

