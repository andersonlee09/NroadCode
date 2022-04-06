## 这里对本Demo进行简单说明

### api包
api包下没有写接收参数的情况
直接写了对于订单的处理
订单是一个hashmap类型，key->商品类，value->数量
ProcessOrderServiceFacade只进行两个操作：1. 计算出来订单 2. 保存订单

### application
这里对CountTotalPriceAfterDiscount就是对于订单的总价格进行判断，返回最小的价格
逻辑就是：如果订单商品数类型大于5就打8折，如果商品价格大于500就打七折，计算出最小值并返回订单

repository 就是对于数据库的一些操作

### domain
这里的Order订单类更像是一种Entity，它具有计算出总价格的能力
Commodity则像是一种普通的DP