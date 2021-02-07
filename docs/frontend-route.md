前端路由：

`/ -> /index -> template: src/main/resources/templates/index.html`: 展示首页，@PreAuthorize

`/show/search -> template: templates/searchres.html`: 展示搜索结构，要求先登陆

`/show/items/<CateID>/<ItemID>`: 展示对应分类下的对应商品的详细页面

`/show/itemCate/<CateID>`: 仅展示对应类别下的商品

`/show/user`: 展示当前用户信息

`/show/order`: 展示当前用户订单

`/show/order/<OrderID>`: 展现订单详情

`/show/user/cart`: 展现当前用户购物车

`/show/order/<OrderID>/pay`: 展示支付页面

`/show/login`, `/show/register`: 注册、登陆，@PreAuthorize

`/manager/admin`: 超管 系统管理

`/manager/shop`: 商店管理

