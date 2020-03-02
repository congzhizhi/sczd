package com.casc.sczd.bean;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-27 09:43:45
 */
@Data
public class Errorlog implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
		private Long id;
	/**
	 * 日志详细信息
	 */
	private String desp;
	/**
	 * 1.系统错误 2.应用错误
	 */
	private Integer type;
	/**
	 * 日志生成时间
	 */
	private Date time;

}
