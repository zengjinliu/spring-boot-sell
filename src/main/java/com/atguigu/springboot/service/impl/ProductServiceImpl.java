package com.atguigu.springboot.service.impl;

import com.atguigu.springboot.bean.ProductInfo;
import com.atguigu.springboot.dao.ProductInfoRepository;
import com.atguigu.springboot.dto.CartDTO;
import com.atguigu.springboot.enums.ProductStatusEnum;
import com.atguigu.springboot.enums.ResultEnum;
import com.atguigu.springboot.exception.SellException;
import com.atguigu.springboot.service.serviceInterface.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/22 17:58
 */
@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductInfoRepository repository;

	@Override
	public ProductInfo findOne(String productId) {
		return repository.findById(productId).get();
	}

	@Override
	public List<ProductInfo> findUpAll() {
		return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
	}

	@Override
	public Page<ProductInfo> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public ProductInfo save(ProductInfo productInfo) {
		return repository.save(productInfo);
	}

	//增加库存
	@Override
	@Transactional
	public void increaseStock(List<CartDTO> cartDTOList) {
		for (CartDTO cartDTO : cartDTOList) {
			ProductInfo productInfo = repository.getOne(cartDTO.getProductId());
			if (productInfo==null){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
			productInfo.setProductStock(result);
			repository.save(productInfo);
		}

	}

	//减少库存
	//TODO,项目优化
	@Override
	@Transactional
	public void decreaseStock(List<CartDTO> cartDTOList) {
		for (CartDTO cartDTO : cartDTOList) {
			ProductInfo productInfo  = repository.getOne(cartDTO.getProductId());
			if (productInfo.getProductStock()<cartDTO.getProductQuantity()){
				throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
			}
			//减少库存的操作
			Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
			if (result<0){
				throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
			}
			productInfo.setProductStock(result);
			repository.save(productInfo);
		}
	}

}
