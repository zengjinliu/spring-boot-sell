package com.atguigu.springboot.dao;

import com.atguigu.springboot.bean.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/22 8:24
 */
public interface ProductRepository extends JpaRepository<ProductCategory,Integer> {
	//通过类目类型查出数据
	List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
