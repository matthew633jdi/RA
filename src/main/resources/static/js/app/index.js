var main = {
    init : function () {
        var _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });

        $('#btn-user-save').on('click',  function () {
            _this.userSave();
        });

        $('#btn-user-login').on('click', function () {
           _this.login();
        });
    },
    save : function () {
        var data = {
            name: $('#name').val(),
            url: $('#url').val(),
            level: $('#level').val(),
            type: $('#type').val(),
            org: $('#org').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/problems',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('문제가 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    userSave : function () {
        var data = {
            email: $('#email').val(),
            pwd: $('#pwd').val(),
            name: $('#name').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/users',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('회원 가입을 축하드립니다.');
            window.location.href = '/login';
        }).fail(function (jqXHR) {
            var response = jqXHR.responseJSON;
            var responseMsg = '';

            $.each(response.validation, function (fieldName, errorBag) {
                responseMsg += errorBag + "\n";
            });

            alert(responseMsg);
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };

        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            alert('글이 삭제되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    login : function () {
        var email = $('#email').val();
        var password = $('#pwd').val();
        var result = "";

        $.ajax({
            type: 'GET',
            url: '/users',
            data: {
                "email" : email,
                "pwd" : password
            },
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function(data) {
            alert('로그인되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            var response = error.responseJSON;
            alert(response.message);
            // alert(JSON.stringify(error));
        });
    }

};

main.init();