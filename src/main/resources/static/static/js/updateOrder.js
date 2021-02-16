//计算商品总价
function updateFinalPrice() {
    var cartTableElem = $("#itemTable").find("tr[id]");
    var totalPrice = 0;
    cartTableElem.each(function(cIndex, cElement) {
        if($(cElement).find("#sItemChecked").is(":checked")){
            var cNum = $(cElement).find(".ItemNo").val(); //商品数量
            if(cNum < 1){
                alert("请修改商品数量！");
            }else{
                var singlePrice = cNum * $(cElement).find(".sPrice").text(); //商品小计
                totalPrice += singlePrice; //总价
            }
        }
    });
    $("#finalPrice").text(totalPrice);
}
//提交订单
function submitOrder() {

}
//更新购物车
function updateCart() {
    var cartTableElem = $("#itemTable").find("tr[id]");
    cartTableElem.each(function(cIndex, cElement) {
        if($(cElement).find("#sItemChecked").is(":checked")){
            var cElemId= $(cElement).find("#sItemID").val(); //商品ID
            var cElemNum = $(cElement).find("#sItemNo").val(); //商品数量
            if(cElemNum < 1){
                alert("请修改商品数量！");
            }else {
                var formData = {"id":cElemId,"num":cElemNum};
                $.ajax({
                    type: "post",
                    url: "/api/cart",
                    data: formData,
                    dataType: "json",
                    encode: true,
                }).done(function(respData){
                    alert(respData.message);
                }).fail(function(){
                    alert("更新购物车失败!");
                })
            }
        }
    });
}
//删除当前商品
function deleteItemFromCart() {
    var cartTableElem = $("#itemTable").find("tr[id]");
    cartTableElem.each(function(cIndex, cElement) {
        if($(cElement).find("#sItemChecked").is(":checked")){
            var cElemId = $(cElement).find("#sItemID").val(); //商品ID
            var cItemUrl = "/api/cart?sItemId=" + cElemId;
            $.ajax({
                type: "delete",
                url: cItemUrl,
                data: "",
                encode: true,
            }).done(function(respData){
                alert(respData.message);
            }).fail(function(){
                alert("删除商品失败!");
            })

        }
    });
}


