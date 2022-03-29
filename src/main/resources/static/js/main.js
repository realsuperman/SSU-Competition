var sw=0;
var badCnt=0;
var locationCnt=0;
var main = {
    init: function () {
        var _this = this;
        window.onload = function(){
            webgazer.setGazeListener(function(data, elapsedTime) {}).begin();
        }
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
        $('#main').on('click', function () { //Logout 누를 때
            _this.pageReload("/login/users");
        });
        $('#common').on('click', function () { //Common 누를 때
            _this.pageReload("/common");
        });
        $('#view').on('click', function () { //View 누를 때
            _this.pageReload("/view");
        });
    },
    pageReload : function(url) {
        var form = document.createElement('form');
        var objs;
        objs = document.createElement('input');
        objs.setAttribute('type', 'hidden');
        objs.setAttribute('name', 'uId');
        objs.setAttribute('value', $('#uId').val());
        form.appendChild(objs);
        form.setAttribute('method', 'post');
        form.setAttribute('action', url);
        document.body.appendChild(form);
        form.submit();
    }
};



main.init();