var write = {
        init: function () {
            var _this = this;
            $('#btn-write').on('click', function () {
                _this.save();
            });
            $('#btn-modify-check').on('click', function () {
                _this.modifyCheck();
            });
            $('#btn-modify').on('click', function () {
                _this.modify();
            });
            $('#btn-delete').on('click', function () {
                _this.delete();
            });
            $('#btn-search').on('click',function () {
                _this.search();
            })
        },
        save: function () {
            var data = {
                title: $('#title').val(),
                author: $('#author').val(),
                content: $('#content').val(),
                password: $('#password').val()

            };

            $.ajax({
                type: 'POST',
                url: '/api/save',
                // dataType: 'json',
                contentType: 'application/json; charset=UTF-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('등록되었습니다.');
                window.location.href = '/bbs';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        },
        modifyCheck: function () {
            let data = {
                id: $('#id').val(),
                password: $('#password').val()
            };

            $.ajax({
                type: 'POST',
                url: '/api/modifycheck',
                dataType: 'text',
                async: false,
                contentType: 'application/json; charset=UTF-8',
                data: JSON.stringify(data)
            }).done(function (data) {
                console.log(data)
                if (data !== -1) {
                    window.location.href = '/bbs/modify/' + data;
                } else {
                    window.location.href = '/bbs';
                }
            }).fail(function (error) {
                console.log(error)
            })
        },
        modify: function () {
            var data = {
                id: $('#id').val(),
                title: $('#title').val(),
                author: $('#author').val(),
                content: $('#content').val(),
                password: $('#password').val()

            };
            $.ajax({
                type: 'POST',
                url: '/api/modify',
                dataType: 'json',
                contentType: 'application/json; charset=UTF-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('수정되었습니다.');
                window.location.href = '/bbs';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        },
        delete: function () {
            var data = {
                id: $('#id').val(),
                title: $('#title').val(),
                author: $('#author').val(),
                content: $('#content').val(),
                password: $('#password').val()
            };
            $.ajax({
                type: 'POST',
                url: '/api/delete',
                dataType: 'text',
                contentType: 'application/json; charset=UTF-8',
                data: JSON.stringify(data)
            }).done(function () {
                alert('삭제되었습니다.');
                window.location.href = '/bbs';
            }).fail(function (error) {
                alert(JSON.stringify(error));
            });
        },
        search: function () {
            var data = {
                "type": $('#search_type option:selected').val(),
                "keyword": $('#in_keyword').val()
            };
            // $.ajax({
            //     type: 'GET',
            //     url: '/bbs/search',
            //     dataType: 'text',
            //     contentType: 'application/json; charset=UTF-8',
            //     data: data
            // }).done(function () {
            //     alert("searched")
            //     window.location.href = '/bbs/search';
            // }).fail(function (error) {
            //     alert(JSON.stringify(error));
            // })
            console.log(data);
            window.location.href = '/bbs/search?type=' + data.type
                + '&keyword=' + data.keyword;
        }

    }
;
write.init();