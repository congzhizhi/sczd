package com.casc.sczd.bean;


import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 权限表
 *
 * @author congzhizhi
 * @email congzhizhi@163.com
 * @date 2020-02-20 16:30:49
 */
@Data
public class SysPrivilege implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 权限ID
	 */
	private Long id;
	/**
	 * 权限名称
	 */
	private String privilegeName;
	/**
	 * 权限URL
	 */
	private String privilegeUrl;
	/**
	 * 父级菜单,一级菜单为0
	 */
	private Long pid;
	/**
	 * 界面显示的顺序
	 */
	private Integer order;
	/**
	 * 授权(多个用逗号分隔，如：user:list,user:create)
	 */
	private String perm;
	/**
	 * 类型   0：目录   1：菜单   2：按钮
	 */
	private Integer type;

}
