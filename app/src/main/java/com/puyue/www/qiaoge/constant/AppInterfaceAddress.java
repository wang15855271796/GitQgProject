package com.puyue.www.qiaoge.constant;

/**
 * Created by GuoGai on 2016/10/31.
 */
public class AppInterfaceAddress {
    //https://shaokao.qoger.com/qiaoge/正式
    //http://116.62.67.230:8082/qiaoge/   测试http://120.55.55.99:8082/qiaoge/
    public static final String BASE_URL = "http://120.55.55.99:8082/qiaoge/";
    // 正式https://shaokao.qoger.com/qiaoge/
    //http://192.168.1.45/   本地192.168.101.69:8088
    //http://qg.zhiyun88u.com/shen
    //http://192.168.101.17/

    //http://192.168.101.41:8088/

    /**
     *账户列表
     */
    public static final String My_Account = "wallet/queryWalletRecordMonth";
    /**
     *首页是否根据城市展示价格
     */
    public static final String Is_Show = "common/company/enjoyProduct";
    /**
     *余额兑换优惠券
     */
    public static final String Ex_Coupon = "user/exchangeBalanceToGift";
    /**
     *关键字搜索
     */
    public static final String Hot_Key = "common/getSearchHotKey";
    /**
     * 获取注销原因
     */

    public static final String Cancle_Reason = "app/selectConfigByKey";
    /**
     *用户注销--校验用户是否可以注销
     *
     */
    public static final String Is_Cancle = "user/isCancel";

    /**
     * 是否能跳转产品详情
     */
    public static final String JumpDetail = "commonOrder/orderProdJump";

    public static final String ProdRECOMMEND = "common/queryBrandProdBrandName";
    public static final String SEARCHRESULT = "common/queryProductByName";
    public static final String RECOMMEND = "common/getRecommendProductName";
    public static final String REGISTER_AGREEMENT = "auth/getRegisterProtocol";
    public static final String SEND_AUTH_CODE = "auth/sendMsg";
    public static final String REGISTER = "auth/register";
    public static final String LOGIN = "auth/login";
    public static final String ACCOUNT_CENTER = "user/personCenter";
    public static final String LOG_OUT = "user/loginOut";
    public static final String CHANGE_LOGIN_PASSWORD = "auth/resetPwd";
    public static final String CHANGE_PAY_PASSWORD = "pay/updatePayPwd";

    /**
     *
     * 判断地址是否在配送范围内
     */

    public static final String Is_Send = "common/judgeAddressIsInSend";
    /**
     * 转盘数据
     */
    public static final String Turn_Table = "user/getRegisterPoolInfo";


    /**
     * 转盘数据
     */
    public static final String Is_Turn_Table = "user/getRegisterPoolState";

    /**
     *
     * 转盘点击领取
     */
    public static final String Turn_Table_Receive = "user/getRegisterPool";
    /**
     * 获取隐私政策
     */
    public static final String Privacy = "common/getPrivacyPolicyBtn";

    /**
     * 猜你喜欢
     */
    public static final String GUESSLIKE = "common/product/getGuessYourLikeProductList";

    /**
     * 子账户详情
     */
    public static final String GET_SUB_ACCOUNT = "user/appUserGetSubAccountDetail";
    /**
     * 子账户列表
     */
    public static final String GET_SUB_USER = "user/appGetUserSubAccountList";

    /**
     * 资质信息
     */
    public static final String IntellGency = "common/getCompanyQualification";

    public static final String SEARCHEQUIPMENT = " equipment/searchEquipment?";
    public static final String CHECKMESSAGE = "auth/checkRegisterCode";
    /**
     *用户注销
     */
    public static final String User_cancle = "user/cancelUser";
    /**
     * 首页基础信息
     */
    public static final String INDEXHOME = "common/basicHomeIndexInfo";

    /**
     * 必买清单
     */

    public static final String INDEXMUST = "common/product/getMustBuyProductList";

    /**
     * 首页新品，热销，降价，常用列表
     */
    public static final String NORMALLIST = "common/queryProductPageByType";

