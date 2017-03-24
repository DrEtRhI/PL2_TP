$(document).ready(
        function () {
            $("#bonjourBtn").click(
                    function () {
                        $("#message").css("color","green");
                        $("#message").load("bonjour?nom=" + encodeURIComponent($("#text1").val()));
                    });
            $("#bonsoirBtn").click(
                    function () {
                        $("#message").css("color","blue");
                        $("#message").load("bonsoir?nom=" + encodeURIComponent($("#text1").val()));
                    });
        }
);


