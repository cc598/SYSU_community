package cn.sysu.comm.dao;

import cn.sysu.comm.entity.User;

/**
 * 
 * @Description:
 *  User类数据层接口
 * @CreateTime: 2018-4-24 下午8:07:51 
 * @author: bee
 * @version V1.0
 */

public interface UserMapper {
	/*
	 * 通过UserId查询User
	 * 用于登录
	 */
	public User findUserById(String id);
	/*
	 * 通过用户名查找用户
	 */
	public User findUserByName(String username);
	/*
	 * 编辑用户，更改用户信息
	 */
	public void editUser(User user);
	
}