    /**
     * 首页banner
     */
    public static final String BANNER = "common/product/getTopBanner";
    /**
     * 首页其他信息
     */
    public static final String INDEXINFO = "common/getAreaHomeBasicInfo";
    /**
     * 首页其他信息
     */
    public static final String COUPONINFO = "common/getAreaHomeActiveInfo";

    /**
     * 首页优惠券弹窗列表
     */
    public static final String Coupon_List = "common/getUserPopups";


    public static final String HOMENEW = "common/prodHomeIndexInfo";
    public static final String GETPRODUCTLIST = "common/product/getProductList?";
    public static final String GETSELLPLACELISTBYIDANDDATE = "place/getSellPlaceListByIdAndDate?";
    public static final String SEARCHSCENICSPOT = "place/searchScenicSpot?";
    /**
     * 活动订阅取消
     */
    public static final String SPIKEACTIVEQUERY = "common/saveOrCancelActive";

    public static final String SECKILLISTQUERY = "auth/secKillMoreList?";
    /**
     * 团购tan
     */
    public static final String TEAMTab = "common/getActiveTab";
    /**
     * 团购列表
     */
    public static final String TEAMACTIVEQUERY = "common/getActiveInfoList";
    public static final String SPIKEACTIVEQUERYBYID = "auth/spikeActiveQueryById?";
    public static final String COMMENTORDERQUERY = "auth/commentOrderQuery?";
    public static final String SEARCHMASTERWORKDER = "master/searchMasterWorkder?";
    public static final String GETCOMMONPRODUCT = "product/getCommonProduct?";
    public static final String GETMASTERWORKERBYIDANDDATE = "master/getMasterWorkerByIdAndDate?";
    public static final String GETMASTERWORKERDETAIL = "master/getMasterWorkerDetailPicByMasterWorkerId?";
    public static final String GETCOMMENTLISTBYPAGE = "master/getCommentListByPage?";
    public static final String COLLET = "common/collect?";
    public static final String MARKET_GOODS_CLASSIFY = "common/product/getAllProduct";
    public static final String MARKET_GOODS = "common/product/getProductByClassifyId";
    public static final String DISABLE_SUB_USER = "user/disableSubUser";
    public static final String CHECK_COMMON_CODE = "auth/CommonCheckResetPwd";
    /**
     * 添加子账户
     */
    public static final String ADD_SUB_USER = "user/appUserAddSubAccount";
    /**
     * 编辑子账户 Edit_SUB_USER
     */
    public static final String Edit_SUB_USER = "user/appUserEditSubAccount";
    /**
     * 删除子账户
     */
    public static final String DELETE_SUB_USER = "user/appUserDelSubAccount";
    /**
     * 子账户消息列表
     */
    public static final String Sub_Account_list = "user/querySubMessagePage";

    /**
     * 设子账号消息 - 消息设为已读
     */

    public static final String Message_Read = "user/subMessageRead";

    /**
     * 获取未读消息数量
     */

    public static final String Message_Unread = "user/getUnReadMessageNum";


    /**
     * 子账户消息全部已读
     *
     */
    public static final String Sub_Account_Message = "user/allSubMessageRead";

    public static final String RECOVER_SUB_USER = "user/recoverSubUser";
    /**
     * 获取地址列表
     */
    public static final String GET_ADDRESS_LIST = "common/getHomeAddressList";
    /**
     * 选择省市区
     */
    public static final String GET_AREA_LIST = "common/area/getAllAreaList";
    /**
     * 选择可用省市区
     */
    public static final String GET_ENABLEAREA_LIST = "common/area/getEnableAreaList";
    public static final String GET_COLLECTION_LIST = "commonCollect/myCollectProd";
    public static final String GET_ORDER_LIST = "commonOrder/getCommonOrderListByPage";
    /**
     *子账户订单列表
     *
     */
    public static final String Sub_Account_List = "user/querySubAllOrderPage";

