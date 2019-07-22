var main = {
    init: function () {
        var _this = this;
        _this.resizeMethod();
        _this.searchMethod();
        $('#logout').on('click', function () { //Logout 누를 때
            $.ajax({
                type: 'GET',
                url: '/logout',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8'
            }).done(function (data) {
                if (data == 1) {
                    alert("로그아웃 되었습니다");
                    location.href = "/";
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

        $('#search').on('click', function () { //조회 누를 때
            _this.searchMethod();
        });

        $( window ).resize(function() {
            _this.resizeMethod();
        });

    },
    pageReload: function (url) {
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
    },searchMethod : function(){
        var _this = this;
        $.ajax({
            type: 'GET',
            url: '/common',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: {userId: $('#uId').val()}
        }).done(function (data) {
            _this.gridDraw(data);
        }).fail(function () {
            alert("알 수 없는 에러입니다 관리자에게 문의해주세요.");
        });
    },gridDraw :function(result) { // result는 위의 json임
        var columnDefs = [
            {headerName : "선택",       field:"선택",         width:100, cellStyle:{"textAlign":"center"},checkboxSelection: true, suppressSorting: true, suppressMenu: true,
            headerCheckSelection: true, headerCheckSelectionFilteredOnly: true, checkboxSelction: true},
            {headerName : "유저아이디", field:"userId",       width: 0, cellStyle:{"textAlign":"center"},hide:true},
            {headerName : "코드값",     field:"typeCode",     maxWidth: 200, cellStyle:{"textAlign":"center"}},
            {headerName : "코드명",     field:"typeName",     maxWidth: 200, cellStyle:{"textAlign":"center"}},
            {headerName : "생성일",     field:"regdate",      maxWidth: 280, cellStyle:{"textAlign":"center"}},
            {headerName : "수정일",     field:"moddate",      maxWidth: 280, cellStyle:{"textAlign":"center"}}
            // headerName은 보여질이름 || filed는 json객체의이름 || cellStyle는 값이 보여질 형식? 양식? || onCellClicked는 셀이 클릭되었을때 이벤트
        ];

        var rowDataSet = result; // 받은 json을 넣어줌

        var rowData = rowDataSet;
        var gridOptions = {
            columnDefs: columnDefs,
            rowSelection: 'multiple',
            enableColResize: true,
            enableSorting: true,
            enableFilter: true,
            enableRangeSelection: true,
            suppressRowClickSelection: true,
            animateRows: true,
            editable:true,
            debug: true
        };

        $('#commonGrid').children().remove();
        var eGridDiv = document.querySelector('#commonGrid');
        new agGrid.Grid(eGridDiv, gridOptions);
        gridOptions.api.setRowData(rowData);
    }, resizeMethod : function(){
        var windowWidth = $( window ).width();

        if(windowWidth > 1500 ) {
            $( ".size" ).css( "right", "25%" );
        }else {
            $( ".size" ).css( "right", "250px" );
        }
    }
};



main.init();