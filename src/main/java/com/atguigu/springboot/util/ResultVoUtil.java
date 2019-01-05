package com.atguigu.springboot.util;

import com.atguigu.springboot.VO.ResultVO;

import java.util.List;

/**
 * @author Jason
 * @Description:
 * @date 2018/11/23 12:16
 */
public class ResultVoUtil {
	//数据结果成功
	public static ResultVO success(Object object){
		ResultVO resultVO= new ResultVO();
		resultVO.setCode(0);
		resultVO.setMsg("成功");
		resultVO.setData(object);
		return resultVO;
	}
	//数据结果失败
	public static ResultVO success(){
		return success(null);
	}

	//error
	public static ResultVO error(Integer code,String msg){
		ResultVO resultVO = new ResultVO();
		resultVO.setCode(code);
		resultVO.setMsg(msg);
		return resultVO;
	}
}