    public static final String FEEDBACK = "feedback/addFeedBack";
    public static final String EDIT_ADDRESS = "address/updateAddress";
    public static final String DELETE_ADDRESS = "address/deleteAddressById";
    /**
     * 设置默认地址
     */
    public static final String EDIT_DEFAULT_ADDRESS = "address/setDefaultAddressById";
    public static final String TEAMACTIVEQUERYBYID = "auth/teamActiveQueryById?";
    public static final String MESSAGE_CENTER = "notice/getNoticeInfoVOPage";
    public static final String MESSAGE_DETAIL = "notice/getNoticeInfoDetail";
    public static final String GETSCENICSPOTDETAILBYIDANDDATE = "place/getScenicSpotDetailByIdAndDate?";
    public static final String GETSELLPLACEDETAILBYSELLPLACEID = "place/getSellPlaceDetailBySellPlaceId?";
    public static final String GETSELLPLACEDETAILPICBYSELLPLACEID = "place/getSellPlaceDetailPicBySellPlaceId?";
    public static final String GETPLACECOMMENTLISTBYPAGE = "place/getCommentListByPage?";
    public static final String GETEQUIPMENTBYIDANDDATE = "equipment/getEquipmentByIdAndDate?";
    public static final String GETEQUIPMENTDETAILPICBYEQUIPMENTID = "equipment/getEquipmentDetailPicByEquipmentId?";
    public static final String GETEQUIPMENTCOMMENTLISTBYPAGE = "equipment/getCommentListByPage?";
    public static final String GETPRODUCTDETAIL = "common/querySpecProductDetail";
    public static final String COMMENTORDER = "commonOrder/commentOrder?";
    /**
     * 切换商品规格
     */
    public static final String EXCHANGEPRODUCT = "common/prodChangeSpec";
    /**
     * 商品详情用户评论
     */
    public static final String GETALLCOMMENTLISTBYPAGE = "common/getCommentListByPage?";
    public static final String HASCOLLECT = "commonCollect/hasCollect?";
    public static final String CLICKCOLLECTION = "commonCollect/collectOrCancel?";
    public static final String ADDCART = "cart/addCart?";
    public static final String BUYPRODUCT = "product/buyProduct?";

    public static final String GETORDERDETAIL = "commonOrder/getOrderDetail?";
    public static final String SECKILLORTEAMPRODUCT = "product/secKillOrTeamProduct?";
    public static final String MASTERWORKERRESERVE = "masterWorker/masterWorkerReserve?";
    public static final String SELLPLACERESERVE = " sellPlace/sellPlaceReserve?";
    public static final String EQUIPMENTRESERVE = "equipmentProduct/equipmentReserve?";
    /**
     * 去支付
     */
    public static final String ORDERPAY = "commonOrder/orderPay?";
    /**
     * 配送信息
     */
    public static final String DISTRIBUTE = "common/getAreaHomeOrderSendInfo";
    public static final String CHECKPAYPWD = "pay/checkPayPwd?";
    public static final String GETPAYRESULT = "commonOrder/getPaymentResult";
    /**
     * 取消订单
     */
    public static final String CANCELORDER = "commonOrder/cancelOrder?";
    /**
     * 删除订单
     */
    public static final String DELETEORDER = "commonOrder/deleteOrder";

    public static final String APPLAYRETURNGOODS = "commonOrder/applayReturnGoods?";
    public static final String RECHARGE = "pay/recharge?";
    public static final String APPLYWITHDRAW = "pay/applyWithdraw?";
    public static final String GETSUBUSERLIST = "user/getSubUserList?";
    public static final String GETWALLETAMOUNT = "wallet/getWalletAmount?";
    /**
     * 我的账单列表
     */
    public static final String GETWALLERTRECORDBYPAGE = "wallet/queryWalletRecordMonth";


    public static final String GET_CART_LIST = "cart/getMyCartList?";
    public static final String DELETE_CART = "cart/deleteByCartIds";
    public static final String CART_BALANCE = "cart/cartToBalance";
    public static final String GET_ORDER_STATUS_NUM = "notice/getOrderStatusNum";

    /**
     * 我的
     */
    public static final String USER_MY_COUNTER = "user/myCenter";


    public static final String RETURNEQUIPMENTORDERLISTBYID = "equipmentProduct/returnEquipmentOrderListById";
    /**
     * 商品评价
     */
    public static final String COMMENT_ORDER = "commonOrder/commentOrder";
    /**
     * 创蓝一键注册
     */
    public static final String CHAUNGLAN = "auth/getMobileCl";

