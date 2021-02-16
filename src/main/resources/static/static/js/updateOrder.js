//计算商品总价
function updateFinalPrice() {
    var tr = $("#itemTable").find("tr[id]");
    var summer = 0;
    tr.each(function(i, dom) {
        if($(dom).find("#sItemChecked").is(":checked")){
            var num = $(dom).find(".ItemNo").val(); //商品数量
            if(num < 1){
                alert("请修改商品数量！");
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
    var tr = $("#itemTable").find("tr[id]");
    tr.each(function(i, dom) {
        if($(dom).find("#sItemChecked").is(":checked")){
            var id = $(dom).find("#sItemID").val(); //商品ID
            var num = $(dom).find("#sItemNo").val(); //商品数量
            if(num < 1){
                alert("请修改商品数量！");
            }else {
                var formData = {"id":id,"num":num};
                $.ajax({
                    type: "post",
                    url: "/api/cart/update",
                    data: formData,
                    dataType: "json",
                    encode: true,
                }).done(function(data){
                    alert(data.message);
                }).fail(function(){
                    alert("更新购物车失败!");
                })
            }
        }
    });
}
//删除当前商品
function deleteItemFromCart() {
    var tr = $("#itemTable").find("tr[id]");
    tr.each(function(i, dom) {
        if($(dom).find("#sItemChecked").is(":checked")){
            var id = $(dom).find("#sItemID").val(); //商品ID
            var url = "/api/cart/delete?sItemID=" + id;
            $.ajax({
                type: "get",
                url: url,
                data: "",
                encode: true,
            }).done(function(data){
                alert(data.message);
            }).fail(function(){
                alert("删除商品失败!");
            })

        }
    });
}


