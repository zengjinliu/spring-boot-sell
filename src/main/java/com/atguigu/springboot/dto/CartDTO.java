package com.atguigu.springboot.dto;

import lombok.Data;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/23 18:20
 */
@Data
public class CartDTO {
	private String productId;
	private Integer productQuantity;

	public CartDTO(String productId, Integer productQuantity) {
		this.productId = productId;
		this.productQuantity = productQuantity;
	}
}
