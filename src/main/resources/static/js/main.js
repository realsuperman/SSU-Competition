var main = {
    init: function () {
        var _this = this;
        $('#logout').on('click', function () { //Logout 누를 때
            $.ajax({
                type: 'GET',
                url: '/logout',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function (data) {
                if (data == 1) {
                    alert("로그아웃 되었습니다");
                    location.href="/";
                }
            }).fail(function (error) {
                alert(error);
            });
        });
    }
};



main.init();