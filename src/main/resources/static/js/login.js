var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#userCheckButton').on('click',function() { //중복체크 클릭시
            $.ajax({
                type: 'GET',
                url: '/users',
                dataType: 'json',
                contentType:'application/json; charset=utf-8',
                data: { userId: $('#userId').val() }
            }).done(function(data) {
                console.log(data);
            }).fail(function (error) {
                alert(error);
            });
        });
    },
    save : function () {
        var data = {
            userId: $('#userId').val(),
            userPw: $('#userPw').val(),
            name: $('#name').val(),
            phone : $('#phone').val()
        };

        $.ajax({
            type: 'POST',
            url: '/users',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('유저정보 저장완료');
            location.reload();
        }).fail(function (error) {
            alert(error);
        });
    }

};

main.init();