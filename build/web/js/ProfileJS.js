

function goBack() {
    document.getElementById("subjectContent").innerHTML = "";
    document.getElementById("subjectContent").style.display = 'none';
    document.getElementById("mainTable").style.display = 'block';
}
function saveReply() {
    var user_info = document.getElementById("track_id").getAttribute("user");
    var track_id = document.getElementById("track_id").innerHTML;
    var reply_data = document.getElementById("reply").value;
    var encode = encodeURIComponent(reply_data);
    if (reply_data == null || reply_data === "") {
        alert("Please fill the reply box");
    } else {
        var xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function () {
            if (this.readyState == 4 && this.status == 200) {
                alert(this.responseText);
            }
        };
        xhttp.open("GET", "saveReply?user=" + user_info + "&track_id=" + track_id + "&reply_data=" + encode, true);
        xhttp.send();
    }
}

function arr_diff (a1, a2) {

    var a = [], diff = [];

    for (var i = 0; i < a1.length; i++) {
        a[a1[i]] = true;
    }

    for (var i = 0; i < a2.length; i++) {
        if (a[a2[i]]) {
            delete a[a2[i]];
        } 
    }

    for (var k in a) {
        diff.push(k);
    }

    return diff;
};


$(document).ready(function () {

    $("#subjectContent").hide();
    $tracking_id = "";

    $(".rtisubject").click(function () {
        $htmlContent = "";
        $user_info = $("#table1").attr("userinfo");
        $track_id = $(this).parent().attr("id");
        $("#mainTable").hide();
        $("#subjectContent").show();
        $("#subjectContent").html("Loading...");
        $.get('ViewSubject', {user_info: $user_info, tracking_id: $track_id}, function (responseText) {
            $htmlContent = responseText;
            $("#subjectContent").html($htmlContent);
        });
    });

    $(".edit").click(function () {
        var originalList=[];
        var newList=[];
        $(".modal-body").empty();
        $tracking_id = $(this).parent().attr("id");
        $user_info = $("#table1").attr("userinfo");

        $(this).parent().children("a.contentlink").each(function () {
            $(".modal-body").append("<input type=\"email\" value=\"" + $(this).text() + "\"><br><br>");
            originalList.push($(this).text());
        });

        $("#addinput").click(function () {

            $(".modal-body").append("<input type=\"text\"><br><br>");
            $(".modal-body").animate({scrollTop: $('.modal-body').prop("scrollHeight")}, 10);
        });

        $("#submit").click(function () {
            var forward_data = "";
            $user_info = $("#table1").attr("userinfo");
            $(".modal-body").children("input").each(function () {
                newList.push($(this).val());
                if ($(this).val() != "") {
                    forward_data = forward_data + $(this).val() + ",";
                }
            });
            var diff=arr_diff(newList,originalList);
            emailValues=diff.toString();
            forward_data = forward_data.slice(0, -1);
            $.get('UpdateForwardList', {user_info: $user_info, tracking_id: $tracking_id, forward_data: forward_data, email_data: emailValues}, function (responseText) {
                alert(responseText);
                location.reload();

            });

        });
    });

});



