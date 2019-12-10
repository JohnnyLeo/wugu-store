function createtable(){
    var tableData = "<tr>";
    tableData += "<th>订单号</th><th>客户名</th><th>商品</th><th>数量</th><th>单价</th></tr>";
    for(var i = 0;i<5;i++){
        tableData +="<tr>";
        for(var j = 0;j<4;j++){
            tableData += "<th>1</th>";
        }
        tableData +="</tr>";
    }
    $("#order").html(tableData);
}
createtable();
alert("1");