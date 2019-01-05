package com.atguigu.springboot.enums;

import com.atguigu.springboot.exception.SellException;
import lombok.Getter;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/23 17:05
 */
@Getter
public enum ResultEnum {

	SUCCESS(0,"成功"),

	PARAM_ERROR(1,"参数不正确"),

	PRODUCT_NOT_EXIST(10, "改商品不存在"),

	PRODUCT_STOCK_ERROR(11, "商品库存不正确"),

	ORDER_NOT_EXIST(12, "订单不存在"),

	ORDERDETAIL_NOT_EXIST(13, "订单详情不存在"),

	ORDER_STATUS_ERROR(14, "订单状态不正常"),

	ORDER_UPDATE_FAIL(15, "订单更新失败"),

	ORDER_DETAIL_EMPTY(16, "订单详情为空"),

	ORDER_PAY_STATUS_ERROR(17, "订单支付状态不正确"),

	CART_EMPTY(18,"购物车不能为空"),

	OPENID_OWNER_ERROR(19,"订单用户不一致"),

	ORDER_CANCEL_SUCCESS(22,"订单取消成功"),

	ORDER_FINISH_SUCCESS(23,"订单完结成功"),

	LOGIN_FAIL(25,"登录失败，登录信息不正确"),

	;
	private Integer code;
	private String msg;

	ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}


}
