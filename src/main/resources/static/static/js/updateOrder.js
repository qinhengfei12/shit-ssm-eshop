//计算商品总价
function updateFinalPrice() {
    var $tr = $("#itemTable").find("tr[id]");
    var summer = 0;
    $tr.each(function (i, dom) {
        var num = $(dom).children(".ItemNo").val(); //商品数量
        var price = num * $(dom).children(".sPrice").text(); //商品小计
        summer += price;
    });
    $("#finalPrice").text(summer);
}
//提交订单
function submitOrder() {

}
//更新购物车
function uploadCart() {

}
//删除当前商品
function deleteItemFromCart() {

}


