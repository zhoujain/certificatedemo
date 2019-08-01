$(function () {
    var $table = $('#table');
    $table.hide();
    $.ajax({
        url: '/getUsersDataJSON',
        type: 'post',
        dataType: 'json',
        success: function (d) {
            $("#loadGif").hide();
            $table.show();
            $table.bootstrapTable({
                data: d
            });
        }
    });
    $('#updateUserModal').modal("hide");
});

/**
 * 将数据带到用户更新弹出框
 * @param username
 */
function updateUser(username) {
    $('#username').val(username);

    $.ajax({
        url:'/getUserByUsername',
        type:'post',
        data:{'username':username},
        dataType:'json',
        success:function(res){
            $('#uid').val(res.uid);
            $('#usertype').val(res.utid);
            $('#upwd').val(res.upwd);
            $('#upwd2').val(res.upwd);
        }
    });
}

/**
 * 删除用户
 * @param uid
 */
function delUser(uid) {
    var r=confirm("确认删除？");
    if (r===true) {
        $.get('/delUserByUid', {'uid': uid}, function (res) {
            if (res === true) {
                alert("删除成功");
                $.ajax({
                    url: '/getUsersDataJSON',
                    type: 'post',
                    dataType: 'json',
                    success: function (res) {
                        var $table = $('#table');
                        $table.bootstrapTable('load', res);
                    }
                });
            } else {
                alert("修改失败")
            }
        })
    }
}

/**
 * 通过查询条件查询用户
 */
function queryUserByLikeUsername() {
    var username = $.trim($('#usernametext').val());
    if (username===""){
        var $table = $('#table');
        $.ajax({
            url: '/getUsersDataJSON',
            type: 'post',
            dataType: 'json',
            success: function (res) {
                $table.bootstrapTable('load',res);
            }
        });
    }else {
        var $table = $('#table');
        $.ajax({
            url: '/getUserByLikeUsername',
            data:{'username':username},
            type: 'post',
            dataType: 'json',
            success: function (res) {
                $table.bootstrapTable('load',res);
            }
        });
    }
}

/**
 * 提交更新用户
 */
function commitUpdateUser() {
    var uid=$.trim($('#uid').val());
    var username=$.trim($('#username').val());
    var usertype=$.trim($('#usertype').val());
    var upwd=$.trim($('#upwd').val());
    var upwd2=$.trim($('#upwd2').val());

    if (username===""||upwd===""||upwd2===""){
        alert("输入的值不能为空，或仅含有空格")
    }else if (upwd!==upwd2){
        alert("两次输入密码不一致，请检查")
    }else {
        $.ajax({
            url: '/updateUser',
            data:{'uid':uid,'username':username,'usertype':usertype,'upwd':upwd},
            type: 'post',
            success: function (res) {
                if (res===true) {
                    alert("修改成功")
                    $('#updateUserModal').modal("hide");
                    $.ajax({
                        url: '/getUsersDataJSON',
                        type: 'post',
                        dataType: 'json',
                        success: function (res) {
                            var $table = $('#table');
                            $table.bootstrapTable('load', res);

                        }
                    });
                }
                else {
                    alert("修改失败")
                }
            }
        });
    }
}

/**
 * 刷新表格
 */
function refreshUserList() {
    queryUserByLikeUsername();
}