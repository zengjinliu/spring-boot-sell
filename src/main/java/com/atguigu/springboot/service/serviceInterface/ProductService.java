package com.atguigu.springboot.service.serviceInterface;

import com.atguigu.springboot.bean.ProductInfo;
import com.atguigu.springboot.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/22 17:54
 */
public interface ProductService {
	ProductInfo findOne(String productId);

	List<ProductInfo> findUpAll();

	Page<ProductInfo> findAll(Pageable pageable);

	ProductInfo save(ProductInfo productInfo);

	//库存增加
	void increaseStock(List<CartDTO> cartDTOList);

	//库存减少
	void decreaseStock(List<CartDTO> cartDTOList);




}
