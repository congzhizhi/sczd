package com.casc.sczd.bean;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 用户管理
 * 
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-19 10:04:16
 */
@Data
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
		private Integer id;
	/**
	 * 
	 */
	private String phone;

}
