var sw=0;
var badCnt=0;
var locationCnt=0;
var main = {
    init: function () {
        var _this = this;
        window.onload = function(){
            webgazer.setGazeListener(function(data, elapsedTime) {
                if(color == 'green') sw=1;
                if (data == null) {
                    if(sw==1 && color!='green') if(++badCnt > 100) console.log("부정행위 의심됨 db에 우선저장");
                    return;
                }

                if(sw==1 && color !='green') if(++badCnt > 100) console.log("부정행위 의심됨 db에 우선저장");

                var xprediction = data.x; //these x coordinates are relative to the viewport
                var yprediction = data.y; //these y coordinates are relative to the viewport

                console.log(xprediction, yprediction); //elapsed time is based on time since begin was called
            }).begin();
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