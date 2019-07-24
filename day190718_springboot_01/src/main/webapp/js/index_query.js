/*jQuery(function ($) {
    $('.table').footable({
        "columns": [
            {"name": "cid", "title": "序号"},
            {"name": "cnumber", "title": "证书编号"},
            {"name": "ccompany", "title": "证书单位"},
            {"name": "ctoolname", "title": "器具名称"},
            {"name": "cmodel", "title": "型号规格"},
            {"name": "coutnumber", "title": "出厂编号"},
            {"name": "cmanfacturer", "title": "制造厂商"},
            {"name": "cdelegate", "title": "委托单号"},
            {"name": "ccheckdate", "title": "检定日期"},
            {"name": "ccheckdepartment", "title": "检测部门"},
            {"name": "uname", "title": "添加人"},
            {"name": "puname", "title": "打印人", "breakpoints": "xs sm"},
            {"name": "cprintdate", "title": "打印日期", "breakpoints": "xs sm"},
            {"name": "cmoney", "title": "检测费", "breakpoints": "xs sm"},
            {"name": "actions", "title": "操作", "breakpoints": "xs sm"}
        ]
    });
    $.get("/getCertificatesDataJSON",function(result){

        $('.table').rows.load(result);
    });
});*/

jQuery(function($) {
    var ft = FooTable.init('#myTable', {
        "columns": [
            {"name": "cid", "title": "序号"},
            {"name": "cnumber", "title": "证书编号"},
            {"name": "ccompany", "title": "证书单位"},
            {"name": "ctoolname", "title": "器具名称"},
            {"name": "cmodel", "title": "型号规格"},
            {"name": "coutnumber", "title": "出厂编号"},
            {"name": "cmanfacturer", "title": "制造厂商"},
            {"name": "cdelegate", "title": "委托单号"},
            {"name": "ccheckdate", "title": "检定日期"},
            {"name": "ccheckdepartment", "title": "检测部门"},
            {"name": "uname", "title": "添加人"},
            {"name": "puname", "title": "打印人", "breakpoints": "xs sm"},
            {"name": "cprintdate", "title": "打印日期", "breakpoints": "xs sm"},
            {"name": "cmoney", "title": "检测费", "breakpoints": "xs sm"},
            {"name": "actions", "title": "操作", "breakpoints": "xs sm"}
        ]
    });

    $.get("/getCertificatesDataJSON").then(function (rows) {
        ft.rows.load(rows);
    });
});