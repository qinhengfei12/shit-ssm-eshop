//计算商品总价
function updateFinalPrice() {
    var $tr = $("#itemTable").find("tr[id]");
    var summer = 0;
    $tr.each(function(i, dom) {
        if($(dom).find("#sItemChecked").is(":checked")){
            var num = $(dom).find(".ItemNo").val(); //商品数量
            var name = $(dom).find("#ItemName").text();//商品名称
            if(num < 1){
                alert("请修改商品:"+ name+" 数量！");
            }else{
                var price = num * $(dom).find(".sPrice").text(); //商品小计
                summer += price; //总价
            }
        }
    });
    $("#finalPrice").text(summer);
}
//提交订单
function submitOrder() {

}
//更新购物车
function updateCart() {
    var $tr = $("#itemTable").find("tr[id]");
    $tr.each(function(i, dom) {
        if($(dom).find("#sItemChecked").is(":checked")){
            var ID = $(dom).find("#sItemID").val(); //商品ID
            var NUM = $(dom).find("#sItemNo").val(); //商品数量
            if(NUM < 1){
                alert("请修改商品数量！");
            }else {
                var formData = {"id":ID,"num":NUM};
                $.ajax({
                    type: "post",
                    url: "/api/cart/update",
                    data: formData,
                    dataType: "json",
                    encode: true,
                }).done(function(data){
                    alert(data.message);
                })
            }
        }
    });
}
//删除当前商品
function deleteItemFromCart() {
    var $tr = $("#itemTable").find("tr[id]");
    $tr.each(function(i, dom) {
        if($(dom).find("#sItemChecked").is(":checked")){
            var ID = $(dom).find("#sItemID").val(); //商品ID
            var URL = "/api/cart/delete?sItemID=" + ID;
            $.ajax({
                type: "get",
                url: URL,
                data: "",
                encode: true,
            }).done(function(data){
                alert(data.message);
            })

        }
    });
    location.reload();
}


