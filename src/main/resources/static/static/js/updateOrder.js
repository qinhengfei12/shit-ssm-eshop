//计算商品总价
function updateFinalPrice() {
    var $tr = $("#itemTable").find("tr[id]");
    var summer = 0;
    $tr.each(function(i, dom) {
        if($(dom).find("#sItemChecked").is(":checked")){
            var num = $(dom).find(".ItemNo").val(); //商品数量
            var price = num * $(dom).find(".sPrice").text(); //商品小计
            summer += price; //总价
        }
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
    let ID = document.getElementById("sItemID").value;
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


