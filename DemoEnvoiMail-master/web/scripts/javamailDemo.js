/* 
 * 
 */
$(document).ready(function () {
    
    $("#waitMessage").hide(); 
    // appel ajax qui peremt d'initialiser la liste des épreuves
    // la servlet EpreuvesServlet est invoquée, et elle renvoie le code HTML
    // correspondant à la liste des options correspondant aux différentes epreuves
    $("#epreuve").load("epreuve?action=list");

    // gestionaire d'événement "change" sur la liste des épreuves
    // dès qu'une épreuve est sélectionnée dans la liste, un appel ajax 
    // à la servlet EpreuvesServlet est effectué afin de récupérer la date de
    // l'épreuve et l'afficher dans le champ text "dateEpreuve"
    $("#epreuve").change(
            function () {
                // appel du service pour obtenir la date de l'épreuve
                 var idEpreuve = this.value;
                 $.get("epreuve?action=date&noEpreuve=" + idEpreuve,
                    function(data) {
                        $("#dateEpreuve").val(data);
                    });
            }
    );
    
    $("#achatForm").submit(function() {
       $("#waitMessage").show(); 
    });
});