    /**
     * 授权码
     */
    public static final String CHECK_CODE = "auth/validateUserInvitation";
    public static final String CONFIRM_GET_GOODS = "commonOrder/confirmGetGoods";
    public static final String APPLAY_RETURN_GOODS = "commonOrder/applayReturnGoods";
    public static final String CHANGE_ANOTHER_ADDRESS = "commonOrder/changeAnotherAddress";
    public static final String COPY_TO_CART = "cart/copyToCart";
    public static final String UPDATE_BIND_PHONE = "user/updateBindPhone";
    public static final String UPDATE_MESSAGE_STATE = "notice/updateNoticeLookStatus";
    //版本升级
    public static final String AND_VERSION = "auth/andVersion";
    public static final String GET_INDEX_NOTICE_PAGE = "auth/getIndexNoticePage";
    public static final String GETCARTNUM = "cart/getCartNum";
    public static final String GETCUSTOMERPHONE = "auth/getCustomerPhone";
    public static final String DELETE_COMMON_COLLECT = "commonCollect/deleteCommonCollect";
    public static final String UPDATEUSERINVITATION = "auth/updateUserInvitation";
    public static final String QUERY_USER_DEDUCTBYSTATE = "gift/queryUserDeductByState";
    public static final String GET_WALLET_INFO = "wallet/getWalletInfo";
    public static final String USER_CHOOSE_DEDUCT = "gift/userChooseDeduct";//gift/userChooseDeduct
    /**
     * 生成订单
     */
    public static final String CART_GENERATEORDER = "cart/generateOrder";
    public static final String POINT_MYPOINT = "point/myPoint";
    public static final String WALLETGETMYBALANCEINFO = "wallet/getMyBalanceInfo";
    public static final String COMMONSHARGETSHAREINFO = "common/share/getShareInfo";
    public static final String AUTHQUERYHOMEPROPUP = "auth/queryHomePropup";
    public static final String AUTHPOPVIEW = "auth/popupView";
    public static final String VIPPAY = "vip/pay";
    public static final String VIPPAYRESULT = "vip/payResult";
    public static final String CARTGETREDUCTDESC = "cart/getReductDesc";
    public static final String CARTPOSTCHANGEORDERDETAIL = "cart/cartNumChange";
    /**
     * 特惠专区更多
     */
    public static final String SPECIALGOOD = "auth/specialOfferMore";
    /**
     * 公用详情
     */
    public static final String SPECIALOFFERDETAIL = "common/getActiveInfoDetail";
    /**
     * 秒杀-更多-顶部
     */
    public static final String SECKILLMORE = "auth/secKillMore";
    /**
     * 结算温馨提示
     */
    public static final String POINT = "cart/checkIsToRecharge";

    /**
     * 获取热门店铺类型
     */
    public static final String REGISTERSHOPTYPE = "auth/getHotShopType";

    /**
     *  店铺类型列表数据
     */
    public static final String SHOP_TYPE_LIST = "auth/getShopTypeList";

    /**
     * 商品详情添加数量判断
     */
    public static final String JUDGEPRODUCTINVENT = "cart/judgeProductInvent";
    /**
     * 获取配送时间
     */
    public static final String GETDELIVERTIME = "cart/getDeliverTime";
    /**
     * 发送地理位置
     */
    public static final String GETUSERADDRESS = "auth/getUserAddress";

    public static final String GET_CART_LISTS = "cart/queryUserCartList";
    /**
     * 获取退货订单信息
     */
    public static final String RETURNORDERTYPE = "order/queryOrderReturnProduct";
    /**
     * 退货理由
     */
    public static final String QUERYORDERRETURNTYPE = "order/queryOrderReturnType";
    /**
     * 商品分类banner
     */
    public static final String CLASSIFYBANNER = "common/product/queryClassifyBanner";
    /**
     * 获取分类筛选商品名字
     */
    public static final String GETPRODUCTBRAND = "common/product/getProductBrand";

    /**
     * 购买过的商品
     */
    public static final String GETALREADYPRODUCT = "common/product/getAlreadyBuyProduct";
    /**
     * 新分类筛选确定
     */
    public static final String QUERYCLASSIFYPRODUCT = "common/product/queryClassifyProductPage";
    /**
     * 单位改变
     */


