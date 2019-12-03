var urls='http://120.55.55.99:8082/qiaoge/';	//测试

//var urls='https://shaokao.qoger.com/qiaoge/'; //正式

var router_url='http://120.55.55.99:8082/apph5/BusinessApp/page/';
var token= '727aa6cea2bd4564afe072c31b6b3640';
var service_url={
	'querySaleIndexUserBaseInfo_url':'user/querySaleIndexUserBaseInfo',   // 首页用户基础信息
	'querySaleIndexUserRankList_url' : 'user/querySaleIndexUserRankList',  //排行榜
	'querySaleIndexUserDataInfo_url' : 'user/querySaleIndexUserDataInfo',  //我的数据
	'querySaleIndexCard_url' : 'user/querySaleIndexCard',  //我的名片
	'querySaleUserPageByInfo_url' : 'user/querySaleUserPageByInfo',  //客户列表
	'querySaleUserDetail_url' : 'user/querySaleUserDetail',  //客户详情
	'querySaleShopTypeList_url' : 'user/querySaleShopTypeList',  //获取店铺类型
	'querySaleUserOrderPage_url' : 'user/querySaleUserOrderPage',  //订单列表
	'querySaleUserOrderDetail_url' : 'user/querySaleUserOrderDetail',  //客户详情
	'querySaleSelfGiftList_url' : 'user/querySaleSelfGiftList',  //业务员优惠券列表
	'querySaleUserGiftDetailInfo_url' : 'user/querySaleUserGiftDetailInfo',  //业务员优惠券列表详情
	'queryGiftBeSendUser_url' : 'user/queryGiftBeSendUser',    //优惠券发放搜索用户
	'saleSendGiftToUser_url' : 'user/saleSendGiftToUser',  //发放优惠卷
	'queryGiftSendUser_url' : 'user/queryGiftSendUser',  //优惠券详情-客户使用情况
	'querySaleUserGiftOrder_url' : 'user/querySaleUserGiftOrder',  //查看优惠券使用订单
	'querySaleUserSubList_url' : 'user/querySaleUserSubList',  //查看子账号列表
	'querySaleUserGiftInfo_url' : 'user/querySaleUserGiftInfo',  //优惠卷使用明细
	'saleSetUserName_url' : 'user/saleSetUserName',  //优惠卷使用明细
	'saleUserLogin_url' : 'auth/saleUserLogin',  //登陆
	'querySaleUserMemoLog_url':'user/querySaleUserMemoLog',//备注列表
	'addSaleUserMemoLog_url':'user/addSaleUserMemoLog',//添加备注
	'querySaleUserMemoLogDetail_url':'user/querySaleUserMemoLogDetail',//查看备注
	'querySaleIndexCard_url':'user/querySaleIndexCard',//我的名片
};
/*无记录*/
var record='<div class="norecord"><div class="norecordbg"></div><div class="norecordtext">暂无搜索记录</div></div>';

		    		
		    		
		    	
var ajaxmethod=function(url,type,params,callback){
	//alert('params'+JSON.stringify(params));
	mui.ajax(url,{
		data:params,
		dataType:'json',//服务器返回json格式数据
		type:'post',//HTTP请求类型
		timeout:10000,//超时时间设置为10秒；
		headers:{'Content-Type':'application/x-www-form-urlencoded'},	              
		success:function(data){
			//服务器返回响应，根据响应结果，分析是否登录成功；
			callback(data);
		},
		error:function(xhr,type,errorThrown){
			//异常处理；
			console.log(type);
		}
	})	
};



//公共函数
(function($, owner) {
	/**
	 * 用户登录
	 **/
	owner.login = function(loginInfo, callback) {
		callback = callback || $.noop;
		loginInfo = loginInfo || {};
		loginInfo.loginName = loginInfo.loginName || '';
		loginInfo.loginPwd = loginInfo.loginPwd || '';
		//请求
		var url=urls+service_url.saleUserLogin_url;
		ajaxmethod(url,'POST',loginInfo,function(data){
			if(data.code==1){
				return owner.createState(loginInfo.loginName,data.data, callback);     //登陆成功
			}else{
				return callback('用户名或密码错误');
			}
		})
		/*var users = JSON.parse(localStorage.getItem('$users') || '[]');   //获取值
		var authed = users.some(function(user) {    //检测数组中的元素是否满足指定条件
			return loginInfo.account == user.account && loginInfo.password == user.password;
		});
		if (authed) {
			return owner.createState(loginInfo.account, callback);     //登陆成功
		} else {
			return callback('用户名或密码错误');
		}*/
	};
	
	owner.createState = function(name, token,callback) {
		var state = owner.getState();
		state.loginName = name;
		state.token = token;
		owner.setState(state);
		return callback();
	};
	/**
	 * 设置应用本地配置
	 **/
	owner.getSettings = function() {
			var settingsText = localStorage.getItem('$settings') || "{}";
			return JSON.parse(settingsText);
		}
	/**
	 * 获取当前状态
	 **/
	owner.getState = function() {
		var stateText = localStorage.getItem('$state') || "{}";
		return JSON.parse(stateText);
	};
	/**
	 * 设置当前状态
	 **/
	owner.setState = function(state) {
		state = state || {};
		//token=state.token;
		localStorage.setItem('$state', JSON.stringify(state));
		//var settings = owner.getSettings();
		//settings.gestures = '';
		//owner.setSettings(settings);
	};
	
	/**
	 * 获取应用本地配置
	 **/
	owner.setSettings = function(settings) {
		settings = settings || {};
		localStorage.setItem('$settings', JSON.stringify(settings));
	}
	
	
}(mui, window.app = {}));