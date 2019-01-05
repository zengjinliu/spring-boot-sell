package com.atguigu.springboot.controller;

import com.atguigu.springboot.VO.ProductInfoVO;
import com.atguigu.springboot.VO.ProductVO;
import com.atguigu.springboot.VO.ResultVO;
import com.atguigu.springboot.bean.ProductCategory;
import com.atguigu.springboot.bean.ProductInfo;
import com.atguigu.springboot.service.serviceInterface.CategoryService;
import com.atguigu.springboot.service.serviceInterface.ProductService;
import com.atguigu.springboot.util.ResultVoUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/23 9:33
 */
@RestController
@RequestMapping(value = "/buyer/product")
public class BuyerProductController {

	@Autowired
	ProductService productService;
	@Autowired
	CategoryService categoryService;

	@GetMapping(value = "/list")
	public ResultVO list(){
		//1.先查询所有在架的商品
		List<ProductInfo> productInfoList = productService.findUpAll();

		//2.查询所有的类目(一次性查询)
		List<Integer> categotyTypeList = new ArrayList<>();
		/**
		 * 传统写法
		 */
		for (ProductInfo productInfo:productInfoList){
			categotyTypeList.add(productInfo.getCategoryType());
		}

		List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categotyTypeList);

		//3.数据的拼接

		List<ProductVO> productVOList = new ArrayList<>();
		for (ProductCategory productCategory:productCategoryList){
			ProductVO productVO = new ProductVO();
			productVO.setCategoryType(productCategory.getCategoryType());
			productVO.setProductName(productCategory.getCategoryName());

			List<ProductInfoVO> productInfoVOlist = new ArrayList<>();
			for (ProductInfo productInfo : productInfoList){
				if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
					ProductInfoVO productInfoVO = new ProductInfoVO();
					//数据的靠背
					BeanUtils.copyProperties(productInfo,productInfoVO);
					productInfoVOlist.add(productInfoVO);
				}
			}
			productVO.setProductInfoVOList(productInfoVOlist);
			productVOList.add(productVO);
		}
		return ResultVoUtil.success(productVOList);
	}
}
