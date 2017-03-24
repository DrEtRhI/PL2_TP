$(document).ready(
        function () {
            $("tr").click(function () {
                var rang = $(this).find("td:first-child").text();
                BootstrapDialog.show({
                    title: 'Résultat détaillé',
                    message: $('<div></div>').load('detailResultat?rang='+ rang)
                });
            });
        }
);
