package com.atguigu.springboot.service.serviceInterface;

import com.atguigu.springboot.bean.ProductCategory;

import java.util.List;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/22 14:01
 */
public interface CategoryService {
	//查询所有的类目
	List<ProductCategory> findAll();
	//根据类目id查找
	ProductCategory findOne(Integer categoryId);
	//根据输入的类目id查询多个
	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
	//
	ProductCategory save(ProductCategory productCategory);

}
