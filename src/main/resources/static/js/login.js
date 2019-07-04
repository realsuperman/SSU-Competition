var duplication = 1; //중복체크
var firstID; //중복체크 눌렀을 때 아이디
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
                if(data>0){
                    alert("이미 아이디가 존재합니다");
                }
                else{
                    duplication = 0;
                    firstID=$('#userId').val();
                    alert("사용 가능한 아이디입니다");
                }
            }).fail(function (error) {
                alert(error);
            });
        });
    },
    save : function() {

        if ($('#userId').val()== "") {
            alert("아이디를 입력해주세요");
            return;
        }

        if (duplication){
            alert("중복체크를 해주십시요");
            return;
        }
       if ($('#userId').val()!=firstID) {
                alert("아이디 입력을 바꾸셨습니다. 중복체크를 다시 해주십시요");
                return;
            }
        if ($('#userPw').val()=="") {
            alert("비밀번호를 입력해주세요");
            return;
        }
        if ($('#name').val()=="") {
            alert("이름을 입력해주세요");
            return;
        }
        if ($('#phone').val() == "") {
            alert("휴대폰번호를 입력해주세요");
            return;
        }
        var data = {
            userId: $('#userId').val(),
            userPw: $('#userPw').val(),
            name: $('#name').val(),
            phone: $('#phone').val()
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