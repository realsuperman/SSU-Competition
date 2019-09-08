var gridOptions;
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

        $( window ).resize(function() {
            _this.resizeMethod();
        });

        $('#search').on('click', function () { //조회 누를 때
            _this.searchMethod();
        });
        $('#delete').on('click', function () { //삭제 누를 때
            _this.deleteMethod();
        });
        $('#add').on('click',function(){
            _this.addMethod();
        });
        $('#save').on('click',function(){
            _this.saveMethod();
        });
       /* $('#itemDelete').on('click', function () { //삭제 누를 때
            _this.deleteMethod();
        });
        $('#itemAdd').on('click',function(){
            _this.addMethod();
        });
        $('#itemSave').on('click',function(){
            _this.saveMethod();
        });*/
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
    },searchMethod : function(){//search 누를때
        var _this = this;
        $.ajax({
            type: 'GET',
            url: '/view',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: {userId: $('#uId').val()}
        }).done(function (data) {
            console.log(data);
            _this.gridDraw(data);
        }).fail(function () {
            //alert("알 수 없는 에러입니다 관리자에게 문의해주세요.");
        });
    },gridDraw :function(result) { // result는 위의 json임
        var _this = this;
        var columnDefs = [
            {
                headerName: "선택",
                field: "선택",
                width: 60,
                cellStyle: {"textAlign": "center"},
                checkboxSelection: true,
                suppressSorting: true,
                suppressMenu: true,
                headerCheckSelection: true,
                headerCheckSelectionFilteredOnly: true,
                checkboxSelction: true
            },
            {headerName: "유저아이디", field: "userId", width: 0, cellStyle: {"textAlign": "center"}, hide: true},
            {headerName: "년", field: "year", width: 70, cellStyle: {"textAlign": "center"}, editable: true},
            {headerName: "월", field: "month", width: 70, cellStyle: {"textAlign": "center"}, editable: true},
            {headerName: "용돈", field: "money", width: 80, cellStyle: {"textAlign": "center"}, editable: true},
            {headerName: "남은돈", field: "leftMoney", width: 80, cellStyle: {"textAlign": "center"}},
            {headerName: "생성일", field: "regdate", width: 150, cellStyle: {"textAlign": "center"}},
            {headerName: "수정일", field: "moddate", width: 150, cellStyle: {"textAlign": "center"}}
            // headerName은 보여질이름 || filed는 json객체의이름 || cellStyle는 값이 보여질 형식? 양식? || onCellClicked는 셀이 클릭되었을때 이벤트
        ];

        var rowDataSet = result; // 받은 json을 넣어줌

        var rowData = rowDataSet;
        gridOptions = {
            columnDefs: columnDefs,
            rowSelection: 'single',
            enableColResize: true,
            enableSorting: true,
            enableFilter: true,
            enableRangeSelection: true,
            suppressRowClickSelection: true,
            animateRows: true,
            editable: true,
            debug: true,
            onSelectionChanged: onselectionchange,
            onRowClicked: _this.onRowClicked
        };
        gridOptions.onCellEditingStarted = function (event) { // 이부분을 설정하는것이 제일 중요함
            event.data.edit = true; // 변경 이벤트가 발생시 해당 data의 edit을 true로 설정해준다
            gridOptions.api.updateRowData({update: [event.data]});
        };

        onRowClicked = function() {
            var selectedRows = gridOptions.api.getSelectedRows();
            selectedRows.forEach(
                function (selectedRow,index) {

                }
            );
            console.log("hi");
            var itemData = {
                userId: userId,
                year: year,
                month: month
            };
            $.ajax({
                type: 'GET',
                url: '/itemview',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(itemData),
                async: false,
            }).done(function () {
                console.log(itemData);
                _this.gridItemDraw();
            }).fail(function (error) {

            })
        };

        $('#viewGrid').children().remove();
        var eGridDiv = document.querySelector('#viewGrid');
        new agGrid.Grid(eGridDiv, gridOptions);
        gridOptions.api.setRowData(rowData);
    },resizeMethod : function() {
        var windowWidth = $(window).width();

        if (windowWidth > 1500) {
            $(".size").css("left", "38%");
            $(".size2").css("left", "72%");
        } else {
            $(".size").css("right", "250px");
            $(".size").css("right", "500px");
        }
    },fnDelete : function(userId, year,month) {
        $.ajax({
            type: 'DELETE',
            url: '/view?userId=' + userId + "&year=" + year + "&month=" + month,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        })
    },deleteMethod : function(){
        var _this = this;
        var checkGrid = gridOptions.api.getSelectedRows();

        if (checkGrid.length == "0") {
            alert("체크하신 목록이 없습니다");
            return;
        }

        checkGrid.forEach(function (item) {
            //체크된 녀석만
            _this.fnDelete(item.userId,item.year,item.month)
        });
        alert("삭제 완료");
        _this.searchMethod();
    },addMethod : function(){
        var newRow = {
            userId: $('#uId').val(),
            money: null,
            month: null,
            year : null,
            regdate: null,
            moddate: null,
        }
        gridOptions.api.updateRowData({add: [newRow]});
    },
    saveMethod : function(){
        var _this = this;

        gridOptions.api.forEachNode(function (item) {
            if(item.data.edit) {
                _this.fnSave(item.data.userId, item.data.money,item.data.year,item.data.month)
            }
        });
        _this.searchMethod();
    },
    fnSave : function(userId,money,year,month){
        var data = {
            userId: userId,
            money: money,
            month : month,
            year : year,
        };

        $.ajax({
            type: 'POST',
            url: '/saveview',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data),
            async: false
        }).done(function() {

        }).fail(function (error) {

        });
    },gridItemDraw :function(result) { // result는 위의 json임
        var _this = this;
        var columnDefs = [
            {
                headerName: "선택",
                field: "선택",
                width: 60,
                cellStyle: {"textAlign": "center"},
                checkboxSelection: true,
                suppressSorting: true,
                suppressMenu: true,
                headerCheckSelection: true,
                headerCheckSelectionFilteredOnly: true,
                checkboxSelction: true
            },
            {headerName: "유저아이디", field: "userId", width: 0, cellStyle: {"textAlign": "center"}, hide: true},
            {headerName: "년", field: "year", width: 70, cellStyle: {"textAlign": "center"}, editable: true},
            {headerName: "월", field: "month", width: 70, cellStyle: {"textAlign": "center"}, editable: true},
            {headerName: "가격", field: "price", width: 80, cellStyle: {"textAlign": "center"}, editable: true},
            {headerName: "생성일", field: "regdate", width: 150, cellStyle: {"textAlign": "center"}},
            {headerName: "수정일", field: "moddate", width: 150, cellStyle: {"textAlign": "center"}}
            // headerName은 보여질이름 || filed는 json객체의이름 || cellStyle는 값이 보여질 형식? 양식? || onCellClicked는 셀이 클릭되었을때 이벤트
        ];

        var rowItemDataSet = result; // 받은 json을 넣어줌

        var rowData = rowItemDataSet;
        gridOptions = {
            columnDefs: columnDefs,
            rowSelection: 'single',
            enableColResize: true,
            enableSorting: true,
            enableFilter: true,
            enableRangeSelection: true,
            suppressRowClickSelection: true,
            animateRows: true,
            editable: true,
            debug: true
        };
        gridOptions.onCellEditingStarted = function (event) { // 이부분을 설정하는것이 제일 중요함
            event.data.edit = true; // 변경 이벤트가 발생시 해당 data의 edit을 true로 설정해준다
            gridOptions.api.updateRowData({update: [event.data]});
        };

        $('#viewMoneyItemGrid').children().remove();
        var eGridDiv = document.querySelector('#viewGrid');
        new agGrid.Grid(eGridDiv, gridOptions);
        gridOptions.api.setRowData(rowData);
    },

};



main.init();