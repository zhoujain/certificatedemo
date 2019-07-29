$(function () {
    $('#btn_clear').click(function () {
        $('#usernametext').val("");
        $('#upwdtext').val("");
        $('#upwd2text').val("");
        $('#usertype').val("3")
    });

    $('#btn_commitInsert').click(function () {
        var username=$.trim($('#usernametext').val());
        var usertype=$.trim($('#usertype').val());
        var upwd=$.trim($('#upwdtext').val());
        var upwd2=$.trim($('#upwd2text').val());

        if (username===""||upwd===""||upwd2===""){
            alert("输入的值不能为空，或仅含有空格")
        }else if (upwd!==upwd2){
            alert("两次输入密码不一致，请检查")
        }else {
            $.get('/insertUser',{'username':username,'usertype':usertype,'upwd':upwd},function (res) {
                if (res==="true") {
                    alert("添加成功");
                    $('#usernametext').val("");
                    $('#upwdtext').val("");
                    $('#upwd2text').val("");
                    $('#usertype').val("3")
                }
                else {
                    alert(res)
                }
            })
        }
    });

    $('#idShowPwd').change(function () {
        if ($('#idShowPwd').is(':checked')){
            $('#upwdtext').attr("type","text");
            $('#upwd2text').attr("type","text");
        }else {
            $('#upwdtext').attr("type","password");
            $('#upwd2text').attr("type","password");
        }
    })
});
