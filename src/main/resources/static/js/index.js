var write = {
    init : function () {
        var _this = this;
        $('#btn-write').on('click', function () {
            _this.save();
        });
    },
    save : function(){
        var data = {
            title : $('#title').val(),
            author : $('#author').val(),
            content : $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url : '/api/save',
            dataType : 'json',
            contentType : 'application/json; charset=UTF-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert('등록되었습니다.');
            window.location.href = '/bbs';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};
write.init();