package com.atguigu.springboot.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

/**
 * @author Jason
 * @Description: 商品包含类目
 * @date 2018/11/23 10:15
 */
@Data
@DynamicUpdate
public class ProductVO {
	//类目名
	@JsonProperty("name")
	private String productName;

	@JsonProperty("type")
	private Integer categoryType;

	@JsonProperty("foods")
	private List<ProductInfoVO> productInfoVOList;
}