    public static final String ONLINEPRODUCTAMOUTN = "order/sumOnlineProductAmount";

    /**
     * 分类左边数据
     */
    public static final String CLASSIFY = "common/queryProdNewClassify";

    /**
     * 分类右边
     */
    public static final String CLASSIFY_RIGHT = "common/queryAppProductByClassifyId";

    /**
     * 上传图片凭证
     */
    public static final String UPLOADMESSAGEIMG = "common/uploadMessageImg";
    /**
     * 生成退货单
     */
    public static final String RETURNORDER = "order/onlineUserReturnOrder";

    /**
     * 获取物流信息
     */

    public static final String GETORDERMAP = "commonOrder/getLogisticsDetail";
    /**
     * 我的界面确认优惠
     */
    public static final String CLOSEINFO = "user/closeExpiredInfo";
    /**
     * 上传极光推送id
     */
    public static final String SENDJGUESRID = "common/returnJiGuangUserId";
    /**
     * 下载量
     */
    public static final String LOADAMOUNT = "common/appDownLoadLogAdd";
    /**
     * 退出司机登录
     */
    public static final String DRIVERLOGOUT = "common/driverLoginOut";
    /**
     * 上传司机位置
     */
    public static final String SENDLOCAITON = "common/saveLocation";

    /**
     * 扫描先校验是否属于该司机
     */
    public static final String DRIVERCHECK = "common/checkOrderDriver";
    /**
     * 校验完成后装车
     */

    public static final String DRIVERSCANORDER = "common/scanOrderToCar";

    /**
     * 分类购物车加减
     */
    public static final String CARTADDPRODUCT = "cart/addClassifyProdToCart";
    /**
     * 获取分类新接口
     */
    public static final String PRODUCTCLASSIFY = "common/product/queryAllClassify";
    /**
     *判断库存
     */
    public static final String ADDCARTJUDGE = "cart/judgeCartProductInvent";

    /**
     *购物车列表加减接口
     */
    public static final String ADDCARTJUDGES = "cart/userChangeCartGoodsNum";

    /**
     * 本机认证
     */
    public static final String AUTHMOBILE = "auth/verifyMobileCl";
    /**
     * 获取自提时间
     */
    public static final String QUERYSELFORDERTIME = "common/querySelfOrderDeliverTime";
    /**
     * 修改自提信息
     */
    public static final String MODIFYORDERINFO = "commonOrder/modifyOrderPickInfo";

    /**
     * 自提订单确认提货
     */
    public static final String SELFORDERRECEIVE = "commonOrder/selfOrderReceive";

    /**
     * 获取配送时间列表接口
     */
    public static final String GETDELIVERORDERTIME = "commonOrder/getOrderDeliverTime";
    /**
     * 修改配送时间接口
     */
    public static final String UPDATEDELIVERTIME = "commonOrder/updateDeliverTime";

    /**
     * 获取开放城市列表
     */
    public static final String MODIFYCITY = "common/getAreaOpenList";


    /**
     * 账单明细搜索
     */
    public static final String WALLETSEARCH = "wallet/getSearchList";
    /**
     * 我的账单列表总计
     */
    public static final String WALLETSUMPRICE = "wallet/getSumPrice";
    /**
     * 账单流水详情
     */
    public static final String WALLETDETAIL = "wallet/getWalletDetail";

    /**
     * 评价详情
     */
    public static final String EVALUATEDETAIL = "commonOrder/getCommentDetail";

    /**
     * 退货优惠均价不同单位
     */
    public static final String ORDERSUMDEDUCTAMOUNT = "order/sumProdDeductAmount";


    /**
     * 新退货订单详情
     */
    public static final String NEWRETURNDETAIL = "order/queryReturnOrderDetail";
    /**
     * 取消退货申请
     */
    public static final String USERCANCELORDER = "commonOrder/userCancelReturnOrder";

    /**
     * 首页关闭优惠券列表弹窗
     */
    public static final String Coupon_Close_List = "common/viewedUserPopups";
    /**
     * 隐私政策已读
     */
    public static final String Read_Privacy = "common/readPrivacyPolicy";

}
