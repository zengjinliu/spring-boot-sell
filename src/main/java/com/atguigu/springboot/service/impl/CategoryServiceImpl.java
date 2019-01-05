package com.atguigu.springboot.service.impl;

import com.atguigu.springboot.bean.ProductCategory;
import com.atguigu.springboot.dao.ProductRepository;
import com.atguigu.springboot.service.serviceInterface.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/22 14:08
 */
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	ProductRepository repository;

	@Override
	public List<ProductCategory> findAll() {
		return repository.findAll();
	}

	@Override
	public ProductCategory findOne(Integer categoryId) {
		return repository.findById(categoryId).get();
	}

	@Override
	public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
		return repository.findByCategoryTypeIn(categoryTypeList);
	}

	@Override
	public ProductCategory save(ProductCategory productCategory) {
		return repository.save(productCategory);
	}
}
